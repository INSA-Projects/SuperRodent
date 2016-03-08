import java.util.Vector;

public class Board 
{
	private Piece[][] cells;
	private Rules rules;
	private Vector<Cat> cats;
	
	public Board(int sideSize)
	{
		this.cells = new Piece[sideSize][sideSize];
		this.rules = new Rules(this);
		this.cats = new Vector<Cat>();
	}
	
	public Rules getRules()
	{
		return this.rules;
	}
	

	// return the size of the board
	public int getSideSize() 
	{		
		return this.cells.length;
	}


	// return true if x and y are valid coordinates in the board
	private boolean areValidCoordinates(int x, int y)
	{
		return x>0 && y>0 && x<this.cells.length || x<this.cells.length;
	}

	
	// put the piece at the given position
	public void putPieceAt(Piece piece, int x, int y) 
	{
		if (!this.areValidCoordinates(x,y))
		{
			System.out.println("Out of bound!");
			return;
		}
		
		this.cells[x][y] = piece;
		piece.setX(x);
		piece.setY(y);
	}
	
	// put the cat at a random empty position
	public void putCatAtRandomPos(Cat cat)
	{
		this.cats.addElement(cat);
		Piece p = this.getRandomEmptyBlock();
		
		this.putPieceAt(cat, p.getX(), p.getY());
	}
	

	// return an empty block
	private Piece getRandomEmptyBlock() 
	{
		Vector<Piece> vec = new Vector<Piece>();
		for (Piece[] tab : this.cells)
		{
			for (Piece p : tab)
			{
				if (p.getClass().getName() == EmptyBlock.class.getName())
				{
					vec.add(p);					
				}				
			}
		}
		
		if (vec.isEmpty())
		{
			return null;
		}
		return vec.firstElement();
	}

		
	// return the piece in the adjacent cell (x,y) in the given direction
	public Piece getAdjacentPiece(int x, int y, Direction dir) 
	{
		if (!this.areValidCoordinates(x, y))
		{
			System.out.println("out of bound");
			return null;
		}
		
		int leX = x;
		int leY = y;
		
		switch (dir)
		{
			case Up:
				leX--;
				break;				
			case RightUp:
				leX--;
				leY++;
				break;
			case Right:
				leY++;
				break;				
			case RightDown:
				leY++;
				leX++;
				break;				
			case Down:
				leX++;
				break;				
			case LeftDown:
				leX++;
				leY--;
				break;
			case Left:
				leY--;
				break;
			case LeftUp:
				leY--;
				leX--;
				break;
				
			default:
				System.out.println("direction not implemented: " + dir.toString());
		}
		return this.cells[leX][leY];
	}
	

	// return true if the movableBlock is movable in the direction dir
	public boolean blockIsMovable(MovableBlock movBlock, Direction dir) 
	{		
		Piece p = this.getAdjacentPiece(movBlock.getX(), movBlock.getY(), dir);		
		if (p instanceof EmptyBlock)
		{
			return true;
		}
		if (p instanceof MovableBlock)
		{			
			return this.blockIsMovable((MovableBlock) p, dir);
		}
		return false;		
	}
	

	
	// check if there are cats trapped
	public void checkTrappedCats() 
	{
		for (Cat cat : this.cats)
		{
			this.rules.updateTrapCat(cat, this.isTrapped(cat));
		}
	}

	// returns true if the given cat is surrounded by blocks
	public boolean isTrapped(Cat cat)
	{
		int catx = cat.getX();
		int caty = cat.getY();
		boolean trapped = true;
		
		for (Direction dir : Direction.values())
		{
			Piece p = this.getAdjacentPiece(catx, caty, dir);
			trapped = trapped && (p instanceof MovableBlock || p instanceof ImmovableBlock);
		}
		
		return trapped;
	}
	
	// just for printing
	public String toString()
	{
		String res = "";
		for (Piece[] tab : cells)
		{
			for (Piece p : tab)
			{		
				if (p == null)
				{
					res += "[null]";
					continue;
				}
				switch (p.getClass().getName())
				{
				case "ImmovableBlock":
					res += "[I]";
					break;				
				case "MovableBlock":
					res += "[M]";
					break;
				case "EmptyBlock":
					res += "[ ]";
					break;
				case "Rat":
					res += "[R]";
					break;
				case "Cat":
					res += "[C]";
					break;
				case "Cheese":
					res += "[F]";
					break;
				default:
					res += "[?]";
						
						
				}
			}
			res += "\n";
		}
		return res;
	}


	
}
