package piece;

import java.util.ArrayList;

import board.Cell;

public class King extends Piece{
	private int x;
	private int y;
	private boolean neverMoved;
	private boolean notCheckmated;
	
	public King(int x, int y) {
		setX(x);
		setY(y);
		this.neverMoved = true;
		this.notCheckmated = true;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setMoved() {
		this.neverMoved = false;
	}
	
	public void setCheckmate() {
		this.notCheckmated = false;
	}
	
	public void removeCheckmate() {
		this.notCheckmated = true;
	}
	
	//Basic Movement, king can be move or attack cells one unit beside him in any directions.
	@Override
	public ArrayList<Cell> posMove(Cell[][] pos) {
		possiblemoves.clear();
		for(int i = -1; i <= 1; i++) 
			for(int j = -1; j <= 1; j++) 
				if((this.x + i) >= 0 && (this.x + i) <= 8) 
					if((this.y + j) >= 0 && (this.y + j) <= 8) 
						if(pos[x+i][y+j] == null || pos[x+i][y+j].getPiece().getColor() != this.getColor())
							possiblemoves.add(pos[x+i][y+i]);
		
		//Castling: 
		//Rule 1: Kind & Associating Rook shall not move before
		//Rule 2: Castle out of check is not allowed
		//Rule 3: Nothing shall be in between the Corresponding King and Rook
		if(neverMoved && notCheckmated) {
			boolean temp0 = true;
			boolean temp1 = true;
			for(int i = 1; i <= 2; i++) {
				if(pos[this.x + i][this.y] != null)
					temp0 = false;
			}
			for(int j = 1; j <= 3; j++) {
				if(pos[this.x - j][this.y] != null)
					temp1 = false;
			}
			if(temp0)
				possiblemoves.add(pos[this.x + 2][this.y]);
			if(temp1)
				possiblemoves.add(pos[this.x - 3][this.y]);
		}
		
		return possiblemoves;
	}
}
