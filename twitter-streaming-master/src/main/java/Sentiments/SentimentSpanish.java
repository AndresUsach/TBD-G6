package Sentiments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//https://gist.github.com/leomelzer/3075236

//Singleton
public class SentimentSpanish 
{
	 private static SentimentSpanish instance = null;
	 
	 protected SentimentSpanish() 
	 {
	 }
	 
	 public static SentimentSpanish getInstance() 
	 {
		 if(instance == null) 
		 {
			 instance = new SentimentSpanish();
		 }
		 return instance;
	 }
	
	private List<String> posWords = new ArrayList<String>();
	private List<String> negWords = new ArrayList<String>();
	
	//Estadísticas
	private int positiveScore;
	private int negativeScore;

	private int totalWords;
	private double positivePercent;
	private double negativePercent;
	
	private String analysis;
	
	public void loadWords()
	{
		//System.out.println("\nReading negative and positive words...");
		BufferedReader negReader = null;
		BufferedReader posReader = null;
		try 
		{
			negReader = new BufferedReader(new FileReader(new File(
					"./src/negative-words.txt")));
			posReader = new BufferedReader(new FileReader(new File(
					"./src/positive-words.txt")));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		String word;

		try {
			while ((word = negReader.readLine()) != null) 
			{
				negWords.add(word);
			}
			while ((word = posReader.readLine()) != null) 
			{
				posWords.add(word);
			}

			this.totalWords = negWords.size() + posWords.size();
			//System.out.println("\nTotal words: " + this.totalWords);

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			negReader.close();
			posReader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void analyze(String input) 
	{
		this.positiveScore = 0;
		this.negativeScore = 0;
		input = input.toLowerCase();
		input = input.trim();

		String[] words = input.split(" ");

		for (int i = 0; i < words.length; i++) {
			if (posWords.contains(words[i])) {
				//System.out.println("Positva: " + words[i]);
				this.positiveScore++;
			}
			if (negWords.contains(words[i])) {
				//System.out.println("Negativa: " + words[i]);
				this.negativeScore++;
			}
		}

		if(this.positiveScore + this.negativeScore > 0)
		{
			this.positivePercent = ((double) this.positiveScore/((double) this.positiveScore + (double) this.negativeScore));
			this.negativePercent = ((double) this.negativeScore/((double) this.positiveScore + (double) this.negativeScore));
		}

		int result = (this.positiveScore - this.negativeScore);

		if (result < 0)
		{
			this.analysis = "Negative";
		}
		else if (result > 0) 
		{
			this.analysis = "Positive";
		}
		else
		{
			this.analysis = "Neutral";
		}
	}
	
	public int getPositiveScore()
	{
		return this.positiveScore;
	}
	
	public int getNegativeScore()
	{
		return this.negativeScore;
	}
	
	public String getAnalysis()
	{
		return this.analysis;
	}

	public double getPositivePercent()
	{
		return this.positivePercent;
	}

	public double getNegativePercent()
	{
		return this.negativePercent;
	}
	
	public void resetScores()
	{
		this.negativeScore = 0;
		this.positiveScore = 0;
	}

}
