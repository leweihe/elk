package myframe;

import java.awt.Point;

public class Line implements Runnable {
	private int kind;
	private Point p1;
	private Point p2;
	private Point p3;
	private Point p4;
	private java.awt.Graphics g;

	public Line(java.awt.Graphics g) {
		this.g = g;
	}

	public int getKind() {
		return this.kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public Point getP1() {
		return this.p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return this.p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public Point getP3() {
		return this.p3;
	}

	public void setP3(Point p3) {
		this.p3 = p3;
	}

	public Point getP4() {
		return this.p4;
	}

	public void setP4(Point p4) {
		this.p4 = p4;
	}

	public Line(int i, Point p1, Point p2) {
		this.kind = i;
		this.p1 = p1;
		this.p2 = p2;
	}

	public Line(int i, Point p1, Point p2, Point p3) {
		this.kind = i;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	public void run() {
		if (this.kind == 0) {
			this.g.drawLine(this.p1.x, this.p1.y, this.p2.x, this.p2.y);
		} else if (this.kind == 1) {
			this.g.drawLine(this.p1.x, this.p1.y, this.p3.x, this.p3.y);
			this.g.drawLine(this.p3.x, this.p3.y, this.p2.x, this.p2.y);
		} else if (this.kind == 2) {
			this.g.drawLine(this.p1.x, this.p1.y, this.p4.x, this.p4.y);
			this.g.drawLine(this.p4.x, this.p4.y, this.p2.x, this.p2.y);
		}
	}
}
