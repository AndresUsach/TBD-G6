package edu.usach.tbdgrupo5;

public class Tweet {
	private String userName;
	private String text;
	private int followers;
	private int followees;
	private int favourites;
	private int retweets;
	
	public Tweet(){
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	public int getFavourites() {
		return favourites;
	}
	public void setFavourites(int favourites) {
		this.favourites = favourites;
	}
	public int getRetweets() {
		return retweets;
	}
	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	public int getFollowees() {
		return followees;
	}

	public void setFollowees(int followees) {
		this.followees = followees;
	}

	public double getPopularity()
	{
		double potencia = Math.pow(Math.E, -1.0 * (double)this.getFollowers());
		System.out.println("> Potencia: " + potencia);
		double resultado = 1.0 - potencia ;
		System.out.println("> Resultado: " + resultado);
		return resultado;
	}

	public double getFollowerRank()
	{
		return (double)this.getFollowers() / ( (double)this.getFollowers() + (double)this.getFollowees() );
	}
	
	
}
