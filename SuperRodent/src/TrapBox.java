import java.util.Timer;
import java.util.TimerTask;


public class TrapBox extends TimerTask 
{
	private Rules rules;
	private Timer timer;
	private Cat cat;
	private boolean isRunning = false;
	
	public TrapBox(Rules rules, Cat victim)
	{
		this.rules = rules;
		this.cat = victim;
		this.timer = new Timer();
	}

	public void choke(int secondes)
	{
		if (this.isRunning)
		{
			return;
		}
		System.out.println("KOF KOF");
		this.timer.schedule(this, secondes * 1000);
		this.isRunning = true;
	}
	
	@Override
	public void run() 
	{
		this.rules.disposeOfBody(this.cat);			
	}	

	
}
