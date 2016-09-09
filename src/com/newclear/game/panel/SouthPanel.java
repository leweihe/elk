package com.newclear.game.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class SouthPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public SouthPanel() {
		initialize();
	}

	private void initialize() {
		setSize(300, 200);
		setLayout(new BorderLayout());
		add(TimeCtroller.getTimeProgressBar(), "North");
	}
}
