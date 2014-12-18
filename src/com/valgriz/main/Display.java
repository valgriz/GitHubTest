package com.valgriz.main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class Display {
	private int x, y, bx, by, blockSize;
	private boolean[][] percLog;

	public Display() {
		blockSize = 20;
		bx = by = -1;
		percLog = new boolean[10][10];
	}

	public void update() {

	}

	public void clicked(int mouseX, int mouseY) {

		if (mouseX > blockSize && mouseX < (208 - blockSize)) {
			if (mouseY > blockSize && mouseY < (202 - blockSize)) {
				System.out.println(mouseX + ", " + mouseY);
				bx = (mouseX / blockSize);
				by = (mouseY / blockSize);
				System.out.println("bx:" + bx + "  by:" + by);
				percLog[bx][by] = true;
			}
		} else {

			System.out.println("Perc: " + checkPercent());
		}

		if (checkPercent()) {
			JOptionPane.showMessageDialog(null, "**Claps**");
			JOptionPane.showMessageDialog(null, "Okay, 2%");
			JOptionPane.showInputDialog("What is your name?");
		}
	}

	public boolean checkPercent() {
		if (percLog[1][1]) {
			for (int i = 1; i < 8; i += 2) {
				for (int j = 1; j < 8; j += 2) {
					if (!percLog[i][j])
						return false;
					if (!percLog[i + 1][j + 1])
						return false;
					if (percLog[i + 1][j])
						return false;
					if (percLog[i][j + 1])
						return false;
				}
			}
		} else {
			for (int i = 1; i < 8; i += 2) {
				for (int j = 1; j < 8; j += 2) {
					if (percLog[i][j])
						return false;
					if (percLog[i + 1][j + 1])
						return false;
					if (!percLog[i + 1][j])
						return false;
					if (!percLog[i][j + 1])
						return false;
				}
			}
		}
		return true;
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				if (percLog[i][j])
					g.fillRect(blockSize * i, blockSize * j, blockSize,
							blockSize);
				g.drawRect(blockSize * i, blockSize * j, blockSize, blockSize);

			}
		}
	}

}
