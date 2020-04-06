package objects;

import com.sun.org.apache.bcel.internal.generic.GotoInstruction;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	// a group of objects
	// CompositeGObject = parent
	// GObject = child

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		// TODO: Implement this method.
		// add child
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
		// remove child
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		// TODO: Implement this method.
		for(GObject go: gObjects) {
			go.move(dX, dY);
		}
	}
	
	public void recalculateRegion() {
		// TODO: Implement this method.
		// region expand base on all of children
		int minX = 9999;
		int maxX = -9999;
		int minY = 9999;
		int maxY = -9999;
		int maxWidth = -9999;
		int maxHeight = -9999;
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
		this.width = (maxX + maxWidth) - minX;
		this.height = (maxY + maxHeight) - minY;
	}

	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		// call paintObject of all children
		for(GObject go: gObjects) {
			go.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		// call paintLabel of all children
		for(GObject go: gObjects) {
			go.paintLabel(g);
		}
	}
	
}
