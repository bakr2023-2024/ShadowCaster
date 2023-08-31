package app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Prompt extends JPanel {
	Color background = Color.black, wall = Color.white, particle = Color.white;
	int particleSize = 5, rayThickness = 1, rayInterval = 1, wallNumber = 0, wallThickness = 1;
	JButton startButton;
	Random rand = new Random();

	public List<Line2D> initWalls(int s) {
		List<Line2D> walls = new ArrayList<>();
		walls = IntStream.range(0, wallNumber)
				.mapToObj(i -> new Line2D.Double(Math.abs(rand.nextInt(s) - wallThickness),
						Math.abs(rand.nextInt(s) - wallThickness), Math.abs(rand.nextInt(s) - wallThickness),
						Math.abs(rand.nextInt(s) - wallThickness)))
				.collect(Collectors.toList());
		walls.add(new Line2D.Double(0, 0, s, 0));
		walls.add(new Line2D.Double(0, 0, 0, s));
		walls.add(new Line2D.Double(0, s - wallThickness, s, s - wallThickness));
		walls.add(new Line2D.Double(s - wallThickness, 0, s - wallThickness, s));
		return walls;
	}

	public Prompt() {
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		JButton btn2 = new JButton("select");
		btn2.addActionListener(e -> {
			wall = JColorChooser.showDialog(null, "Choose wall color", Color.white);
			btn2.setBackground(wall);
		});
		btn2.setBounds(194, 232, 85, 21);
		add(btn2);

		JButton btn3 = new JButton("select");
		btn3.setBounds(194, 263, 85, 21);
		add(btn3);
		btn3.addActionListener(e -> {
			particle = JColorChooser.showDialog(null, "Choose particle color", Color.white);
			btn3.setBackground(particle);
		});
		JLabel lblNewLabel = new JLabel("Particle Color");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(99, 263, 85, 21);
		add(lblNewLabel);

		JLabel lblWallColor = new JLabel("Wall Color");
		lblWallColor.setForeground(new Color(255, 255, 255));
		lblWallColor.setBounds(99, 232, 85, 21);
		add(lblWallColor);

		JLabel lblRayInterval = new JLabel("Ray interval");
		lblRayInterval.setForeground(new Color(255, 255, 255));
		lblRayInterval.setBounds(99, 311, 85, 21);
		add(lblRayInterval);

		JSpinner spinner = new JSpinner();
		spinner.setBackground(new Color(255, 255, 255));
		spinner.addChangeListener(e -> rayInterval = (Integer) spinner.getValue());
		spinner.setModel(new SpinnerNumberModel(rayInterval, 1, 360, 1));
		spinner.setBounds(194, 312, 45, 20);
		add(spinner);

		JLabel lblNewLabel_3 = new JLabel("Ray thickness");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(99, 404, 62, 13);
		add(lblNewLabel_3);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.addChangeListener(e -> rayThickness = (Integer) spinner_1.getValue());
		spinner_1.setModel(new SpinnerNumberModel(rayThickness, 1, 5, 1));
		spinner_1.setBounds(194, 401, 45, 20);
		add(spinner_1);

		JLabel lblNewLabel_2_1 = new JLabel("Particle Size");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setBounds(99, 358, 85, 21);
		add(lblNewLabel_2_1);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.addChangeListener(e -> particleSize = (Integer) spinner_2.getValue());
		spinner_2.setModel(new SpinnerNumberModel(particleSize, 5, 20, 1));
		spinner_2.setBounds(194, 359, 45, 20);
		add(spinner_2);

		JLabel lblNewLabel_3_1 = new JLabel("Wall thickness");
		lblNewLabel_3_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_1.setBounds(99, 444, 73, 13);
		add(lblNewLabel_3_1);

		JSpinner spinner_1_1 = new JSpinner();
		spinner_1_1.addChangeListener(e -> wallThickness = (Integer) spinner_1_1.getValue());
		spinner_1_1.setModel(new SpinnerNumberModel(wallThickness, 1, 10, 1));
		spinner_1_1.setBounds(194, 441, 45, 20);
		add(spinner_1_1);
		JLabel lblNewLabel_3_1_1 = new JLabel("No. of walls");
		lblNewLabel_3_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_1_1.setBounds(99, 484, 85, 13);
		add(lblNewLabel_3_1_1);

		JSpinner spinner_1_1_1 = new JSpinner();
		spinner_1_1_1.addChangeListener(e -> wallNumber = (Integer) spinner_1_1_1.getValue());
		spinner_1_1_1.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		spinner_1_1_1.setBounds(194, 481, 45, 20);
		add(spinner_1_1_1);

		JLabel lblBackground = new JLabel("Background Color");
		lblBackground.setForeground(new Color(255, 255, 255));
		lblBackground.setBounds(99, 201, 85, 21);
		add(lblBackground);

		JButton btn1 = new JButton("select");
		btn1.addActionListener(e -> {
			background = JColorChooser.showDialog(null, "Choose background color", Color.black);
			btn1.setBackground(background);
		});
		btn1.setBounds(194, 201, 85, 21);
		add(btn1);

		startButton = new JButton("Start 2D Raycaster");
		startButton.setForeground(new Color(0, 0, 255));
		startButton.setBackground(new Color(255, 255, 0));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		startButton.setFont(new Font("Helvetica", Font.BOLD, 25));
		startButton.setBounds(370, 427, 280, 84);
		add(startButton);

		JLabel lblNewLabel_1 = new JLabel("press B in sim mode to return back here");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(370, 400, 280, 17);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("2D Raycaster");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(370, 170, 280, 110);
		add(lblNewLabel_2);

		JLabel lblNewLabel_4 = new JLabel("By: Bakr Mohamed Bakr");
		lblNewLabel_4.setForeground(new Color(0, 255, 0));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(407, 281, 213, 13);
		add(lblNewLabel_4);

	}
}
