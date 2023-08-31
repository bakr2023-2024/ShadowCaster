package app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Mainframe extends JFrame {
	CardLayout cl = new CardLayout();
	JPanel contPanel;

	public Mainframe() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		this.setResizable(false);
		contPanel = new JPanel();
		contPanel.setLayout(cl);
		Prompt prompt = new Prompt();
		Point2D center = new Point2D.Double(getWidth() / 2, getHeight() / 2);
		Particle particle = new Particle(center, prompt.particleSize, prompt.rayThickness, prompt.rayInterval,
				prompt.particle);
		Simulation simulation = new Simulation(particle, prompt.initWalls(this.getWidth()), prompt.wallNumber,
				prompt.background, prompt.wall, getWidth());
		contPanel.add(prompt, "1");
		contPanel.add(simulation, "2");
		prompt.startButton.addActionListener(e -> {
			Particle part = new Particle(center, prompt.particleSize, prompt.rayThickness, prompt.rayInterval,
					prompt.particle);
			simulation.resetSimulation(part, prompt.initWalls(this.getWidth()), prompt.wallThickness, prompt.background,
					prompt.wall);
			cl.show(contPanel, "2");
		});
		cl.show(contPanel, "1");
		simulation.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('b'), "back");
		simulation.getActionMap().put("back", new BackAction());
		getContentPane().add(contPanel);
		setVisible(true);
	}

	private class BackAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			cl.show(contPanel, "1");
		}
	}
}
