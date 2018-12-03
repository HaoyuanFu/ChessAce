package piece;

import java.util.ArrayList;

import board.Cell;

public class King extends Piece {
	private boolean notCheckmated;

	public King(String Id, String p, int x, int y, int c) {
		setId(Id);
		setPath(p);
		setX(x);
		setY(y);
		setColor(c);
		this.neverMoved = true;
		this.notCheckmated = true;
	}

	public boolean isMoved() {
		return !neverMoved;
	}

	public boolean isCheckmated() {
		return !notCheckmated;
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

	// Basic Movement, king can be move or attack cells one unit beside him in any
	// directions.
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
				if(pos[this.x][this.y + 3].getPiece() != null) {
				if (pos[this.x][this.y + i].getPiece() != null || !(pos[this.x][this.y + 3].getPiece() instanceof Rook) || pos[this.x][this.y + 3].getPiece().isMoved())
					temp0 = false;
				}else{
					temp0 = false;
				}
			}
			for (int j = 1; j <= 3; j++) {
				if(pos[this.x][this.y - 4].getPiece() != null) {
					if (pos[this.x][this.y - j].getPiece() != null || !(pos[this.x][this.y - 4].getPiece() instanceof Rook) || pos[this.x][this.y - 4].getPiece().isMoved())
						temp1 = false;
				}else
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
