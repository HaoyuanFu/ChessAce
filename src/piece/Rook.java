/**
 *  \file Rook.java
 *  \brief Rook ADT
 */
package piece;

import java.util.ArrayList;

import board.Cell;

/**
 *  \brief Rook Class, Piece that can only move in a straight line.
 */
public class Rook extends Piece {
	
	/**
	 *  \brief Construct a new Rook instance using id, xy coordinates and color indicator
	 *  \details extended from piece class
	 *  \param Id string variable representing Id
	 *  \param p picture path
	 *  \param x x-coordinate
	 *  \param y y-coordinate
	 *  \param c color indicator
	 */
	public Rook(String Id, String p, int x, int y, int c) {
		setId(Id);
		setPath(p);
		setX(x);
		setY(y);
		setColor(c);
		this.neverMoved = true;
	}

	/**
	 *  \brief Basic Movement Check, Rook can be move or attack cells in a vertical or horizontal line.
	 *  \details if the path is blocked by a friendly piece, break and exclude the blocked cell. If the path is blocked by a hostile piece, break and
	 *  include the blocked cell. Else, keep iterating and include :P. Check castling possibility as well.
	 *  \param pos chessboard constructed by a 2D Cell Array.
	 */
	@Override
	public ArrayList<Cell> posMove(Cell[][] pos) {
		int temp;
		possiblemoves.clear();
		
		temp = this.x - 1;
		while(temp >= 0) {
			if(pos[temp][this.y].getPiece()==null) {
				possiblemoves.add(pos[temp][this.y]);
			}else if(pos[temp][this.y].getPiece().getColor() != this.getColor()){
				possiblemoves.add(pos[temp][this.y]);
				break;
			}else {
				break;
			}
			temp = temp - 1;
		}
		
		temp = this.x + 1;
		while(temp < 8) {
			if(pos[temp][this.y].getPiece()==null) {
				possiblemoves.add(pos[temp][this.y]);
			}else if(pos[temp][this.y].getPiece().getColor() != this.getColor()){
				possiblemoves.add(pos[temp][this.y]);
				break;
			}else {
				break;
			}
			temp = temp + 1;
		}
		
		temp = this.y - 1;
		while(temp >= 0) {
			if(pos[this.x][temp].getPiece()==null) {
				possiblemoves.add(pos[this.x][temp]);
			}else if(pos[this.x][temp].getPiece().getColor() != this.getColor()){
				possiblemoves.add(pos[this.x][temp]);
				break;
			}else {
				break;
			}
			temp = temp - 1;
		}
		
		temp = this.y + 1;
		while(temp < 8) {
			if(pos[this.x][temp].getPiece()==null) {
				possiblemoves.add(pos[this.x][temp]);
			}else if(pos[this.x][temp].getPiece().getColor() != this.getColor()){
				possiblemoves.add(pos[this.x][temp]);
				break;
			}else
				break;
			temp = temp + 1;
			}
	
			
		//Castling: 
		//Rule 1: Kind & Associating Rook shall not move before
		//Rule 2: Castle out of check is not allowed
		//Rule 3: Nothing shall be in between the Corresponding King and Rook
		/*if(neverMoved) {
			if(this.y == 0 )
				possiblemoves.add(pos[this.x][3]);
			if(this.y == 8)
				possiblemoves.add(pos[this.x][5]);
		}*/
		
		return possiblemoves;
	}
	
}


