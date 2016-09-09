package com.newclear.game.frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JButton jButton = null;

	public About() {
		initialize();
	}

	private void initialize() {
		setSize(300, 200);
		setResizable(false);
		setName("frame1");
		setLocationRelativeTo(null);
		setContentPane(getJContentPane());
		setTitle("关于");
		setLocationRelativeTo(null);
	}

	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new BorderLayout());
			this.jContentPane.add(getJPanel(), "Center");
		}
		return this.jContentPane;
	}

	private JPanel getJPanel() {
		if (this.jPanel == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(7, 108, 44, 114);
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.ipadx = 10;
			gridBagConstraints3.ipady = -3;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(6, 116, 7, 124);
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.ipadx = 11;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(5, 101, 5, 106);
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.ipadx = 4;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(16, 92, 5, 95);
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 8;
			gridBagConstraints.gridx = 0;
			this.jLabel2 = new JLabel();
			this.jLabel2.setFont(new Font("微软雅黑", 0, 14));
			this.jLabel2.setText("2011年");
			this.jLabel2.setHorizontalTextPosition(0);
			this.jLabel2.setHorizontalAlignment(0);
			this.jLabel1 = new JLabel();
			this.jLabel1.setFont(new Font("微软雅黑", 0, 14));
			this.jLabel1.setText(" 作者：核辐射");
			this.jLabel1.setHorizontalTextPosition(0);
			this.jLabel1.setHorizontalAlignment(0);
			this.jLabel = new JLabel();
			this.jLabel.setFont(new Font("微软雅黑", 0, 14));
			this.jLabel.setText(" 连连看    版本2.41a");
			this.jLabel.setHorizontalTextPosition(0);
			this.jLabel.setHorizontalAlignment(0);
			this.jPanel = new JPanel();
			this.jPanel.setLayout(new GridBagLayout());
			this.jPanel.add(this.jLabel, gridBagConstraints);
			this.jPanel.add(this.jLabel1, gridBagConstraints1);
			this.jPanel.add(this.jLabel2, gridBagConstraints2);
			this.jPanel.add(getJButton(), gridBagConstraints3);
		}
		return this.jPanel;
	}

	private JButton getJButton() {
		if (this.jButton == null) {
			this.jButton = new JButton();
			this.jButton.setFont(new Font("微软雅黑", 0, 14));
			this.jButton.setText("确定");
			this.jButton.setHorizontalTextPosition(0);
			this.jButton.setSelected(false);
			this.jButton.setVisible(true);
			this.jButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					About.this.dispose();
				}
			});
		}
		return this.jButton;
	}
}
