package cl.citiaps.twitter.streaming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import MongoDB.MongoConnection;
import Utilities.ArtistFinder;
import Utilities.CountryLocator;
import Utilities.SentimentSpanish;
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

	private CountryLocator countryLocator;

	private ArtistFinder artistFinder;

	private TwitterStreaming() 
	{
		this.twitterStream = new TwitterStreamFactory().getInstance();
		this.keywords = new HashSet<>();
		loadKeywords();
		
		this.mongoConnection = new MongoConnection("tweets", "tweetsPrueba");
		mongoConnection.connect();
		
		this.sentimentSpanish = SentimentSpanish.getInstance();
		this.sentimentSpanish.loadWords();

        this.countryLocator = CountryLocator.getInstance();
        this.countryLocator.loadCountries();
        this.countryLocator.loadCitiesByCountry();

        this.artistFinder = ArtistFinder.getInstance();
        this.artistFinder.loadArtists();
	}
	private void printKeyWords(){
		System.out.println(this.keywords);
	}
	private boolean verifyKeys(){
		try{
			FileReader lector = new FileReader("../Backend/verificador.txt");
			BufferedReader br = new BufferedReader(lector);
			String buffer = br.readLine();
			//System.out.println(br.readLine());
			if(buffer.equals("true")){
				return true;
			}
			else if(buffer.equals("false")){
				return false;
			}
		}catch(Exception err){
			System.out.println(err.getMessage());
			return false;
		}
		return false;
		
		
	}

	private boolean resetVerify(){
		try{
			FileWriter fichero = new FileWriter("../Backend/verificador.txt");
			fichero.write("false\n");

			fichero.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return true;
	}
	private void loadKeywords() 
	{
		try 
		{
			/*ClassLoader classLoader = getClass().getClassLoader();
			keywords.addAll(IOUtils.readLines(classLoader.getResourceAsStream("../Backend/words.dat"), "UTF-8"));*/
			FileReader lector = new FileReader("../Backend/words.dat");
			BufferedReader br = new BufferedReader(lector);
			String buffer = br.readLine();
			this.keywords = new HashSet<>();
			while(buffer != null){
				this.keywords.add(buffer);
				buffer = br.readLine();

			}
			lector.close();
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
				
				//printKeyWords();
				//Almacena solo tweets en español
				if(status.getLang().equalsIgnoreCase("es"))
				{
					System.out.println(">Tweet recibido");
					//Realiza análisis de sentimientos
					sentimentSpanish.analyze(status.getText());
					countryLocator.locateCountry(status.getUser().getLocation());
					artistFinder.findArtist(status.getText());
					//System.out.println("Likes:" + status.getUser().getFavouritesCount());
					//System.out.println("Retweets:" + status.getRetweetCount());
					//System.out.println("Seguidores: " + status.getUser().getFollowersCount());
					//System.out.println("Seguidos: " + status.getUser().getFriendsCount());
					//System.out.println("id: " + status.getId());


					
					//Guarda en MongoDB
					mongoConnection.saveTweet(
							status.getUser().getScreenName(),
							status.getUser().getLocation(),
							status.getGeoLocation(),
							status.getText(),
							status.getCreatedAt(),
							sentimentSpanish.getPositiveScore(),
							sentimentSpanish.getNegativeScore(),
							sentimentSpanish.getAnalysis(),
							sentimentSpanish.getPositivePercent(),
							sentimentSpanish.getNegativePercent(),
                            countryLocator.getFinalCountry(),
							status.getUser().getId(),
							artistFinder.getFinalArtist(),
							status.getUser().getFollowersCount(),
							status.getUser().getFriendsCount()
					);
				}
			}
		};

		FilterQuery fq = new FilterQuery();
		fq.track(keywords.toArray(new String[0]));
		this.twitterStream.addListener(listener);
		this.twitterStream.filter(fq);
		//this.twitterStream.shutdown();
	}
	
	public static void main(String[] args) throws UnknownHostException, InterruptedException 
	{
		TwitterStreaming ts = new TwitterStreaming();
		ts.init();
		//System.out.println("Empezando!");
		while(true){
			Thread.sleep (10000); 
			if(ts.verifyKeys()){
				//System.out.println("Entre al if");
				ts.loadKeywords();
				ts.resetVerify();
				ts.twitterStream.shutdown();
				ts.init();
			}
			
		}
		

		/*
		//Neo4j
		Neo neo = new Neo();

		neo.connect("bolt://localhost", "neo4j", "root");
		neo.deleteAll();
		neo.neo();
		neo.disconnect();
		*/

	}

}
