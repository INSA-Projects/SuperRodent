
public class Rules 
{
	private Board board;
	
	public Rules(Board board)
	{
		this.board = board;
	}
	
	public void resolve(Cat fromCat, EmptyBlock toEmpty)
	{
		this.switchPieces(fromCat, toEmpty);
	}
	
	private void switchPieces(Piece p1, Piece p2)
	{
		int p1X = p1.getX();
		int p1Y = p1.getY();
		int p2X = p2.getX();
		int p2Y = p2.getY();
		
		this.board.setPieceAt(p1, p2X, p2Y);
		this.board.setPieceAt(p2, p1X, p1Y);		
	}

}
