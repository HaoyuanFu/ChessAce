package piece;

import java.util.ArrayList;

import board.Cell;

public class King extends Piece{
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

	public boolean isKingInDanger(Cell[][] pos){
		//Checking for attact form the Pawn of opposite color

  		if(x+1>=0&&x+1<8&&y+1>=0&&y+1<8&&pos[x+1][y+1].getPiece() instanceof Pawn&&pos[x+1][y+1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x+1>=0&&x+1<8&&y-1>=0&&y-1<8&&pos[x+1][y-1].getPiece() instanceof Pawn&&pos[x+1][y-1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x-1>=0&&x-1<8&&y-1>=0&&y-1<8&&pos[x-1][y-1].getPiece() instanceof Pawn&&pos[x-1][y-1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x-1>=0&&x-1<8&&y+1>=0&&y+1<8&&pos[x-1][y+1].getPiece() instanceof Pawn&&pos[x-1][y+1].getPiece().getColor()!= this.getColor())
    		return true;
    	//Checking for attact form left, right, up and down, if there is a queen or rook with different color and without any other piece infront of it, return true, otherwise return false

    	for(int i=x+1; i<8; i++){
    		if(pos[i][y].getPiece()==null)
    			continue;
    		else if(pos[i][y].getPiece().getColor()==this.getColor())
    			break;
    		else{
    			if((pos[i][y].getPiece() instanceof Rook)||(pos[i][y].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}

    	for(int i=y+1; i<8; i++){
    		if(pos[x][i].getPiece()==null)
    			continue;
    		else if(pos[x][i].getPiece().getColor()==this.getColor())
    			break;
    		else{
    			if((pos[x][i].getPiece() instanceof Rook)||(pos[x][i].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=x-1; i>=0; i--){
    		if(pos[i][y].getPiece()==null)
    			continue;
    		else if(pos[i][y].getPiece().getColor()==this.getColor())
    			break;
    		else{
    			if((pos[i][y].getPiece() instanceof Rook)||(pos[i][y].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=y-1; i>=0; i--){
    		if(pos[x][i].getPiece()==null)
    			continue;
    		else if(pos[x][i].getPiece().getColor()==this.getColor())
    			break;
    		else{
    			if((pos[x][i].getPiece() instanceof Rook)||(pos[x][i].getPiece() instanceof Queen)) {
    				return true;
    			}
    			else
    				break;
    		}
    	}
    	//Checking for attack from diagnoal direction with bishop or queen, if there is any piece with same color or not bishop or queen, return false, otherwise return true
		int posy=y;
		for(int posx=x+1; posx<8; posx++){
			if(posy<7) { 
				posy++;
			}
			else {
				break;
			}
    		if(pos[posx][posy].getPiece()==null) 
    			continue;
    		else if(pos[posx][posy].getPiece().getColor()==this.getColor()) 
    			break;
    		else if(pos[posx][posy].getPiece() instanceof Bishop||pos[posx][posy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
    	}
		
		posy=y;
    	for(int posx=x-1; posx>=0; posx--){
    		if(posy<7) 
    			posy++;
    		else
    			break;
    		if(pos[posx][posy].getPiece()==null)
    			continue;
    		else if(pos[posx][posy].getPiece().getColor()==this.getColor())
				break;
			else{
    			if(pos[posx][posy].getPiece() instanceof Bishop||pos[posx][posy].getPiece() instanceof Queen)
    				return true;
    			else
					break;
				}
    	}
		
		posy=y;
    	for(int posx=x+1; posx<8; posx++){
    		if(posy>0) 
    			posy--;
    		else
    			break;
			if(pos[posx][posy].getPiece()==null)
    			continue;
    		else if(pos[posx][posy].getPiece().getColor()==this.getColor())
				break;
			else{
    			if(pos[posx][posy].getPiece() instanceof Bishop||pos[posx][posy].getPiece() instanceof Queen)
    				return true;
    			else
					break;
				}	
    	}
		
		posy=y;
    	for(int posx=x-1; posx>=0; posx--){
    		if(posy>0) 
				posy--;
    		else
    			break;
    		if(pos[posx][posy].getPiece()==null)
    			continue;
    		else if(pos[posx][posy].getPiece().getColor()==this.getColor())
				break;
			else{
    			if(pos[posx][posy].getPiece() instanceof Bishop||pos[posx][posy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
    			}
    	}

    	//Check for attack from knight of opposite color
    	if(x+1>=0&&x+1<8&&y+2>=0&&y+2<8&&pos[x+1][y+2].getPiece() instanceof Knight&&pos[x+1][y+2].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x-1>=0&&x-1<8&&y+2>=0&&y+2<8&&pos[x-1][y+2].getPiece() instanceof Knight&&pos[x-1][y+2].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x+1>=0&&x+1<8&&y-2>=0&&y-2<8&&pos[x+1][y-2].getPiece() instanceof Knight&&pos[x+1][y-2].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x-1>=0&&x-1<8&&y-2>=0&&y-2<8&&pos[x-1][y-2].getPiece() instanceof Knight&&pos[x-1][y-2].getPiece().getColor()!= this.getColor())
    		return true;
    	if(x+2>=0&&x+2<8&&y+1>=0&&y+1<8&&pos[x+2][y+1].getPiece() instanceof Knight&&pos[x+2][y+1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x+2>=0&&x+2<8&&y-1>=0&&y-1<8&&pos[x+2][y-1].getPiece() instanceof Knight&&pos[x+2][y-1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x-2>=0&&x-2<8&&y-1>=0&&y-1<8&&pos[x-2][y-1].getPiece() instanceof Knight&&pos[x-2][y-1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x-2>=0&&x-2<8&&y+1>=0&&y+1<8&&pos[x-2][y+1].getPiece() instanceof Knight&&pos[x-2][y+1].getPiece().getColor()!= this.getColor())
    		return true;
    	//Check for attact from King of opposite color
    	if(x+1>=0&&x+1<8&&y+1>=0&&y+1<8&&pos[x+1][y+1].getPiece() instanceof King&&pos[x+1][y+1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x-1>=0&&x-1<8&&y-1>=0&&y-1<8&&pos[x-1][y-1].getPiece() instanceof King&&pos[x-1][y-1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x+1>=0&&x+1<8&&y-1>=0&&y-1<8&&pos[x+1][y-1].getPiece() instanceof King&&pos[x+1][y-1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x-1>=0&&x-1<8&&y+1>=0&&y+1<8&&pos[x-1][y+1].getPiece() instanceof King&&pos[x-1][y+1].getPiece().getColor()!= this.getColor())
    		return true;
    	if(y+1>=0&&y+1<8&&pos[x][y+1].getPiece() instanceof King&&pos[x][y+1].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x+1>=0&&x+1<8&&pos[x+1][y].getPiece() instanceof King&&pos[x+1][y].getPiece().getColor()!= this.getColor())
    		return true;
   		if(x-1>=0&&x-1<8&&pos[x-1][y].getPiece() instanceof King&&pos[x-1][y].getPiece().getColor()!= this.getColor())
    		return true;
   		if(y-1>=0&&y-1<8&&pos[x][y-1].getPiece() instanceof King&&pos[x-2][y+1].getPiece().getColor()!= this.getColor())
    		return true;
		return false;
	}

	//Basic Movement, king can be move or attack cells one unit beside him in any directions.
	@Override
	public ArrayList<Cell> posMove(Cell[][] pos) {
		possiblemoves.clear();
		if(x < 7 && y < 7 && (pos[x+1][y+1].getPiece() == null || pos[x+1][y+1].getPiece().getColor() != this.getColor())) 
			possiblemoves.add(pos[x+1][y+1]);
		if(y < 7 && (pos[x][y+1].getPiece() == null || pos[x][y+1].getPiece().getColor() != this.getColor())) 
			possiblemoves.add(pos[x][y+1]);
		if(x > 0 && y < 7 && (pos[x-1][y+1].getPiece() == null || pos[x-1][y+1].getPiece().getColor() != this.getColor())) 
			possiblemoves.add(pos[x-1][y+1]);
		if(x < 7 && (pos[x+1][y].getPiece() == null || pos[x+1][y].getPiece().getColor() != this.getColor())) 
			possiblemoves.add(pos[x+1][y]);
		if(x > 0 && (pos[x-1][y].getPiece() == null || pos[x-1][y].getPiece().getColor() != this.getColor()))  
			possiblemoves.add(pos[x-1][y]);
		if(x < 7 && y > 0 && (pos[x+1][y-1].getPiece() == null || pos[x+1][y-1].getPiece().getColor() != this.getColor()))  
			possiblemoves.add(pos[x+1][y-1]);
		if(y > 0 && (pos[x][y-1].getPiece() == null || pos[x][y-1].getPiece().getColor() != this.getColor()))
			possiblemoves.add(pos[x][y-1]);
		if(x > 0 && y > 0 && (pos[x-1][y-1].getPiece() == null || pos[x-1][y-1].getPiece().getColor() != this.getColor())) 
			possiblemoves.add(pos[x-1][y-1]);
		
		
		//Castling:
		//Rule 1: Kind & Associating Rook shall not move before
		//Rule 2: Castle out of check is not allowed
		//Rule 3: Nothing shall be in between the Corresponding King and Rook
		if(neverMoved && notCheckmated) {
			boolean temp0 = true;
			boolean temp1 = true;
			for(int i = 1; i <= 2; i++) {
				if(pos[this.x][this.y + i].getPiece() != null)
					temp0 = false;
			}
			for(int j = 1; j <= 3; j++) {
				if(pos[this.x][this.y - j].getPiece() != null)
					temp1 = false;
			}
			if(temp0)
				possiblemoves.add(pos[this.x][this.y + 2]);
			if(temp1)
				possiblemoves.add(pos[this.x ][this.y - 2]);
		}

		return possiblemoves;
	}


}
