package myframe;

import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Flag {
	private int size;
	private int degree;
	private int[][] array;
	private Point p1;
	private Point p2;
	private Point p3;
	private Point p4;
	private Point p11 = new Point(0, 0);
	private Point p22 = new Point(0, 0);
	private Point p44 = new Point(0, 0);
	private Point p33 = new Point(0, 0);
	private Image[] images;
	public static boolean bool;
	private String whichMission;

	public String getWhichMission() {
		return this.whichMission;
	}

	public void setWhichMission(String whichMission) {
		this.whichMission = whichMission;
	}

	public Flag() {
		setDifficality(this.size, this.degree);
		this.array = new int[this.size + 2][this.size + 2];
		try {
			readImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Flag(int size, int degree) {
		this.size = size;
		this.degree = degree;
		setDifficality(size, degree);
	}

	public Point getP11() {
		return p11;
	}

	public void setP11(Point p11) {
		this.p11 = p11;
	}

	public Point getP22() {
		return p22;
	}

	public void setP22(Point p22) {
		this.p22 = p22;
	}

	public Point getP44() {
		return this.p44;
	}

	public void setP44(Point p44) {
		this.p44 = p44;
	}

	public Point getP33() {
		return this.p33;
	}

	public void setP33(Point p33) {
		this.p33 = p33;
	}

	public int getDegree() {
		return this.degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public Point getP2() {
		return this.p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
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

	public boolean canRemove() {
		return false;
	}

	public int countHowManyLast() {
		int last = 0;
		for (int i = 1; i < this.array.length - 1; i++) {
			for (int j = 1; j < this.array.length - 1; j++) {
				if (this.array[i][j] == 0) {
					last++;
				}
			}
		}
		return last;
	}

	public void setDifficality(int size, int degree) {
		this.size = size;
		this.degree = degree;
		this.array = new int[size + 2][size + 2];
		setArray(RandomArr());
	}

	public int[][] reList(int[][] array) {
		int[] array1 = new int[(array.length - 2) * (array.length - 2)];
		int n = 0;

		for (int i = 1; i < array.length - 1; i++) {
			for (int j = 1; j < array.length - 1; j++) {
				array1[n] = array[i][j];
				n++;
			}
		}

		for (int i = 0; i < this.size * this.size; i++) {
			int x1 = new Random().nextInt(this.size * this.size);
			int x2 = i;
			if ((array1[x1] != 0) && (array1[x2] != 0)) {
				int y1 = array1[x1];
				int y2 = array1[x2];
				array1[x1] = y2;
				array1[x2] = y1;
			}
		}

		n = 0;
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				array[(i + 1)][(j + 1)] = array1[n];
				n++;
			}
		}
		return array;
	}

	public int[][] RandomArr() {
		int[] array1 = new int[this.size * this.size];
		int n = 0;

		for (int i = 0; i < this.size * this.size / 2; i++) {
			array1[(2 * i)] = ((i + 2) / 2);
			array1[(2 * i + 1)] = array1[(2 * i)];
		}

		for (int i = 0; i < this.size * this.size; i++) {
			int x1 = i;
			int x2 = new Random().nextInt(this.size * this.size);
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

	public boolean isNull() {
		int a = 0;
		for (int i = 1; i <= this.size; i++) {
			for (int j = 1; j <= this.size; j++) {
				if (this.array[i][j] == 0) {
					a++;
					if (a == this.size * this.size) {
						return true;
					}
				}
			}
		}

		return false;
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

	public boolean twoCorner(Point p1, Point p2) {
		int x = 0;
		int y = 0;
		Point p3 = new Point(p1.x, y);
		Point p4 = new Point(x, p1.y);
		for (int i = 0; i < this.size + 2; i++)
			if (this.array[p3.y][p3.x] != 0) {
				p3.y += 1;
			} else {
				if ((verticalMatch(p1, p3)) && (oneCorner(p3, p2))) {
					this.p3 = p3;
					return true;
				}
				p3.y += 1;
			}
		for (int i = 0; i < this.size + 2; i++)
			if (this.array[p4.y][p4.x] != 0) {
				p4.x += 1;
			} else {
				if ((horizonMatch(p1, p4)) && (oneCorner(p4, p2))) {
					this.p3 = p4;
					return true;
				}
				p4.x += 1;
			}
		return false;
	}

	public boolean oneCorner(Point p1, Point p2) {
		Point p3 = new Point(p1.x, p2.y);
		Point p4 = new Point(p2.x, p1.y);
		if ((this.array[p3.y][p3.x] == 0) && (verticalMatch(p1, p3)) && (horizonMatch(p3, p2))) {
			this.p33 = p3;
			return true;
		}
		if ((this.array[p4.y][p4.x] == 0) && (horizonMatch(p1, p4)) && (verticalMatch(p4, p2))) {
			this.p33 = p4;
			return true;
		}

		return false;
	}

	public Point getP3() {
		return this.p3;
	}

	public Point getP4() {
		return this.p4;
	}

	public boolean isMathced(Point p1, Point p2) throws Exception {
		if ((isEqual(p1, p2))
				&& ((horizonMatch(p1, p2)) || (verticalMatch(p1, p2)) || (twoCorner(p1, p2)) || (oneCorner(p1, p2)))) {
			return true;
		}
		return false;
	}

	private String flagURL(int k) {
		String s = null;
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
			break;
		case 10:
			s = "/flags/10.png";
			break;
		case 11:
			s = "/flags/11.png";
			break;
		case 12:
			s = "/flags/12.png";
			break;
		case 13:
			s = "/flags/13.png";
			break;
		case 14:
			s = "/flags/14.png";
			break;
		case 15:
			s = "/flags/15.png";
			break;
		case 16:
			s = "/flags/16.png";
			break;
		case 17:
			s = "/flags/17.png";
			break;
		case 18:
			s = "/flags/18.png";
			break;
		case 19:
			s = "/flags/19.png";
			break;
		case 20:
			s = "/flags/20.png";
			break;
		case 21:
			s = "/flags/21.png";
			break;
		case 22:
			s = "/flags/22.png";
			break;
		case 23:
			s = "/flags/23.png";
			break;
		case 24:
			s = "/flags/24.png";
			break;
		case 25:
			s = "/flags/25.png";
			break;
		case 26:
			s = "/flags/26.png";
			break;
		case 27:
			s = "/flags/27.png";
			break;
		case 28:
			s = "/flags/28.png";
			break;
		case 29:
			s = "/flags/29.png";
			break;
		case 30:
			s = "/flags/30.png";
			break;
		case 31:
			s = "/flags/31.png";
			break;
		case 32:
			s = "/flags/32.png";
			break;
		case 33:
			s = "/flags/33.png";
			break;
		case 34:
			s = "/flags/34.png";
			break;
		case 35:
			s = "/flags/35.png";
			break;
		case 36:
			s = "/flags/36.png";
			break;
		case 37:
			s = "/flags/37.png";
			break;
		case 38:
			s = "/flags/38.png";
			break;
		case 39:
			s = "/flags/39.png";
			break;
		case 40:
			s = "/flags/40.png";
			break;
		case 41:
			s = "/flags/41.png";
			break;
		case 42:
			s = "/flags/42.png";
			break;
		case 43:
			s = "/flags/43.png";
			break;
		case 44:
			s = "/flags/44.png";
			break;
		case 45:
			s = "/flags/45.png";
			break;
		case 46:
			s = "/flags/46.png";
			break;
		case 47:
			s = "/flags/47.png";
			break;
		case 48:
			s = "/flags/48.png";
			break;
		case 49:
			s = "/flags/49.png";
			break;
		case 50:
			s = "/flags/50.png";
		}

		return s;
	}

	private void readImage() throws IOException {
		this.images = new Image[25];
		for (int i = 0; i < 25; i++) {
			this.images[i] = ImageIO.read(getClass().getResource(flagURL(i + 1)));
		}
	}

	public Image img(int k) throws IOException {
		if (k == 0) {
			return null;
		}
		Image img = this.images[(k - 1)];
		return img;
	}

	public boolean isRelist() throws Exception {
		int a = 0;
		for (int i = 1; i <= this.size; i++) {
			for (int j = 1; j <= this.size; j++) {
				for (int n = 1; n <= this.size; n++) {
					for (int m = 1; m <= this.size; m++) {
						p11.x = i;
						p11.y = j;
						p22.x = n;
						p22.y = m;
						if (isMathced(p11, p22)) {
							if (((this.array[j][i] != 0 ? 1 : 0) & (this.array[m][n] != 0 ? 1 : 0)) != 0) {
								a++;
							}
						}
					}
				}
			}
		}
		if (a == 0) {
			return true;
		}

		return false;
	}

	public boolean cue() throws Exception {
		for (int i = 1; i <= this.size; i++) {
			for (int j = 1; j <= this.size; j++) {
				for (int n = 1; n <= this.size; n++) {
					for (int m = 1; m <= this.size; m++) {
						p11.x = i;
						p11.y = j;
						p22.x = n;
						p22.y = m;
						if ((isMathced(p11, p22)) && (this.array[j][i] != 0) && (this.array[m][n] != 0)
								&& (this.array[j][i] == this.array[m][n])) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public void goDown() {
		this.whichMission = "向下";
		for (int i = 1; i <= getSize(); i++) {
			for (int j = 1; j <= getSize(); j++) {
				if ((this.array[i][j] == 0) && (this.array[(i - 1)][j] != 0)) {
					this.array[i][j] = this.array[(i - 1)][j];
					this.array[(i - 1)][j] = 0;
					i = 1;
				}
			}
		}
	}

	public void goUp() {
		this.whichMission = "向上";
		for (int i = this.size; i >= 1; i--) {
			for (int j = this.size; j >= 1; j--) {
				if (((this.array[i][j] == 0 ? 1 : 0) & (this.array[(i + 1)][j] != 0 ? 1 : 0)) != 0) {
					this.array[i][j] = this.array[(i + 1)][j];
					this.array[(i + 1)][j] = 0;
					i = this.size;
				}
			}
		}
	}

	public void goRight() {
		this.whichMission = "向右";
		for (int i = 1; i <= this.size; i++) {
			for (int j = 1; j <= this.size; j++) {
				if (((this.array[i][j] == 0 ? 1 : 0) & (this.array[i][(j - 1)] != 0 ? 1 : 0)) != 0) {
					this.array[i][j] = this.array[i][(j - 1)];
					this.array[i][(j - 1)] = 0;
					j = 1;
				}
			}
		}
	}

	public void goLeft() {
		this.whichMission = "向左";
		for (int i = this.size; i >= 1; i--) {
			for (int j = this.size; j >= 1; j--) {
				if (((this.array[i][j] == 0 ? 1 : 0) & (this.array[i][(j + 1)] != 0 ? 1 : 0)) != 0) {
					this.array[i][j] = this.array[i][(j + 1)];
					this.array[i][(j + 1)] = 0;
					j = this.size;
				}
			}
		}
	}

	public void inRightLeft() {
		this.whichMission = "左右向内";
		for (int i = 1; i <= this.size; i++) {
			for (int j = 1; j <= this.size / 2; j++) {
				if ((this.array[i][j] == 0) && (this.array[i][(j - 1)] != 0)) {
					this.array[i][j] = this.array[i][(j - 1)];
					this.array[i][(j - 1)] = 0;
					j = 1;
				}
			}
		}
		for (int i = 1; i <= this.size; i++) {
			for (int j = this.size; j > this.size / 2; j--) {
				if (((this.array[i][j] == 0 ? 1 : 0) & (this.array[i][(j + 1)] != 0 ? 1 : 0)) != 0) {
					this.array[i][j] = this.array[i][(j + 1)];
					this.array[i][(j + 1)] = 0;
					j = this.size;
				}
			}
		}
	}

	public void outRightLeft() {
		this.whichMission = "左右向外";
		for (int i = 1; i <= this.size; i++) {
			for (int j = this.size / 2 + 1; j <= this.size; j++) {
				if (this.array[i][j] == 0)
					if (((this.array[i][(j - 1)] != 0 ? 1 : 0) & (j - 1 > this.size / 2 ? 1 : 0)) != 0) {
						this.array[i][j] = this.array[i][(j - 1)];
						this.array[i][(j - 1)] = 0;
						j = this.size / 2 + 1;
					}
			}
		}
		for (int i = 1; i <= this.size; i++) {
			for (int j = this.size / 2; j >= 1; j--) {
				if ((((this.array[i][j] == 0 ? 1 : 0) & (this.array[i][(j + 1)] != 0 ? 1 : 0)) != 0)
						&& (j + 1 <= this.size / 2)) {
					this.array[i][j] = this.array[i][(j + 1)];
					this.array[i][(j + 1)] = 0;
					j = this.size / 2;
				}
			}
		}
	}

	public void outTopBottom() {
		this.whichMission = "上下 向外";
		for (int i = this.size / 2; i >= 1; i--) {
			for (int j = 1; j <= this.size; j++) {
				if (this.array[i][j] == 0)
					if (((this.array[(i + 1)][j] != 0 ? 1 : 0) & (i + 1 <= this.size / 2 ? 1 : 0)) != 0) {
						this.array[i][j] = this.array[(i + 1)][j];
						this.array[(i + 1)][j] = 0;
						i = this.size / 2;
					}
			}
		}
		for (int i = this.size / 2 + 1; i <= this.size; i++) {
			for (int j = 1; j <= this.size; j++) {
				if (((this.array[i][j] == 0 ? 1 : 0) & (this.array[(i - 1)][j] != 0 ? 1 : 0)
						& (i - 1 > this.size / 2 ? 1 : 0)) != 0) {
					this.array[i][j] = this.array[(i - 1)][j];
					this.array[(i - 1)][j] = 0;
					i = this.size / 2 + 1;
				}
			}
		}
	}

	public void inTopBottom() {
		this.whichMission = "上下向内";
		for (int i = 1; i <= this.size / 2; i++) {
			for (int j = 1; j <= this.size; j++) {
				if ((this.array[i][j] == 0) && (this.array[(i - 1)][j] != 0)) {
					this.array[i][j] = this.array[(i - 1)][j];
					this.array[(i - 1)][j] = 0;
					i = 1;
				}
			}
		}
		for (int i = this.size; i > this.size / 2; i--) {
			for (int j = 1; j <= this.size; j++) {
				if ((this.array[i][j] == 0) && (this.array[(i + 1)][j] != 0)) {
					this.array[i][j] = this.array[(i + 1)][j];
					this.array[(i + 1)][j] = 0;
					i = this.size;
				}
			}
		}
	}

	public void in() {
		this.whichMission = "四面向内";
		inTopBottom();
		inRightLeft();
	}

	public void out() {
		this.whichMission = "四面向外";
		outRightLeft();
		outTopBottom();
	}
}
