package com.newclear.game.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.newclear.game.container.ElkContainer;
import com.newclear.game.exception.ClickOutOfBoardException;
import com.newclear.game.object.GameBoard;
import com.newclear.game.object.Square;

public class ElkMainPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String PLAYBACKGROUND = "/flags/background.jpg";

    private Graphics2D g2d;
    private GameBoard f;
    private Integer[][] array;
    private SouthPanel southPanel;

    private Square tmpSquare1;
    private Square tmpSquare2;

    private TimeCtroller timeCtrl = new TimeCtroller();

    public GameBoard getF() {
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
        this.f = new GameBoard();
    }

    private void initialize() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            if (this.f == null) {
                return;
            }
            // if (!this.f.hasSolution()) {
            // reloadBoard();
            // }
            super.paintComponent(g);
            g.drawImage(ImageIO.read(getClass().getResource(PLAYBACKGROUND)), -100, -100, this);

            drawMap(g);
            // drawline(g);
            // drawSqual(g, null);
            if (f.isPromptflag()) {
                drawPromptBox(g);
                repaint();
            }
        } catch (IOException | ClickOutOfBoardException e1) {
            e1.printStackTrace();
        }
    }

    private void drawPromptBox(Graphics g) throws ClickOutOfBoardException {
        if (this.f.hasSolution()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(Color.red);
            // g2d.drawRect(f.getP11().x * 50 + 50, f.getP11().y * 50 + 50, 48, 48);
            // g2d.drawRect(f.getP22().x * 50 + 50, f.getP22().y * 50 + 50, 48, 48);
            // TODO get two point and draw them.
        }
    }

    public void drawline(Graphics g, Square s1, Square s2) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4.0F));
        if (((s1.x != 0 ? 1 : 0) & (s1.y != 0 ? 1 : 0)
                | (s2.x == 0 ? 1 : 0) & (s2.y != 0 ? 1 : 0)) != 0) {
            if ((this.f.isValueEqual(s1, s2)) && (this.f.horizonMatch(s1, s2))) {
                g2d.drawLine(s2.x * 50 + 75, s2.y * 50 + 75, s1.x * 50 + 75,
                        s1.y * 50 + 75);
            } else if ((this.f.isValueEqual(s1, s2)) && (this.f.verticalMatch(s1, s2))) {
                g2d.drawLine(s2.x * 50 + 75, s2.y * 50 + 75, s1.x * 50 + 75,
                        s1.y * 50 + 75);
            } else if ((this.f.isValueEqual(s1, s2)) && (this.f.oneCorner(s1, s2))) {
                // g2d.drawLine(s2.x * 50 + 75, s2.y * 50 + 75, this.f.getP33().x * 50 + 75,
                // this.f.getP33().y * 50 + 75);
                // g2d.drawLine(s1.x * 50 + 75, s1.y * 50 + 75, this.f.getP33().x * 50 + 75,
                // this.f.getP33().y * 50 + 75);
                // TODO how to pass the point to here?
            } else if ((this.f.isValueEqual(s1, s2)) && (this.f.twoCorner(s1, s2))) {
                // onec(s2, this.f.getP3(), g2d);
                // g2d.drawLine(s1.x * 50 + 75, s1.y * 50 + 75, this.f.getP3().x * 50 + 75,
                // this.f.getP3().y * 50 + 75);
                // TODO how to pass the point to here?
            }
            repaint();
        }
    }

    public void onec(Square p11, Square p22, Graphics2D g2d) throws Exception {
        if ((this.f.isValueEqual(p11, p22)) && (this.f.oneCorner(p11, p22))) {
            // g2d.drawLine(p22.x * 50 + 75, p22.y * 50 + 75, this.f.getP33().x * 50 + 75, this.f.getP33().y * 50 + 75);
            // g2d.drawLine(p11.x * 50 + 75, p11.y * 50 + 75, this.f.getP33().x * 50 + 75, this.f.getP33().y * 50 + 75);
            // TODO draw one corner point
        }
    }

    private boolean drawSqual(Graphics g, Square p1) {
        if (p1 == null) {
            return false;
        }
        if ((p1.x > this.f.getSize()) || (p1.y > this.f.getSize()) || (p1.x <= 0) || (p1.y <= 0)) {
            return false;
        }
        if (this.array[p1.y][p1.x] == 0) {
            return false;
        }

        g2d.setPaint(Color.PINK);
        g.drawRect(p1.getPositX(), p1.getPositY(), 48, 48);
        return true;
    }

    private void drawText(Graphics g) {
        g.setFont(new Font(ElkContainer.FONT_STYLE, 1, 22));
        String drawStr = "AMANI NAKUPENDA NAKUPENDA WE WE";
        g.drawString(drawStr, 50, 50);
    }

    public void drawMap(Graphics g) throws IOException {
        this.g2d = ((Graphics2D) g);
        for (int i = 1; i < this.f.getSize() + 1; i++) {
            for (int j = 1; j < this.f.getSize() + 1; j++) {
                int k = this.f.getArray()[i][j];
                Square square = new Square(j, i);
                this.g2d.drawImage(this.f.getFlagImg(k), square.getPositX(), square.getPositY(), this);
            }
        }
    }

    public void clickEvent(MouseEvent e) throws ClickOutOfBoardException {
        Square s = this.f.transferSquare(e.getX(), e.getY());
        if (s.getValue(array) <= 0) {
            this.tmpSquare1 = null;
            this.tmpSquare2 = null;
            return;
        }
        if (this.tmpSquare1 == null) {
            this.tmpSquare1 = s;
        } else {
            this.tmpSquare2 = s;
        }
        if (this.tmpSquare2 != null) {
            if (this.f.isMathced(tmpSquare1, tmpSquare2)) {
                this.f.remove(tmpSquare1, tmpSquare2);
            }

            this.tmpSquare1 = null;
            this.tmpSquare2 = null;
        }
        super.repaint();
    }

    public void reloadBoard() {
        this.f.setArray(this.f.reloadGameBoard(this.array));
        repaint();
    }

    public void prompt() {
        this.f.setPromptflag(true);
        repaint();
    }

    public void start(int size, int degree) {
        this.f = new GameBoard();
        this.f.setDifficality(size, degree);
        this.array = this.f.generateRandomArray();
        this.timeCtrl.startButton();
        repaint();
    }

    public void reStart(int size, int degree) {
        gameOver();
        this.f = new GameBoard();
        this.f.setDifficality(size, degree);
        this.array = this.f.generateRandomArray();
        this.timeCtrl.startButton();
        repaint();
    }

    public void gameOver() {
        this.array = null;
        this.timeCtrl.stopButton();
        this.timeCtrl.stopThread();
        this.f = null;
        TimeCtroller.getTimeProgressBar().setEnabled(false);
        TimeCtroller.getTimeProgressBar().setValue(120);
    }

    public TimeCtroller getTimeCtrl() {
        return timeCtrl;
    }

    public void setTimeCtrl(TimeCtroller timeCtrl) {
        this.timeCtrl = timeCtrl;
    }

}
