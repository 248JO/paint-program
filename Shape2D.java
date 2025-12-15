package assign10;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A general 2D shape that can be drawn.
 * 
 * @author Prof. Heisler
 * @version Nov. 6, 2023
 */
public abstract class Shape2D{
	private int positionX;
	private int positionY;
	private Color color;
	private boolean filled;
	
	/**
	 * Initializes the position and color.
	 * 
	 * @param x - coordinate
	 * @param y - coordinate
	 * @param color - color
	 */
	public Shape2D(int posX, int posY, Color color, boolean filled) {
		this.color = color;
		this.positionX = posX;
		this.positionY = posY;
		this.filled = filled;
	}
	
	/**
	 * Gets the x position.
	 * 
	 * @return x position
	 */
	public int getX() {
		return this.positionX;
	}
	
	/**
	 * Gets the y position.
	 * 
	 * @return y position
	 */
	public int getY() {
		return this.positionY;
	}
	
	/**
	 * Gets the color.
	 * 
	 * @return color
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Is the shape drawn filled or just as an outline?
	 * 
	 * @return true if the shape is filled, false if outline only
	 */
	public boolean isFilled() {
		return this.filled;
	}
	
	/**
	 * Move the shape to a new position.
	 * 
	 * @param newX - x position to move to
	 * @param newY - y position to move to
	 */
	public void move(int newX, int newY) {
		this.positionX = newX;
		this.positionY = newY;
	}
	
	/**
	 * Resize the Shape base on specified X and Y values.
	 * Different shapes will use these values in different ways.
	 * 
	 * @param sizeX
	 * @param sizeY
	 */
	public abstract void resize(int sizeX, int sizeY);
	
	/**
	 * Draws this shape to the given Graphics context.
	 * The zoom factor is applied to positions and sizes.
	 * 
	 * @param g - Graphics context
	 * @param zoom - zoom factor
	 */
	public abstract void draw(Graphics g, double zoom);
}
