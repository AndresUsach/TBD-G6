package MongoDB;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import twitter4j.GeoLocation;

public class MongoConnection {
	
	private static String uriConnection = "mongodb://localhost:27017";
	private String databaseName;
	private String collectionName;
	
	private MongoClient mongoClient;
	private DB database;
	private DBCollection collection;
	
	public MongoConnection(String db, String collection)
	{
		this.databaseName = db;
		this.collectionName = collection;
	}
	
	public void connect()
	{
		try 
		{
			this.mongoClient = new MongoClient(new MongoClientURI(uriConnection));
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.database = mongoClient.getDB(this.databaseName);
		this.collection = database.getCollection(this.collectionName);
	}
	
	public void saveTweet(String userName, String userLocation, GeoLocation geoLocation, String text, Date date,
                   int positiveScore, int negativeScore, String result, double positivePercent, double negativePercent,
						  String finalCountry, long userId, String finalArtist)
	{
		DBObject tweet = new BasicDBObject("userName", userName)
				.append("userLocation", userLocation)
				.append("location", geoLocation)
				.append("text", text)
				.append("createdAt", date)
                .append("positiveScore", positiveScore)
                .append("negativeScore", negativeScore)
                .append("analysis", result)
                .append("positivePercent", positivePercent)
                .append("negativePercent", negativePercent)
				.append("finalCountry", finalCountry)
				.append("userId", userId)
				.append("finalArtist", finalArtist);
		
		this.collection.insert(tweet);
	}
	
}
