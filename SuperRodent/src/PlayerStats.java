
public class PlayerStats 
{
	private int score = 0;
	private int lives = 3;
	
	public PlayerStats(int lives)
	{
		this.lives = lives;
	}
	
	public void addPoints(int points)
	{
		this.score += points;
	}
	
	public void looseLife()
	{
		this.lives--;
	}
	
	public boolean canContinue()
	{
		return this.lives > 0;
	}
	
	public int getScore()
	{
		return this.score;
	}

}
