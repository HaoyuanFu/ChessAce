package piece;
import java.util.ArrayList;
import board.Cell;


public class Knight extends Piece{
	private int x;
	private int y;
	public Knight(String i,String p,int c, int x, int y) {
		setId(i);
		setPath(p);
		setColor(c);
		setX(x);
		setY(y);
	}
	
	public Knight(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}


    public ArrayList<Cell> posmove(Cell position[][]) {       
		possiblemoves.clear();
		
    	int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 }; 
		int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 }; 
		int posx, posy;
		
		for (int i = 0; i < 8; i++) { 
			posx = this.x + X[i]; 
			posy = this.y + Y[i];
		}
		
		if ((8 > posx && posx >= 0) && (8 > posy && posy >= 0))
			if (position[posx][posy].getpiece() == null || position[posx][posy].getpiece().getcolor() != this.getColor())
            	if (position[posx][posy].getpiece().ispossibledestination()) {
					possiblemoves.add(position[posx][posy]);
				}
		
		return possiblemoves;
    }
}