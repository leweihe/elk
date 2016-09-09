package com.newclear.game.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton deleteButton = null;
	private JButton closeButton = null;
	private Object[] data;
	private String[] colNames = { "排名", "姓名", "得分" };
	private JTable jTable = null;
	private JPanel jPanel = null;
	private JScrollPane jScrollPane = null;

	public MenuFrame() {
		initialize();
	}

	public void select() throws Exception, SQLException {
		String sql = "select * from (select * from top order by score desc)where rownum <= 10";
		Connection conn = new ConnectionOrcl().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		DefaultTableModel dtm = new DefaultTableModel();
		for (int i = 0; i < 3; i++) {
			dtm.addColumn(this.colNames[i]);
		}
		int i = 1;
		while (rs.next()) {
			this.data = new Object[] { Integer.valueOf(i), rs.getString(1), Integer.valueOf(rs.getInt(2)) };
			dtm.addRow(this.data);
			i++;
		}
		this.jTable.setModel(dtm);
		rs.close();
		st.close();
		conn.close();
	}

	public void delete() throws ClassNotFoundException, SQLException {
		String sql = "delete  from  top";
		Connection conn = new ConnectionOrcl().getConnection();
		Statement st = conn.createStatement();
		st.executeUpdate(sql);

		conn.close();
		st.close();
	}

	private void initialize() {
		setSize(350, 235);
		setBackground(Color.WHITE);
		setResizable(false);
		setContentPane(getJContentPane());
		setFont(new Font("微软雅黑", 0, 14));
		setTitle("排行榜");
		setLocationRelativeTo(null);
	}

	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new BorderLayout());
			this.jContentPane.setBackground(Color.white);
			this.jContentPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			this.jContentPane.add(getJPanel(), "South");
			this.jContentPane.add(getJScrollPane(), "Center");
		}
		return this.jContentPane;
	}

	private JButton getDeleteButton() {
		if (this.deleteButton == null) {
			this.deleteButton = new JButton();
			this.deleteButton.setFont(new Font("微软雅黑", 0, 12));
			this.deleteButton.setText("清空");
			this.deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int sel = JOptionPane.showConfirmDialog(null, "确认清除记录", "提示", 0);
						switch (sel) {
						case 0:
							MenuFrame.this.delete();
							MenuFrame.this.select();
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}

		return this.deleteButton;
	}

	private JButton getCloseButton() {
		if (this.closeButton == null) {
			this.closeButton = new JButton();
			this.closeButton.setFont(new Font("微软雅黑", 0, 12));
			this.closeButton.setText("关闭");
			this.closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MenuFrame.this.dispose();
				}
			});
		}
		return this.closeButton;
	}

	private JTable getJTable() {
		if (this.jTable == null) {
			this.jTable = new JTable();
			try {
				select();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.jTable.setEnabled(false);
		}
		return this.jTable;
	}

	private JPanel getJPanel() {
		if (this.jPanel == null) {
			this.jPanel = new JPanel();
			this.jPanel.setBackground(Color.WHITE);
			this.jPanel.setLayout(new BorderLayout());
			this.jPanel.setOpaque(false);
			this.jPanel.add(getCloseButton(), "West");
			this.jPanel.add(getDeleteButton(), "East");
		}
		return this.jPanel;
	}

	private JScrollPane getJScrollPane() {
		if (this.jScrollPane == null) {
			this.jScrollPane = new JScrollPane();
			this.jScrollPane.setBackground(Color.white);
			this.jScrollPane.setViewportView(getJTable());
		}
		return this.jScrollPane;
	}
}
