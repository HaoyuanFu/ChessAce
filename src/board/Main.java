package board;

import java.awt.event.MouseEvent;
import java.io.Console;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

public class Main implements MouseListener {
	private static Cell[][] pos;
	private static Rook wrk1, wrk2, brk1, brk2;
	private static King wkg1, bkg1;
	private static Queen wqn1, bqn1;
	private static Pawn[] wpn1, bpn1;
	private static Knight wknt1, wknt2, bknt1, bknt2;
	private static Bishop wbsp1, wbsp2, bbsp1, bbsp2;
	private static boolean isCheckmate;
	private static Cell previous;
	private static Cell current;
	private static int wqn;
	private static int bqn;
	private static int player; // 1 = white, -1 = black

	public static void Initial() {
		// variable initialization
		previous = new Cell(9, 9, null);
		current = new Cell(9, 9, null);
		isCheckmate = false;
		player = 1;
		Cell cell;
		wqn = 1;
		bqn = 1;
		wrk1 = new Rook("WRK1", "White_Rook.png", 7, 0, 1);
		wrk2 = new Rook("WRK2", "White_Rook.png", 7, 7, 1);
		brk1 = new Rook("BRK1", "Black_Rook.png", 0, 0, -1);
		brk2 = new Rook("BRK2", "Black_Rook.png", 0, 7, -1);
		wknt1 = new Knight("WKNT1", "White_Knight.png", 7, 1, 1);
		wknt2 = new Knight("WKNT2", "White_Knight.png", 7, 6, 1);
		bknt1 = new Knight("BKNT1", "Black_Knight.png", 0, 1, -1);
		bknt2 = new Knight("BKNT2", "Black_Knight.png", 0, 6, -1);
		wbsp1 = new Bishop("WBSP1", "White_Bishop.png", 7, 2, 1);
		wbsp2 = new Bishop("WBSP2", "White_Bishop.png", 7, 5, 1);
		bbsp1 = new Bishop("BBSP1", "Black_Bishop.png", 0, 2, -1);
		bbsp2 = new Bishop("BBSP2", "Black_Bishop.png", 0, 5, -1);
		wqn1 = new Queen("WQN1", "White_Queen.png", 7, 3, 1);
		bqn1 = new Queen("BQN1", "Black_Queen.png", 0, 3, -1);
		wkg1 = new King("WKG1", "White_King.png", 7, 4, 1);
		bkg1 = new King("BKG1", "Black_King.png", 0, 4, -1);
		wpn1 = new Pawn[8];
		bpn1 = new Pawn[8];

		pos = new Cell[8][8];
		for (int i = 0; i < 8; i++) {
			wpn1[i] = new Pawn("WPN" + i, "White_Pawn.png", 6, i, 1);
			bpn1[i] = new Pawn("BPN" + i, "Black_Pawn.png", 1, i, -1);
		}

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (i == 0 && j == 0)
					cell = new Cell(i, j, brk1);
				else if (i == 0 && j == 7)
					cell = new Cell(i, j, brk2);
				else if (i == 7 && j == 0)
					cell = new Cell(i, j, wrk1);
				else if (i == 7 && j == 7)
					cell = new Cell(i, j, wrk2);
				else if (i == 0 && j == 1)
					cell = new Cell(i, j, bknt1);
				else if (i == 0 && j == 6)
					cell = new Cell(i, j, bknt2);
				else if (i == 7 && j == 1)
					cell = new Cell(i, j, wknt1);
				else if (i == 7 && j == 6)
					cell = new Cell(i, j, wknt2);
				else if (i == 0 && j == 2)
					cell = new Cell(i, j, bbsp1);
				else if (i == 0 && j == 5)
					cell = new Cell(i, j, bbsp2);
				else if (i == 7 && j == 2)
					cell = new Cell(i, j, wbsp1);
				else if (i == 7 && j == 5)
					cell = new Cell(i, j, wbsp2);
				else if (i == 0 && j == 3)
					cell = new Cell(i, j, bqn1);
				else if (i == 0 && j == 4)
					cell = new Cell(i, j, bkg1);
				else if (i == 7 && j == 3)
					cell = new Cell(i, j, wqn1);
				else if (i == 7 && j == 4)
					cell = new Cell(i, j, wkg1);
				else if (i == 1)
					cell = new Cell(i, j, bpn1[j]);
				else if (i == 6)
					cell = new Cell(i, j, wpn1[j]);
				else
					cell = new Cell(i, j, null);

				pos[i][j] = cell;
			}

	}

	public static void move(Cell a, Cell b) {
		b.setPiece(a.getPiece());
		a.removePiece();
		b.getPiece().setX(b.getX());
		b.getPiece().setY(b.getY());
		previous = new Cell(9, 9, null);
		current = new Cell(9, 9, null);
	}

	public static void castling(Cell king, Cell kTo, Cell rook, Cell rTo) {
		move(king, kTo);
		move(rook, rTo);
	}

	public static int isCastling(Cell p, Cell c) {
		if (p.getPiece() instanceof King && !(p.getPiece().isMoved())) {
			if (p.getY() - c.getY() == 2) {
				if (pos[c.getX()][c.getY() - 2].getPiece() instanceof Rook
						&& !(pos[c.getX()][c.getY() - 2].getPiece().isMoved()))
					return 0;
			} else if (p.getY() - c.getY() == -2) {
				if (pos[c.getX()][c.getY() + 1].getPiece() instanceof Rook
						&& !(pos[c.getX()][c.getY() + 1].getPiece().isMoved()))
					return 1;
			} else
				return -1;
		}
		return -1;
	}

	public static King retrieveKing(int color) {
		if (color == 1) {
			return wkg1;
		} else if (color == -1)
			return bkg1;
		else
			return null;
	}

	public static void transform(Cell c) {
		if (c.getPiece().getColor() == 1) {
			c.setPiece(new Queen("WQN" + (1 + wqn), "White_Queen.png", c.getX(), c.getY(), 1));
			wqn += 1;
		} else {
			c.setPiece(new Queen("BQN" + (1 + bqn), "Black_Queen.png", c.getX(), c.getY(), -1));
			bqn += 1;
		}

	}

	public static boolean checkmate(int color/* , Cell att */) {
		return true;
	}

	public static void gameOver() {

	}

	public static ArrayList<Cell> filterDestination(Piece p, ArrayList<Cell> a){
		ArrayList<Cell> newList = new ArrayList<Cell>();
		Cell moveCell;
		Piece temp = null;
		if(p instanceof King) {
			// iterate via "iterator loop"
			int x = p.getX();
			int y = p.getY();
			pos[p.getX()][p.getY()].removePiece();
			Iterator<Cell> moveIterator = a.iterator();
			while (moveIterator.hasNext()) {
				moveCell = moveIterator.next();
				System.out.println(moveCell.getX() + ":::" + moveCell.getY());
				p.setX(moveCell.getX());
				p.setY(moveCell.getY());
				if(moveCell.getPiece() != null) {
					temp = moveCell.getPiece();
					moveCell.setPiece(p);
					if(!(((King)p).isKingInDanger(pos))){
						System.out.println("::");
						newList.add(moveCell);
					}
					moveCell.setPiece(temp);
				}else {
					moveCell.setPiece(p);
					if(!(((King)p).isKingInDanger(pos))){
						System.out.println("::");
						newList.add(moveCell);
					}
					moveCell.removePiece();
				}
			}
			p.setX(x);
			p.setY(y);
			pos[p.getX()][p.getY()].setPiece(p);
		}else{
			//return the list of posMove that will not cause the friendly king checkmated, using isKingInDanger and retrieveKing for king location.
			int x = p.getX();
			int y = p.getY();
			pos[p.getX()][p.getY()].removePiece();
			Iterator<Cell> moveIterator = a.iterator();
			while (moveIterator.hasNext()) {
				moveCell = moveIterator.next();
				//System.out.println(moveCell.getX() + "::" + moveCell.getY());
				p.setX(moveCell.getX());
				p.setY(moveCell.getY());
				if(moveCell.getPiece() != null) {
					temp = moveCell.getPiece();
					moveCell.setPiece(p);
					if(!(retrieveKing(p.getColor()).isKingInDanger(pos))){
						newList.add(moveCell);
						System.out.println("::");
						newList.add(moveCell);
					}
					moveCell.setPiece(temp);
				}else{
					moveCell.setPiece(p);
					if(!(retrieveKing(p.getColor()).isKingInDanger(pos))){
						newList.add(moveCell);
						System.out.println("::");
						newList.add(moveCell);
					}
					moveCell.removePiece();
				}
			}
			p.setX(x);
			p.setY(y);
			pos[x][y].setPiece(p);
		}
		return newList;
	}


	public static void operation(int x, int y) {
		current = pos[x][y];
		ArrayList<Cell> filteredList = new ArrayList<Cell>();
		if (previous.getPiece() == null) {
			if (current.getPiece() != null) {
				if (current.getPiece().getColor() != player)
					return;
			}
			previous = pos[x][y];

			current = null;
		} else {
			if (previous.getX() == current.getX() && previous.getY() == current.getY()) {
				return;
				// To do Deselect;
			} else if (current.getPiece() == null || (current.getPiece() != null
					&& previous.getPiece().getColor() != current.getPiece().getColor())) {
				filteredList = filterDestination(previous.getPiece(), previous.getPiece().posMove(pos));
				if (filteredList.contains(current)) {
					if (previous.getPiece() instanceof King) {
						if (isCastling(previous, current) == 0) {
							previous.getPiece().setMove();
							castling(previous, current, pos[previous.getX()][previous.getY() - 4],
									pos[previous.getX()][previous.getY() - 1]);
						} else if (isCastling(previous, current) == 1) {
							previous.getPiece().setMove();
							castling(previous, current, pos[previous.getX()][previous.getY() + 3],
									pos[previous.getX()][previous.getY() + 1]);
						} else {
							previous.getPiece().setMove();
							move(previous, current);
						}
					} else if ((previous.getPiece() instanceof Rook)) {
						previous.getPiece().setMove();
						move(previous, current);
					} else if ((previous.getPiece() instanceof Pawn)) {
						x = current.getX();
						y = current.getY();
						if ((current.getX() == 7 && previous.getPiece().getColor() == -1)
								|| (current.getX() == 0 && previous.getPiece().getColor() == 1)) {
							move(previous, current);
							transform(pos[x][y]);
						} else {
							move(previous, current);
						}
					} else {
						move(previous, current);
					}
					if (isCheckmate) {
						isCheckmate = false;
					}
					if (retrieveKing(player * -1).isKingInDanger(pos)) {
						System.out.println(":DDDDDDD");
						isCheckmate = true;
						// if(checkmate(player * -1))
						// gameOver();
					}
				}
				player = player * -1;
			} else if (current.getPiece() != null && previous.getPiece().getColor() == current.getPiece().getColor()) {
				// Some operation that deselect the previous cell and select current cell
				// instead.
				// Also, display the feasible moveTo Cells.
			}
		}
	}

	public static void display() {
		for (Cell[] x : pos) {
			for (Cell y : x) {
				if (y.getPiece() != null)
					System.out.format("%7s", y.getPiece().getId() + " ");
				else
					System.out.format("%7s", "EM ");
			}
			System.out.println();
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

	public static void main(String args[]) {
		Initial();
		operation(6, 4);
		operation(4, 4);

		operation(1, 2);
		operation(3, 2);
		operation(7, 6);
		operation(5, 5);
		operation(0, 1);
		operation(2, 2);
		operation(6, 3);
		operation(4, 3);
		operation(3, 2);
		operation(4, 3);
		operation(5, 5);
		operation(4, 3);
		operation(1, 4);
		operation(3, 4);
		operation(4, 3);
		operation(3, 1);
		operation(1,0);
		operation(2,0);
		operation(3,1);
		operation(2,3);
		operation(0,5);
		operation(2,3);
		operation(7,3);
		operation(2,3);
		operation(0,3);
		operation(2,5);
		operation(2,3);
		operation(7,3);
		operation(2,5);
		operation(2,6);
		operation(7,1);
		operation(5,2);
		operation(0,6);
		operation(1,4);
		operation(7,2);
		operation(5,4);
		operation(1,3);
		operation(3,3);
		operation(5,2);
		operation(3,3);
		operation(1,4);
		operation(3,3);
		operation(7,3);
		operation(3,3);
		operation(0,2);
		operation(2,4);
		operation(3,3);
		operation(6,3);
		operation(2,6);
		operation(4,4);
		operation(6,5);
		operation(5,5);
		operation(4,4);
		operation(4,7);
		operation(6,6);
		operation(5,6);
		operation(4,7);
		operation(1,4);
		operation(6,3);
		operation(6,5);
		operation(2,2);
		operation(4,3);
		operation(7,5);
		operation(5,3);
		operation(0,0);
		operation(0,3);
		operation(7,4);
		operation(7,6);
		operation(0,4);
		operation(0,6);
		operation(7,5);
		operation(7,4);
		operation(1,4);
		operation(2,5);
		operation(5,3);
		operation(4,4);
		operation(2,4);
		operation(3,5);
		operation(5,4);
		operation(4,3);
		operation(0,3);
		operation(4,3);
		operation(4,4);
		operation(1,1);
		operation(0,5);
		operation(0,3);
		operation(6,5);
		operation(5,4);
		operation(1,7);
		operation(2,7);
		operation(5,4);
		operation(3,4);
		operation(2,5);
		operation(2,1);
		operation(7,6);
		operation(7,7);
		operation(3,5);
		operation(5,7);
		operation(3,4);
		operation(3,7);
		operation(5,7);
		operation(4,6);
		operation(5,5);
		operation(4,6);
		operation(2,1);
		operation(1,1);
		operation(7,7);
		operation(7,6);
		operation(4,3);
		operation(6,3);
		operation(3,7);
		operation(5,7);
		operation(1,1);
		operation(2,1);
		operation(7,6);
		operation(7,7);
		operation(2,1);
		operation(2,2);
		operation(7,7);
		operation(7,6);
		operation(2,2);
		operation(3,2);
		operation(7,6);
		operation(7,7);
		operation(3,2);
		operation(3,3);
		operation(7,7);
		operation(7,6);
		operation(6,3);
		operation(6,2);
		operation(4,6);
		operation(3,6);
		operation(3,3);
		operation(3,2);
		operation(7,6);
		operation(7,7);
		operation(3,2);
		operation(2,2);
		operation(7,7);
		operation(7,6);
		operation(0,3);
		operation(6,3);
		display();
	}
}