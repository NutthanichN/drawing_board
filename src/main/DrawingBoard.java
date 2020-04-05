package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
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
		// TODO: Implement this method.
		gObjects.add(gObject);
		repaint();
//		for(GObject go: gObjects) {
//			System.out.println(go);
//		}
	}
	
	public void groupAll() {
		// TODO: Implement this method.
		// create CompositeGObject then pass GObj as its child
		// multi layer CompositeGObject
	}

	public void deleteSelected() {
		// TODO: Implement this method.
	}
	
	public void clear() {
		// TODO: Implement this method.
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
//		System.out.println("Paint tryyyy");
		for (GObject go : gObjects) {
//			System.out.println("Obj");
//			System.out.println(go);
			go.paint(g);
//			go.paintObject(g);
		}
	}

	class MAdapter extends MouseAdapter {

		// TODO: You need some variables here
		int x = getX();
		int y = getY();
		
		private void deselectAll() {
			// TODO: Implement this method.
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO: Implement this method.
//			for(GObject go: gObjects) {
//				if(go.pointerHit(e.getX(), e.getY())) {
//					go.selected();
//					System.out.println(e.getX() + " " + e.getY());
//				}
//			}
			System.out.println(e.getX() + " " + e.getY());
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO: Implement this method.
			int dX = x - e.getX();
			int dY = y - e.getY();
//			for(GObject go: gObjects) {
//				if(go.selected() == true)
//			}
		}
	}
	
}