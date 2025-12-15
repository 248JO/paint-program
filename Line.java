package assign10;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a line, which is a Shape2D.
 * 
 * @author Prof. Heisler and Judy Ojewia
 * @version Nov. 30, 2023
 */
public class Line extends Shape2D {
	private int endX;
	private int endY;

	/**
	 * Construct a Line with the given end positions, color, and filled property.
	 * 
	 * @param startX - x position of start point
	 * @param startY - y position of start point
	 * @param endX   - x position of end point
	 * @param endY   - y position of end point
	 * @param color  - a color representing color of shape
	 * @param filled - has no effect on a Line object, but is needed by Shape2D
	 */
	public Line(int startX, int startY, int endX, int endY, Color color, boolean filled) {
		super(startX, startY, color, filled);
		this.endX = endX;
		this.endY = endY;
	}

	/**
	 * When given two integer values x and y this method resizes by changing the end
	 * point of the line. The start point that was set in the constructor does not
	 * change.
	 * 
	 * @param x - an integer representing the new line x end point component
	 * @param y - an integer representing the new oval y end point component
	 */
	public void resize(int x, int y) {
		this.endX = x;
		this.endY = y;

	}

	/**
	 * This method draws a line with a Graphics instance and zoom value. There is no
	 * difference between filled and outline for a Line object so a fill mode does
	 * not matter.
	 * 
	 * @param g    - graphics instance
	 * @param zoom - double value representing the zoom factor
	 */
	public void draw(Graphics g, double zoom) {
		g.setColor(getColor());
		g.drawLine((int) (this.getX() * zoom), (int) (this.getY() * zoom), (int) (endX * zoom), (int) (endY * zoom));

	}

}
