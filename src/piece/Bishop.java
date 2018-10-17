package piece;
import java.util.ArrayList;
import board.Cell;

public class Bishop extends Piece{
	
	public Bishop(String Id, String p, int x, int y, int c)
	{
		setId(Id);
		setPath(p);
		setColor(c);
		setX(x);
		setY(y);
	}

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