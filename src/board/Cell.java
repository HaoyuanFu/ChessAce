package board;

import piece.King;
import piece.Piece;

public class Cell {
	int x,y;  
	private Piece piece;
	
	public Cell(int x,int y, Piece p)
	{		
		this.x=x;
		this.y=y;
	}
	
	public void setPiece(Piece p)    //Function to inflate a cell with a piece
	{
		piece=p;
	}
	
	public void removePiece()      //Function to remove a piece from the cell
	{
		piece=null;
	}
	
	
	public Piece getPiece()    //Function to access piece of a particular cell
	{
		return this.piece;
	}
}
