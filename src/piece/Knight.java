package piece;
import java.util.ArrayList;
import board.Cell;

/**
 * This is the Knight Class inherited from the abstract Piece class
 *	
 * @author Morgan Cui
 * @version     1.0                 (current version number of program)
 * @since       0.0          
 * 
 */ 
public class Knight extends Piece{
	/**
	 * Constructor of Knight Instance, initializing its position, color and corresponding PNG picture.
	 *
	 * @param id used for identification
	 * @param p PNG picture path
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param c 1: white, -1: black
	 */
	public Knight(String Id, String p, int x, int y,  int c) {
		setId(Id);
		setPath(p);
		setColor(c);
		setX(x);
		setY(y);
	}
	
	/**
	 * Check the possible movement of this piece. Iterate through possible path according to the game rules, and break the iteration
	 * when blocked by other pieces or hit the edge of the board. If iteration hits a enemy piece, include the corresponding cell for
	 * elimination
	 * <p>
	 * Knight moves in all (1,2) combination on (x,y) directions
	 * </p>
	 * @param pos board state about positions of pieces.
	 * @return An ArrayList structure contains all cells that the piece can move to.
	 */
    public ArrayList<Cell> posMove(Cell pos[][]) {       
		possiblemoves.clear();
		
    	int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 }; 
		int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 }; 
		int posx = 0;
		int posy = 0;
		
		for (int i = 0; i < 8; i++) {

			posx = this.x + X[i]; 
			posy = this.y + Y[i];
			if ((8 > posx && posx >= 0) && (8 > posy && posy >= 0)){
				
				if (pos[posx][posy].getPiece() == null || pos[posx][posy].getPiece().getColor() != this.getColor())
					possiblemoves.add(pos[posx][posy]);
			}
		}
		return possiblemoves;
    }
}
