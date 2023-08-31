package app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Simulation extends JPanel implements MouseMotionListener {
	List<Line2D> walls;
	Particle particle;
	BasicStroke wallThickness;
	Color wallColor;
	int maxSize;

	Simulation(Particle particle, List<Line2D> walls, int thickness, Color backgroundColor, Color wallColor,
			int maxSize) {
		this.maxSize = maxSize;
		this.addMouseMotionListener(this);
		resetSimulation(particle, walls, thickness, backgroundColor, wallColor);
	}

	public void resetSimulation(Particle particle, List<Line2D> walls, int thickness, Color background, Color wallColor) {
		this.particle = particle;
		this.walls = walls;
		this.wallColor = wallColor;
		this.wallThickness = new BasicStroke(thickness);
		this.setBackground(background);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(wallColor);
		particle.renderParticle(g2d);
		g2d.setStroke(wallThickness);
		for (Line2D wall : walls)
			g2d.drawLine((int) wall.getX1(), (int) wall.getY1(), (int) wall.getX2(), (int) wall.getY2());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.particle.setParticleCenter(e.getPoint());
		this.particle.updateEndPointOfRays(walls);
		repaint();
	}

}
