/* Author: Colton Morris
 * Date: November 15 2021 V.1
 * Purpose: The purpose of this program is to create and control simple windows on a canvas 
 * Prof: Andrew Hannum
*/

package MorrisProject5;

import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;

import java.awt.Color;
import java.util.ArrayList;

public class WindowDisplay implements DrawListener {

	// instance variables
	private ArrayList<Window> windows;
	private Draw canvas;

	// inner class windows
	public class Window {

		// instance variables
		private double x;
		private double y;
		private double ogX;
		private double ogY;

		private double height;
		private double width;

		private Color color;

		// window constructor
		public Window(double a, double b, double h, double w, Color c) {
			x = a;
			y = b;

			// ogX and ogY are used to set the windows back to their original position
			ogX = x;
			ogY = y;
			height = h;
			width = w;
			color = c;

		}

		// draws the individual window
		public void draw() {
			canvas.setPenColor(color);
			canvas.filledRectangle(x, y, width, height);

		}

		// checks to see if a point (the mouse) is within the bounds of the rectangle
		// window
		public boolean contains(double a, double b) {

			if ((x + width >= a) && (x - width <= a) && (y + height >= b) && (y - height <= b)) {
				return true;
			}
			return false;

		}

	}

	// window display constructor
	public WindowDisplay(int x, int y) {
		windows = new ArrayList<>();
		canvas = new Draw();
		canvas.setCanvasSize(x, y);
		canvas.enableDoubleBuffering();
		canvas.addListener(this);

	}

	// adds a window to the arraylist of the outer class
	public void addWindow(double a, double b, double h, double w, Color c) {
		windows.add(new Window(a, b, h, w, c));
	}

	// draws all the windows, in reverse order
	public void draw() {

		// reverse order, because we want them to draw with the newest ones on top
		for (int i = windows.size() - 1; i >= 0; i--) {
			windows.get(i).draw();
		}

	}

	// initializes and declares these two
	private double startingX = 0;
	private double startingY = 0;

	@Override
	public void keyPressed(int key) {
		// exits if q is pressed
		if (key == 81) {
			System.exit(0);
		}

		// resets if r is pressed
		if (key == 82) {
			System.out.println("You just hit the r key");
			for (Window w : windows) {
				w.x = w.ogX;
				w.y = w.ogY;
			}

		}

	}

	@Override
	public void mouseDragged(double arg0, double arg1) {

		int index = findTile();

		// calculates the new window position based on how the mouse has moved
		if (windows.get(index).contains(canvas.mouseX(), canvas.mouseY())) {
			windows.get(index).x += (arg0 - startingX);
			windows.get(index).y += (arg1 - startingY);
		}

		// gives us a new starting position after we've done all the above math
		startingX = arg0;
		startingY = arg1;

	}

	@Override
	public void mousePressed(double arg0, double arg1) {
		startingX = arg0;
		startingY = arg1;

		findTile();

	}

	@Override
	public void mouseReleased(double arg0, double arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

		// these are all the functions that make the canvas animation look good`
		draw();

		canvas.show();
		canvas.pause(1);
		canvas.clear();

	}

	@Override
	public void keyReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(char arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(double arg0, double arg1) {
		// TODO Auto-generated method stub

	}

	// this method gets the z function to work, as well as orders them correctly
	// based on the input
	public int findTile() {
		int index = -1;
		for (Window w : windows) {

			// checks all windows;
			if (w.contains(canvas.mouseX(), canvas.mouseY())) {
				index = windows.indexOf(w);

			}

			// we only want to grab the index of the first window that this is true to
			if (index > -1) {
				break;
			}
		}

		windows.add(0, windows.get(index));

		// removes the tile we dont want anymore
		windows.remove(index + 1);

		return index;
	}

}
