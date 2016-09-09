package com.newclear.game.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar mainMenu = null;
	private JMenu menu_choice = null;
	private JMenu menu_ganme = null;
	private JMenu menu_help = null;
	private JMenuItem jMenuItem_start = null;
	private JMenuItem jMenuItem_stop = null;
	private JMenuItem jMenuItem_quit = null;
	private JMenuItem jMenuItem_Tishi = null;
	private JMenuItem jMenuItem_phb = null;
	private JMenuItem jMenuItem_shuoming = null;
	private JMenuItem jMenuItem_about = null;
	private JMenuItem jMenuItem_restart = null;
	private JComponent jComponent = null;
	private JPanel jPanel = null;

	private JComponent getJComponent() {
		if (this.jComponent == null) {
			this.jComponent = new MyJComponent();
			this.jComponent.setName("jComponent");
		}
		return this.jComponent;
	}

	private JPanel getJPanel() {
		if (this.jPanel == null) {
			this.jPanel = new JPanel();
			this.jPanel.setLayout(new BorderLayout());
			this.jPanel.setName("jPanel");
			this.jPanel.add(getJComponent(), "Center");
		}
		return this.jPanel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame thisClass = new MainFrame();
				thisClass.setDefaultCloseOperation(3);
				thisClass.setVisible(true);
			}
		});
	}

	public MainFrame() {
		initialize();
	}

	private void initialize() {
		setSize(600, 600);
		setResizable(false);
		setJMenuBar(getMainMenu());
		setContentPane(getJContentPane());
		setTitle("连连看");
		setLocationRelativeTo(null);
	}

	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new CardLayout());
			this.jContentPane.add(getJPanel(), getJPanel().getName());
		}
		return this.jContentPane;
	}

	private JMenuBar getMainMenu() {
		if (this.mainMenu == null) {
			this.mainMenu = new JMenuBar();
			this.mainMenu.add(getMenu_ganme());
			this.mainMenu.add(getMenu_choice());
			this.mainMenu.add(getMenu_help());
		}
		return this.mainMenu;
	}

	private JMenu getMenu_choice() {
		if (this.menu_choice == null) {
			this.menu_choice = new JMenu();
			this.menu_choice.setText("选项(C)");
			this.menu_choice.setFont(new Font("微软雅黑", 1, 14));
			this.menu_choice.setMnemonic(67);
			this.menu_choice.add(getJMenuItem_Tishi());
			this.menu_choice.addSeparator();
			this.menu_choice.add(getJMenuItem_phb());
		}
		return this.menu_choice;
	}

	private JMenu getMenu_ganme() {
		if (this.menu_ganme == null) {
			this.menu_ganme = new JMenu();
			this.menu_ganme.setText("游戏(G)");
			this.menu_ganme.setFont(new Font("微软雅黑", 1, 14));
			this.menu_ganme.setMnemonic(71);
			this.menu_ganme.add(getJMenuItem_start());
			this.menu_ganme.addSeparator();
			this.menu_ganme.add(getJMenuItem_stop());
			this.menu_ganme.addSeparator();
			this.menu_ganme.add(getJMenuItem_restart());
			this.menu_ganme.addSeparator();
			this.menu_ganme.add(getJMenuItem_quit());
		}
		return this.menu_ganme;
	}

	private JMenu getMenu_help() {
		if (this.menu_help == null) {
			this.menu_help = new JMenu();
			this.menu_help.setText("帮助(H)");
			this.menu_help.setFont(new Font("微软雅黑", 1, 14));
			this.menu_help.setMnemonic(72);
			this.menu_help.add(getJMenuItem_shuoming());
			this.menu_help.addSeparator();
			this.menu_help.add(getJMenuItem_about());
		}
		return this.menu_help;
	}

	private JMenuItem getJMenuItem_start() {
		if (this.jMenuItem_start == null) {
			this.jMenuItem_start = new JMenuItem();
			this.jMenuItem_start.setText("开始");
			this.jMenuItem_start.setMnemonic(0);
			this.jMenuItem_start.setAccelerator(KeyStroke.getKeyStroke(112, 0, false));
		}
		return this.jMenuItem_start;
	}

	private JMenuItem getJMenuItem_stop() {
		if (this.jMenuItem_stop == null) {
			this.jMenuItem_stop = new JMenuItem();
			this.jMenuItem_stop.setText("暂停");
			this.jMenuItem_stop.setAccelerator(KeyStroke.getKeyStroke(113, 0, false));
		}
		return this.jMenuItem_stop;
	}

	private JMenuItem getJMenuItem_quit() {
		if (this.jMenuItem_quit == null) {
			this.jMenuItem_quit = new JMenuItem();
			this.jMenuItem_quit.setText("退出");
			this.jMenuItem_quit.setAccelerator(KeyStroke.getKeyStroke(115, 2, false));
			this.jMenuItem_quit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "确定退出!", "选择", 0);
					switch (result) {
					case 0:
						System.exit(0);
						break;
					}

				}
			});
		}

		return this.jMenuItem_quit;
	}

	private JMenuItem getJMenuItem_Tishi() {
		if (this.jMenuItem_Tishi == null) {
			this.jMenuItem_Tishi = new JMenuItem();
			this.jMenuItem_Tishi.setText("提示");
		}
		return this.jMenuItem_Tishi;
	}

	private JMenuItem getJMenuItem_phb() {
		if (this.jMenuItem_phb == null) {
			this.jMenuItem_phb = new JMenuItem();
			this.jMenuItem_phb.setText("排行榜");
			this.jMenuItem_phb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List phb = new List();
					phb.setVisible(true);
				}
			});
		}
		return this.jMenuItem_phb;
	}

	private JMenuItem getJMenuItem_shuoming() {
		if (this.jMenuItem_shuoming == null) {
			this.jMenuItem_shuoming = new JMenuItem();
			this.jMenuItem_shuoming.setText("游戏说明");
			this.jMenuItem_shuoming.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Help sh = new Help();
					sh.setVisible(true);
				}
			});
		}
		return this.jMenuItem_shuoming;
	}

	private JMenuItem getJMenuItem_about() {
		if (this.jMenuItem_about == null) {
			this.jMenuItem_about = new JMenuItem();
			this.jMenuItem_about.setText("关于...");
			this.jMenuItem_about.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					About fabout = new About();
					fabout.setVisible(true);
				}
			});
		}

		return this.jMenuItem_about;
	}

	private JMenuItem getJMenuItem_restart() {
		if (this.jMenuItem_restart == null) {
			this.jMenuItem_restart = new JMenuItem();
			this.jMenuItem_restart.setText("重新开始");
			this.jMenuItem_restart.setAccelerator(KeyStroke.getKeyStroke(114, 0, false));
		}
		return this.jMenuItem_restart;
	}
}