import java.util.TimerTask;

public class CatControler extends TimerTask
{
	private Cat cat;
	
	public CatControler(Board board)
	{
		this.cat = new Cat(board);
	}

	// return the cat controled by this controler
	public Piece getPiece() 
	{
		return this.cat;
	}

	
	// the cat does random movement
	@Override
	public void run() 
	{
		this.cat.moveTo(Direction.Right);
	}
}
