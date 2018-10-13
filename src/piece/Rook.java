package piece;

import java.util.ArrayList;

import board.Cell;

public class Rook extends Piece {
	private int x;
	private int y;
	private boolean neverMoved;
	
	public Rook(String p, int x, int y, int c) {
		setPath(p);
		setX(x);
		setY(y);
		setColor(c);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setMove() {
		this.neverMoved = false;
	}
	
	public boolean isMoved() {
		return !neverMoved;
	}
	
	
	//Basic Movement, king can be move or attack cells one unit beside him in any directions.
	@Override
	public ArrayList<Cell> posMove(Cell[][] pos) {
		int temp;
		possiblemoves.clear();
		
		temp = this.x - 1;
		while(temp >= 0) {
			if(pos[temp][this.y]==null) {
				possiblemoves.add(pos[temp][this.y]);
			}else if(pos[temp][this.y].getPiece().getColor() != this.getColor()){
				possiblemoves.add(pos[temp][this.y]);
				break;
			}else {
				break;
			}
		}
		
		temp = this.x + 1;
		while(temp < 8) {
			if(pos[temp][this.y]==null) {
				possiblemoves.add(pos[temp][this.y]);
			}else if(pos[temp][this.y].getPiece().getColor() != this.getColor()){
				possiblemoves.add(pos[temp][this.y]);
				break;
			}else {
				break;
			}
		}
		
		temp = this.y - 1;
		while(temp >= 0) {
			if(pos[this.x][temp]==null) {
				possiblemoves.add(pos[temp][this.y]);
			}else if(pos[this.x][temp].getPiece().getColor() != this.getColor()){
				possiblemoves.add(pos[this.x][temp]);
				break;
			}else {
				break;
			}
		}
		
		temp = this.y - 1;
		while(temp < 8) {
			if(pos[this.x][temp]==null || pos[this.x][temp].getPiece().getColor() != this.getColor()) {
				possiblemoves.add(pos[this.x][temp]);
			}
		}
	
			
		//Castling: 
		//Rule 1: Kind & Associating Rook shall not move before
		//Rule 2: Castle out of check is not allowed
		//Rule 3: Nothing shall be in between the Corresponding King and Rook
		if(neverMoved) {
			if(this.x == 0)
				possiblemoves.add(pos[2][this.y]);
			if(this.x == 8)
				possiblemoves.add(pos[6][this.y]);
		}
		
		return possiblemoves;
	}
	
}


