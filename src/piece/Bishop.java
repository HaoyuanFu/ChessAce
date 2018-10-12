package piece;
import java.util.ArrayList;
import board.Cell;

public class Bishop extends Piece{
	private int x;
	private int y;
	
	public Bishop(String i,String p,int c, int x, int y)
	{
		setId(i);
		setPath(p);
		setColor(c);
		setX(x);
		setY(y);
	}

		
	
	public Bishop(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}


	
	public ArrayList<Cell> posmove(Cell position[][])
	{

		possiblemoves.clear();
		
		
		int tempx = this.x+1;
		int tempy = this.y-1;
		while(tempx < 8 && tempy >= 0)
		{
			if(position[tempx][tempy].getPiece() == null){
				possiblemoves.add(position[tempx][tempy]);
			}
			else if(position[tempx][tempy].getPiece().getcolor() == this.getColor())
				break;
			else{
				possiblemoves.add(position[tempx][tempy]);
				break;
			}

			tempx++;
			tempy--;
		}


		tempx = this.x-1;
		tempy = this.y+1;
		while(tempx >= 0 && tempy < 8)
		{
			if(position[tempx][tempy].getPiece()==null)
				possiblemoves.add(position[tempx][tempy]);
			else if(position[tempx][tempy].getPiece().getcolor()==this.getColor())
				break;
			else
			{
				possiblemoves.add(position[tempx][tempy]);
				break;
			}
			tempx--;
			tempy++;
		}


		tempx = this.x-1;
		tempy = this.y-1;
		while(tempx >= 0 && tempy>=0)
		{
			if(position[tempx][tempy].getPiece()==null)
				possiblemoves.add(position[tempx][tempy]);
			else if(position[tempx][tempy].getPiece().getcolor()==this.getColor())
				break;
			else
			{
				possiblemoves.add(position[tempx][tempy]);
				break;
			}
			tempx--;
			tempy--;
		}


		tempx = this.x+1;
		tempy = this.y+1;
		while(tempx<8&&tempy<8)
		{
			if(position[tempx][tempy].getPiece()==null)
				possiblemoves.add(position[tempx][tempy]);
			else if(position[tempx][tempy].getPiece().getcolor()==this.getColor())
				break;
			else
			{
				possiblemoves.add(position[tempx][tempy]);
				break;
			}
			tempx++;
			tempy++;
		}
		return possiblemoves;
	}
}