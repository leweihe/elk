package com.newclear.game.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String STARTBACKGROUND = "/flags/backgroundWelcome.jpg";
	private static final String PAUSEBACKGROUND = "/flags/backgroundPause.jpg";
	private static final String ENDBACKGROUND = "/flags/backgroundOver.jpg";
	private static final String NEXTBACKGROUND = "/flags/backgroundNext.jpg";
	private ElkMainPanel elkMainPanel;
	private JPanel startPanel;
	private JPanel pausePanel;
	private JPanel endPanel;
	private JPanel nextPanel;
	private JLabel nextLabel;
	private JLabel startLabel;
	private JLabel pauseLabel;
	private JLabel endLabel;

	public CenterPanel() {
		initialize();
	}

	private void initialize() {
		setSize(300, 200);
		setLayout(new CardLayout());

		add(getStartPanel(), getStartPanel().getName());
		add(getElkMainPanel(), getElkMainPanel().getName());
		add(getPausePanel(), getPausePanel().getName());
		add(getEndPanel(), getEndPanel().getName());
		add(getNextPanel(), getNextPanel().getName());
	}

	public ElkMainPanel getElkMainPanel() {
		if (this.elkMainPanel == null) {
			this.elkMainPanel = new ElkMainPanel();
			this.elkMainPanel.setName("myJComponent");
			add(this.elkMainPanel, "South");
		}
		return this.elkMainPanel;
	}

	public JPanel getStartPanel() {
		if (this.startPanel == null) {
			this.startLabel = new JLabel();
			this.startLabel.setText("JLabel");
			this.startLabel.setIcon(new ImageIcon(getClass().getResource(STARTBACKGROUND)));
			this.startPanel = new JPanel();
			this.startPanel.setLayout(new BorderLayout());
			this.startPanel.setName("startPanel");
			this.startPanel.add(this.startLabel, "Center");
		}
		return this.startPanel;
	}

	public JPanel getNextPanel() {
		if (this.nextPanel == null) {
			this.nextLabel = new JLabel();
			this.nextLabel.setText("JLabe1");
			this.nextLabel.setIcon(new ImageIcon(getClass().getResource(NEXTBACKGROUND)));
			this.nextPanel = new JPanel();
			this.nextPanel.setLayout(new BorderLayout());
			this.nextPanel.setName("startPanel");
			this.nextPanel.add(this.nextLabel, "Center");
		}
		return this.nextPanel;
	}

	public JPanel getPausePanel() {
		if (this.pausePanel == null) {
			this.pauseLabel = new JLabel();
			this.pauseLabel.setText("JLabel");
			this.pauseLabel.setIcon(new ImageIcon(getClass().getResource(PAUSEBACKGROUND)));
			this.pausePanel = new JPanel();
			this.pausePanel.setLayout(new BorderLayout());
			this.pausePanel.setName("pausePanel");
			this.pausePanel.add(this.pauseLabel, "Center");
		}
		return this.pausePanel;
	}

	public JPanel getEndPanel() {
		if (this.endPanel == null) {
			this.endLabel = new JLabel();
			this.endLabel.setText("JLabel");
			this.endLabel.setIcon(new ImageIcon(getClass().getResource(ENDBACKGROUND)));
			this.endPanel = new JPanel();
			this.endPanel.setLayout(new BorderLayout());
			this.endPanel.setName("endPanel");
			this.endPanel.add(this.endLabel, "Center");
		}
		return this.endPanel;
	}
}
