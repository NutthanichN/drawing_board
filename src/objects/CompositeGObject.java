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
		int minX, minY, maxX, maxY;
		maxX = maxY = -9999999;
		minX = minY = 9999999;
		for(GObject go: gObjects) {
			if(go.x < minX) {
				minX = go.x;
			}
			if(go.y < minY) {
				minY = go.y;
			}
		}
		for(GObject go: gObjects) {
			if((go.x + go.width) > maxX) {
				maxX = go.x + go.width;
			}
			if((go.y + go.height) > maxY) {
				maxY = go.y + go.height;
			}
		}
		this.x = minX;
		this.y = minY;
		this.width = maxX - x;
		this.height = maxY - y;
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
