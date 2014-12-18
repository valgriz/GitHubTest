package com.valgriz.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Main extends Canvas implements MouseListener, Runnable {

	private JFrame frame;
	private Thread thread;
	private Display display;

	private boolean running;

	public static void main(String[] args) {
		new Main().startThread();
	}

	public Main() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(208, 228);
		frame.add(this);
		frame.setTitle("2%");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		display = new Display();

		addMouseListener(this);

	}

	public void startThread() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void update() {
		display.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		{
			g.setColor(Color.white);
			g.fillRect(0, 0, 208, 228);
			display.paint(g);

		}
		g.dispose();
		bs.show();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double tps = 1000000000D / 60;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			boolean shouldRender = false;
			delta += (now - lastTime) / tps;
			lastTime = now;
			while (delta >= 1) {
				ticks++;
				delta -= 1;
				shouldRender = true;
			}

			if (shouldRender) {
				frames++;
				update();
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(frames + ":" + ticks);
				frames = 0;
				ticks = 0;
			}
			render();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		display.clicked((int) e.getX(), (int) e.getY());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		display.clicked((int) e.getX(), (int) e.getY());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
