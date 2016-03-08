import java.util.Vector;

public class Board 
{
	private Piece[][] cells;
	private Rules rules;
	
	public Board(int sideSize)
	{
		this.cells = new Piece[sideSize][sideSize];
		this.rules = new Rules(this);
	}
	
	public Rules getRules()
	{
		return this.rules;
	}
	

	// put the piece at the given position
	public void setPieceAt(Piece piece, int x, int y) 
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
	

	// return the size of the board (23)
	public int getSideSize() 
	{		
		return this.cells.length;
	}


	// return an empty block
	public Piece getRandomEmptyBlock() 
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
				leX--;
				break;
				
			case Right:
				leX++;
				break;
				
			default:
				System.out.println("unknown direction!");
				return null;
		}
		return this.cells[leX][leY];
	}
	
	
	// return true if x and y are valid coordinates in the board
	private boolean areValidCoordinates(int x, int y)
	{
		return x>0 && y>0 && x<this.cells.length || x<this.cells.length;
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
