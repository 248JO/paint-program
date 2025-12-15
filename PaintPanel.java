package assign10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Represents a specific type of GUI panel for painting. This class paints
 * shapes, allows a user to select a color, and shows a preview of the shape
 * being drawn.
 * 
 * @author Judy Ojewia
 * @version November 30, 2023
 */
public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

	private ArrayList<Shape2D> shapes;
	private Shape2D preview;
	private Shape current;
	private Color color;
	private boolean filled;
	private double zoom;
	private int x;
	private int y;

	/**
	 * Constructs a Paint Panel with a white background. The initial shape is set to
	 * a black line with no fill (lines have on fill/no fill option). The canvas
	 * starts out with no zoom (zoom value of 1).
	 * 
	 */
	public PaintPanel() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);

		this.setBackground(Color.WHITE);
		this.shapes = new ArrayList<Shape2D>();
		current = Shape.LINE;
		this.color = Color.BLACK;
		this.filled = false;
		this.zoom = 1.0;
		preview = new Line(0, 0, 0, 0, this.color, this.filled);
	}

	/**
	 * This method is used when a shape needs to be painted. Which can be at one of
	 * three times: when the component first appears, when the size of the component
	 * changes (including resizing by the user), and when repaint() is called. This
	 * method also draws all of the shapes in the list using their draw methods. If
	 * the preview shape is not null it is drawn as well.
	 * 
	 * @param g -- graphics context onto which we can draw
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Shape2D shape : shapes) {
			if (shape instanceof Line) {
				shape.draw(g, zoom);
			} else if (shape instanceof Oval) {
				shape.draw(g, zoom);
			} else if (shape instanceof Rectangle) {
				shape.draw(g, zoom);
			}
		}
		if (preview != null)
			this.preview.draw(g, zoom);
	}

	/**
	 * Given a color this method sets the color for the next shape.
	 * 
	 * @param newColor - a color that becomes the new color of the shape
	 */
	public void setColor(Color newColor) {
		this.color = newColor;
	}

	/**
	 * Given a shape this method sets the current shape variable, which keeps track
	 * of the current shape type to draw.
	 * 
	 * @param newShape - a shape that becomes the new current shape being drawn
	 */
	public void setShape(Shape newShape) {
		this.current = newShape;
	}

	/**
	 * This method gets the value of current shape which is the shape variable
	 * keeping track of the current shape type to draw.
	 * 
	 */
	public Shape getShape() {
		return this.current;
	}

	/**
	 * This method clears the shapes from the panel.
	 */
	public void clearShapes() {
		this.shapes.clear();
		this.repaint();
	}

	/**
	 * Given a boolean value corresponding to whether the shape should be filled
	 * this method sets the filled value to a new boolean.
	 * 
	 * @param isFilled - a boolean value true if the shape should be filled or false
	 *                 if the shape should not be filled
	 */
	public void setFilled(boolean isFilled) {
		this.filled = isFilled;
	}

	/**
	 * This method gets the value of filled which is a boolean value keeping track
	 * of whether the shape should be drawn with a fill or not.
	 * 
	 * @return filled a boolean value keeping track of whether the shape should be
	 *         drawn with a fill or not
	 */
	public boolean getFilled() {
		return this.filled;
	}

	/**
	 * When called (when called by the fillNoFill button) this method changes the
	 * value of the filled variable.
	 */
	public void toggleFilled() {
		filled = !filled;
	}

	/**
	 * When the mouse is pressed, a temporary preview shape is created based on the
	 * currently selected shape type, color, and fill mode. This method uses a
	 * switch statement that depends on the current shape type variable.
	 * 
	 * @param e - a mouse event (the mouse being pressed)
	 */
	public void mousePressed(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();

		switch (current) {
		case LINE:
			preview = new Line((int) (this.x / zoom), (int) (this.y / zoom), (int) (x / zoom), (int) (y / zoom),
					this.color, this.filled);
			break;
		case RECTANGLE:
			preview = new Rectangle((int) (this.x / zoom), (int) (this.y / zoom), 0, 0, this.color, this.filled);
			break;
		case OVAL:
			preview = new Oval((int) (this.x / zoom), (int) (this.y / zoom), 0, 0, this.color, this.filled);
			break;
		}

		this.repaint();

	}

	/**
	 * When the mouse is dragged while holding the button this method resizes the
	 * preview shape: Line - The coordinates saved in the mousePressed method are
	 * the start of the line. The current mouse coordinates are the end of the line.
	 * Rectangle - The coordinates saved in the mousePressed method are the top left
	 * corner of the rectangle. The current mouse coordinates are the bottom right
	 * corner. Oval - The coordinates saved in the mousePressed method are the
	 * location of the Oval. The X and Y distances between that location and the
	 * current mouse coordinates represent the width and height of the oval.
	 * 
	 * e - a mouse event (the mouse being dragged)
	 */
	public void mouseDragged(MouseEvent e) {
		if (preview instanceof Line) {
			preview.resize((int) (e.getX() / zoom), (int) (e.getY() / zoom));
		} else if (preview instanceof Oval) {
			preview.resize((int) ((e.getX() - x) / zoom), (int) ((e.getY() - y) / zoom));
		} else if (preview instanceof Rectangle) {
			preview.resize((int) ((e.getX() - x) / zoom), (int) ((e.getY() - y) / zoom));
		}
		this.repaint();
	}

	/**
	 * When the mouse button is released, the temporary preview shape becomes
	 * permanent by adding it to the panel's list of shapes. The preview shape is
	 * set back to null.
	 * 
	 * @param e - mouse event (the mouse is released)
	 */
	public void mouseReleased(MouseEvent e) {
		shapes.add(preview);
		preview = null;
		this.repaint();
	}

	/**
	 * When the mouse wheel is turned, the image is zoomed in or out. Turning the
	 * wheel up (away from the user) zooms in. Turning it down (toward the user)
	 * zooms out.
	 * 
	 * @param e - mouse wheel event (The mouse wheel is turned)
	 */
	public void mouseWheelMoved(MouseWheelEvent e) {
		e.getWheelRotation();
		if (e.getWheelRotation() < 0) {
			zoom = zoom * 1.2;
		} else if (e.getWheelRotation() > 0) {
			zoom = zoom / 1.2;
		}
		this.repaint();
	}

	/**
	 * This method is not used
	 * 
	 * @param e - mouse event
	 */
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 * This method is not used
	 * 
	 * @param e - mouse event
	 */
	public void mouseExited(MouseEvent e) {

	}

	/**
	 * This method is not used
	 * 
	 * @param e - mouse event
	 */
	public void mouseMoved(MouseEvent e) {

	}

	/**
	 * This method is not used
	 * 
	 * @param e - mouse event
	 */
	public void mouseClicked(MouseEvent e) {

	}

	private static final long serialVersionUID = 1L;
}
