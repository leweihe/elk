package com.newclear.game.panel;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NorthPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton restartButton = null;
	private JButton startButton = null;
	private JButton helpButton = null;
	private JButton relistButton = null;

	public NorthPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(new java.awt.GridBagLayout());
		add(getRestartButton(), new GridBagConstraints());
		add(getStartButton(), new GridBagConstraints());
		add(getHelpButton(), new GridBagConstraints());
		add(getRelistButton(), new GridBagConstraints());
	}

	private JButton getRestartButton() {
		if (this.restartButton == null) {
			this.restartButton = new JButton();
			this.restartButton.setText("重新开始");
		}
		return this.restartButton;
	}

	private JButton getStartButton() {
		if (this.startButton == null) {
			this.startButton = new JButton();
			this.startButton.setText("开始");
			this.startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (NorthPanel.this.startButton.getText() == "开始") {
						NorthPanel.this.startButton.setText("暂停");

					} else {
						NorthPanel.this.startButton.setText("开始");
					}
				}
			});
		}

		return this.startButton;
	}

	private JButton getHelpButton() {
		if (this.helpButton == null) {
			this.helpButton = new JButton();
			this.helpButton.setText("提示");
		}
		return this.helpButton;
	}

	private JButton getRelistButton() {
		if (this.relistButton == null) {
			this.relistButton = new JButton();
			this.relistButton.setText("重排");
		}
		return this.relistButton;
	}
}
