
public class Rules 
{
	private static final int CHEESE_POINTS = 10;
	
	private Board board;
	private PlayerStats playerStats;
	private Initializator initializator;
	
	public Rules(Board board)
	{
		this.board = board;
		this.playerStats = new PlayerStats(3);
	}
	
	
	// set the initializator in order to restart the game
	public void setInitializator(Initializator initializator)
	{
		this.initializator = initializator;
	}
	
	
	// switch the two pieces on the board
	private void switchPieces(Piece p1, Piece p2)
	{
		int p1X = p1.getX();
		int p1Y = p1.getY();
		int p2X = p2.getX();
		int p2Y = p2.getY();
		
		this.board.putPieceAt(p1, p2X, p2Y);
		this.board.putPieceAt(p2, p1X, p1Y);
	}
	
	
	// the 'eater' piece eats the 'eaten' piece
	private void eat(Piece eater, Piece eaten)
	{
		EmptyBlock newCell = new EmptyBlock();
		newCell.setX(eaten.getX());
		newCell.setY(eaten.getY());
		
		this.switchPieces(eater, newCell);
	}

	
	// move recursively the pieces (if possible)
	private void moveIfPossible(Piece fromP, Piece toP)
	{
		// determine the direction of the movement
		Direction dir = Direction.Left;
		if (fromP.getX() > toP.getX())
		{
			dir = Direction.Up;
		}
		else if (fromP.getX() < toP.getX())
		{
			dir = Direction.Down;
		}
		else if (fromP.getY() < toP.getY())
		{
			dir = Direction.Right;
		}
		
		// move the toPiece THEN the fromPiece (if possible)
		toP.moveTo(dir);
		Piece adjCell = this.board.getAdjacentPiece(fromP.getX(), fromP.getY(), dir);
		if (adjCell != toP)
		{
			this.switchPieces(fromP, adjCell);
		}
	}

	
	// the player loose a life, board is reset
	public void playerLooseLife()
	{
		this.playerStats.looseLife();
		if (this.playerStats.canContinue())
		{
			this.initializator.resetBoard();
		}
		else
		{
			this.initializator.terminate();
		}
	}
	
	
	// Rules of the game
	// -------------------------
	// Rules for cat
	public void resolve(Cat fromCat, EmptyBlock toEmpty)
	{
		// cat moves
		this.switchPieces(fromCat, toEmpty);
	}
	
	
	public void resolve(Cat fromCat, Rat toRat) 
	{
		// lose a life
		this.playerLooseLife();
	}

	public void resolve(Cat fromCat, Cat cat) 
	{
		// do nothing
	}

	public void resolve(Cat fromCat, MovableBlock movBlock) 
	{
		// do nothing		
	}

	public void resolve(Cat fromCat, ImmovableBlock toImmoBlock) 
	{
		// do nothing
	}

	public void resolve(Cat fromCat, Cheese toCheese) 
	{
		// the cat eats the cheese
		this.eat(fromCat, toCheese);
	}

	
	// -------------------------
	// Rules for rat
	
	public void resolve(Rat rat, Cat cat) 
	{
		// loose life
		this.playerLooseLife();
	}
	
	public void resolve(Rat fromRat, Cheese toCheese) 
	{
		// the rat eat the cheese and the player gets points
		this.eat(fromRat, toCheese);
		this.playerStats.addPoints(Rules.CHEESE_POINTS);
	}

	public void resolve(Rat fromRat, MovableBlock toMovBlock) 
	{
		// move the blocks if possible
		this.moveIfPossible(fromRat, toMovBlock);
	}

	public void resolve(Rat rat, ImmovableBlock immoBlock) 
	{
		// do nothing
	}

	
	public void resolve(Rat fromRat, EmptyBlock toEmpty) 
	{
		// the rat moves
		this.switchPieces(fromRat, toEmpty);		
	}


	public void resolve(Rat fromRat, Rat rat) 
	{
		// do nothing		
	}
	
	// -------------------------
	// Rules for movable blocks
	public void resolve(MovableBlock movableBlock, Rat rat) 
	{
		// do nothing		
	}

	public void resolve(MovableBlock movableBlock, Cat cat) 
	{
		// do nothing
	}

	public void resolve(MovableBlock movableBlock, ImmovableBlock immoBlock) 
	{
		// do nothing
	}

	public void resolve(MovableBlock movableBlock, EmptyBlock empty) 
	{
		// the movable block is moved and a check is launched to see if a cat is traped
		this.switchPieces(movableBlock, empty);
		this.board.checkTrappedCats();
	}

	public void resolve(MovableBlock movableBlock, Cheese cheese) 
	{
		// movable block is moved and cheese is eaten
		this.eat(movableBlock, cheese);
		this.playerStats.addPoints(CHEESE_POINTS);
	}

	public void resolve(MovableBlock fromMovBlock, MovableBlock toMovBlock) 
	{
		this.moveIfPossible(fromMovBlock, toMovBlock);		
	}

	

	

}
