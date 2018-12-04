package board;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;
import javax.swing.JSlider;

/** Main class
 * @version 1.0 (current version number of program)
 * @since 0.0
 * @author Xingjian
 *
 */
public class Main extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	private Cell[][] pos;

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
	private final JPanel welcomePage = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private final JLabel message = new JLabel("White turn");
	private ArrayList<Cell> filteredList = new ArrayList<Cell>();

	private ArrayList<Piece> white;
	private ArrayList<Piece> black;

	private JLabel label;
	private JPanel displayTime;
	private JSlider timeSlider;
	private countDownTimer timer;
	public int timeremaining;

	private boolean states = false;

	/**
	 * Constructor of Main Instance, initializing all GUI components, including
	 * toolbar, chessboard, countdownTimer. Also, load the data of all pieces, and
	 * set them into correct position. Record pieces with same color in a data
	 * structure for future use.
	 */
	public Main() throws IOException {
		JToolBar tools = new JToolBar();
		Main m0 = this;

		tools.setFloatable(false);
		JButton New = new JButton("New");
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Initial();
			}
		});

		tools.add(New);

		tools.addSeparator();

		JButton Pause = new JButton("Pause");
		Pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pauseGame();
			}
		});
		tools.add(Pause);
		tools.addSeparator();

		JButton surrender = new JButton("Resign");
		surrender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player = player * -1;
				gameOver();
			}
		});
		tools.add(surrender);
		tools.addSeparator();

		gui.add(tools, BorderLayout.PAGE_START);

		timeSlider = new JSlider();

		// implementation of time slider
		timeSlider.setMinimum(1);
		timeSlider.setMaximum(15);
		timeSlider.setValue(1);
		timeSlider.setMajorTickSpacing(2);
		timeSlider.setPaintLabels(true);
		timeSlider.setPaintTicks(true);
		timeSlider.addChangeListener(new TimeChange());
		JLabel setTime = new JLabel("Set Timer(in mins):");
		JPanel slider = new JPanel(new GridLayout(3, 3));
		slider.add(setTime, BorderLayout.PAGE_START);
		slider.add(timeSlider, BorderLayout.PAGE_END);
		welcomePage.add(slider, BorderLayout.LINE_END);

		// implementation for display timer
		this.timeremaining = 60;
		label = new JLabel("", JLabel.CENTER);
		displayTime = new JPanel(new FlowLayout());
		displayTime.add(label);

		tools.add(displayTime);
		tools.add(message);

		chessBoard = new JPanel(new GridLayout(8, 8));

		white = new ArrayList<Piece>();
		black = new ArrayList<Piece>();
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
		white.add(wrk1);
		wrk2 = new Rook("WRK2", "White_Rook.png", 7, 7, 1);
		white.add(wrk2);
		brk1 = new Rook("BRK1", "Black_Rook.png", 0, 0, -1);
		black.add(brk1);
		brk2 = new Rook("BRK2", "Black_Rook.png", 0, 7, -1);
		black.add(brk2);
		wknt1 = new Knight("WKNT1", "White_Knight.png", 7, 1, 1);
		white.add(wknt1);
		wknt2 = new Knight("WKNT2", "White_Knight.png", 7, 6, 1);
		white.add(wknt2);
		bknt1 = new Knight("BKNT1", "Black_Knight.png", 0, 1, -1);
		black.add(bknt1);
		bknt2 = new Knight("BKNT2", "Black_Knight.png", 0, 6, -1);
		black.add(bknt2);
		wbsp1 = new Bishop("WBSP1", "White_Bishop.png", 7, 2, 1);
		white.add(wbsp1);
		wbsp2 = new Bishop("WBSP2", "White_Bishop.png", 7, 5, 1);
		white.add(wbsp2);
		bbsp1 = new Bishop("BBSP1", "Black_Bishop.png", 0, 2, -1);
		black.add(bbsp1);
		bbsp2 = new Bishop("BBSP2", "Black_Bishop.png", 0, 5, -1);
		black.add(bbsp2);
		wqn1 = new Queen("WQN1", "White_Queen.png", 7, 3, 1);
		white.add(wqn1);
		bqn1 = new Queen("BQN1", "Black_Queen.png", 0, 3, -1);
		black.add(bqn1);
		wkg1 = new King("WKG1", "White_King.png", 7, 4, 1);
		white.add(wkg1);
		bkg1 = new King("BKG1", "Black_King.png", 0, 4, -1);
		black.add(bkg1);
		wpn1 = new Pawn[8];
		bpn1 = new Pawn[8];

		pos = new Cell[8][8];

		for (int i = 0; i < 8; i++) {
			wpn1[i] = new Pawn("WPN" + i, "White_Pawn.png", 6, i, 1);
			white.add(wpn1[i]);
			bpn1[i] = new Pawn("BPN" + i, "Black_Pawn.png", 1, i, -1);
			black.add(bpn1[i]);
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

		gui.add(chessBoard, BorderLayout.CENTER);

		this.add(gui, BorderLayout.CENTER);
		this.add(welcomePage, BorderLayout.CENTER);
		gui.setVisible(false);

		welcomePage.setMinimumSize(new Dimension(700, 800));
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
				timeremaining = timeSlider.getValue() * 60;
				timer = new countDownTimer(label, m0);
				timer.start();
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
		this.setResizable(false);

	}

	private JPanel getGui() {
		return gui;
	}

	private JPanel getWelcome() {
		return welcomePage;
	}

	private void pauseGame() {
		if (states == false) {
			timer.stop();
			states = true;
		} else {
			timer.start();
			states = false;
		}
	}

	// re-load the initial data for chess game.
	private void Initial() {
		white.clear();
		black.clear();
		message.setText("White turn");
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
		white.add(wrk1);
		wrk2 = new Rook("WRK2", "White_Rook.png", 7, 7, 1);
		white.add(wrk2);
		brk1 = new Rook("BRK1", "Black_Rook.png", 0, 0, -1);
		black.add(brk1);
		brk2 = new Rook("BRK2", "Black_Rook.png", 0, 7, -1);
		black.add(brk2);
		wknt1 = new Knight("WKNT1", "White_Knight.png", 7, 1, 1);
		white.add(wknt1);
		wknt2 = new Knight("WKNT2", "White_Knight.png", 7, 6, 1);
		white.add(wknt2);
		bknt1 = new Knight("BKNT1", "Black_Knight.png", 0, 1, -1);
		black.add(bknt1);
		bknt2 = new Knight("BKNT2", "Black_Knight.png", 0, 6, -1);
		black.add(bknt2);
		wbsp1 = new Bishop("WBSP1", "White_Bishop.png", 7, 2, 1);
		white.add(wbsp1);
		wbsp2 = new Bishop("WBSP2", "White_Bishop.png", 7, 5, 1);
		white.add(wbsp2);
		bbsp1 = new Bishop("BBSP1", "Black_Bishop.png", 0, 2, -1);
		black.add(bbsp1);
		bbsp2 = new Bishop("BBSP2", "Black_Bishop.png", 0, 5, -1);
		black.add(bbsp2);
		wqn1 = new Queen("WQN1", "White_Queen.png", 7, 3, 1);
		white.add(wqn1);
		bqn1 = new Queen("BQN1", "Black_Queen.png", 0, 3, -1);
		black.add(bqn1);
		wkg1 = new King("WKG1", "White_King.png", 7, 4, 1);
		white.add(wkg1);
		bkg1 = new King("BKG1", "Black_King.png", 0, 4, -1);
		black.add(bkg1);
		wpn1 = new Pawn[8];
		bpn1 = new Pawn[8];

		pos = new Cell[8][8];

		for (int i = 0; i < 8; i++) {
			wpn1[i] = new Pawn("WPN" + i, "White_Pawn.png", 6, i, 1);
			white.add(wpn1[i]);
			bpn1[i] = new Pawn("BPN" + i, "Black_Pawn.png", 1, i, -1);
			black.add(bpn1[i]);
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

	// highligh a list of cell in the purpose of possibleMove
	private void highlight(ArrayList<Cell> list) {
		Iterator<Cell> moveIterator = list.iterator();
		while (moveIterator.hasNext()) {
			moveIterator.next().setPos();
		}
	}

	// unhighlight a list of cell in the purpose of possibleMove
	private void unhighlight(ArrayList<Cell> list) {
		Iterator<Cell> moveIterator = list.iterator();
		while (moveIterator.hasNext()) {
			moveIterator.next().removePos();
		}
		this.revalidate();
		this.repaint();
	}

	/**
	 * move all contents from a to b. Clear all contents of a, and if b has a piece
	 * before, remove it from the cell, and the data structure storing pieces with
	 * the same color.
	 * 
	 * @param a from-Cell
	 * @param b to-Cell
	 */
	public void move(Cell a, Cell b) {
		if (b.getPiece() != null) {
			if (player == 1) {
				black.remove(b.getPiece());
			} else {
				white.remove(b.getPiece());
			}
		}
		b.removePiece();
		b.setPiece(a.getPiece());
		a.removePiece();
		b.getPiece().setX(b.gX());
		b.getPiece().setY(b.gY());
		previous.deselect();
		previous = empty;
		current = empty;
	}

	// double move statement to move king and rook at the same time
	private void castling(Cell king, Cell kTo, Cell rook, Cell rTo) {
		move(king, kTo);
		move(rook, rTo);
	}

	/**
	 * check if p to c can perform a valid castling action
	 * 
	 * @param p from-cell
	 * @param c to-cell
	 */
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

	/**
	 * @param color color of King we want to retrieve
	 * @return color = 1 => retrieve the white king instance; color = -1 => retrieve
	 *         the black king instance.
	 */
	public King retrieveKing(int color) {
		if (color == 1) {
			return wkg1;
		} else if (color == -1)
			return bkg1;
		else
			return null;
	}

	/**
	 * remove all contents on Cell c, and set a new Queen instance of Cell c based
	 * on the previous piece's color.
	 * 
	 * @param c Cell
	 */
	public void transform(Cell c) {
		Piece p;
		if (c.getPiece().getColor() == 1) {
			white.remove(c.getPiece());
			c.removePiece();
			p = new Queen("WQN" + (1 + wqn), "White_Queen.png", c.gX(), c.gY(), 1);
			c.setPiece(p);
			wqn += 1;
			white.add(p);
		} else {
			black.remove(c.getPiece());
			c.removePiece();
			p = new Queen("BQN" + (1 + bqn), "Black_Queen.png", c.gX(), c.gY(), -1);
			c.setPiece(p);
			bqn += 1;
			black.add(p);
		}

	}

	/**
	 * By analyzing the posMove list of Piece p, remove all the possibility that
	 * will cause a checkmate on the friendly king. (isKingInDanger in Piece.java
	 * used.)
	 * 
	 * @param p Piece
	 * @param a corresponding PosMove list
	 * @return list of Cell that can actually move to depends on the list a.
	 */
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

	/**
	 * Evaluating the current situation and selected piece. Decide what kind of
	 * operation the user wants to do. Validate and decide to perform or not.
	 * 
	 * @param arg0 a mouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		timer.start();
		states = false;
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
					if (retrieveKing(player * -1).isKingInDanger(pos)) {
						System.out.println("A");
						retrieveKing(player * -1).setCheckmate();
						pos[retrieveKing(player * -1).getX()][retrieveKing(player * -1).getY()].check();
						isCheckmate = true;
						// if(checkmate(player * -1, System.out.println("+1");))
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

	// reset the timer and change the active player.
	// Also, if detect a check situation, verify if the game is over or not.
	private int switchSide(int p) {
		if (p * -1 == 1) {
			message.setText("White turn");
			timer.reset();
			timer.start();
		} else
			message.setText("Black turn");
		timer.reset();
		timer.start();
		if (isCheckmate == true) {
			if (!(existPos(p * -1))) {
				gameOver();
				return 1;
			}
		}
		return p * -1;
	}

	/**
	 * Iterate through the data structure storing the pieces with the same color,
	 * check if there exists any feasible movement that will remove the check state
	 * and will not invoke another check state.
	 * 
	 * @return true if such movement exists; false otherwise
	 */
	public boolean existPos(int color) {
		if (color == 1) {
			for (Piece p : white) {
				if (!(filterDestination(p, p.posMove(pos)).isEmpty())) {
					return true;
				}
			}
		} else {
			for (Piece p : black) {
				if (!(filterDestination(p, p.posMove(pos)).isEmpty()))
					return true;
			}
		}
		return false;
	}

	/**
	 * gameOver re-load the game
	 */
	public void gameOver() {
		filteredList.clear();
		timer.stop();
		if (player == 1) {
			JOptionPane.showMessageDialog(this, "Checkmate!!!\n" + "White " + " wins");
		} else {
			JOptionPane.showMessageDialog(this, "Checkmate!!!\n" + "Black " + " wins");
		}
		this.setVisible(true);
		this.setResizable(false);
		Initial();
	}

	/**
	 * change the active player, designed specifically for countdownTimer.
	 */
	public void playerChange() {
		this.player = this.player * -1;
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

	class TimeChange implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent arg0) {
			timeremaining = timeSlider.getValue() * 60;
		}

	}
}