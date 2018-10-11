package piece;

import board.Cell;

public class Rook {
	private int x;
	private int y;
	private boolean isMoved = true;
	
	public Rook(int x, int y) {
		setX(x);
		setY(y);
	}
	
	private void setX(int x) {
		this.x = x;
	}
	
	private void setY(int y) {
		this.y = y;
	}
	
	
	
	public boolean hasMoved() {
		return (!isMoved);
	}
	
	
}


