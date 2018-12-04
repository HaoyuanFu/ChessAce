package piece;

import java.util.ArrayList;

import board.Cell;

/**
 * This is the Queen Class inherited from the abstract Piece class
 *	
 * @author Harry Fu
 * @version     1.0                 (current version number of program)
 * @since       0.0          
 * 
 */ 
public class Queen extends Piece{
	//Constructors
	
	/**
	 * Constructor of Queen Instance, initializing its position, color and corresponding PNG picture.
	 *
	 * @param id used for identification
	 * @param path PNG picture path
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param color 1: white, -1: black
	 */
	public Queen(String id, String path, int x, int y, int color){
		setId(id);
		setX(x);
		setY(y);
		setPath(path);
		setColor(color);
	}
	
	
	
	/**
	 * Check the possible movement of this piece. Iterate through possible path according to the game rules, and break the iteration
	 * when blocked by other pieces or hit the edge of the board. If iteration hits a enemy piece, include the corresponding cell for
	 * elimination
	 * <p>
	 * Queen moves in all diagonal, vertical and horizontal directions with MAXIMUM length.
	 * </p>
	 * @param pos board state about positions of pieces.
	 * @return An ArrayList structure contains all cells that the piece can move to.
	 */
	@Override
	public ArrayList<Cell> posMove(Cell[][] pos){
		//Queen can move horizontally, vertically and diagnoally for any cell only other piece block its way
		possiblemoves.clear();

		//Check for horizontal posmoves
		for(int posy= y - 1; posy >= 0; posy--){
			System.out.println(posy);
			if(pos[x][posy].getPiece()==null) 
				possiblemoves.add(pos[x][posy]);
			else if(pos[x][posy].getPiece().getColor()==this.getColor()) 
				break;
			else{possiblemoves.add(pos[x][posy]); 
			break;}
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
			if(posy<7) 
				posy++;
			else
				break;
			if(pos[posx][posy].getPiece()==null) possiblemoves.add(pos[posx][posy]);
			else if(pos[posx][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][posy]); break;}
		}
		
		posy=y;
		for(int posx=x-1; posx>=0; posx--){
			if(posy<7) 
				posy++;
			else
				break;
			if(pos[posx][posy].getPiece()==null) possiblemoves.add(pos[posx][posy]);
			else if(pos[posx][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][posy]); break;}
		}
		
		posy=y;
		for(int posx=x+1; posx<8; posx++){ 
			if(posy>0) 
				posy--;
			else
				break;
			if(pos[posx][posy].getPiece()==null) possiblemoves.add(pos[posx][posy]);
			else if(pos[posx][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][posy]); break;}
		}
		
		posy=y;
		for(int posx=x-1; posx>=0; posx--){
			if(posy>0) 
				posy--;
			else
				break;
			if(pos[posx][posy].getPiece()==null) possiblemoves.add(pos[posx][posy]);
			else if(pos[posx][posy].getPiece().getColor()==this.getColor()) break;
			else{possiblemoves.add(pos[posx][posy]); break;}
		}
		return possiblemoves;

	}
}
