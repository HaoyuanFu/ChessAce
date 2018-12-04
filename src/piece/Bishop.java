package piece;
import java.util.ArrayList;

import board.Cell;
/**
 * This is the Bishop Class inherited from the abstract Piece class
 *	
 * @author Morgan Cui
 * @version     1.0                 (current version number of program)
 * @since       0.0          
 * 
 */ 
public class Bishop extends Piece{
	/**
	 * Constructor of Bishop Instance, initializing its position, color and corresponding PNG picture.
	 *
	 * @param id used for identification
	 * @param p PNG picture path
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param c 1: white, -1: black
	 */
	public Bishop(String Id, String p, int x, int y, int c)
	{
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
	 * Bishop moves in all diagonal directions with MAXIMUM length.
	 * </p>
	 * @param pos board state about positions of pieces.
	 * @return An ArrayList structure contains all cells that the piece can move to.
	 */
	public ArrayList<Cell> posMove(Cell pos[][])

	{

		possiblemoves.clear();
		
		
		int tempx = this.x+1;
		int tempy = this.y-1;
		while(tempx < 8 && tempy >= 0)
		{
			if(pos[tempx][tempy].getPiece() == null){
				possiblemoves.add(pos[tempx][tempy]);
			}

			else if(pos[tempx][tempy].getPiece().getColor() == this.getColor())

				break;
			else{
				possiblemoves.add(pos[tempx][tempy]);
				break;
			}

			tempx++;
			tempy--;
		}


		tempx = this.x-1;
		tempy = this.y+1;
		while(tempx >= 0 && tempy < 8)
		{

			if(pos[tempx][tempy].getPiece()==null)
				possiblemoves.add(pos[tempx][tempy]);
			else if(pos[tempx][tempy].getPiece().getColor()==this.getColor())

				break;
			else
			{
				possiblemoves.add(pos[tempx][tempy]);
				break;
			}
			tempx--;
			tempy++;
		}


		tempx = this.x-1;
		tempy = this.y-1;
		while(tempx >= 0 && tempy>=0)
		{

			if(pos[tempx][tempy].getPiece()==null)
				possiblemoves.add(pos[tempx][tempy]);
			else if(pos[tempx][tempy].getPiece().getColor()==this.getColor())

				break;
			else
			{
				possiblemoves.add(pos[tempx][tempy]);
				break;
			}
			tempx--;
			tempy--;
		}


		tempx = this.x+1;
		tempy = this.y+1;
		while(tempx<8&&tempy<8)
		{

			if(pos[tempx][tempy].getPiece()==null)
				possiblemoves.add(pos[tempx][tempy]);
			else if(pos[tempx][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				possiblemoves.add(pos[tempx][tempy]);
				break;
			}
			tempx++;
			tempy++;
		}
		return possiblemoves;
	}
}