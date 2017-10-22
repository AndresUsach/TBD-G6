package cl.citiaps.twitter.streaming;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import twitter4j.*;

//http://twitter4j.org/javadoc/twitter4j/Status.html
//https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i

//Top 10 de Chile:
//https://spotifycharts.com/regional/cl/daily/latest

public class TwitterStreaming 
{
	private MongoConnection mongoConnection;
	private final TwitterStream twitterStream;
	private Set<String> keywords;
	
	private SentimentSpanish sentimentSpanish;

	private TwitterStreaming() 
	{
		this.twitterStream = new TwitterStreamFactory().getInstance();
		this.keywords = new HashSet<>();
		loadKeywords();
		
		this.mongoConnection = new MongoConnection("tweets", "tweetsPrueba");
		mongoConnection.connect();
		
		this.sentimentSpanish = SentimentSpanish.getInstance();
		this.sentimentSpanish.loadWords();
	}

	private void loadKeywords() 
	{
		try 
		{
			ClassLoader classLoader = getClass().getClassLoader();
			keywords.addAll(IOUtils.readLines(classLoader.getResourceAsStream("words.dat"), "UTF-8"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void init() throws UnknownHostException 
	{
	
		StatusListener listener = new StatusListener() 
		{

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) 
			{
				System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) 
			{
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

			public void onScrubGeo(long userId, long upToStatusId) 
			{
				System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
			}

			public void onException(Exception ex) 
			{
				ex.printStackTrace();
			}

			@Override
			public void onStallWarning(StallWarning arg0) 
			{

			}

			@Override
			public void onStatus(Status status) 
			{

				//Almacena solo tweets en español
				if(status.getLang().equalsIgnoreCase("es"))
				{
					System.out.println(">Tweet recibido");
					//Realiza análisis de sentimientos
					sentimentSpanish.analyze(status.getText());

					//Guarda en MongoDB
					mongoConnection.saveTweet(
							status.getUser().getName(),
							status.getUser().getLocation(),
							status.getGeoLocation(),
							status.getText(),
							status.getCreatedAt(),
							sentimentSpanish.getPositiveScore(),
							sentimentSpanish.getNegativeScore(),
							sentimentSpanish.getAnalysis(),
							sentimentSpanish.getPositivePercent(),
							sentimentSpanish.getNegativePercent()
					);
				}
			}
		};

		FilterQuery fq = new FilterQuery();

		fq.track(keywords.toArray(new String[0]));

		this.twitterStream.addListener(listener);
		this.twitterStream.filter(fq);
	}
	
	public static void main(String[] args) throws UnknownHostException 
	{
		new TwitterStreaming().init();
	}

}
