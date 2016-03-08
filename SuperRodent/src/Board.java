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
			case Left:
				leY--;
				break;
				
			case Right:
				leY++;
				break;
				
			case Up:
				leX--;
				break;
				
			case Down:
				leX++;
				break;
				
			default:
				System.out.println("direction not implemented!");
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
	

	// put a cheese instead of a trapped cat
	public void checkTrappedCats() 
	{
		/*
		 
		Vector<Cat> trappedCats = new Vector<Cat>();
		
		for (Cat cat : this.cats)
		{
			int catx = cat.getX();
			int caty = cat.getY();
			boolean trapped = true;
			
			for (int i = catx-1; trapped && i<catx+1;i++)
			{
				for (int j = caty-1; trapped && j<caty+1;j++)
				{
					if (i==j)
					{
						continue;
					}
					if(this.cells[i][j] instanceof EmptyBlock)
					{
						trapped = false;
					}
				}
			}
			if (trapped)
			{
				System.out.println("Cat is trapped");
				trappedCats.add(cat);
			}
		}
		
		for (Cat cat : trappedCats)
		{
			this.cats.remove(cat);
			int catx = cat.getX();
			int caty = cat.getY();
			this.putPieceAt(new Cheese(), catx, caty);
		}
		
		*/
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
				default:
					res += "[?]";
						
						
				}
			}
			res += "\n";
		}
		return res;
	}


	
}
