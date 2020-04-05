package objects;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends GObject {

	private Color color;
	
	public Oval(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}

	public void paintOval(Graphics g) {
		System.out.println(1);
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}

	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
//		System.out.println("Paint Object Oval");
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		int left = x - width;
		int bottom = y - (height / 2);
//		g.setColor(Color.black);
		g.drawString("Oval", left, bottom);
	}
	
}
