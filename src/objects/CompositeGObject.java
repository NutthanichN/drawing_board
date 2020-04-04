package objects;

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
	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
		// remove child
	}

	@Override
	public void move(int dX, int dY) {
		// TODO: Implement this method.
	}
	
	public void recalculateRegion() {
		// TODO: Implement this method.
		// region expand base on all of children
	}

	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		// call paintObject of all children
	}

	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		// call paintLabel of all children
	}
	
}
