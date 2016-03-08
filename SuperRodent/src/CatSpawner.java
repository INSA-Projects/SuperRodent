import java.util.Timer;
import java.util.TimerTask;


public class CatSpawner extends TimerTask 
{
	private Timer timer;
	private Initializator init;
	
	public CatSpawner(Initializator init)
	{
		this.timer = new Timer();
		this.init = init;
	}

	@Override
	public void run() 
	{
		this.init.initNewCat();
	}

	public void start() 
	{
		this.timer.schedule(this, 0, 4000);
	}

}
