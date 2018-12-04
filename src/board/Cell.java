package board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import piece.Piece;

/**
 * This is the Cell Class contains inherits from JPanel and will point to either no or one piece.
 *	
 * @author Xingjian Ke
 * @version     1.0                 (current version number of program)
 * @since       0.0          
 * 
 */ 

public class Cell extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean isSelected;
	private boolean isCheck;
	private boolean isPOS;
	int x, y;
	private Piece piece;
	private JLabel content;
	
	/**
	 * Constructor of Cell Instance, initializing its position, check/select states, 
	 * and other GUI related components, including size and background.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param p PNG picture path
	 */
	public Cell(int x, int y, Piece p) {
		this.x = x;
		this.y = y;
		this.isCheck = false;
		this.isSelected = false;
		this.isPOS = false;
		this.setPreferredSize(new Dimension(98,98));
		if ((x + y) % 2 == 0)
			this.setBackground(new Color(160, 82, 45));
		else
			this.setBackground(Color.white);
		if (p != null)
			setPiece(p);
	}
	
	/**
	 * set the correspong piece to p, and using p's path to integrate the picture.
	 * 
	 * @param p piece instance
	 */
	public void setPiece(Piece p) // Function to inflate a cell with a piece
	{
		piece=p;
		ImageIcon img=new ImageIcon("src/board/"+p.getPath());
		content=new JLabel(img);
		this.add(content);
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * remove the pre-stored piece to p, and remove all related contents.
	 */
	public void removePiece()      //Function to remove a piece from the cell
	{
			piece=null;
			this.remove(content);
	}
	
	/**
	 * @return current pointed piece instance
	 */
	public Piece getPiece() // Function to access piece of a particular cell
	{
		return this.piece;
	}
	
	/**
	 * set the border of this Cell to red as it is selected
	 */
	public void select() {
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		this.isSelected = true;
	}
	
	/**
	 * @return if this cell is selected
	 */
	public boolean isSelected() {
		return this.isSelected;
	}
	
	/**
	 * set the border of this Cell to no board as it is not selected any more.
	 */
	public void deselect() {
		this.setBorder(null);
		this.isSelected = false;
	}
	
	/**
	 * set the background of this Cell to red as the piece of it is checked.
	 */
	public void check() {
		this.setBackground(Color.RED);
		this.isCheck = true;
	}
	
	/**
	 * @return if this cell is checked
	 */
	public boolean isCheck() {
		return this.isCheck;
	}
	
	/**
	 * set the background of this Cell to white/brown depends on its (x,y) pair
	 */
	public void removeCheck() {
		if((x+y)%2==0)
			setBackground(new Color(160, 82, 45));
		else
			setBackground(Color.white);
		this.isCheck=false;
	}
	
	/**
	 * @return if this cell is a possible move destination
	 */
	public boolean isPOS() {
		return this.isPOS;
	}
	
	/**
	 * set the border of this Cell to blue as it is a possible move
	 */
	public void setPos() {
		this.setBorder(BorderFactory.createLineBorder(Color.blue,6));
		this.isPOS = true;
	}
	
	/**
	 * set the border of this Cell to white/brown depends on its (x,y) pair.
	 */
	public void removePos() {
		this.setBorder(null);
		this.isPOS = false;
	}
	
	/**
	 * @return x-coordination of the cell
	 */
	public int gX() {
		return this.x;
	}
	
	/**
	 * @return y-coordination of the cell
	 */
	public int gY() {
		return this.y;
	}
	
}
