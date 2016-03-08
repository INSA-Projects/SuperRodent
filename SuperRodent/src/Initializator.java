import java.util.Timer;


public class Initializator 
{
	private Timer timer;
	
	public Initializator()
	{
		this.timer = new Timer();
	}
	
	
	// init the board with ImmovableBlocks, EmptyBlocks and MovableBlocks
	public void initBoard(Board board)
	{
		if (board == null)
		{
			System.out.println("Error: Board is null");
			return;
		}
		
		int sideSize = board.getSideSize();
		
		
		// ImmovableBlocks
		for (int i = 0; i<sideSize; i++)
		{
			board.setPieceAt(new ImmovableBlock(), 0, i);
			board.setPieceAt(new ImmovableBlock(), sideSize-1, i);
			board.setPieceAt(new ImmovableBlock(), i, 0);
			board.setPieceAt(new ImmovableBlock(), i, sideSize-1);			
		}
		
		// MovableBlocks and EmptyBlocks
		int freeSpace = 5;
		for (int i=1; i<sideSize-1; i++)
		{
			for (int j=1; j<sideSize-1; j++)
			{
				if (i<freeSpace || i>=sideSize-freeSpace || j<freeSpace || j>=sideSize-freeSpace)
				{
					board.setPieceAt(new EmptyBlock(), i, j);
				}
				else
				{
					board.setPieceAt(new MovableBlock(board), i, j);
				}			
			}
		}		
	}

	// add the rat to the board
	public void addRatToBoard(RatControler ratControl, Board board)
	{
		int middle = board.getSideSize() / 2;
		board.setPieceAt(ratControl.getPiece(),middle, middle);
	}

	// add a cat to the board
	public void addCatToBoard(CatControler catControl, Board board) 
	{
		// get a free cell
		Piece empty = board.getRandomEmptyBlock();
		if (empty == null)
		{
			System.out.println("No empty space");
			return;
		}
		
		// put the cat
		board.setPieceAt(catControl.getPiece(), empty.getX(), empty.getY());
		
		// start the random movement
		this.timer.scheduleAtFixedRate(catControl, 0, 2000);
	}
}
