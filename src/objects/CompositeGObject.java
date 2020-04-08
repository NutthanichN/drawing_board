package objects;

import com.sun.org.apache.bcel.internal.generic.GotoInstruction;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
		System.out.println("Add child");
		for(GObject go: gObjects) {
			if(selected) {
				go.selected();
			}
			System.out.println(go);
		}
		System.out.println("*--------------------------*");
	}

	public void remove(GObject gObject) {
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		super.move(dX, dY);
		for(GObject go: gObjects) {
			go.move(dX, dY);
		}
		recalculateRegion();
	}
	
	public void recalculateRegion() {
		// TODO: Fix.
		// region expand base on all of children
		// left-top most obj , right-bottom most obj
		int minX, minY, maxX, maxY, maxHeight, maxWidth;
		maxX = maxY = maxHeight = maxWidth = -9999;
		minX = minY = 9999;
		for(GObject go: gObjects) {
			if(go.x < minX) {
				minX = go.x;
			}
			if(go.x > maxX) {
				maxX = go.x;
				maxWidth = go.width;
			}
			if(go.y < minY) {
				minY = go.y;
			}
			if(go.y > maxY) {
				maxY = go.y;
				maxHeight = go.height;
			}
		}
		this.x = minX;
		this.y = minY;
//		System.out.println("W0" + " " + width+ " "+ "H0" + " " + height);

		this.width = (maxX + maxWidth) - minX;
		this.height = (maxY + maxHeight) - minY;
//		if(((maxX + maxWidth) - minX) > width) {
//			this.width = (maxX + maxWidth) - minX;
//		}
//		if(((maxY + maxHeight) - minY) > height) {
//			this.height = (maxY + maxHeight) - minY;
//		}

		System.out.println("min" + " " +  x + " "+ y);
		System.out.println("max" + " " +  (x + width) + " "+ (y + height));
		System.out.println("W" + " " + width+ " "+ "H" + " " + height);
	}

	@Override
	public void paintObject(Graphics g) {
		for(GObject go: gObjects) {
			go.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		int left = x;
		int bottom = y + height + 20;
		g.drawString("Group", left, bottom);
	}
	
}
