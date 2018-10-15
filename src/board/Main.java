package board;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

public class Main implements MouseListener {
	private Cell[][] pos;
	private static Rook wrk1, wrk2, brk1, brk2;
	private static King wkg1, bkg1;
	private static Queen wqn1, bqn1;
	private static Pawn[] wpn1, bpn1;
	private static Knight wknt1, wknt2, bknt1, bknt2;
	private static Bishop wbsp1, wbsp2, bbsp1, bbsp2;
	private boolean isCheckmate;
	private Cell fromCell;
	private Cell toCell; 
	private Cell previous;
	private Cell current;
	private int player; //1 = white, -1 = black
	
	public Main(String[] args) {
		//variable initialization
		isCheckmate = false;
		player = 1;
		Cell cell;
		wrk1 = new Rook("White_Rook.png", 0, 0, 1);
		wrk2 = new Rook("White_Rook.png", 0, 7, 1);
		brk1 = new Rook("Black_Rook.png", 7, 0, -1);
		brk2 = new Rook("Black_Rook.png", 7, 7, -1);
		wknt1=new Knight("White_Knight.png", 0, 1, 1);
		wknt2=new Knight("White_Knight.png", 0, 6, 1);
		bknt1=new Knight("Black_Knight.png", 7, 1, -1);
		bknt2=new Knight("Black_Knight.png", 7, 6, -1);
		wbsp1=new Bishop("White_Bishop.png", 0, 2, 1);
		wbsp2=new Bishop("White_Bishop.png", 0, 5, 1);
		bbsp1=new Bishop("Black_Bishop.png", 7, 2, -1);
		bbsp2=new Bishop("Black_Bishop.png", 7, 5, -1);
		wqn1=new Queen("White_Queen.png", 0, 3, 1);
		bqn1=new Queen("Black_Queen.png", 7, 3, -1);
		wkg1=new King("White_King.png", 0, 4, 1);
		bkg1=new King("Black_King.png", 7, 4, -1);
		wpn1=new Pawn[8];
		bpn1=new Pawn[8];
		
		Cell[][] pos = new Cell[8][8];
		for(int i = 0; i < 8; i++) {
			wpn1[i] = new Pawn("White_Pawn.png", 1, i, 1); 
			bpn1[i] = new Pawn("Black_Pawn.png", 6, i, -1);
		}
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{	
				cell = new Cell(i,j,null);
				if(i==0&&j==0)
					cell=new Cell(i,j,brk1);
				else if(i==0&&j==7)
					cell=new Cell(i,j,brk2);
				else if(i==7&&j==0)
					cell=new Cell(i,j,wrk1);
				else if(i==7&&j==7)
					cell=new Cell(i,j,wrk2);
				else if(i==0&&j==1)
					cell=new Cell(i,j,bknt1);
				else if (i==0&&j==6)
					cell=new Cell(i,j,bknt2);
				else if(i==7&&j==1)
					cell=new Cell(i,j,wknt1);
				else if (i==7&&j==6)
					cell=new Cell(i,j,wknt2);
				else if(i==0&&j==2)
					cell=new Cell(i,j,bbsp1);
				else if (i==0&&j==5)
					cell=new Cell(i,j,bbsp2);
				else if(i==7&&j==2)
					cell=new Cell(i,j,wbsp1);
				else if(i==7&&j==5)
					cell=new Cell(i,j,wbsp2);
				else if(i==0&&j==3)
					cell=new Cell(i,j,bqn1);
				else if(i==0&&j==4)
					cell=new Cell(i,j,bkg1);
				else if(i==7&&j==3)
					cell=new Cell(i,j,wqn1);
				else if(i==7&&j==4)
					cell=new Cell(i,j,wkg1);
				else if(i==1)
					cell=new Cell(i,j,bpn1[i]);
				else if(i==6)
					cell=new Cell(i,j,wpn1[i]);

				pos[i][j] = cell;
			}
		
			
	}
	
	
	public ArrayList<Cell> filterDestination(Piece p, ArrayList<Cell> a){
		ArrayList<Cell> newList = null;
		if(p instanceof King) {
			//return the list of posMove that will not cause a checkmate, using isKingInDanger
		}else {
			//return the list of posMove that will not cause the friendly king checkmated, using isKing InDanger and retrieveKing for king location.
		}
		return newList;		
	}
	
	public void move(Cell a, Cell b) {
		
	}
	
	public void castling(Cell a, Cell c) {
		
	}
	
	public King retrieveKing(int color) {
		return null;
	}
	
	public void transform(Cell c) {
		
	}
	
	public boolean checkmate(int color/*, Cell att*/) {
		return true;
	}
	
	public void gameOver() {
		
	}
	
	public void operation(int x, int y) {
		current = pos[x][y];
		ArrayList<Cell> filteredList = new ArrayList<Cell>();
		
		if(previous.getPiece() == null) {
			if(current.getPiece().getColor() == player) {
				previous = pos[x][y];
				current = null;
				return;
			}
			else {
				return;
			}
		}else{
			if(previous.getX() == current.getX() && previous.getY() == current.getY()) {
				return;
				// To do Deselect;
			}else if(current.getPiece() == null || (current.getPiece() != null && previous.getPiece().getColor() != current.getPiece().getColor())) {
				filteredList = filterDestination(previous.getPiece(), previous.getPiece().posMove(pos));
				((King) previous.getPiece()).removeCheckmate();
				if(filteredList.contains(current)) {
					if(previous.getPiece() instanceof King) {
						if(true /*condition to determine castling*/)
								castling(previous, current);
						else
							move(previous, current);
					}else {
						move(previous, current);
					}
					if((previous.getPiece() instanceof Pawn)) {
						transform(previous);
					}
					if(isCheckmate) {
						isCheckmate = false;
					}
					if(King.isKingInDanger(retrieveKing(player*-1), pos)) {
						isCheckmate= true;
						if(checkmate(player * -1))
							gameOver();
					}
				}
				player = player * -1;
			}else if(current.getPiece() != null && previous.getPiece().getColor() == current.getPiece().getColor()) {
				//Some operation that deselect the previous cell and select current cell instead.
				//Also, display the feasible moveTo Cells.
			}
		}
			
		//if FROM cell has piece.
		/*if(previous.getPiece() != null) {
			ArrayList<Cell> destinations = previous.getPiece().posMove(pos);
			
			//check if TO cell is a valid move
			//if TO cell is empty and no checkmate is happening
			if(current.getPiece() == null && previous.getPiece().posMove(pos).contains(current)) {
				if(isCheckmate == -1) {
					//if FROM cell associates with a king, no suicide movement is allowed.(handled by posMove())
					//if FROM cell associates with a king, castling should be considered
					if(previous.getPiece() instanceof King){
						
					}
					current.setPiece(previous.getPiece());
					previous.removePiece();
					current.getPiece().setX(x1);
					current.getPiece().setY(y1);				
					//needs to check if the movement will cause checkmate
					
					
					
					//TO DO
					
					
					
					
				}
				//if checkmate is happening, check if such movement will end the event
				else {
					
				}
			}
			//if TO cell is not empty, posMove will check if it is an enemy piece or friendly. 
			else if(current.getPiece() != null && previous.getPiece().posMove(pos).contains(current)) {
				if(isCheckmate == -1) {
				current.setPiece(previous.getPiece());
				previous.removePiece();
				current.getPiece().setX(x1);
				current.getPiece().setY(y1);
				}
				//check if checkmate will happen
				
				//TO DO
				
				
				//check if checkmate will end
				else {
					
				}
				
			}

		
		
		}*/
	}
	
	public boolean isKingInDanger(Cell previous, Cell current) {
		return isCheckmate;
	}
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
