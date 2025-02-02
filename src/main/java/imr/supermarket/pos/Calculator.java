package imr.supermarket.pos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
	JPanel[] row = new JPanel[5];
	JButton[] button = new JButton[19];
	String[] buttonString = { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2",
			"3", "*", ".", "/", "C", "√", "+/-", "=", "0" };
	
	int[] dimW = { 300, 45, 100, 90 };
	int[] dimH = { 35, 40 };
	
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
	Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
	Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
	Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);
	
	boolean[] function = new boolean[4];
	
	double[] temp = { 0, 0 };
	
	JTextArea display = new JTextArea(1, 20);
	Font font = new Font("Times new Roman", Font.BOLD, 14);

	Calculator() {
		super("Calculator");
		setDesign();
		setSize(380, 250);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		GridLayout grid = new GridLayout(5, 5);
		setLayout(grid);

		for (int i = 0; i < 4; i++)

			function[i] = false;

		FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);
		for (int i = 0; i < 5; i++)
			row[i] = new JPanel();
		row[0].setLayout(f1);
		for (int i = 1; i < 5; i++)
			row[i].setLayout(f2);

		for (int i = 0; i < 19; i++) {
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
		}

		display.setFont(font);
		display.setEditable(false);
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		display.setPreferredSize(displayDimension);
		for (int i = 0; i < 14; i++)
			button[i].setPreferredSize(regularDimension);
		for (int i = 14; i < 18; i++)
			button[i].setPreferredSize(rColumnDimension);
		button[18].setPreferredSize(zeroButDimension);

		row[0].add(display);
		add(row[0]);

		for (int i = 0; i < 4; i++)
			row[1].add(button[i]);
		row[1].add(button[14]);
		add(row[1]);

		for (int i = 4; i < 8; i++)
			row[2].add(button[i]);
		row[2].add(button[15]);
		add(row[2]);

		for (int i = 8; i < 12; i++)
			row[3].add(button[i]);
		row[3].add(button[16]);
		add(row[3]);

		row[4].add(button[18]);
		for (int i = 12; i < 14; i++)
			row[4].add(button[i]);
		row[4].add(button[17]);
		add(row[4]);

		setVisible(true);
	}

	public void clear() {
		try {
			display.setText("");
			for (int i = 0; i < 4; i++)

				function[i] = false;
			for (int i = 0; i < 2; i++)
				temp[i] = 0;
		} catch (NullPointerException e) {
		}
	}

	public void getSqrt() {
		try {
			double value = Math.sqrt(Double.parseDouble(display.getText()));
			display.setText(Double.toString(value));
		} catch (NumberFormatException e) {
		}
	}

	public void getPosNeg() {
		try {
			double value = Double.parseDouble(display.getText());
			if (value != 0) {
				value = value * (-1);
				display.setText(Double.toString(value));
			} else {
			}
		} catch (NumberFormatException e) {
		}
	}

	public void getResult() {
		double result = 0;
		temp[1] = Double.parseDouble(display.getText());
		String temp0 = Double.toString(temp[0]);
		String temp1 = Double.toString(temp[1]);
		try {
			if (temp0.contains("-")) {
				String[] temp00 = temp0.split("-", 2);
				temp[0] = (Double.parseDouble(temp00[1]) * -1);
			}
			if (temp1.contains("-")) {
				String[] temp11 = temp1.split("-", 2);
				temp[1] = (Double.parseDouble(temp11[1]) * -1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (function[2] == true)
				result = temp[0] * temp[1];
			else if (function[3] == true)
				result = temp[0] / temp[1];
			else if (function[0] == true)
				result = temp[0] + temp[1];
			else if (function[1] == true)
				result = temp[0] - temp[1];
			display.setText(Double.toString(result));
			for (int i = 0; i < 4; i++)

				function[i] = false;
		} catch (NumberFormatException e) {
		}
	}

	public final void setDesign() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == button[0])
			display.append("7");
		if (ev.getSource() == button[1])
			display.append("8");
		if (ev.getSource() == button[2])
			display.append("9");
		if (ev.getSource() == button[3]) {
			temp[0] = Double.parseDouble(display.getText());

			function[0] = true;
			display.setText("");
		}
		if (ev.getSource() == button[4])
			display.append("4");
		if (ev.getSource() == button[5])
			display.append("5");
		if (ev.getSource() == button[6])
			display.append("6");
		if (ev.getSource() == button[7]) {
			temp[0] = Double.parseDouble(display.getText());

			function[1] = true;
			display.setText("");
		}
		if (ev.getSource() == button[8])
			display.append("1");
		if (ev.getSource() == button[9])
			display.append("2");
		if (ev.getSource() == button[10])
			display.append("3");
		if (ev.getSource() == button[11]) {
			temp[0] = Double.parseDouble(display.getText());

			function[2] = true;
			display.setText("");
		}
		if (ev.getSource() == button[12])
			display.append(".");
		if (ev.getSource() == button[13]) {
			temp[0] = Double.parseDouble(display.getText());

			function[3] = true;
			display.setText("");
		}
		if (ev.getSource() == button[14])
			clear();
		if (ev.getSource() == button[15])
			getSqrt();
		if (ev.getSource() == button[16])
			getPosNeg();
		if (ev.getSource() == button[17])
			getResult();
		if (ev.getSource() == button[18])
			display.append("0");
	}

	public static void main(String[] arguments) {
		new Calculator();
	}
}
