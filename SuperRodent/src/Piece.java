
public abstract class Piece 
{
	private int x = -1;
	private int y = -1;
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}

	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}

	// move function
	public abstract void moveTo(Direction dir);
	
	
	// accept of Visitor pattern
	public abstract void accept(Piece p);

	// visit of Visitor pattern
	public abstract void visit(Rat rat);
	public abstract void visit(Cat cat);
	public abstract void visit(MovableBlock movBlock);
	public abstract void visit(ImmovableBlock immoBlock);
	public abstract void visit(EmptyBlock empty);
	public abstract void visit(Cheese cheese);
	
	// to string
	public String toString()
	{
		return this.getClass().toString() + " (" + this.x + "," + this.y + ")";
	}
	
}
