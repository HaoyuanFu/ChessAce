package piece;

import java.util.ArrayList;

import board.Cell;

public abstract class Piece implements Cloneable{

	//Member Variables
	private int color;
	private String path;
	protected ArrayList<Cell> possiblemoves = new ArrayList<Cell>();  //Protected (access from child classes)
	public abstract ArrayList<Cell> posMove(Cell[][] pos);  //Abstract Function. Must be overridden
	protected int x;
	protected int y;
	
	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}
	
	//Path Setter
	public void setPath(String path)
	{
		this.path=path;
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