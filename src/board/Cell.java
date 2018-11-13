package board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import piece.Piece;

public class Cell extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isSelected;
	private boolean isCheck;
	private boolean isPOS;
	int x, y;
	private Piece piece;
	private JLabel content;

	public Cell(int x, int y, Piece p) {
		this.x = x;
		this.y = y;
		this.isCheck = false;
		this.isSelected = false;
		this.isPOS = false;
		if ((x + y) % 2 == 0)
			this.setBackground(new Color(160, 82, 45));
		else
			this.setBackground(Color.white);
		if (p != null)
			setPiece(p);
	}

	public void setPiece(Piece p) // Function to inflate a cell with a piece
	{
		piece=p;
		ImageIcon img=new ImageIcon("src/board/"+p.getPath());
		content=new JLabel(img);
		this.add(content);
		this.revalidate();
		this.repaint();
	}

	public void removePiece()      //Function to remove a piece from the cell
	{
			piece=null;
			this.remove(content);
	}
	public Piece getPiece() // Function to access piece of a particular cell
	{
		return this.piece;
	}
	
	public void select() {
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		this.isSelected = true;
	}
	
	public boolean isSelected() {
		return this.isSelected;
	}
	
	public void deselect() {
		System.out.println("here");
		System.out.println(this.gX());
		System.out.println(this.gY());
		this.setBorder(null);
		this.isSelected = false;
	}
	
	public void check() {
		this.setBackground(Color.RED);
		this.isCheck = true;
	}
	
	public boolean isCheck() {
		return this.isCheck;
	}
	public void removeCheck() {
		if((x+y)%2==0)
			setBackground(new Color(160, 82, 45));
		else
			setBackground(Color.white);
		this.isCheck=false;
	}
	
	public boolean isPOS() {
		return this.isPOS;
	}
	
	public void setPos() {
		this.setBorder(BorderFactory.createLineBorder(Color.blue,6));
		this.isPOS = true;
	}
	
	public void removePos() {
		this.setBorder(null);
		this.isPOS = false;
	}
	
	public int gX() {
		return this.x;
	}
	
	public int gY() {
		return this.y;
	}
}
