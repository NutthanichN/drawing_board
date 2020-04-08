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
		CompositeGObject comGo = new CompositeGObject();
		for(GObject go: gObjects) {
			comGo.add(go);
		}
		gObjects = new ArrayList<GObject>();
		comGo.recalculateRegion();
		gObjects.add(comGo);
		repaint();  // call paint
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

		int oldX;
		int oldY;
		
		private void deselectAll() {
			if(target != null) {
				target.deselected();
				target = null;
			}
			repaint();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			int currentX = e.getX();
			int currentY = e.getY();
			System.out.println("Press: "+ e.getX() + " " + e.getY());
			deselectAll();
			for(int i = (gObjects.size() - 1); i >= 0; i--) {
				if(gObjects.get(i).pointerHit(currentX, currentY)) {
					target = gObjects.get(i);
					target.selected();
					break;
				}
			}
			oldX = currentX;
			oldY = currentY;
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int currentX = e.getX();
			int currentY = e.getY();
			int dX = currentX - oldX;
			int dY = currentY - oldY;
			if(target != null) {
				target.move(dX, dY);
				repaint();
				oldX = currentX;
				oldY = currentY;
			}
		}
	}
	
}