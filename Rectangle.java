package assign10;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a rectangle, which is a Shape2D.
 * 
 * @author Prof. Heisler and Judy Ojewia
 * @version Nov. 30, 2023
 */
public class Rectangle extends Shape2D {
	private int width;
	private int height;

	/**
	 * Construct a Rectangle with the given position, size, color, and filled
	 * property.
	 * 
	 * @param posX   - an integer representing x position
	 * @param posY   - an integer representing y position
	 * @param width  - an integer representing rectangle width
	 * @param height - an integer representing rectangle height
	 * @param color  - a color representing color of shape
	 * @param filled - a boolean true if it will be drawn filled or false if the
	 *               outline will be drawn
	 */
	public Rectangle(int posX, int posY, int width, int height, Color color, boolean filled) {
		super(posX, posY, color, filled);
		this.width = width;
		this.height = height;
	}

	/**
	 * When given two integer values sizeX and sizeY this method resizes by changing
	 * the rectangle width and height.
	 * 
	 * @param sizeX - an integer representing the new rectangle width
	 * @param sizeY - an integer representing the new rectangle height
	 */
	public void resize(int sizeX, int sizeY) {
		this.width = sizeX;
		this.height = sizeY;
	}

	/**
	 * This method draws a shape with a Graphics instance and zoom value. If
	 * isFilled() returns true for a Rectangle object the corresponding method for
	 * drawing a filled shape in the Graphics class is called. Otherwise the method
	 * for drawing only the outline is called.
	 * 
	 * @param g    - graphics instance
	 * @param zoom - double value representing the zoom factor
	 */
	public void draw(Graphics g, double zoom) {
		g.setColor(getColor());
		if (isFilled()) {

			g.fillRect((int) (getX() * zoom), (int) (getY() * zoom), (int) (width * zoom), (int) (height * zoom));
		} else if (!isFilled()) {

			g.drawRect((int) (getX() * zoom), (int) (getY() * zoom), (int) (width * zoom), (int) (height * zoom));
		}
	}

}
