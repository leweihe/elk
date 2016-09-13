package com.newclear.game.object;

import java.awt.Image;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import com.newclear.game.container.ElkContainer;
import com.newclear.game.exception.ClickOutOfBoardException;

public class GameBoard {
    private Integer size;
    private Integer degree;
    private Integer[][] array;
    private Image[] images;
    private boolean promptflag;
    private String missionNum;

    public GameBoard() {
        try {
            readImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameBoard(int size, int degree) {
        this.size = size;
        this.degree = degree;
        setDifficality(size, degree);
    }

    public void setDifficality(int size, int degree) {
        this.size = size;
        this.degree = degree;
        this.array = new Integer[size + 2][size + 2];
        this.setArray(generateRandomArray());
    }

    public Integer[][] reloadGameBoard(Integer[][] array) {
        Integer[] tmpArray = new Integer[(array.length - 2) * (array.length - 2)];
        int n = 0;

        for (int i = 1; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - 1; j++) {
                tmpArray[n] = array[i][j];
                n++;
            }
        }

        for (int i = 0; i < this.size * this.size; i++) {
            int x1 = new Random().nextInt(this.size * this.size);
            int x2 = i;
            if ((tmpArray[x1] != 0) && (tmpArray[x2] != 0)) {
                int y1 = tmpArray[x1];
                int y2 = tmpArray[x2];
                tmpArray[x1] = y2;
                tmpArray[x2] = y1;
            }
        }

        n = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                array[(i + 1)][(j + 1)] = tmpArray[n];
                n++;
            }
        }
        return array;
    }

    public Integer[][] generateRandomArray() {
        Integer[] array1 = new Integer[this.size * this.size];
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

    public boolean hasGameFinished() {
        return Stream.of(this.array).flatMap(Arrays::stream).filter(n -> n != 0).count() == 0;
    }

    public Square transferSquare(int x, int y) {
        int i = x / 50 - 1;
        int j = y / 50 - 1;
        if ((i <= 0) || (i >= this.size + 2) || (j <= 0) || (j >= this.size + 2)) {
            return new Square(-1, -1);
        }
        return new Square(i, j);
    }

    public void remove(Square p1, Square p2) {
        this.array[p1.y][p1.x] = 0;
        this.array[p2.y][p2.x] = 0;
    }

    public boolean verticalMatch(Square p1, Square p2) {
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

    public boolean horizonMatch(Square p1, Square p2) {
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

    public boolean isValueEqual(Square p1, Square p2) {
        if ((p1.x == p2.x) && (p1.y == p2.y))
            return false;
        if (p1.getValue(array) <= 0 || p2.getValue(array) <= 0)
            return false;
        return p1.getValue(array) == p2.getValue(array);
    }

    public boolean twoCorner(Square p1, Square p2) {
        int x = 0;
        int y = 0;
        Square p3 = new Square(p1.x, y);
        Square p4 = new Square(x, p1.y);
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

    public boolean oneCorner(Square p1, Square p2) {
        Square p3 = new Square(p1.x, p2.y);
        Square p4 = new Square(p2.x, p1.y);
        if ((this.array[p3.y][p3.x] == 0) && (verticalMatch(p1, p3)) && (horizonMatch(p3, p2))) {
            return true;
        }
        if ((this.array[p4.y][p4.x] == 0) && (horizonMatch(p1, p4)) && (verticalMatch(p4, p2))) {
            return true;
        }

        return false;
    }

    public boolean isMathced(Square p1, Square p2) throws ClickOutOfBoardException {
        return isValueEqual(p1, p2) && (horizonMatch(p1, p2) || verticalMatch(p1, p2) || twoCorner(p1, p2) || oneCorner(p1, p2));
    }

    private void readImages() throws IOException {
        this.images = new Image[25];
        for (int i = 0; i < 25; i++) {
            this.images[i] = ImageIO.read(getClass().getResource(ElkContainer.IMG_FOLDER_PATH + (i + 1) + ".png"));
        }
    }

    public Image getFlagImg(int k) throws IOException {
        if (k == 0) {
            return null;
        }
        Image img = this.images[(k - 1)];
        return img;
    }

    public boolean hasSolution() throws ClickOutOfBoardException {
        for (int i = 1; i <= this.size; i++) {
            for (int j = 1; j <= this.size; j++) {
                for (int n = 1; n <= this.size; n++) {
                    for (int m = 1; m <= this.size; m++) {
                        if (isMathced(new Square(i, j), new Square(m, n))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void goDown() {
        this.missionNum = "向下";
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
        this.missionNum = "向上";
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
        this.missionNum = "向右";
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
        this.missionNum = "向左";
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
        this.missionNum = "左右向内";
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
        this.missionNum = "左右向外";
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
        this.missionNum = "上下 向外";
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
        this.missionNum = "上下向内";
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
        this.missionNum = "四面向内";
        inTopBottom();
        inRightLeft();
    }

    public void out() {
        this.missionNum = "四面向外";
        outRightLeft();
        outTopBottom();
    }

    public boolean isPromptflag() {
        return promptflag;
    }

    public void setPromptflag(boolean promptflag) {
        this.promptflag = promptflag;
    }

    public int getDegree() {
        return this.degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Integer[][] getArray() {
        return array;
    }

    public void setArray(Integer[][] array) {
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

    public String getMissionNum() {
        return this.missionNum;
    }

    public void setMissionNum(String missionNum) {
        this.missionNum = missionNum;
    }
}
