
public class PlayerStats 
{
	private int score = 0;
	private int lives = 3;
	
	public PlayerStats(int lives)
	{
		this.lives = lives;
	}
	
	// add points to the score
	public void addPoints(int points)
	{
		this.score += points;
	}
	
	// loose a life
	public void looseLife()
	{
		this.lives--;
	}
	
	// return true if the player has at least one life
	public boolean canContinue()
	{
		return this.lives > 0;
	}
	
	// return the score
	public int getScore()
	{
		return this.score;
	}
	
	public String toString()
	{
		return "Lives: " + this.lives + " - Score: " + this.score;
	}

}
