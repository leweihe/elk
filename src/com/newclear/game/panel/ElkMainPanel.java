package com.newclear.game.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.newclear.game.frame.ConnectionOrcl;
import com.newclear.game.frame.TimeCtrl;
import com.newclear.game.object.Flag;

public class ElkMainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String PLAYBACKGROUND = "/flags/background.jpg";

	private Graphics2D g2d;
	private Point p1 = new Point(0, 0);
	private Point p2 = new Point(0, 0);
	private Point p11 = new Point(0, 0);
	private Point p22 = new Point(0, 0);
	private Flag f = new Flag();
	private int[][] array = this.f.RandomArr();
	private SouthPanel southPanel = null;

	private int mission;
	private TimeCtrl timeCtrl = new TimeCtrl();

	public Flag getF() {
		return this.f;
	}

	public JPanel getSouthPanel() {
		if (this.southPanel == null) {
			this.southPanel = new SouthPanel();
			add(this.southPanel, "South");
		}
		return this.southPanel;
	}

	public ElkMainPanel() {
		initialize();
	}

	public void insert(String name, int score) throws ClassNotFoundException, SQLException {
		String sql = "insert into top (name,score) values (?,?)";

		Connection conn = new ConnectionOrcl().getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, score);
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
	}

	protected void paintComponent(Graphics g) {
		if (this.f == null) {
			return;
		}
		try {
			if (this.f.isRelist()) {
				reList();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		Point p = this.f.getP1();
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(getClass().getResource(PLAYBACKGROUND)), -100, -100, this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		drawMap(g);
		drawline(g);
		drawSqual(g, p);
		if (Flag.bool) {
			try {
				drawR(g);
				repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getMission() {
		return this.mission;
	}

	public void setMission(int mission) {
		this.mission = mission;
	}

	private void drawR(Graphics g) throws Exception {
		if (this.f.cue()) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(Color.red);
			g2d.drawRect(f.getP11().x * 50 + 50, f.getP11().y * 50 + 50, 48, 48);
			g2d.drawRect(f.getP22().x * 50 + 50, f.getP22().y * 50 + 50, 48, 48);
		}
	}

	public void drawline(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(4.0F));
		if (((this.p11.x != 0 ? 1 : 0) & (this.p11.y != 0 ? 1 : 0)
				| (this.p22.x == 0 ? 1 : 0) & (this.p22.y != 0 ? 1 : 0)) != 0) {
			try {
				if ((this.f.isEqual(this.p11, this.p22)) && (this.f.horizonMatch(this.p11, this.p22))) {
					g2d.drawLine(this.p22.x * 50 + 75, this.p22.y * 50 + 75, this.p11.x * 50 + 75,
							this.p11.y * 50 + 75);
				} else if ((this.f.isEqual(this.p11, this.p22)) && (this.f.verticalMatch(this.p11, this.p22))) {
					g2d.drawLine(this.p22.x * 50 + 75, this.p22.y * 50 + 75, this.p11.x * 50 + 75,
							this.p11.y * 50 + 75);
				} else if ((this.f.isEqual(this.p11, this.p22)) && (this.f.oneCorner(this.p11, this.p22))) {
					g2d.drawLine(this.p22.x * 50 + 75, this.p22.y * 50 + 75, this.f.getP33().x * 50 + 75,
							this.f.getP33().y * 50 + 75);
					g2d.drawLine(this.p11.x * 50 + 75, this.p11.y * 50 + 75, this.f.getP33().x * 50 + 75,
							this.f.getP33().y * 50 + 75);
				} else if ((this.f.isEqual(this.p11, this.p22)) && (this.f.twoCorner(this.p11, this.p22))) {
					onec(this.p22, this.f.getP3(), g2d);
					g2d.drawLine(this.p11.x * 50 + 75, this.p11.y * 50 + 75, this.f.getP3().x * 50 + 75,
							this.f.getP3().y * 50 + 75);
				}
				this.p11.x = 0;
				this.p11.y = 0;
				this.p22.x = 0;
				this.p22.y = 0;
				if (this.mission == 1) {
					this.f.goDown();
				} else if (this.mission == 2) {
					this.f.goUp();
				} else if (this.mission == 3) {
					this.f.goRight();
				} else if (this.mission == 4) {
					this.f.goLeft();
				} else if (this.mission == 5) {
					this.f.inRightLeft();
				} else if (this.mission == 6) {
					this.f.outRightLeft();
				} else if (this.mission == 7) {
					this.f.inTopBottom();
				} else if (this.mission == 8) {
					this.f.outTopBottom();
				} else if (this.mission == 9) {
					this.f.in();
				} else if (this.mission == 10) {
					this.f.out();
				}
				repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void onec(Point p11, Point p22, Graphics2D g2d) throws Exception {
		if ((this.f.isEqual(p11, p22)) && (this.f.oneCorner(p11, p22))) {
			g2d.drawLine(p22.x * 50 + 75, p22.y * 50 + 75, this.f.getP33().x * 50 + 75, this.f.getP33().y * 50 + 75);
			g2d.drawLine(p11.x * 50 + 75, p11.y * 50 + 75, this.f.getP33().x * 50 + 75, this.f.getP33().y * 50 + 75);
		}
	}

	private boolean drawSqual(Graphics g, Point p1) {
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(new Font("幼圆", 1, 22));
		String drawStr = "AMANI NAKUPENDA NAKUPENDA WE WE";
		g.drawString(drawStr, 50, 50);
		if (this.f.getWhichMission() != null) {
			g.setFont(new Font("微软雅黑", 0, 14));
			if (this.mission != 0) {
				String drawMission = "这是您的第" + this.mission + "关 ,是" + this.f.getWhichMission() + "飞的!";
				g.drawString(drawMission, 90, 90);
			} else {
				String drawMission = "这是第" + this.mission + "关 ,是最难的一关!";
				g.drawString(drawMission, 90, 90);
			}
		}

		if (p1 == null) {
			return false;
		}
		if ((p1.x > this.f.getSize()) || (p1.y > this.f.getSize()) || (p1.x <= 0) || (p1.y <= 0)) {
			return false;
		}
		if (this.array[p1.y][p1.x] == 0) {
			return false;
		}

		g2d.setPaint(Color.ORANGE);
		g.drawRect(p1.x * 50 + 50, p1.y * 50 + 50, 48, 48);
		return true;
	}

	public void drawMap(Graphics g) {
		this.g2d = ((Graphics2D) g);
		try {
			for (int i = 1; i < this.f.getSize() + 1; i++) {
				for (int j = 1; j < this.f.getSize() + 1; j++) {
					int k = this.f.getArray()[i][j];
					this.g2d.drawImage(this.f.img(k), 50 + j * 50, 50 + i * 50, this);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doIt(MouseEvent e) throws Exception {
		this.f.setP1(new Point(this.f.transfer(e.getX(), e.getY())));
		repaint();
		if (((this.p1.x == 0 ? 1 : 0) & (this.p1.y == 0 ? 1 : 0)) != 0) {
			this.p1 = this.f.transfer(e.getX(), e.getY());
		} else {
			this.p2 = this.p1;
			this.p1 = this.f.transfer(e.getX(), e.getY());
			if ((this.p1.x >= 0) && (this.p1.x <= this.f.getSize()))
				if ((((this.p1.y >= 0 ? 1 : 0) & (this.p1.y <= this.f.getSize() ? 1 : 0)) != 0) && (this.p2.x >= 0)
						&& (this.p2.x <= this.f.getSize()))
					if (((this.p2.y >= 0 ? 1 : 0) & (this.p2.y <= this.f.getSize() ? 1 : 0)) != 0)
						if (this.f.remove(this.p1, this.p2,
								(this.f.isMathced(this.p1, this.p2)) && (this.array[this.p1.y][this.p1.x] != 0)
										&& (this.array[this.p2.y][this.p2.x] != 0))) {
							Flag.bool = false;

							this.p22.x = this.p2.x;
							this.p22.y = this.p2.y;
							this.p11.x = this.p1.x;
							this.p11.y = this.p1.y;
							this.p1.x = 0;
							this.p1.y = 0;
							this.p2.x = 0;
							this.p2.y = 0;

							this.f.setP1(null);
							repaint();
						}
		}
	}

	public void reList() {
		this.f.setArray(this.f.reList(this.array));
		repaint();
	}

	public void start(int size, int degree) {
		this.f = new Flag();
		this.f.setDifficality(size, degree);
		this.array = this.f.RandomArr();
		this.timeCtrl.startButton();
		repaint();
	}

	public void reStart(int size, int degree) {
		gameOver();
		this.f = new Flag();
		this.f.setDifficality(size, degree);
		this.array = this.f.RandomArr();
		this.timeCtrl.startButton();
		repaint();
	}

	public void gameOver() {
		this.array = null;
		this.timeCtrl.stopButton();
		this.timeCtrl.stopThread();
		this.f = null;
		TimeCtrl.getTimeProgressBar().setEnabled(false);
		TimeCtrl.getTimeProgressBar().setValue(120);
	}

	public TimeCtrl getTimeCtrl() {
		return timeCtrl;
	}

	public void setTimeCtrl(TimeCtrl timeCtrl) {
		this.timeCtrl = timeCtrl;
	}
	
}