package piece;

import java.util.ArrayList;

import board.Cell;

/**
 * This is the Pawn Class inherited from the piece
 *
 */
public class Pawn extends Piece{
	private boolean promoPossible;

	//Constructors
	public Pawn(String Id, String path, int x, int y, int color) {
		setId(Id);
		setPath(path);
		setColor(color);
		setX(x);
		setY(y);
		this.promoPossible = false;
	}

	public void setPromo(){
		this.promoPossible = true;
	}
	
	public boolean getPromo() {
		return this.promoPossible;
	}

	//Move Function Overridden
	@Override
	public ArrayList<Cell> posMove(Cell[][] pos){
		//Pawn only moves one step except the first step, for the first step it may move one or two steps
		//Pawn can only move in a diagnoal when it is attacking a piece of opposite color.
		//Pawn can do queening(promotion) after it reaches its 8th rank which is the 8th line for white and 1st line for black
		//Pawn can not move backward or forward to attact a piece

		possiblemoves.clear();
		if(this.getColor()==1){
			if(x==0){
				setPromo();
				return possiblemoves;
			}
			if(pos[x-1][y].getPiece()==null ){
				possiblemoves.add(pos[x-1][y]);
				if(x==6){
					if(pos[4][y].getPiece()==null) possiblemoves.add(pos[4][y]);
				}
			}
			if((y>0)&&(pos[x-1][y-1].getPiece()!=null)&&(pos[x-1][y-1].getPiece().getColor()!=this.getColor()))
				possiblemoves.add(pos[x-1][y-1]);
			if((y<7)&&(pos[x-1][y+1].getPiece()!=null)&&(pos[x-1][y+1].getPiece().getColor()!=this.getColor())) 
				possiblemoves.add(pos[x-1][y+1]);
			
		}
		else{
			if(x==7){
				setPromo();
				return possiblemoves;
			}
			if(pos[x+1][y].getPiece()==null){
				possiblemoves.add(pos[x+1][y]);
				if(x==1){
					if(pos[3][y].getPiece()==null) possiblemoves.add(pos[3][y]);
				}
			}
			if((y>0)&&(pos[x+1][y-1].getPiece()!=null)&&(pos[x+1][y-1].getPiece().getColor()!=this.getColor()))
				possiblemoves.add(pos[x+1][y-1]);
			if((y<7)&&(pos[x+1][y+1].getPiece()!=null)&&(pos[x+1][y+1].getPiece().getColor()!=this.getColor()))
				possiblemoves.add(pos[x+1][y+1]);
		}
		return possiblemoves;
	}
}
