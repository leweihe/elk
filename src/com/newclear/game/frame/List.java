package com.newclear.game.frame;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class List extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JSplitPane jSplitPane = null;
	private JSplitPane jSplitPane1 = null;
	private JPanel jPanel = null;
	private JButton deleteButton = null;
	private JPanel jPanel1 = null;
	private JButton closeButton = null;
	private Object[][] data = { { "1", "1000", "abc" }, { "1", "1000", "abc" } };
	private String[] colNames = { "排名", "得分", "姓名" };
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;

	public List() {
		initialize();
	}

	private void initialize() {
		setSize(300, 200);
		setResizable(false);
		setContentPane(getJContentPane());
		setTitle("排行榜");
		setLocationRelativeTo(null);
	}

	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new BorderLayout());
			this.jContentPane.add(getJSplitPane(), "Center");
		}
		return this.jContentPane;
	}

	private JSplitPane getJSplitPane() {
		if (this.jSplitPane == null) {
			this.jSplitPane = new JSplitPane();
			this.jSplitPane.setDividerSize(1);
			this.jSplitPane.setDividerLocation(200);
			this.jSplitPane.setRightComponent(getJSplitPane1());
			this.jSplitPane.setLeftComponent(getJScrollPane());
			this.jSplitPane.setComponentOrientation(ComponentOrientation.UNKNOWN);
		}
		return this.jSplitPane;
	}

	private JSplitPane getJSplitPane1() {
		if (this.jSplitPane1 == null) {
			this.jSplitPane1 = new JSplitPane();
			this.jSplitPane1.setOrientation(0);
			this.jSplitPane1.setDividerLocation(80);
			this.jSplitPane1.setTopComponent(getJPanel());
			this.jSplitPane1.setBottomComponent(getJPanel1());
			this.jSplitPane1.setDividerSize(1);
		}
		return this.jSplitPane1;
	}

	private JPanel getJPanel() {
		if (this.jPanel == null) {
			this.jPanel = new JPanel();
			this.jPanel.setLayout(null);
			this.jPanel.add(getDeleteButton(), null);
		}
		return this.jPanel;
	}

	private JButton getDeleteButton() {
		if (this.deleteButton == null) {
			this.deleteButton = new JButton();
			this.deleteButton.setFont(new Font("楷体_GB2312", 1, 10));
			this.deleteButton.setSize(new Dimension(85, 25));
			this.deleteButton.setText("清除记录");
			this.deleteButton.setFont(new Font("Dialog", 1, 10));
			this.deleteButton.setLocation(new Point(2, 30));
			this.deleteButton.setPreferredSize(new Dimension(80, 25));
			this.deleteButton.setText("清空记录");
		}
		return this.deleteButton;
	}

	private JPanel getJPanel1() {
		if (this.jPanel1 == null) {
			this.jPanel1 = new JPanel();
			this.jPanel1.setLayout(null);
			this.jPanel1.add(getCloseButton(), null);
		}
		return this.jPanel1;
	}

	private JButton getCloseButton() {
		if (this.closeButton == null) {
			this.closeButton = new JButton();
			this.closeButton.setSize(new Dimension(60, 25));
			this.closeButton.setText("关闭");
			this.closeButton.setPreferredSize(new Dimension(60, 25));
			this.closeButton.setFont(new Font("楷体_GB2312", 1, 12));
			this.closeButton.setLocation(new Point(15, 30));
			this.closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List.this.dispose();
				}
			});
		}
		return this.closeButton;
	}

	private JScrollPane getJScrollPane() {
		if (this.jScrollPane == null) {
			this.jScrollPane = new JScrollPane();
			this.jScrollPane.setViewportView(getJTable());
		}
		return this.jScrollPane;
	}

	private JTable getJTable() {
		if (this.jTable == null) {
			this.jTable = new JTable(this.data, this.colNames);
			this.jTable.setEnabled(false);
		}
		return this.jTable;
	}
}
