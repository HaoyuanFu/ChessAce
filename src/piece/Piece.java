package piece;

import java.util.ArrayList;

import board.Cell;

public abstract class Piece implements Cloneable {

	// Member Variables
	protected boolean neverMoved;
	private int color;
	private String Id;
	private String path;
	protected ArrayList<Cell> possiblemoves = new ArrayList<Cell>(); // Protected (access from child classes)

	public abstract ArrayList<Cell> posMove(Cell[][] pos); // Abstract Function. Must be overridden

	protected int x;
	protected int y;

	public void setMove() {
		this.neverMoved = false;
	}

	public boolean isMoved() {
		return !neverMoved;
	}

	public void setX(int x) {
		if (0 <= x && x <= 7)
			this.x = x;
	}

	public void setY(int y) {
		if (0 <= y && y <= 7)
			this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	// Path Setter
	public void setPath(String path) {
		this.path = path;
	}

	// Path Setter
	public void setId(String id) {
		this.Id = id;
	}

	public String getId() {
		return this.Id;
	}

	// Color Setter
	public void setColor(int c) {
		this.color = c;
	}

	// Path getter
	public String getPath() {
		return path;
	}

	// Color Getter
	public int getColor() {
		return this.color;
	}

	// Function to return the a "shallow" copy of the object. The copy has exact
	// same variable value but different reference
	public Piece getcopy() throws CloneNotSupportedException {
		return (Piece) this.clone();
	}

	public boolean isKingInDanger(Cell[][] pos) {
		// Checking for attact form the Pawn of opposite color
		if (x + 1 >= 0 && x + 1 < 8 && y + 1 >= 0 && y + 1 < 8 && pos[x + 1][y + 1].getPiece() instanceof Pawn
				&& pos[x + 1][y + 1].getPiece().getColor() != this.getColor()&&!(pos[x][y].getPiece().getId().equals("n")))
			return true;
		if (x + 1 >= 0 && x + 1 < 8 && y - 1 >= 0 && y - 1 < 8 && pos[x + 1][y - 1].getPiece() instanceof Pawn
				&& pos[x + 1][y - 1].getPiece().getColor() != this.getColor()&&!(pos[x][y].getPiece().getId().equals("n")))
			return true;
		if (x - 1 >= 0 && x - 1 < 8 && y - 1 >= 0 && y - 1 < 8 && pos[x - 1][y - 1].getPiece() instanceof Pawn
				&& pos[x - 1][y - 1].getPiece().getColor() != this.getColor()&&!(pos[x][y].getPiece().getId().equals("n")))
			return true;
		if (x - 1 >= 0 && x - 1 < 8 && y + 1 >= 0 && y + 1 < 8 && pos[x - 1][y + 1].getPiece() instanceof Pawn
				&& pos[x - 1][y + 1].getPiece().getColor() != this.getColor()&&!(pos[x][y].getPiece().getId().equals("n")))
			return true;
		// Checking for attact form left, right, up and down, if there is a queen or
		// rook with different color and without any other piece infront of it, return
		// true, otherwise return false

		for (int i = x + 1; i < 8; i++) {
			if (pos[i][y].getPiece() == null)
				continue;
			else if (pos[i][y].getPiece().getColor() == this.getColor())
				break;
			else {
				if ((pos[i][y].getPiece() instanceof Rook) || (pos[i][y].getPiece() instanceof Queen))
					return true;
				else
					break;
			}
		}

		for (int i = y + 1; i < 8; i++) {
			if (pos[x][i].getPiece() == null)
				continue;
			else if (pos[x][i].getPiece().getColor() == this.getColor())
				break;
			else {
				if ((pos[x][i].getPiece() instanceof Rook) || (pos[x][i].getPiece() instanceof Queen))
					return true;
				else
					break;
			}
		}
		for (int i = x - 1; i >= 0; i--) {
			if (pos[i][y].getPiece() == null)
				continue;
			else if (pos[i][y].getPiece().getColor() == this.getColor())
				break;
			else {
				if ((pos[i][y].getPiece() instanceof Rook) || (pos[i][y].getPiece() instanceof Queen))
					return true;
				else
					break;
			}
		}
		for (int i = y - 1; i >= 0; i--) {
			if (pos[x][i].getPiece() == null)
				continue;
			else if (pos[x][i].getPiece().getColor() == this.getColor())
				break;
			else {
				if ((pos[x][i].getPiece() instanceof Rook) || (pos[x][i].getPiece() instanceof Queen)) {
					return true;
				} else
					break;
			}
		}
		// Checking for attack from diagnoal direction with bishop or queen, if there is
		// any piece with same color or not bishop or queen, return false, otherwise
		// return true
		int posy = y;
		for (int posx = x + 1; posx < 8; posx++) {
			if (posy < 7) {
				posy++;
			} else {
				break;
			}
			if (pos[posx][posy].getPiece() == null)
				continue;
			else if (pos[posx][posy].getPiece().getColor() == this.getColor())
				break;
			else if (pos[posx][posy].getPiece() instanceof Bishop || pos[posx][posy].getPiece() instanceof Queen)
				return true;
			else
				break;
		}

		posy = y;
		for (int posx = x - 1; posx >= 0; posx--) {
			if (posy < 7)
				posy++;
			else
				break;
			if (pos[posx][posy].getPiece() == null)
				continue;
			else if (pos[posx][posy].getPiece().getColor() == this.getColor())
				break;
			else {
				if (pos[posx][posy].getPiece() instanceof Bishop || pos[posx][posy].getPiece() instanceof Queen)
					return true;
				else
					break;
			}
		}

		posy = y;
		for (int posx = x + 1; posx < 8; posx++) {
			if (posy > 0)
				posy--;
			else
				break;
			if (pos[posx][posy].getPiece() == null)
				continue;
			else if (pos[posx][posy].getPiece().getColor() == this.getColor())
				break;
			else {
				if (pos[posx][posy].getPiece() instanceof Bishop || pos[posx][posy].getPiece() instanceof Queen)
					return true;
				else
					break;
			}
		}

		posy = y;
		for (int posx = x - 1; posx >= 0; posx--) {
			if (posy > 0)
				posy--;
			else
				break;
			if (pos[posx][posy].getPiece() == null)
				continue;
			else if (pos[posx][posy].getPiece().getColor() == this.getColor())
				break;
			else {
				if (pos[posx][posy].getPiece() instanceof Bishop || pos[posx][posy].getPiece() instanceof Queen)
					return true;
				else
					break;
			}
		}

		// Check for attack from knight of opposite color
		if (x + 1 >= 0 && x + 1 < 8 && y + 2 >= 0 && y + 2 < 8 && pos[x + 1][y + 2].getPiece() instanceof Knight
				&& pos[x + 1][y + 2].getPiece().getColor() != this.getColor())
			return true;
		if (x - 1 >= 0 && x - 1 < 8 && y + 2 >= 0 && y + 2 < 8 && pos[x - 1][y + 2].getPiece() instanceof Knight
				&& pos[x - 1][y + 2].getPiece().getColor() != this.getColor())
			return true;
		if (x + 1 >= 0 && x + 1 < 8 && y - 2 >= 0 && y - 2 < 8 && pos[x + 1][y - 2].getPiece() instanceof Knight
				&& pos[x + 1][y - 2].getPiece().getColor() != this.getColor())
			return true;
		if (x - 1 >= 0 && x - 1 < 8 && y - 2 >= 0 && y - 2 < 8 && pos[x - 1][y - 2].getPiece() instanceof Knight
				&& pos[x - 1][y - 2].getPiece().getColor() != this.getColor())
			return true;
		if (x + 2 >= 0 && x + 2 < 8 && y + 1 >= 0 && y + 1 < 8 && pos[x + 2][y + 1].getPiece() instanceof Knight
				&& pos[x + 2][y + 1].getPiece().getColor() != this.getColor())
			return true;
		if (x + 2 >= 0 && x + 2 < 8 && y - 1 >= 0 && y - 1 < 8 && pos[x + 2][y - 1].getPiece() instanceof Knight
				&& pos[x + 2][y - 1].getPiece().getColor() != this.getColor())
			return true;
		if (x - 2 >= 0 && x - 2 < 8 && y - 1 >= 0 && y - 1 < 8 && pos[x - 2][y - 1].getPiece() instanceof Knight
				&& pos[x - 2][y - 1].getPiece().getColor() != this.getColor())
			return true;
		if (x - 2 >= 0 && x - 2 < 8 && y + 1 >= 0 && y + 1 < 8 && pos[x - 2][y + 1].getPiece() instanceof Knight
				&& pos[x - 2][y + 1].getPiece().getColor() != this.getColor())
			return true;
		// Check for attack from King of opposite color
		/*if (x + 1 >= 0 && x + 1 < 8 && y + 1 >= 0 && y + 1 < 8 && pos[x + 1][y + 1].getPiece() instanceof King
				&& pos[x + 1][y + 1].getPiece().getColor() != this.getColor())
			if (checkSecond(x + 1, y + 1, pos))
				return true;
		if (x - 1 >= 0 && x - 1 < 8 && y - 1 >= 0 && y - 1 < 8 && pos[x - 1][y - 1].getPiece() instanceof King
				&& pos[x - 1][y - 1].getPiece().getColor() != this.getColor())
			if (checkSecond(x - 1, y - 1, pos))
				return true;
		if (x + 1 >= 0 && x + 1 < 8 && y - 1 >= 0 && y - 1 < 8 && pos[x + 1][y - 1].getPiece() instanceof King
				&& pos[x + 1][y - 1].getPiece().getColor() != this.getColor())
			if (checkSecond(x + 1, y - 1, pos))
				return true;
		if (x - 1 >= 0 && x - 1 < 8 && y + 1 >= 0 && y + 1 < 8 && pos[x - 1][y + 1].getPiece() instanceof King
				&& pos[x - 1][y + 1].getPiece().getColor() != this.getColor())
			if (checkSecond(x - 1, y + 1, pos))
				return true;
		if (y + 1 >= 0 && y + 1 < 8 && pos[x][y + 1].getPiece() instanceof King
				&& pos[x][y + 1].getPiece().getColor() != this.getColor())
			if (checkSecond(x, y + 1, pos))
				return true;
		if (x + 1 >= 0 && x + 1 < 8 && pos[x + 1][y].getPiece() instanceof King
				&& pos[x + 1][y].getPiece().getColor() != this.getColor())
			if (checkSecond(x + 1, y, pos))
				return true;
		if (x - 1 >= 0 && x - 1 < 8 && pos[x - 1][y].getPiece() instanceof King
				&& pos[x - 1][y].getPiece().getColor() != this.getColor())
			if (checkSecond(x - 1, y, pos))
				return true;
		if (y - 1 >= 0 && y - 1 < 8 && pos[x][y - 1].getPiece() instanceof King
				&& pos[x][y - 1].getPiece().getColor() != this.getColor())
			if (checkSecond(x, y - 1, pos))
				return true;*/
		return false;
	}

	/*private boolean checkSecond(int x0, int y0, Cell[][] pos) {
		Piece p;
		p = pos[x][y].getPiece();
		pos[x][y].removePiece();
		pos[x][y].setPiece(pos[x0][y0].getPiece());
		pos[x0][y0].removePiece();
		if (pos[x][y].getPiece().isKingInDanger(pos)) {
			System.out.println("danger");
			pos[x0][y0].setPiece(pos[x][y].getPiece());
			pos[x][y].removePiece();
			if (p != null) {
				pos[x][y].setPiece(p);
			}
			return false;
		}
		pos[x0][y0].setPiece(pos[x][y].getPiece());
		pos[x][y].removePiece();
		if (p != null) {
			pos[x][y].setPiece(p);
		}
		return true;
	}*/
}