package com.newclear.game.object;

import java.awt.Point;

public class Square {
    public int x;
    public int y;
    private boolean isFlagImg;
    private int positX;
    private int positY;
    private int value;

    public Square() {
        this(0, 0);
    }

    public Square(Square p) {
        this(p.x, p.y);
    }

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isFlagImg() {
        return isFlagImg;
    }

    public void setFlagImg(boolean isFlagImg) {
        this.isFlagImg = isFlagImg;
    }

    public int getPositX() {
        return this.x * 50 + 50;
    }

    public void setPositX(int positX) {
        this.positX = positX;
    }

    public int getPositY() {
        return this.y * 50 + 50;
    }

    public void setPositY(int positY) {
        this.positY = positY;
    }

    public int getValue(Integer[][] array) {
        if (x > array.length || y > array.length)
            return -1;
        return array[y][x];
    }

    public Point getAwtPoint() {
        return new Point(x, y);
    }

}
