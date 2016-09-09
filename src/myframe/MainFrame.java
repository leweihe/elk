package myframe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.sql.SQLException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MainFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar mainMenu = null;
	private JMenu choiceMenu = null;
	private JMenu menuGanme = null;
	private JMenu helpMenu = null;
	private JMenuItem continueMenuItem = null;
	private JMenuItem stopMenuItem = null;
	private JMenuItem quitMenuItem = null;
	private JMenuItem helpMenuItem = null;
	private JMenuItem listMenuItem = null;
	private JMenuItem explainMenuItem = null;
	private JMenuItem aboutMenuItem = null;
	private CenterPanel centerPanel = null;
	private JMenuItem reListMenuItem = null;
	private JMenuItem easyMenuItem = null;
	private JMenuItem middleMenuItem = null;
	private JMenuItem hardMenuItem = null;
	private JMenuItem restartMenuItem = null;
	private JMenuItem giveUpMenuItem;
	private int score;
	private int maxMission;
	private List list;

	private JPanel getCenterPanel() {
		if (this.centerPanel == null) {
			this.centerPanel = new CenterPanel();
			add(this.centerPanel, "Center");
			this.centerPanel.getMyJComponent().addMouseMotionListener(new MouseAdapter() {
				public void mouseDragged(MouseEvent e) {
					MainFrame.this.gameFinished(e);
				}

			});
			this.centerPanel.getMyJComponent().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					MainFrame.this.gameFinished(e);
				}
			});
		}
		return this.centerPanel;
	}

	private JMenuItem getEasyMenuItem() {
		if (this.easyMenuItem == null) {
			this.easyMenuItem = new JMenuItem();
			this.easyMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.easyMenuItem.setText("简单");
			this.easyMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().start(6, 25);
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getMyJComponent().getName());
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(true);
					MainFrame.this.restartMenuItem.setEnabled(true);
					MainFrame.this.listMenuItem.setEnabled(true);
					MainFrame.this.easyMenuItem.setEnabled(false);
					MainFrame.this.middleMenuItem.setEnabled(false);
					MainFrame.this.hardMenuItem.setEnabled(false);
				}
			});
		}
		return this.easyMenuItem;
	}

	private JMenuItem getMiddleMenuItem() {
		if (this.middleMenuItem == null) {
			this.middleMenuItem = new JMenuItem();
			this.middleMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.middleMenuItem.setText("中等");
			this.middleMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().start(8, 25);
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getMyJComponent().getName());
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(true);
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.restartMenuItem.setEnabled(true);
					MainFrame.this.listMenuItem.setEnabled(true);
					MainFrame.this.easyMenuItem.setEnabled(false);
					MainFrame.this.middleMenuItem.setEnabled(false);
					MainFrame.this.hardMenuItem.setEnabled(false);
				}
			});
		}
		return this.middleMenuItem;
	}

	private JMenuItem getHardMenuItem() {
		if (this.hardMenuItem == null) {
			this.hardMenuItem = new JMenuItem();
			this.hardMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.hardMenuItem.setText("困难");
			this.hardMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().start(10, 25);
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getMyJComponent().getName());
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.restartMenuItem.setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(true);
					MainFrame.this.listMenuItem.setEnabled(true);
					MainFrame.this.easyMenuItem.setEnabled(false);
					MainFrame.this.middleMenuItem.setEnabled(false);
					MainFrame.this.hardMenuItem.setEnabled(false);
				}
			});
		}
		return this.hardMenuItem;
	}

	private JMenu getMissionMenu() {
		if (this.missionMenu == null) {
			this.missionMenu = new JMenu();
			this.missionMenu.setFont(new Font("微软雅黑", 0, 14));

			this.missionMenu.setText("");
			this.missionMenu.add(getMissionMenuItem0());
			this.missionMenu.add(getMissionMenuItem1());
			this.missionMenu.add(getMissionMenuItem2());
			this.missionMenu.add(getMissionMenuItem3());
			this.missionMenu.add(getMissionMenuItem4());
			this.missionMenu.add(getMissionMenuItem5());
			this.missionMenu.add(getMissionMenuItem6());
			this.missionMenu.add(getMissionMenuItem7());
			this.missionMenu.add(getMissionMenuItem8());
			this.missionMenu.add(getMissionMenuItem9());
			this.missionMenu.add(getMissionMenuItem10());
			this.missionMenu.setVisible(false);
		}
		return this.missionMenu;
	}

	private JMenuItem getMissionMenuItem0() {
		if (this.missionMenuItem0 == null) {
			this.missionMenuItem0 = new JMenuItem();
			this.missionMenuItem0.setAccelerator(KeyStroke.getKeyStroke(48, 2, false));
			this.missionMenuItem0.setText("第零关");
			this.missionMenuItem0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(0);
				}
			});
		}

		return this.missionMenuItem0;
	}

	private JMenuItem getMissionMenuItem1() {
		if (this.missionMenuItem1 == null) {
			this.missionMenuItem1 = new JMenuItem();
			this.missionMenuItem1.setAccelerator(KeyStroke.getKeyStroke(49, 2, false));
			this.missionMenuItem1.setText("第一关");
			this.missionMenuItem1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(1);
				}
			});
		}
		return this.missionMenuItem1;
	}

	private JMenuItem getMissionMenuItem2() {
		if (this.missionMenuItem2 == null) {
			this.missionMenuItem2 = new JMenuItem();
			this.missionMenuItem2.setText("第二关");
			this.missionMenuItem2.setAccelerator(KeyStroke.getKeyStroke(50, 2, false));
			this.missionMenuItem2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(2);
				}
			});
		}
		return this.missionMenuItem2;
	}

	private JMenuItem getMissionMenuItem3() {
		if (this.missionMenuItem3 == null) {
			this.missionMenuItem3 = new JMenuItem();
			this.missionMenuItem3.setText("第三关");
			this.missionMenuItem3.setAccelerator(KeyStroke.getKeyStroke(51, 2, false));
			this.missionMenuItem3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(3);
				}
			});
		}
		return this.missionMenuItem3;
	}

	private JMenuItem getMissionMenuItem4() {
		if (this.missionMenuItem4 == null) {
			this.missionMenuItem4 = new JMenuItem();
			this.missionMenuItem4.setText("第四关");
			this.missionMenuItem4.setAccelerator(KeyStroke.getKeyStroke(52, 2, false));
			this.missionMenuItem4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(4);
				}
			});
		}
		return this.missionMenuItem4;
	}

	private JMenuItem getMissionMenuItem5() {
		if (this.missionMenuItem5 == null) {
			this.missionMenuItem5 = new JMenuItem();
			this.missionMenuItem5.setText("第五关");
			this.missionMenuItem5.setAccelerator(KeyStroke.getKeyStroke(53, 2, false));
			this.missionMenuItem5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(5);
				}
			});
		}
		return this.missionMenuItem5;
	}

	private JMenuItem getMissionMenuItem6() {
		if (this.missionMenuItem6 == null) {
			this.missionMenuItem6 = new JMenuItem();
			this.missionMenuItem6.setText("第六关");
			this.missionMenuItem6.setAccelerator(KeyStroke.getKeyStroke(54, 2, false));
			this.missionMenuItem6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(6);
				}
			});
		}
		return this.missionMenuItem6;
	}

	private JMenuItem getMissionMenuItem7() {
		if (this.missionMenuItem7 == null) {
			this.missionMenuItem7 = new JMenuItem();
			this.missionMenuItem7.setText("第七关");
			this.missionMenuItem7.setAccelerator(KeyStroke.getKeyStroke(55, 2, false));
			this.missionMenuItem7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(7);
				}
			});
		}
		return this.missionMenuItem7;
	}

	private JMenuItem getMissionMenuItem8() {
		if (this.missionMenuItem8 == null) {
			this.missionMenuItem8 = new JMenuItem();
			this.missionMenuItem8.setText("第八关");
			this.missionMenuItem8.setAccelerator(KeyStroke.getKeyStroke(56, 2, false));
			this.missionMenuItem8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(8);
				}
			});
		}
		return this.missionMenuItem8;
	}

	private JMenuItem getMissionMenuItem9() {
		if (this.missionMenuItem9 == null) {
			this.missionMenuItem9 = new JMenuItem();
			this.missionMenuItem9.setText("第九关");
			this.missionMenuItem9.setAccelerator(KeyStroke.getKeyStroke(57, 2, false));
			this.missionMenuItem9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(9);
				}
			});
		}
		return this.missionMenuItem9;
	}

	private JMenuItem getMissionMenuItem10() {
		if (this.missionMenuItem10 == null) {
			this.missionMenuItem10 = new JMenuItem();
			this.missionMenuItem10.setText("隐藏关");
			this.missionMenuItem10.setAccelerator(KeyStroke.getKeyStroke(123, 0, false));
			this.missionMenuItem10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().setMission(10);
					MainFrame.this.centerPanel.getMyJComponent().getF().setDegree(1);
				}
			});
		}
		return this.missionMenuItem10;
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
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
		setSize(700, 720);
		setResizable(true);
		setJMenuBar(getMainMenu());
		setContentPane(getJContentPane());
		setFont(new Font("微软雅黑", 0, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/flags/icon.png")));
		setTitle("连连看");
		setLocationRelativeTo(null);
		setResizable(false);
		this.choiceMenu.setEnabled(false);
		this.stopMenuItem.setEnabled(false);
		this.restartMenuItem.setEnabled(false);
		this.giveUpMenuItem.setEnabled(false);
		this.continueMenuItem.setEnabled(false);
	}

	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new BorderLayout());
			this.jContentPane.add(getCenterPanel(), "Center");
			this.jContentPane.add(this.centerPanel.getMyJComponent().getSouthPanel(), "South");

			TimeCtrl.getTimeProgressBar().addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent e) {
					MainFrame.this.timeOver(e);
				}
			});
		}
		return this.jContentPane;
	}

	private JMenuBar getMainMenu() {
		if (this.mainMenu == null) {
			this.mainMenu = new JMenuBar();
			this.mainMenu.add(getMenuGanme());
			this.mainMenu.add(getChoiceMenu());
			this.mainMenu.add(getHelpMenu());
			this.mainMenu.add(getMissionMenu());
			this.mainMenu.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(KeyEvent e) {
				}
			});
		}

		return this.mainMenu;
	}

	private JMenu getMenuGanme() {
		if (this.menuGanme == null) {
			this.menuGanme = new JMenu();
			this.menuGanme.setText("游戏(G)");
			this.menuGanme.setFont(new Font("微软雅黑", 0, 14));
			this.menuGanme.setMnemonic(71);
			this.menuGanme.add(getEasyMenuItem());
			this.menuGanme.add(getMiddleMenuItem());
			this.menuGanme.add(getHardMenuItem());
			this.menuGanme.addSeparator();
			this.menuGanme.add(getStopMenuItem());
			this.menuGanme.add(getContinueMenuItem());
			this.menuGanme.add(getGiveupMenuItem());
			this.menuGanme.add(getRestartMenuItem());
			this.menuGanme.addSeparator();
			this.menuGanme.add(getQuitMenuItem());
		}
		return this.menuGanme;
	}

	private JMenuItem getGiveupMenuItem() {
		if (this.giveUpMenuItem == null) {
			this.giveUpMenuItem = new JMenuItem();
			this.giveUpMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.giveUpMenuItem.setText("放弃");
			this.giveUpMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.giveUp(e);
				}
			});
		}

		return this.giveUpMenuItem;
	}

	private JMenu getChoiceMenu() {
		if (this.choiceMenu == null) {
			this.choiceMenu = new JMenu();
			this.choiceMenu.setText("选项(C)");
			this.choiceMenu.setFont(new Font("微软雅黑", 0, 14));
			this.choiceMenu.setMnemonic(67);
			this.choiceMenu.add(getHelpMenuItem());
			this.choiceMenu.add(getReListMenuItem());
		}
		return this.choiceMenu;
	}

	private JMenuItem getReListMenuItem() {
		if (this.reListMenuItem == null) {
			this.reListMenuItem = new JMenuItem();
			this.reListMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.reListMenuItem.setText("重排");
			this.reListMenuItem.setAccelerator(KeyStroke.getKeyStroke(116, 0, false));
			this.reListMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().reList();
				}
			});
		}
		return this.reListMenuItem;
	}

	private JMenu getHelpMenu() {
		if (this.helpMenu == null) {
			this.helpMenu = new JMenu();
			this.helpMenu.setText("帮助(H)");
			this.helpMenu.setFont(new Font("微软雅黑", 0, 14));
			this.helpMenu.setMnemonic(72);
			this.helpMenu.add(getListMenuItem());
			this.helpMenu.addSeparator();
			this.helpMenu.add(getExplainMenuItem());
			this.helpMenu.add(getAboutMenuItem());
		}
		return this.helpMenu;
	}

	private JMenuItem getExplainMenuItem() {
		if (this.explainMenuItem == null) {
			this.explainMenuItem = new JMenuItem();
			this.explainMenuItem.setText("游戏说明");
			this.explainMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.explainMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Help about = new Help();
					about.setVisible(true);
				}
			});
		}
		return this.explainMenuItem;
	}

	private JMenuItem getAboutMenuItem() {
		if (this.aboutMenuItem == null) {
			this.aboutMenuItem = new JMenuItem();
			this.aboutMenuItem.setText("关于核辐射");
			this.aboutMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					About about = new About();
					about.setVisible(true);
				}
			});
		}

		return this.aboutMenuItem;
	}

	private JMenuItem getRestartMenuItem() {
		if (this.restartMenuItem == null) {
			this.restartMenuItem = new JMenuItem();
			this.restartMenuItem.setText("重新开始");
			this.restartMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.restartMenuItem.setAccelerator(KeyStroke.getKeyStroke(114, 0, false));
			this.restartMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (MainFrame.this.restartMenuItem.getText() == "重新开始") {
						MainFrame.this.centerPanel.getMyJComponent().setMission(0);
						MainFrame.this.score = 0;
					} else if (MainFrame.this.restartMenuItem.getText() == "进入下一关") {
						MainFrame.this.centerPanel.getMyJComponent()
								.setMission(MainFrame.this.centerPanel.getMyJComponent().getMission() + 1);
					}
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(true);
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getMyJComponent().getName());
					MainFrame.this.centerPanel.getMyJComponent().reStart(
							MainFrame.this.centerPanel.getMyJComponent().getF().getSize(),
							MainFrame.this.centerPanel.getMyJComponent().getF().getDegree());
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.restartMenuItem.setText("重新开始");
				}
			});
		}

		return this.restartMenuItem;
	}

	private JMenuItem getContinueMenuItem() {
		if (this.continueMenuItem == null) {
			this.continueMenuItem = new JMenuItem();
			this.continueMenuItem.setText("继续");
			this.continueMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.continueMenuItem.setAccelerator(KeyStroke.getKeyStroke(112, 0, false));
			this.continueMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getMyJComponent().timeCtrl.startButton();
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getMyJComponent().getName());
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.continueMenuItem.setEnabled(false);
					MainFrame.this.restartMenuItem.setEnabled(true);
					MainFrame.this.listMenuItem.setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(true);
				}
			});
		}
		return this.continueMenuItem;
	}

	private JMenuItem getQuitMenuItem() {
		if (this.quitMenuItem == null) {
			this.quitMenuItem = new JMenuItem();
			this.quitMenuItem.setText("退出");
			this.quitMenuItem.setFont(new Font("微软雅黑", 0, 14));

			this.quitMenuItem.addActionListener(new ActionListener() {
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

		return this.quitMenuItem;
	}

	private JMenuItem getListMenuItem() {
		if (this.listMenuItem == null) {
			this.listMenuItem = new JMenuItem();
			this.listMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.listMenuItem.setText("排行榜");
			this.listMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.list = new List();
					MainFrame.this.list.setVisible(true);
				}
			});
		}

		return this.listMenuItem;
	}

	private JMenuItem getStopMenuItem() {
		if (this.stopMenuItem == null) {
			this.stopMenuItem = new JMenuItem();
			this.stopMenuItem.setText("暂停");
			this.stopMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.stopMenuItem.setAccelerator(KeyStroke.getKeyStroke(113, 0, false));
			this.stopMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.stopMenuItem.setEnabled(false);
					MainFrame.this.centerPanel.getMyJComponent().timeCtrl.stopButton();
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getPausePanel().getName());
					MainFrame.this.choiceMenu.setEnabled(false);
					MainFrame.this.getContinueMenuItem().setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(false);
					MainFrame.this.restartMenuItem.setEnabled(false);
				}
			});
		}

		return this.stopMenuItem;
	}

	Flag f = new Flag();
	private JMenu missionMenu = null;
	private JMenuItem missionMenuItem0 = null;
	private JMenuItem missionMenuItem1 = null;
	private JMenuItem missionMenuItem2 = null;
	private JMenuItem missionMenuItem3 = null;
	private JMenuItem missionMenuItem4 = null;
	private JMenuItem missionMenuItem5 = null;
	private JMenuItem missionMenuItem6 = null;
	private JMenuItem missionMenuItem7 = null;
	private JMenuItem missionMenuItem8 = null;
	private JMenuItem missionMenuItem9 = null;
	private JMenuItem missionMenuItem10 = null;

	private JMenuItem getHelpMenuItem() {
		if (this.helpMenuItem == null) {
			this.helpMenuItem = new JMenuItem();
			this.helpMenuItem.setFont(new Font("微软雅黑", 0, 14));
			this.helpMenuItem.setText("提示");
			this.helpMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Flag.bool = true;
				}
			});
		}
		return this.helpMenuItem;
	}

	private void gameFinished(MouseEvent e) {
		try {
			this.maxMission = 10;
			Flag.bool = false;
			this.centerPanel.getMyJComponent().doIt(e);
			if (this.centerPanel.getMyJComponent().getF().isNull()) {
				this.stopMenuItem.setEnabled(false);
				this.giveUpMenuItem.setEnabled(false);
				this.choiceMenu.setEnabled(false);

				this.score =

				(TimeCtrl.getTimeProgressBar().getValue()
						+ this.centerPanel.getMyJComponent().getF().countHowManyLast() * 13 + this.score);
				if (this.centerPanel.getMyJComponent().getMission() != this.maxMission) {
					getRestartMenuItem().setText("进入下一关");
					this.centerPanel.getMyJComponent().timeCtrl.stopButton();
					CardLayout card = (CardLayout) this.centerPanel.getLayout();
					card.show(this.centerPanel, this.centerPanel.getNextPanel().getName());
				} else if (this.centerPanel.getMyJComponent().getMission() == this.maxMission) {
					getRestartMenuItem().setText("重新开始");
					this.centerPanel.getMyJComponent().setMission(0);
					String name = JOptionPane.showInputDialog(null, "您 的 分数 是 " + this.score + ",请输入姓名", "提示", 1);
					if ((name != null) && (name != "")) {
						this.centerPanel.getMyJComponent().insert(name, this.score);
						CardLayout card = (CardLayout) this.centerPanel.getLayout();
						card.show(this.centerPanel, this.centerPanel.getEndPanel().getName());
						this.list = new List();
						this.list.setVisible(true);
					}
					this.score = 0;

					this.centerPanel.getMyJComponent().timeCtrl.stopButton();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void giveUp(ActionEvent e) {
		try {
			this.score =

			(TimeCtrl.getTimeProgressBar().getValue()
					+ this.centerPanel.getMyJComponent().getF().countHowManyLast() * 13 + this.score);
			String name = JOptionPane.showInputDialog(null, "您 的 分数 是 " + this.score + ",请输入姓名", "提示", 1);
			if ((name != null) && (name != "")) {
				this.centerPanel.getMyJComponent().insert(name, this.score);
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		this.score = 0;
		List list = new List();
		list.setVisible(true);
		this.centerPanel.getMyJComponent().gameOver();
		CardLayout card = (CardLayout) this.centerPanel.getLayout();
		card.show(this.centerPanel, this.centerPanel.getEndPanel().getName());
		this.centerPanel.getMyJComponent().setMission(0);
		this.easyMenuItem.setEnabled(true);
		this.middleMenuItem.setEnabled(true);
		this.hardMenuItem.setEnabled(true);
		this.choiceMenu.setEnabled(false);
		this.easyMenuItem.setEnabled(true);
		this.middleMenuItem.setEnabled(true);
		this.restartMenuItem.setEnabled(false);
		this.hardMenuItem.setEnabled(true);
		this.giveUpMenuItem.setEnabled(false);
		this.stopMenuItem.setEnabled(false);
	}

	private void timeOver(PropertyChangeEvent e) {
		if (e.getNewValue().equals("over")) {
			try {
				this.choiceMenu.setEnabled(false);
				this.score =

				(TimeCtrl.getTimeProgressBar().getValue()
						+ this.centerPanel.getMyJComponent().getF().countHowManyLast() * 13 + this.score);
				String name = JOptionPane.showInputDialog(null, "您 的 分数 是 " + this.score + ",请输入姓名", "提示", 1);
				if ((name != null) && (name != "")) {
					this.centerPanel.getMyJComponent().insert(name, this.score);
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			TimeCtrl.getTimeProgressBar().setName("begin");
			List list = new List();
			list.setVisible(true);
			CardLayout card = (CardLayout) this.centerPanel.getLayout();
			card.show(this.centerPanel, this.centerPanel.getEndPanel().getName());
			getStopMenuItem().setEnabled(false);
			this.giveUpMenuItem.setEnabled(false);
			this.choiceMenu.setEnabled(false);
		}
	}
}

