package com.newclear.game.object;

import java.awt.Image;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import com.newclear.game.container.ElkContainer;
import com.newclear.game.container.MissionEnum;

public class GameBoard {
    private Integer size;
    private Integer degree;
    private Image[] images;
    private MissionEnum mission;

    private boolean promptflag;

    private Integer[][] array;

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

    public boolean isMathced(Square p1, Square p2) {
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

    public boolean hasSolution() {
        for (int i = 1; i <= this.size; i++) {
            for (int j = 1; j <= this.size; j++) {
                for (int m = 1; m <= this.size; m++) {
                    for (int n = 1; m <= this.size; n++) {
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
        this.mission = MissionEnum.SECOND;
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
        this.mission = MissionEnum.THIRD;
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
        this.mission = MissionEnum.FOURTH;
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
        this.mission = MissionEnum.FIFTH;
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
        this.mission = MissionEnum.SIXTH;
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
        this.mission = MissionEnum.SEVENTH;
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
        this.mission = MissionEnum.EIGHTH;
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
        this.mission = MissionEnum.NINTH;
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
        this.mission = MissionEnum.TENTH;
        inTopBottom();
        inRightLeft();
    }

    public void out() {
        this.mission = MissionEnum.ELEVENTH;
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

    public MissionEnum getMission() {
        return mission;
    }

    public void setMission(int missionNum) {
        this.mission = Stream.of(MissionEnum.values()).filter(n -> n.getValue() == missionNum).findFirst().get();
    }

}
