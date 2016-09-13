package com.newclear.game.frame;

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

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.newclear.game.container.ElkContainer;
import com.newclear.game.exception.ClickOutOfBoardException;
import com.newclear.game.object.GameBoard;
import com.newclear.game.panel.CenterPanel;
import com.newclear.game.panel.TimeCtroller;

public class MainFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = -8273937878294201119L;

	private JPanel jContentPane;
	private JMenuBar mainMenu;
	private JMenu menuGanme;
	private JMenu choiceMenu;
	private JMenuItem continueMenuItem;
	private JMenuItem stopMenuItem;
	private JMenuItem quitMenuItem;
	private JMenuItem promptMenuItem;
	private JMenuItem reListMenuItem;
	private JMenuItem easyMenuItem;
	private JMenuItem middleMenuItem;
	private JMenuItem hardMenuItem;
	private JMenuItem restartMenuItem;
	private JMenuItem giveUpMenuItem;

	private CenterPanel centerPanel;
	private int maxMission;

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

	private JPanel getCenterPanel() {
		if (this.centerPanel == null) {
			this.centerPanel = new CenterPanel();
			add(this.centerPanel, "Center");
			this.centerPanel.getElkMainPanel().addMouseMotionListener(new MouseAdapter() {
				public void mouseDragged(MouseEvent e) {
					MainFrame.this.gameFinished(e);
				}

			});
			this.centerPanel.getElkMainPanel().addMouseListener(new MouseAdapter() {
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
			this.easyMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
			this.easyMenuItem.setText("简单");
			this.easyMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getElkMainPanel().start(6, 25);
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getElkMainPanel().getName());
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(true);
					MainFrame.this.restartMenuItem.setEnabled(true);
					MainFrame.this.reListMenuItem.setEnabled(true);
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
			this.middleMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
			this.middleMenuItem.setText("中等");
			this.middleMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getElkMainPanel().start(8, 25);
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getElkMainPanel().getName());
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(true);
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.restartMenuItem.setEnabled(true);
					MainFrame.this.reListMenuItem.setEnabled(true);
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
			this.hardMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
			this.hardMenuItem.setText("困难");
			this.hardMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getElkMainPanel().start(10, 25);
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getElkMainPanel().getName());
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.restartMenuItem.setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(true);
					MainFrame.this.reListMenuItem.setEnabled(true);
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
			this.missionMenu.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));

			this.missionMenu.setText("");
			for (int i = 0; i < 10; i++) {
				this.missionMenu.add(getMissionMenuItem(i));
			}
			this.missionMenu.setVisible(false);
		}
		return this.missionMenu;
	}

	private JMenuItem getMissionMenuItem(int level) {
		this.missionMenuItem = new JMenuItem();
		this.missionMenuItem.setAccelerator(KeyStroke.getKeyStroke(48, 2, false));
		this.missionMenuItem.setText("第" + (level + 1) + "关");
		this.missionMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.centerPanel.getElkMainPanel().setMission(level);
			}
		});

		return this.missionMenuItem;
	}

	private void initialize() {
		setSize(700, 720);
		setResizable(true);
		setJMenuBar(getMainMenu());
		setContentPane(getJContentPane());
		setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
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
			this.jContentPane.add(this.centerPanel.getElkMainPanel().getSouthPanel(), "South");

			TimeCtroller.getTimeProgressBar().addPropertyChangeListener(new java.beans.PropertyChangeListener() {
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
			this.menuGanme.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
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
			this.giveUpMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
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
			this.choiceMenu.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
			this.choiceMenu.setMnemonic(67);
			this.choiceMenu.add(getHelpMenuItem());
			this.choiceMenu.add(getReListMenuItem());
		}
		return this.choiceMenu;
	}

	private JMenuItem getReListMenuItem() {
		if (this.reListMenuItem == null) {
			this.reListMenuItem = new JMenuItem();
			this.reListMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
			this.reListMenuItem.setText("重排");
			this.reListMenuItem.setAccelerator(KeyStroke.getKeyStroke(116, 0, false));
			this.reListMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getElkMainPanel().reloadBoard();
				}
			});
		}
		return this.reListMenuItem;
	}

	private JMenuItem getRestartMenuItem() {
		if (this.restartMenuItem == null) {
			this.restartMenuItem = new JMenuItem();
			this.restartMenuItem.setText(ElkContainer.ACTION.RESTART);
			this.restartMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
			this.restartMenuItem.setAccelerator(KeyStroke.getKeyStroke(114, 0, false));
			this.restartMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (MainFrame.this.restartMenuItem.getText() == ElkContainer.ACTION.RESTART) {
						MainFrame.this.centerPanel.getElkMainPanel().setMission(0);
					} else if (MainFrame.this.restartMenuItem.getText() == "进入下一关") {
						MainFrame.this.centerPanel.getElkMainPanel()
								.setMission(MainFrame.this.centerPanel.getElkMainPanel().getMission() + 1);
					}
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.giveUpMenuItem.setEnabled(true);
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getElkMainPanel().getName());
					MainFrame.this.centerPanel.getElkMainPanel().reStart(
							MainFrame.this.centerPanel.getElkMainPanel().getF().getSize(),
							MainFrame.this.centerPanel.getElkMainPanel().getF().getDegree());
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.restartMenuItem.setText(ElkContainer.ACTION.RESTART);
				}
			});
		}

		return this.restartMenuItem;
	}

	private JMenuItem getContinueMenuItem() {
		if (this.continueMenuItem == null) {
			this.continueMenuItem = new JMenuItem();
			this.continueMenuItem.setText("继续");
			this.continueMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
			this.continueMenuItem.setAccelerator(KeyStroke.getKeyStroke(112, 0, false));
			this.continueMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getElkMainPanel().getTimeCtrl().startButton();
					CardLayout card = (CardLayout) MainFrame.this.centerPanel.getLayout();
					card.show(MainFrame.this.centerPanel, MainFrame.this.centerPanel.getElkMainPanel().getName());
					MainFrame.this.stopMenuItem.setEnabled(true);
					MainFrame.this.choiceMenu.setEnabled(true);
					MainFrame.this.continueMenuItem.setEnabled(false);
					MainFrame.this.restartMenuItem.setEnabled(true);
					MainFrame.this.reListMenuItem.setEnabled(true);
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
			this.quitMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));

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

	private JMenuItem getStopMenuItem() {
		if (this.stopMenuItem == null) {
			this.stopMenuItem = new JMenuItem();
			this.stopMenuItem.setText("暂停");
			this.stopMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
			this.stopMenuItem.setAccelerator(KeyStroke.getKeyStroke(113, 0, false));
			this.stopMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.stopMenuItem.setEnabled(false);
					MainFrame.this.centerPanel.getElkMainPanel().getTimeCtrl().stopButton();
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

	GameBoard f = new GameBoard();
	private JMenu missionMenu = null;
	private JMenuItem missionMenuItem = null;

	private JMenuItem getHelpMenuItem() {
		if (this.promptMenuItem == null) {
			this.promptMenuItem = new JMenuItem();
			this.promptMenuItem.setFont(new Font(ElkContainer.FONT_STYLE, 0, 14));
			this.promptMenuItem.setText("提示");
			this.promptMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.centerPanel.getElkMainPanel().prompt();
				}
			});
		}
		return this.promptMenuItem;
	}

	private void gameFinished(MouseEvent e) {
		this.maxMission = 10;
		f.setPromptflag(false);
		try {
			this.centerPanel.getElkMainPanel().doIt(e);
		} catch (ClickOutOfBoardException e1) {
			e1.printStackTrace();
		}
		if (this.centerPanel.getElkMainPanel().getF().isNull()) {
			this.stopMenuItem.setEnabled(false);
			this.giveUpMenuItem.setEnabled(false);
			this.choiceMenu.setEnabled(false);

			if (this.centerPanel.getElkMainPanel().getMission() != this.maxMission) {
				getRestartMenuItem().setText("进入下一关");
				this.centerPanel.getElkMainPanel().getTimeCtrl().stopButton();
				CardLayout card = (CardLayout) this.centerPanel.getLayout();
				card.show(this.centerPanel, this.centerPanel.getNextPanel().getName());
			} else if (this.centerPanel.getElkMainPanel().getMission() == this.maxMission) {
				this.centerPanel.getElkMainPanel().setMission(0);
				this.centerPanel.getElkMainPanel().getTimeCtrl().stopButton();
			}
		}
	}

	private void giveUp(ActionEvent e) {
		getRestartMenuItem().setText(ElkContainer.ACTION.RESTART);
		this.centerPanel.getElkMainPanel().setMission(0);
		this.centerPanel.getElkMainPanel().getTimeCtrl().stopButton();
		this.centerPanel.getElkMainPanel().gameOver();
		CardLayout card = (CardLayout) this.centerPanel.getLayout();
		card.show(this.centerPanel, this.centerPanel.getEndPanel().getName());
		this.centerPanel.getElkMainPanel().setMission(0);
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
			getRestartMenuItem().setText(ElkContainer.ACTION.RESTART);
			this.centerPanel.getElkMainPanel().setMission(0);
			this.centerPanel.getElkMainPanel().getTimeCtrl().stopButton();
			TimeCtroller.getTimeProgressBar().setName("begin");
			CardLayout card = (CardLayout) this.centerPanel.getLayout();
			card.show(this.centerPanel, this.centerPanel.getEndPanel().getName());
			getStopMenuItem().setEnabled(false);
			this.giveUpMenuItem.setEnabled(false);
			this.choiceMenu.setEnabled(false);
		}
	}
}
