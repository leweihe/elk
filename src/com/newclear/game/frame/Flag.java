package com.newclear.game.frame;

import java.awt.Point;

public class Flag {
	private int size = 8;
	private int[][] array;
	private Point p1;

	public Flag() {
		this.array = new int[this.size + 2][this.size + 2];
	}

	public Point getP1() {
		return this.p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public int[][] getArray() {
		return this.array;
	}

	public void setArray(int[][] array) {
		this.array = array;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int[][] RandomArr() {
		int[] array1 = new int[this.size * this.size];
		int n = 0;

		for (int i = 0; i < this.size * this.size / 2; i++) {
			array1[(2 * i)] = (new java.util.Random().nextInt(9) + 1);
			array1[(2 * i + 1)] = array1[(2 * i)];
		}
		for (int i = 0; i < this.size * this.size; i++) {
			int x1 = new java.util.Random().nextInt(this.size * this.size);
			int x2 = new java.util.Random().nextInt(this.size * this.size);
			int y1 = array1[x1];
			int y2 = array1[x2];
			array1[x1] = y2;
			array1[x2] = y1;
		}
		for (int i = 0; i < this.size + 2; i++) {
			for (int j = 0; j < this.size + 2; j++) {
				if ((i == 0) || (j == 0) || (i == this.size + 1) || (j == this.size + 1)) {
					this.array[i][j] = 0;
				} else {
					this.array[i][j] = array1[n];
					n++;
				}
			}
		}
		return this.array;
	}

	public Point transfer(int x, int y) {
		int i = x / 50 - 1;
		int j = y / 50 - 1;
		if ((i <= 0) || (i >= this.size + 2) || (j <= 0) || (j >= this.size + 2)) {
			return new Point(-1, -1);
		}
		return new Point(i, j);
	}

	public boolean remove(Point p1, Point p2, boolean b) {
		if (b) {
			this.array[p1.y][p1.x] = 0;
			this.array[p2.y][p2.x] = 0;
			return true;
		}
		return false;
	}

	public boolean verticalMatch(Point p1, Point p2) {
		if (p1.x != p2.x)
			return false;
		if (Math.abs(p1.y - p2.y) == 1)
			return true;
		for (int i = 1; i < Math.abs(p1.y - p2.y); i++) {
			int l = -1 * (p1.y - p2.y) / Math.abs(p1.y - p2.y);
			if (this.array[(p1.y + i * l)][p1.x] != 0)
				return false;
		}
		return true;
	}

	public boolean horizonMatch(Point p1, Point p2) {
		if (p1.y != p2.y)
			return false;
		if (Math.abs(p1.x - p2.x) == 1)
			return true;
		for (int i = 1; i < Math.abs(p1.x - p2.x); i++) {
			int l = -1 * (p1.x - p2.x) / Math.abs(p1.x - p2.x);
			if (this.array[p1.y][(p1.x + i * l)] != 0)
				return false;
		}
		return true;
	}

	public boolean isEqual(Point p1, Point p2) throws Exception {
		if ((p1.x == -1) || (p1.y == -1) || (p2.x == -1) || (p2.y == -1)) {
			throw new Exception();
		}
		if (this.array[p1.y][p1.x] == this.array[p2.y][p2.x]) {
			if ((p1.x == p2.x) && (p1.y == p2.y)) {
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean twoCorner(Point p1, Point p2) {
		int x = 0;
		int y = 0;
		Point p3 = new Point(p1.x, y);
		Point p4 = new Point(x, p1.y);
		for (int i = 0; i < this.size + 2; i++)
			if (this.array[p3.y][p3.x] != 0) {
				p3.y += 1;
			} else {
				if ((verticalMatch(p1, p3)) && (oneCorner(p3, p2))) {
					return true;
				}
				p3.y += 1;
			}
		for (int i = 0; i < this.size + 2; i++)
			if (this.array[p4.y][p4.x] != 0) {
				p4.x += 1;
			} else {
				if ((horizonMatch(p1, p4)) && (oneCorner(p4, p2))) {
					return true;
				}
				p4.x += 1;
			}
		return false;
	}

	private boolean oneCorner(Point p1, Point p2) {
		Point p3 = new Point(p1.x, p2.y);
		Point p4 = new Point(p2.x, p1.y);
		if ((this.array[p3.y][p3.x] == 0) && (verticalMatch(p1, p3)) && (horizonMatch(p3, p2)))
			return true;
		if ((this.array[p4.y][p4.x] == 0) && (horizonMatch(p1, p4)) && (verticalMatch(p4, p2))) {
			return true;
		}
		return false;
	}

	public boolean isMathced(Point p1, Point p2) throws Exception {
		if ((isEqual(p1, p2))
				&& ((horizonMatch(p1, p2)) || (verticalMatch(p1, p2)) || (twoCorner(p1, p2)) || (oneCorner(p1, p2)))) {
			return true;
		}
		return false;
	}
}