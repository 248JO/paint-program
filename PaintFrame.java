package assign10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class is a container that holds all of the graphical components for the
 * Paint GUI
 * 
 * @author Judy Ojewia
 * @param November 30, 2023
 */

public class PaintFrame extends JFrame implements ActionListener {

	private JButton chooseColor;
	private JButton fillNoFill;
	private JButton line;
	private JButton oval;
	private JButton rectangle;

	private JMenuItem save;
	private JMenuItem clear;
	private JMenuItem close;

	private PaintPanel paintPanel;

	/**
	 * Constructs a Paint Frame initializes and provides tool tips for every button
	 * and menu item and adds action listeners for each button and menu item.
	 */
	public PaintFrame() {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		chooseColor = new JButton(new ImageIcon("src/assign10/colorIcon.png"));
		fillNoFill = new JButton(new ImageIcon("src/assign10/fillIcon.png"));
		line = new JButton(new ImageIcon("src/assign10/lineIcon.png"));
		oval = new JButton(new ImageIcon("src/assign10/ovalIcon.png"));
		rectangle = new JButton(new ImageIcon("src/assign10/rectIcon.png"));

		save = new JMenuItem("Save");
		clear = new JMenuItem("Clear");
		close = new JMenuItem("Close");

		chooseColor.setToolTipText("Choose a new color for your shape");
		fillNoFill.setToolTipText("Fills or outlines your shape");
		line.setToolTipText("Draw a line");
		oval.setToolTipText("Draw an oval");
		rectangle.setToolTipText("Draw a rectangle");
		save.setToolTipText("Save canvas");
		clear.setToolTipText("Clear Canvas");
		close.setToolTipText("Close the application");

		this.paintPanel = new PaintPanel();
		this.setContentPane(this.paintPanel);
		paintPanel.setPreferredSize(new Dimension(700, 600));

		chooseColor.setPreferredSize(new Dimension(100, 100));
		fillNoFill.setPreferredSize(new Dimension(100, 100));
		line.setPreferredSize(new Dimension(100, 100));
		oval.setPreferredSize(new Dimension(100, 100));
		rectangle.setPreferredSize(new Dimension(100, 100));

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.setToolTipText("More Options");
		menu.add(this.save);
		menu.add(this.clear);
		menu.add(this.close);
		menubar.add(menu);
		this.setJMenuBar(menubar);

		JPanel panel = new JPanel();
		this.setTitle("Paint");
		this.setPreferredSize(new Dimension(900, 900));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(600, 110));

		buttonPanel.add(chooseColor);
		buttonPanel.add(fillNoFill);
		buttonPanel.add(line);
		buttonPanel.add(oval);
		buttonPanel.add(rectangle);

		this.chooseColor.addActionListener(this);
		this.fillNoFill.addActionListener(this);
		this.line.addActionListener(this);
		this.oval.addActionListener(this);
		this.rectangle.addActionListener(this);

		this.save.addActionListener(this);
		this.close.addActionListener(this);
		this.clear.addActionListener(this);

		panel.add(buttonPanel, BorderLayout.NORTH);
		panel.add(paintPanel, BorderLayout.CENTER);
		setContentPane(panel);
		this.pack();
	}

	/**
	 * This is the method that is invoked when a button or menu item is selected.
	 * When a button is selected a new color is chosen, a line, oval, or rectangle
	 * is chosen, or the shape is changed from an outline to being filled. When a
	 * menu item is selected the file is saved, the canvas is cleared, or the
	 * application is closed.
	 * 
	 * @param e an Action Event object that represents the event of the button or
	 *          menu item being selected.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JMenuItem) {
			JMenuItem menuItem = (JMenuItem) e.getSource();
			if (menuItem == this.save) {
				JFileChooser chooser = new JFileChooser();
				chooser.setSelectedFile(new File("myCanvas.jpg"));
				chooser.setFileFilter(new FileNameExtensionFilter("JPG  Images", "jpg"));
				chooser.setDialogTitle("Select the location for the new file.");
				if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "Save file cancelled.");
					return;
				}
				BufferedImage img = new BufferedImage(this.paintPanel.getWidth(), this.paintPanel.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				this.paintPanel.paint(img.getGraphics());
				try {
					ImageIO.write(img, "jpg", chooser.getSelectedFile());
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "The drawing cannot be written to file.");
				}
			} else if (menuItem == this.clear) {
				this.paintPanel.clearShapes();
			} else if (menuItem == this.close) {
				this.dispose();
			}
		} else if (e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			if (button == this.chooseColor) {
				Color newColor = JColorChooser.showDialog(this, "Select a color", Color.BLACK);
				if (newColor != null) {
					this.paintPanel.setColor(newColor);
				}
			} else if (button == this.line) {
				paintPanel.setShape(Shape.LINE);

			} else if (button == this.oval) {
				paintPanel.setShape(Shape.OVAL);

			} else if (button == this.rectangle) {
				paintPanel.setShape(Shape.RECTANGLE);

			} else if (button == this.fillNoFill) {
				paintPanel.toggleFilled();

			}
		}
	}

	private static final long serialVersionUID = 1L;

}
