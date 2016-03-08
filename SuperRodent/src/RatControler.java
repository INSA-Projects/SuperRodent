import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class RatControler implements KeyListener
{
	private Rat rat;
	
	public RatControler(Board board)
	{
		this.rat = new Rat(board);
	}

	// control of the rat
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		switch (key)
		{
			case KeyEvent.VK_LEFT:
				System.out.println("left");
				break;
			default:
				break;				
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	// return the rat controled by this controler
	public Piece getPiece() 
	{
		return this.rat;
	}
}
