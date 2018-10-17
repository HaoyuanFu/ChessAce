package piece;

import java.util.ArrayList;

import board.Cell;

public abstract class Piece implements Cloneable{

	//Member Variables
	protected boolean neverMoved;
	private int color;
	private String Id;
	private String path;
	protected ArrayList<Cell> possiblemoves = new ArrayList<Cell>();  //Protected (access from child classes)
	public abstract ArrayList<Cell> posMove(Cell[][] pos);  //Abstract Function. Must be overridden
	protected int x;
	protected int y;
	
	public void setMove() {
		this.neverMoved = false;
	}
	
	public boolean isMoved() {
		return !neverMoved;
	}
	
	
	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	//Path Setter
	public void setPath(String path)
	{
		this.path=path;
	}
	
	//Path Setter
	public void setId(String id)
	{
		this.Id = id;
	}
	
	public String getId()
	{
		return this.Id;
	}
	//Color Setter
	public void setColor(int c)
	{
		this.color=c;
	}
	
	//Path getter
	public String getPath()
	{
		return path;
	}
	
	//Color Getter
	public int getColor()
	{
		return this.color;
	}
	
	//Function to return the a "shallow" copy of the object. The copy has exact same variable value but different reference
	public Piece getcopy() throws CloneNotSupportedException
	{
		return (Piece) this.clone();
	}
}