package assign10;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents an oval, which is a Shape2D.
 * 
 * @author Prof. Heisler and Judy Ojewia
 * @version Nov. 30, 2023
 */
public class Oval extends Shape2D {
	private int width;
	private int height;

	/**
	 * Construct an Oval with the given position, size, color, and filled property.
	 * 
	 * @param posX   - x position
	 * @param posY   - y position
	 * @param width  - diameter in x direction
	 * @param height - diameter in y direction
	 * @param color  - a color representing color of shape
	 * @param filled - true if it will be drawn filled
	 */
	public Oval(int posX, int posY, int width, int height, Color color, boolean filled) {
		super(posX, posY, color, filled);
		this.width = width;
		this.height = height;
	}

	/**
	 * When given two integer values sizeX and sizeY this method resizes by changing
	 * the oval horizontal and vertical diameters.
	 * 
	 * @param sizeX - an integer representing the new oval horizontal diameters
	 * @param sizeY - an integer representing the new oval vertical diameters
	 */
	public void resize(int sizeX, int sizeY) {
		this.width = sizeX;
		this.height = sizeY;

	}

	/**
	 * This method draws a shape with a Graphics instance and zoom value. If
	 * isFilled() returns true for an Oval object the corresponding method for
	 * drawing a filled shape in the Graphics class is called. Otherwise the method
	 * for drawing only the outline is called.
	 * 
	 * @param g    - graphics instance
	 * @param zoom - double value representing the zoom factor
	 */
	public void draw(Graphics g, double zoom) {
		g.setColor(getColor());
		if (isFilled()) {
			g.fillOval((int) (getX() * zoom), (int) (getY() * zoom), (int) (width * zoom), (int) (height * zoom));
		} else if (!isFilled()) {
			g.drawOval((int) (getX() * zoom), (int) (getY() * zoom), (int) (width * zoom), (int) (height * zoom));
		}
	}

}
