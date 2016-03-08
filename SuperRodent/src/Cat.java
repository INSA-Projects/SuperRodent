
public class Cat extends Piece 
{
	private Board board;
	private TrapBox box;
	
	public Cat(Board board)
	{
		this.board = board;
		this.box = new TrapBox(board.getRules(), this);
	}
	
	// return the trap box of the cat
	public TrapBox getTrap()
	{
		return this.box;
	}
	
	
	// move the piece to the direction dir
	public void moveTo(Direction dir)
	{
		Piece p = this.board.getAdjacentPiece(this.getX(), this.getY(), dir);
		p.accept(this);
		
		// show the board
		System.out.println(this.board.toString());	
		
	}
	

	public void releaseTrap() 
	{
		this.box.cancel();
		this.box = new TrapBox(this.board.getRules(), this);
	}

	// Visitor pattern
	@Override
	public void accept(Piece p) 
	{
		p.visit(this);
	}

	@Override
	public void visit(Rat rat) 
	{
		this.board.getRules().resolve(this, rat);
	}

	@Override
	public void visit(Cat cat) 
	{
		this.board.getRules().resolve(this, cat);
	}

	@Override
	public void visit(MovableBlock movBlock) 
	{
		this.board.getRules().resolve(this, movBlock);
	}

	@Override
	public void visit(ImmovableBlock immoBlock) 
	{
		this.board.getRules().resolve(this, immoBlock);
	}

	@Override
	public void visit(EmptyBlock empty) 
	{
		this.board.getRules().resolve(this,empty);		
	}

	@Override
	public void visit(Cheese cheese) 
	{
		this.board.getRules().resolve(this, cheese);		
	}


}
