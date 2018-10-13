package board;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
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
	
	public Main(String[] args) {
		//variable initialization
		Cell cell;
		wrk1 = new Rook("White_Rook.png", 0, 0, 0);
		wrk2 = new Rook("White_Rook.png", 0, 7, 0);
		brk1 = new Rook("Black_Rook.png", 7, 0, 1);
		brk2 = new Rook("Black_Rook.png", 7, 7, 1);
		wknt1=new Knight("White_Knight.png", 0, 1, 0);
		wknt2=new Knight("White_Knight.png", 0, 6, 0);
		bknt1=new Knight("Black_Knight.png", 7, 1, 1);
		bknt2=new Knight("Black_Knight.png", 7, 6, 1);
		wbsp1=new Bishop("White_Bishop.png", 0, 2, 0);
		wbsp2=new Bishop("White_Bishop.png", 0, 5, 0);
		bbsp1=new Bishop("Black_Bishop.png", 7, 2, 1);
		bbsp2=new Bishop("Black_Bishop.png", 7, 5, 1);
		wqn1=new Queen("White_Queen.png", 0, 3, 0);
		bqn1=new Queen("Black_Queen.png", 7, 3, 1);
		wkg1=new King("White_King.png", 0, 4, 0);
		bkg1=new King("Black_King.png", 7, 4, 1);
		wpn1=new Pawn[8];
		bpn1=new Pawn[8];
		
		Cell[][] pos = new Cell[8][8];
		for(int i = 0; i < 8; i++) {
			wpn1[i] = new Pawn("White_Pawn.png", 1, i, 0); 
			bpn1[i] = new Pawn("Black_Pawn.png", 6, i, 1);
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
	
	public void move(int x0, int y0, int x1, int y1) {
		Cell previous = pos[x0][y0];
		Cell current = pos[x1][y1];
		if(previous.getPiece() != null) {
			ArrayList<Cell> destinations = previous.getPiece().posMove(pos);
			if(current.getPiece() == null ) {
				current.setPiece(previous.getPiece());
				previous.removePiece();
			}

		}
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
