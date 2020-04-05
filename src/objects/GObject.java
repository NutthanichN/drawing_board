package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GObject {

	protected boolean selected;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public GObject(int x, int y, int width, int height) {
		this.selected = false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean pointerHit(int pointerX, int pointerY) {
		// TODO: Implement this method.
		// check if the cursor is in the area of object or not
		int top = y + (height / 2);
		int bottom = y - (height / 2);
		int left = x - (width / 2);
		int right = x + (width / 2);
		if(pointerX >= left && pointerX <= right) {
			return pointerY >= bottom && pointerY <= top;
		}
		return false;
	}
	
	public void selected() {
		// TODO: Implement this method.
		this.selected = true;
	}
	
	public void deselected() {
		// TODO: Implement this method.
		this.selected = false;
	}
	
	public void move(int dX, int dY) {
		// TODO: Implement this method.
		// change obj position using dx, dy
		this.x += dX;
		this.y += dY;
	}

	public final void paint(Graphics g) {
		/* Example of template method pattern */
		paintObject(g);
		paintRegion(g);
		paintLabel(g);
	}

	public void paintRegion(Graphics g) {
		/* Set color */
		Color color = selected ? Color.red : Color.black;
		g.setColor(color);
		/* Set size */
		int size = selected ? 3 : 1;
		/* Draw a region */
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(size));
		g2d.drawRect(x, y, width, height);
	}

	public abstract void paintObject(Graphics g);

	public abstract void paintLabel(Graphics g);
	
}
