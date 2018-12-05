package piece;

import java.util.ArrayList;

import board.Cell;

/**
 * This is the King Class inherited from the abstract Piece class
 * 
 * @author Xingjian Ke
 * @version 1.0 (current version number of program)
 * @since 0.0
 * 
 */
public class King extends Piece {
	private boolean notCheckmated;

	/**
	 * Constructor of King Instance, initializing its position, color and
	 * corresponding PNG picture. Set neverMoved and notChecked to true.
	 * 
	 * @param id used for identification
	 * @param p  PNG picture path
	 * @param x  x-coordinate
	 * @param y  y-coordinate
	 * @param c  1: white, -1: black
	 */
	public King(String Id, String p, int x, int y, int c) {
		setId(Id);
		setPath(p);
		setX(x);
		setY(y);
		setColor(c);
		this.neverMoved = true;
		this.notCheckmated = true;
	}

	/**
	 * check if King made any movement in this game.
	 *
	 * @return true if king moved before; false otherwise.
	 */
	public boolean isMoved() {
		return !neverMoved;
	}

	/**
	 * check if King is being checked.
	 *
	 * @return true if king is checked; false otherwise.
	 */
	public boolean isCheckmated() {
		return !notCheckmated;
	}

	/**
	 * set King's property to moved
	 */
	public void setMoved() {
		this.neverMoved = false;
	}

	/**
	 * set King's property to checked
	 */
	public void setCheckmate() {
		this.notCheckmated = false;
	}

	/**
	 * set King's property to not checked
	 */
	public void removeCheckmate() {
		this.notCheckmated = true;
	}

	// Basic Movement, king can be move or attack cells one unit beside him in any
	// directions.
	/**
	 * Check the possible movement of this piece. Iterate through possible path
	 * according to the game rules, and break the iteration when blocked by other
	 * pieces or hit the edge of the board. If iteration hits a enemy piece, include
	 * the corresponding cell for elimination
	 * <p>
	 * King moves in all diagonal directions with length 1.
	 * </p>
	 * 
	 * @param pos board state about positions of pieces.
	 * @return An ArrayList structure contains all cells that the piece can move to.
	 */
	@Override
	public ArrayList<Cell> posMove(Cell[][] pos) {
		possiblemoves.clear();
		if (x < 7 && y < 7
				&& (pos[x + 1][y + 1].getPiece() == null || pos[x + 1][y + 1].getPiece().getColor() != this.getColor()))
			possiblemoves.add(pos[x + 1][y + 1]);
		if (y < 7 && (pos[x][y + 1].getPiece() == null || pos[x][y + 1].getPiece().getColor() != this.getColor()))
			possiblemoves.add(pos[x][y + 1]);
		if (x > 0 && y < 7
				&& (pos[x - 1][y + 1].getPiece() == null || pos[x - 1][y + 1].getPiece().getColor() != this.getColor()))
			possiblemoves.add(pos[x - 1][y + 1]);
		if (x < 7 && (pos[x + 1][y].getPiece() == null || pos[x + 1][y].getPiece().getColor() != this.getColor()))
			possiblemoves.add(pos[x + 1][y]);
		if (x > 0 && (pos[x - 1][y].getPiece() == null || pos[x - 1][y].getPiece().getColor() != this.getColor()))
			possiblemoves.add(pos[x - 1][y]);
		if (x < 7 && y > 0
				&& (pos[x + 1][y - 1].getPiece() == null || pos[x + 1][y - 1].getPiece().getColor() != this.getColor()))
			possiblemoves.add(pos[x + 1][y - 1]);
		if (y > 0 && (pos[x][y - 1].getPiece() == null || pos[x][y - 1].getPiece().getColor() != this.getColor()))
			possiblemoves.add(pos[x][y - 1]);
		if (x > 0 && y > 0
				&& (pos[x - 1][y - 1].getPiece() == null || pos[x - 1][y - 1].getPiece().getColor() != this.getColor()))
			possiblemoves.add(pos[x - 1][y - 1]);

		// Castling:
		// Rule 1: Kind & Associating Rook shall not move before
		// Rule 2: Castle out of check is not allowed
		// Rule 3: Nothing shall be in between the Corresponding King and Rook
		if (neverMoved && notCheckmated) {
			boolean temp0 = true;
			boolean temp1 = true;
			for (int i = 1; i <= 2; i++) {
				if (pos[this.x][this.y + 3].getPiece() != null) {
					if (pos[this.x][this.y + i].getPiece() != null
							|| !(pos[this.x][this.y + 3].getPiece() instanceof Rook)
							|| pos[this.x][this.y + 3].getPiece().isMoved())
						temp0 = false;
				} else {
					temp0 = false;
				}
			}
			for (int j = 1; j <= 3; j++) {
				if (pos[this.x][this.y - 4].getPiece() != null) {
					if (pos[this.x][this.y - j].getPiece() != null
							|| !(pos[this.x][this.y - 4].getPiece() instanceof Rook)
							|| pos[this.x][this.y - 4].getPiece().isMoved())
						temp1 = false;
				} else
					temp1 = false;
			}
			if (temp0)
				possiblemoves.add(pos[this.x][this.y + 2]);
			if (temp1)
				possiblemoves.add(pos[this.x][this.y - 2]);
		}

		return possiblemoves;
	}

}
