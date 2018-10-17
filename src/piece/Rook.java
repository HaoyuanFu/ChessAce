package piece;

import java.util.ArrayList;

import board.Cell;

public class Rook extends Piece {
	
	public Rook(String Id, String p, int x, int y, int c) {
		setId(Id);
		setPath(p);
		setX(x);
		setY(y);
		setColor(c);
		this.neverMoved = true;
	}
	
	//Basic Movement, king can be move or attack cells one unit beside him in any directions.
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
		if(neverMoved) {
			if(this.x == 0)
				possiblemoves.add(pos[2][this.y]);
			if(this.x == 8)
				possiblemoves.add(pos[6][this.y]);
		}
		
		return possiblemoves;
	}
	
}


