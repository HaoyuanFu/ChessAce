package piece;
import java.util.ArrayList;
import board.Cell;


public class Knight extends Piece{
	public Knight(String Id, String p, int x, int y,  int c) {
		setId(Id);
		setPath(p);
		setColor(c);
		setX(x);
		setY(y);
	}

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
