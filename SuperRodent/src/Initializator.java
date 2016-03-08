import java.util.Vector;


public class Initializator 
{
	private Board board;
	private RatControler controler;
	private Vector<CatControler> catsControl;
	private CatSpawner catSpawner;
	
	public Initializator(Board board, RatControler controler)
	{
		this.board = board;
		this.controler = controler;
		this.catsControl = new Vector<CatControler>();
		this.catSpawner = new CatSpawner(this);
		
		board.getRules().setInitializator(this);
	}
	
	
	// init the board with ImmovableBlocks, EmptyBlocks and MovableBlocks
	private void initBoard()
	{		
		int sideSize = board.getSideSize();		
		
		// ImmovableBlocks
		for (int i = 0; i<sideSize; i++)
		{
			board.putPieceAt(new ImmovableBlock(), 0, i);
			board.putPieceAt(new ImmovableBlock(), sideSize-1, i);
			board.putPieceAt(new ImmovableBlock(), i, 0);
			board.putPieceAt(new ImmovableBlock(), i, sideSize-1);			
		}
		
		// MovableBlocks and EmptyBlocks
		int freeSpace = 5;
		for (int i=1; i<sideSize-1; i++)
		{
			for (int j=1; j<sideSize-1; j++)
			{
				if (i<freeSpace || i>=sideSize-freeSpace || j<freeSpace || j>=sideSize-freeSpace)
				{
					board.putPieceAt(new EmptyBlock(), i, j);
				}
				else
				{
					board.putPieceAt(new MovableBlock(board), i, j);
				}			
			}
		}		
	}

	private void initControlers()
	{
		// put the rat
		int middle = board.getSideSize() / 2;
		board.putPieceAt(this.controler.getPiece(),middle, middle);
		
		// put a cat
		this.initNewCat();		
	}
	
	// add a new cat on the board
	public void initNewCat()
	{
		// put a cat
		CatControler catControl = new CatControler(this.board);
		board.putCatAtRandomPos(catControl.getPiece());
		this.catsControl.add(catControl);
		
		// init the artificial intelligence of the cat
		catControl.initArtificalIntelligence();
	}
	
	
	// the board is put into the original configuration
	public void resetBoard()
	{
		// stops active cats
		for (CatControler catCont : this.catsControl)
		{
			catCont.cancel();
		}
		this.catsControl.clear();
		
		// reset board and controllers
		this.initBoard();
		this.initControlers();
		
		// reset spawner
		this.catSpawner.cancel();
		this.catSpawner = new CatSpawner(this);
		this.catSpawner.start(Rules.CAT_SPAWN_DELAY);
		
		System.out.println(this.board.getRules().getStats().toString());
	}


	// stops the controlers, fill the board with immovable blocks
	public void terminate() 
	{
		for (CatControler catCont : this.catsControl)
		{
			catCont.cancel();
		}
		this.catsControl.clear();
		
		this.catSpawner.cancel();
		
		int sideSize = board.getSideSize();		
		for (int i=0; i<sideSize; i++)
		{
			for (int j=0; j<sideSize; j++)
			{
				board.putPieceAt(new ImmovableBlock(), i, j);							
			}
		}
		
		System.out.println(this.board.getRules().getStats().toString());
		System.out.println(this.board.toString() + "\n============= GAME OVER =============");
	}


	// stop the cat controler associated to the dead cat
	public void stopCatControler(Cat deadCat) 
	{
		for (CatControler catCont : this.catsControl)
		{
			if (catCont.getPiece() == deadCat)
			{
				System.out.println("Controler of dead cat turned off");
				catCont.cancel();
			}
		}
	}

}
