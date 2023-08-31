package app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Particle {
	Point2D particleCenter;
	List<Line2D> rays;
	double particleSize;
	int rayThickness;
	int rayInterval;
	Color color;

	Particle(Point2D particleCenter, double particleSize, int rayThickness, int rayInterval, Color color) {
		resetParticle(particleCenter, particleSize, rayThickness, rayInterval, color);
	}

	public void resetParticle(Point2D particleCenter, double particleSize, int rayThickness, int rayInterval,
			Color color) {
		this.particleSize = particleSize;
		this.rayThickness = rayThickness;
		this.rayInterval = rayInterval;
		this.color = color;
		initializeParticleCenter(particleCenter);
	}

	private void initializeParticleCenter(Point2D particle) {
		this.rays = new ArrayList<>();
		this.particleCenter = particle;
		double x = particleCenter.getX(), y = particleCenter.getY();
		for (int i = 0; i <=360; i += rayInterval) {
			double rads = Math.toRadians(i);
			Point2D end = new Point2D.Double(x + particleSize * Math.cos(rads), y + particleSize * Math.sin(rads));
			rays.add(new Line2D.Double(this.particleCenter, end));
		}
	}

	public void setParticleCenter(Point2D newCenter) {
		this.particleCenter = newCenter;
		double x = particleCenter.getX(), y = particleCenter.getY();
		int idx = 0;
		for (int i =0; i <= 360; i += rayInterval) {
			double rads = Math.toRadians(i);
			Point2D end = new Point2D.Double(x + particleSize * Math.cos(rads), y + particleSize * Math.sin(rads));
			rays.get(idx++).setLine(this.particleCenter, end);
		}
	}

	public void renderParticle(Graphics2D g2d) {
		int x = (int) (particleCenter.getX() - particleSize / 2);
		int y = (int) (particleCenter.getY() - particleSize / 2);
		Color ogColor = g2d.getColor();
		float[] arr = color.getRGBColorComponents(null);
		g2d.setColor(new Color(arr[0], arr[1], arr[2], 0.5f));
		g2d.drawOval(x, y, (int) particleSize, (int) particleSize);
		g2d.setStroke(new BasicStroke(rayThickness));
		for (Line2D ray : rays)
			g2d.drawLine((int) ray.getX1(), (int) ray.getY1(), (int) ray.getX2(), (int) ray.getY2());
		g2d.setColor(ogColor);
	}

	public void updateEndPointOfRays(List<Line2D> walls) {
		for (Line2D ray : rays) {
			double minDistance = Double.MAX_VALUE;
			Point2D closest = null;
			for (Line2D wall : walls) {
				Point2D intersection = intersects(ray, wall);
				if (intersection == null)
					continue;
				if (ray.getP2().distance(intersection) < minDistance) {
					minDistance = ray.getP2().distance(intersection);
					closest = intersection;
				}
			}
			if (closest != null)
				ray.setLine(ray.getP1(), closest);
		}
	}

	private Point2D intersects(Line2D ray, Line2D line) {
		double x1 = line.getX1(), x2 = line.getX2(), y1 = line.getY1(), y2 = line.getY2();
		double x3 = ray.getX1(), x4 = ray.getX2(), y3 = ray.getY1(), y4 = ray.getY2();
		double denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
		double nom1 = (x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4);
		double nom2 = (x1 - x3) * (y1 - y2) - (y1 - y3) * (x1 - x2);
		double t = nom1 / denom, u = nom2 / denom;
		return (t >= 0 && t <= 1 && u >= 0) ? new Point2D.Double(x1 + t * (x2 - x1), y1 + t * (y2 - y1)) : null;
	}

}
