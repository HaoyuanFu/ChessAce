package board;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;
import javax.swing.JSeparator;
import javax.swing.JSlider;


public class Main extends JFrame implements MouseListener {
	private Cell[][] pos;

	private final int Height = 700;
	private final int Width = 1110;

	private Rook wrk1, wrk2, brk1, brk2;
	private King wkg1, bkg1;
	private Queen wqn1, bqn1;
	private Pawn[] wpn1, bpn1;
	private Knight wknt1, wknt2, bknt1, bknt2;
	private Bishop wbsp1, wbsp2, bbsp1, bbsp2;
	private boolean isCheckmate;
	private Cell previous;
	private Cell current;
	private Cell empty;
	private int wqn;
	private int bqn;
	private int player; // 1 = white, -1 = black
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel[][] chessBoardSquares = new JPanel[8][8];
	private JPanel chessBoard;
	private final JLabel message = new JLabel("ChessAce is ready to play!");
	private ArrayList<Cell> filteredList = new ArrayList<Cell>();
	private final String COLS = "ABCDEFGH";
	private ArrayList<Cell> attacker;
	private Cell temp;
	private ArrayList<Piece> whiteP;
	private ArrayList<Piece> blackP;
	
	private JLabel label;
	private JPanel displayTime;
	private JSlider timeSlider;
	private countDownTimer timer;
	public static int timeremaining=60;

	private final JPanel welcomePage = new JPanel(new BorderLayout(3, 3));

	public Main() throws IOException {
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		JPanel timerpan = new JPanel();
		
		
		
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		gui.add(timerpan, BorderLayout.LINE_END);

		JButton New = new JButton("New");
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Initial();
			}
		});
		
		tools.add(New);

		tools.addSeparator();

		tools.add(new JButton("Pause")); // TODO - add functionality!
		tools.addSeparator();

		JButton surrender = new JButton("Resign");
		surrender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player = player * -1;
				gameOver();
			}
		});
		tools.add(surrender); // TODO - add functionality!
		tools.addSeparator();
		
		
		timeSlider = new JSlider();
		//implementation of time slider
		timeSlider.setMinimum(1);
		timeSlider.setMaximum(15);
		timeSlider.setValue(1);
		timeSlider.setMajorTickSpacing(2);
		timeSlider.setPaintLabels(true);
		timeSlider.setPaintTicks(true);
		timeSlider.addChangeListener(new TimeChange());
		JLabel setTime=new JLabel("Set Timer(in mins):"); 
		JPanel slider = new JPanel(new GridLayout(3,3));
	    slider.add(setTime, BorderLayout.PAGE_START);
	    slider.add(timeSlider,BorderLayout.PAGE_END);
	    welcomePage.add(slider, BorderLayout.LINE_END);
	    
	    //implementation for display timer
	    label= new JLabel("Time Starts now", JLabel.CENTER);
	    displayTime = new JPanel(new FlowLayout());
	    displayTime.add(label);
		timer = new countDownTimer(label);
		timer.start();
		
		timerpan.add(displayTime,BorderLayout.PAGE_END);
		timerpan.add(message,BorderLayout.PAGE_START);
		timerpan.setPreferredSize(new Dimension(200,400));
		
		chessBoard = new JPanel(new GridLayout(8, 8));
		chessBoard.setMinimumSize(new Dimension(800, 700));
		
		// variable initialization
		previous = new Cell(9, 9, null);
		current = new Cell(9, 9, null);
		empty = new Cell(10, 10, null);
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

		attacker = new ArrayList<Cell>();

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
				cell.addMouseListener(this);
				chessBoard.add(cell);
				pos[i][j] = cell;
			}

		gui.add(chessBoard);

		this.add(gui, BorderLayout.CENTER);
		this.add(welcomePage, BorderLayout.CENTER);
		gui.setVisible(false);

		welcomePage.setMinimumSize(new Dimension(800, 700));
		welcomePage.setVisible(true);
		
		BufferedImage welcome = ImageIO.read(new File("src/board/welcomePage.jpg"));
		JLabel welcomeImage = new JLabel(new ImageIcon(welcome));
		welcomeImage.setMaximumSize(welcomePage.getSize());
		welcomePage.add(welcomeImage, BorderLayout.CENTER);
		
		JButton start = new JButton("Start Game!");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcomePage.setVisible(false);
				gui.setVisible(true);
			}
		});
		welcomePage.add(start, BorderLayout.SOUTH);

		this.setTitle("ChessACE");
		this.setLayout(new CardLayout());
		this.getContentPane().add(this.getWelcome());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationByPlatform(true);
		// ensures the frame is the minimum size it needs to be
		// in order display the components within it
		this.pack();
		// ensures the minimum size is enforced.
		this.setMinimumSize(this.getSize());
		this.setVisible(true);

	}

	public JPanel getGui() {
		return gui;
	}

	public JPanel getWelcome() {
		return welcomePage;
	}

	public void Initial() {
		timer.reset();
		timer.start();
		chessBoard.removeAll();
		previous = new Cell(9, 9, null);
		current = new Cell(9, 9, null);
		empty = new Cell(10, 10, null);
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

		attacker = new ArrayList<Cell>();

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
				cell.addMouseListener(this);
				chessBoard.add(cell);
				pos[i][j] = cell;
			}
		chessBoard.revalidate();
		chessBoard.repaint();
	}

	public void move(Cell a, Cell b) {
		b.removePiece();
		b.setPiece(a.getPiece());
		a.removePiece();
		b.getPiece().setX(b.gX());
		b.getPiece().setY(b.gY());
		previous.deselect();
		previous = empty;
		current = empty;
	}

	public void castling(Cell king, Cell kTo, Cell rook, Cell rTo) {
		move(king, kTo);
		move(rook, rTo);
	}

	public int isCastling(Cell p, Cell c) {
		if (p.getPiece() instanceof King && !(p.getPiece().isMoved())) {
			if (p.gY() - c.gY() == 2) {
				if (pos[c.gX()][c.gY() - 2].getPiece() instanceof Rook
						&& !(pos[c.gX()][c.gY() - 2].getPiece().isMoved()))
					return 0;
			} else if (p.gY() - c.gY() == -2) {
				if (pos[c.gX()][c.gY() + 1].getPiece() instanceof Rook
						&& !(pos[c.gX()][c.gY() + 1].getPiece().isMoved()))
					return 1;
			} else
				return -1;
		}
		return -1;
	}

	public King retrieveKing(int color) {
		if (color == 1) {
			return wkg1;
		} else if (color == -1)
			return bkg1;
		else
			return null;
	}

	public void transform(Cell c) {
		if (c.getPiece().getColor() == 1) {
			c.removePiece();
			c.setPiece(new Queen("WQN" + (1 + wqn), "White_Queen.png", c.gX(), c.gY(), 1));
			wqn += 1;
		} else {
			c.removePiece();
			c.setPiece(new Queen("BQN" + (1 + bqn), "Black_Queen.png", c.gX(), c.gY(), -1));
			bqn += 1;
		}

	}

	public boolean checkmate(int color, ArrayList<Cell> attack) {
		int result = 0;
		int stats = 0;
		Cell checkCell;
		Piece p;

		ArrayList<Cell> checkList = filterDestination(retrieveKing(color), retrieveKing(color).posMove(pos));
		if (checkList.isEmpty())
			result++;
		if (attack.size() == 1) {
			if (attack.get(0).getPiece().isKingInDanger(pos))
				stats++;
		} else {
			for (Cell a : attack) {
				checkCell = a;
				if (checkCell.getPiece() != null) {
					p = checkCell.getPiece();
					checkCell.removePiece();
					if (color == 1)
						checkCell.setPiece(new Pawn("", "White_Pawn.png", checkCell.gX(), checkCell.gY(), -1));
					else
						checkCell.setPiece(new Pawn("", "Black_Pawn.png", checkCell.gX(), checkCell.gY(), 1));
					if (checkCell.getPiece().isKingInDanger(pos)) {
						checkCell.removePiece();
						stats++;
						break;
					}
					checkCell.removePiece();
					checkCell.setPiece(p);
					p = null;
				} else {
					if (color == 1)
						checkCell.setPiece(new Pawn("", "White_Pawn.png", checkCell.gX(), checkCell.gY(), -1));
					else
						checkCell.setPiece(new Pawn("", "Black_Pawn.png", checkCell.gX(), checkCell.gY(), 1));
					if (checkCell.getPiece().isKingInDanger(pos)) {
						checkCell.removePiece();
						stats++;
						break;
					} else {
						checkCell.removePiece();
					}
				}
			}
		}
		if (stats == 0)
			result++;
		if (result == 2)
			return true;
		else
			return false;
	}

	public void gameOver() {
		filteredList.clear();
		if (player == 1) {
			JOptionPane.showMessageDialog(this, "Checkmate!!!\n" + "White " + " wins");
		} else {
			JOptionPane.showMessageDialog(this, "Checkmate!!!\n" + "Black " + " wins");
		}
		this.setVisible(true);
		this.setResizable(false);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	public ArrayList<Cell> filterDestination(Piece p, ArrayList<Cell> a) {
		ArrayList<Cell> newList = new ArrayList<Cell>();
		Cell moveCell;
		Piece temp = null;
		if (p instanceof King) {
			// iterate via "iterator loop"
			int x = p.getX();
			int y = p.getY();
			pos[p.getX()][p.getY()].removePiece();
			Iterator<Cell> moveIterator = a.iterator();
			while (moveIterator.hasNext()) {
				moveCell = moveIterator.next();
				p.setX(moveCell.gX());
				p.setY(moveCell.gY());
				if (moveCell.getPiece() != null) {
					temp = moveCell.getPiece();
					moveCell.removePiece();
					moveCell.setPiece(p);
					if (!(((King) p).isKingInDanger(pos))) {
						newList.add(moveCell);
					}
					moveCell.removePiece();
					moveCell.setPiece(temp);
				} else {
					moveCell.setPiece(p);
					if (!(((King) p).isKingInDanger(pos))) {
						newList.add(moveCell);
					}
					moveCell.removePiece();
				}
			}
			p.setX(x);
			p.setY(y);
			pos[p.getX()][p.getY()].setPiece(p);
		} else {
			// return the list of posMove that will not cause the friendly king checkmated,
			// using isKingInDanger and retrieveKing for king location.
			int x = p.getX();
			int y = p.getY();
			pos[p.getX()][p.getY()].removePiece();
			Iterator<Cell> moveIterator = a.iterator();
			while (moveIterator.hasNext()) {
				moveCell = moveIterator.next();
				// System.out.println(moveCell.gX() + "::" + moveCell.gY());
				p.setX(moveCell.gX());
				p.setY(moveCell.gY());
				if (moveCell.getPiece() != null) {
					temp = moveCell.getPiece();
					moveCell.removePiece();
					moveCell.setPiece(p);
					if (!(retrieveKing(p.getColor()).isKingInDanger(pos))) {
						newList.add(moveCell);
					}
					moveCell.removePiece();
					moveCell.setPiece(temp);
				} else {
					moveCell.setPiece(p);
					if (!(retrieveKing(p.getColor()).isKingInDanger(pos))) {
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

	public void highlight(ArrayList<Cell> list) {
		Iterator<Cell> moveIterator = list.iterator();
		Cell temp;
		while (moveIterator.hasNext()) {
			moveIterator.next().setPos();
		}
	}

	public void unhighlight(ArrayList<Cell> list) {
		Iterator<Cell> moveIterator = list.iterator();
		Cell temp;
		while (moveIterator.hasNext()) {
			moveIterator.next().removePos();
		}
		this.revalidate();
		this.repaint();
	}

	public void display() {
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
		current = (Cell) arg0.getSource();
		// haven't select any filled cell yet, check if it is the player's turn and
		// record the selection of the cell.
		if (previous.getPiece() == null) {
			if (current.getPiece() != null) {
				if (current.getPiece().getColor() != player)
					return;
				filteredList = filterDestination(current.getPiece(), current.getPiece().posMove(pos));
				highlight(filteredList);
				previous = current;
				current.select();
				current = null;
			}
			// selected some filled cell before
		} else {
			// deselect the cell if clicked again
			if (previous.gX() == current.gX() && previous.gY() == current.gY()) {
				current.deselect();
				unhighlight(filteredList);
				filteredList.clear();
				previous = new Cell(9, 9, null);
				return;
				// To do Deselect;
				// check if the select cell contains no or hostile piece respect to the previous
				// piece
			} else if (current.getPiece() == null || (current.getPiece() != null
					&& previous.getPiece().getColor() != current.getPiece().getColor())) {
				// check if the next cell is feasible for movement.
				// filteredList = filterDestination(previous.getPiece(),
				// previous.getPiece().posMove(pos));
				if (filteredList.contains(current)) {
					// if the selected piece is king
					if (previous.getPiece() instanceof King) {
						// System.out.println("aha");
						/*
						 * check castling status
						 */
						if (isCastling(previous, current) == 0) {
							previous.getPiece().setMove();
							castling(previous, current, pos[previous.gX()][previous.gY() - 4],
									pos[previous.gX()][previous.gY() - 1]);
							unhighlight(filteredList);
							filteredList.clear();
						} else if (isCastling(previous, current) == 1) {
							previous.getPiece().setMove();
							castling(previous, current, pos[previous.gX()][previous.gY() + 3],
									pos[previous.gX()][previous.gY() + 1]);
							unhighlight(filteredList);
							filteredList.clear();
						}
						// normal movement
						else {
							previous.getPiece().setMove();
							if (isCheckmate) {
								pos[retrieveKing(player).getX()][retrieveKing(player).getY()].removeCheck();
								isCheckmate = false;
							}
							move(previous, current);
							unhighlight(filteredList);
							filteredList.clear();
						}
						// if the piece is a rook, set it to moved for future castling check.
					} else if ((previous.getPiece() instanceof Rook)) {
						previous.getPiece().setMove();
						move(previous, current);
						unhighlight(filteredList);
						filteredList.clear();
					}
					// if the piece is pawn, auto transform if it is reaching the end. Otherwise,
					// normal movement.
					else if ((previous.getPiece() instanceof Pawn)) {
						if ((current.gX() == 7 && previous.getPiece().getColor() == -1)
								|| (current.gX() == 0 && previous.getPiece().getColor() == 1)) {
							Cell temp = current;
							move(previous, current);
							transform(temp);
							unhighlight(filteredList);
							filteredList.clear();
						} else {
							move(previous, current);
							unhighlight(filteredList);
							filteredList.clear();
						}
						// All the other piece shall perform normal movement.
					} else {
						move(previous, current);
						unhighlight(filteredList);
						filteredList.clear();
					}
					// check checkmate status after movement. If true, set it to false.
					if (isCheckmate) {
						retrieveKing(player).removeCheckmate();
						pos[retrieveKing(player).getX()][retrieveKing(player).getY()].removeCheck();
						isCheckmate = false;
					}
					// check checkmate status on the enemy king, if true, set isCheckMate to true.
					// Check if it is checkmated, if true, call gameover();
					if (retrieveKing(player * -1).isKingInDanger(pos, attacker)) {
						retrieveKing(player * -1).setCheckmate();
						pos[retrieveKing(player * -1).getX()][retrieveKing(player * -1).getY()].check();
						isCheckmate = true;
						// if(checkmate(player * -1))
						// gameOver();
					}
					// switch side
					player = switchSide(player);
				}
				// deselect the previous cell.
			} else if (current.getPiece() != null && previous.getPiece().getColor() == current.getPiece().getColor()) {
				// Some operation that deselect the previous cell and select current cell
				// instead.
				// Also, display the feasible moveTo Cells.
			}
		}
	}

	public int switchSide(int p) {
		if (p * -1 == 1) {
			message.setText("White turn");
			timer.reset();
			timer.start();
		} else
			message.setText("Black turn");
			timer.reset();
			timer.start();
		if (isCheckmate == true) {
			if (checkmate(p * -1, attacker))
				gameOver();
		}
		return p * -1;
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
		Main m;
		try {
			m = new Main();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class TimeChange implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			timeremaining = timeSlider.getValue()*60;
		}
		
	}
}