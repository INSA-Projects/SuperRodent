import java.util.Timer;
import java.util.TimerTask;

public class CatControler extends TimerTask
{
	private Cat cat;
	private Timer timer;
	
	public CatControler(Board board)
	{
		this.cat = new Cat(board);
		this.timer = new Timer();
	}

	// return the cat controled by this controler
	public Cat getPiece() 
	{
		return this.cat;
	}

	// init the timer for random movement
	public void initArtificalIntelligence()
	{
		this.timer.scheduleAtFixedRate(this, 0, 2000);
	}
	
	
	// random movement
	@Override
	public void run() 
	{
		this.cat.moveTo(Direction.Right);		
	}
}
