package piece;

import java.util.ArrayList;

import board.Cell;

/**
 * This is the Queen Class inherited from the abstract Piece class
 *
 */
public class Queen extends Piece{
	//Constructors
	
	public Queen(String id, String path, int x, int y, int color){
		setId(id);
		setX(x);
		setY(y);
		setPath(path);
		setColor(color);
	}
	
	
	
	//Move function Overridden
	@Override
	public ArrayList<Cell> posMove(Cell[][] pos){
		//Queen can move horizontally, vertically and diagnoally for any cell only other piece block its way
		possiblemoves.clear();

		//Check for horizontal posmoves
		for(int posy=y-1; posy>=0; posy--){
			if(pos[x][posy].getPiece()==null) possiblemoves.add(pos[x][posy]);
			else if(pos[x][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[x][posy]); break;}
		}
		for(int posy=y+1; posy<8; posy++){
			if(pos[x][posy].getPiece()==null) possiblemoves.add(pos[x][posy]);
			else if(pos[x][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[x][posy]); break;}
		}

		//Check for vertical posmove
		for(int posx=x-1; posx>=0; posx--){
			if(pos[posx][y].getPiece()==null) possiblemoves.add(pos[posx][y]);
			else if(pos[posx][y].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][y]); break;}
		}
		for(int posx=x+1; posx<8; posx++){
			if(pos[posx][y].getPiece()==null) possiblemoves.add(pos[posx][y]);
			else if(pos[posx][y].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][y]); break;}
		}

		//Check for diagonal posmove
		int posy=y;
		for(int posx=x+1; posx<8; posx++){
			if(posy<7) posy++;
			if(pos[posx][posy].getPiece()==null) possiblemoves.add(pos[posx][posy]);
			else if(pos[posx][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][posy]); break;}
		}
		
		posy=y;
		for(int posx=x-1; posx>=0; posx--){
			if(posy<7) posy++;
			if(pos[posx][posy].getPiece()==null) possiblemoves.add(pos[posx][posy]);
			else if(pos[posx][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][posy]); break;}
		}
		
		posy=y;
		for(int posx=x+1; posx<8; posx++){ 
			if(posy>0) posy--;				
			if(pos[posx][posy].getPiece()==null) possiblemoves.add(pos[posx][posy]);
			else if(pos[posx][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][posy]); break;}
		}
		
		posy=y;
		for(int posx=x-1; posx>=0; posx--){
			if(posy>0) posy--;
			if(pos[posx][posy].getPiece()==null) possiblemoves.add(pos[posx][posy]);
			else if(pos[posx][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][posy]); break;}
		}
		return possiblemoves;

	}
}