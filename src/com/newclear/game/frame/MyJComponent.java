package com.newclear.game.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyJComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	private Flag f = new Flag();
	private Graphics2D g2d;
	private Point p1 = new Point(0, 0);
	private Point p2 = new Point(0, 0);
	private int[][] array = this.f.RandomArr();

	public Flag getF() {
		return this.f;
	}

	public MyJComponent() {
		initialize();
	}

	private void initialize() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					MyJComponent.this.doIt(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	protected void paintComponent(Graphics g) {
		Point p = this.f.getP1();
		super.paintComponent(g);
		drawMap(g);
		drawSqual(g, p);
	}

	private boolean drawSqual(Graphics g, Point p1) {
		Graphics2D g2d = (Graphics2D) g;
		if (p1 == null) {
			return false;
		}
		g2d.setPaint(Color.RED);
		g.drawRect(p1.x * 50 + 50, p1.y * 50 + 50, 48, 48);
		return true;
	}

	public void drawMap(Graphics g) {
		this.g2d = ((Graphics2D) g);
		String s = null;
		try {
			this.g2d.drawImage(ImageIO.read(getClass().getResource("/flags/background2.jpg")), -20, -20, this);
			for (int i = 1; i <= this.f.getSize() + 1; i++) {
				for (int j = 1; j <= this.f.getSize() + 1; j++) {
					int k = this.array[i][j];
					switch (k) {
					case 0:
						s = "";
						break;
					case 1:
						s = "/flags/1.png";
						break;
					case 2:
						s = "/flags/2.png";
						break;
					case 3:
						s = "/flags/3.png";
						break;
					case 4:
						s = "/flags/4.png";
						break;
					case 5:
						s = "/flags/5.png";
						break;
					case 6:
						s = "/flags/6.png";
						break;
					case 7:
						s = "/flags/7.png";
						break;
					case 8:
						s = "/flags/8.png";
						break;
					case 9:
						s = "/flags/9.png";
					}

					this.g2d.drawImage(ImageIO.read(getClass().getResource(s)), 50 + j * 50, 50 + i * 50, this);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doIt(MouseEvent e) throws Exception {
		this.f.setP1(new Point(this.f.transfer(e.getX(), e.getY())));
		if ((this.p1.x == 0) && (this.p2.x == 0)) {
			this.p1 = this.f.transfer(e.getX(), e.getY());
			repaint();
			if ((this.p1.x == -1) || (this.p1.y == -1))
				this.p1.x = 0;
		} else if ((this.p1.x != 0) && (this.p2.x == 0)) {
			this.p2 = this.f.transfer(e.getX(), e.getY());
			if ((this.p2.x == -1) || (this.p2.y == -1)) {
				this.p1.x = 0;
				this.p2.x = 0;
			}
			if (this.f.remove(this.p1, this.p2, this.f.isMathced(this.p1, this.p2))) {
				this.p1.x = 0;
				this.p2.x = 0;
				repaint();
			} else {
				this.p1.x = this.p2.x;
				this.p1.y = this.p2.y;
				this.p2.x = 0;
				repaint();
			}
		}
	}
}