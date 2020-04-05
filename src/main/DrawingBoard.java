package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import com.sun.org.apache.bcel.internal.generic.GotoInstruction;
import objects.*;

public class DrawingBoard extends JPanel {

	private MouseAdapter mouseAdapter; 
	private List<GObject> gObjects;
	private GObject target;
	
	private int gridSize = 10;
	
	public DrawingBoard() {
		gObjects = new ArrayList<GObject>();
		mouseAdapter = new MAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setPreferredSize(new Dimension(800, 600));
	}
	
	public void addGObject(GObject gObject) {
		gObjects.add(gObject);
		repaint();
	}
	
	public void groupAll() {
		// TODO: Implement this method.
		// create CompositeGObject then pass GObj as its child
		// multi layer CompositeGObject
	}

	public void deleteSelected() {
		gObjects.remove(target);
		repaint();
	}
	
	public void clear() {
		gObjects = new ArrayList<GObject>();
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintGrids(g);
		paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintGrids(Graphics g) {
		g.setColor(Color.lightGray);
		int gridCountX = getWidth() / gridSize;
		int gridCountY = getHeight() / gridSize;
		for (int i = 0; i < gridCountX; i++) {
			g.drawLine(gridSize * i, 0, gridSize * i, getHeight());
		}
		for (int i = 0; i < gridCountY; i++) {
			g.drawLine(0, gridSize * i, getWidth(), gridSize * i);
		}
	}

	private void paintObjects(Graphics g) {
		for (GObject go : gObjects) {
			go.paint(g);
		}
	}

	class MAdapter extends MouseAdapter {

		// TODO: You need some variables here
		int x;
		int y;
		
		private void deselectAll() {
			for(GObject go: gObjects) {
				go.deselected();
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
//			System.out.println(e.getX() + " " + e.getY());
			this.x = e.getX();
			this.y = e.getY();
			deselectAll();
			for(GObject go: gObjects) {
				if(go.pointerHit(x, y)) {
					target = go;
				}
			}
			target.selected();
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO: Fix bug.
			System.out.println(x + " " + y);
			int dX = e.getX();
			int dY = e.getY();
//			System.out.println(e.getX()+ " " + e.getY());
			System.out.println("dX: " + dX + " dY: " + dY);
			target.move(dX, dY);
//			for(GObject go: gObjects) {
//				go.move(dX, dY);
//			}
			repaint();
		}
	}
	
}