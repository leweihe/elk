package com.newclear.game.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class HelpFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JSplitPane jSplitPane = null;
	private JTree jTree = null;
	private JSplitPane jSplitPane1 = null;
	private JLabel jLabel = null;
	private JTextPane jTextPane = null;

	public HelpFrame() {
		initialize();
	}

	private void initialize() {
		setSize(900, 700);
		setLocationRelativeTo(null);
		setResizable(true);
		setContentPane(getJContentPane());
		setTitle("说明");
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
			this.jSplitPane.setLeftComponent(getJTree());
			this.jSplitPane.setRightComponent(getJSplitPane1());
		}
		return this.jSplitPane;
	}

	private JTree getJTree() {
		if (this.jTree == null) {
			this.jTree = new JTree();

			this.jTree.setRootVisible(false);
			this.jTree.setEditable(false);
			this.jTree.setShowsRootHandles(true);
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("说明");
			DefaultMutableTreeNode a = new DefaultMutableTreeNode("版本宣告");
			DefaultMutableTreeNode b = new DefaultMutableTreeNode("系统需求");
			DefaultMutableTreeNode c = new DefaultMutableTreeNode("游戏规则玩法说明");
			DefaultMutableTreeNode d = new DefaultMutableTreeNode("版本更新");
			DefaultMutableTreeNode e = new DefaultMutableTreeNode("作者说明");
			DefaultMutableTreeNode f = new DefaultMutableTreeNode("国旗说明");
			root.add(a);
			root.add(b);
			root.add(c);
			root.add(d);
			root.add(e);
			root.add(f);
			DefaultMutableTreeNode China = new DefaultMutableTreeNode("中国");
			DefaultMutableTreeNode Singapore = new DefaultMutableTreeNode("新加坡");
			DefaultMutableTreeNode Slovakia = new DefaultMutableTreeNode("斯洛伐克");
			f.add(China);
			f.add(Singapore);
			f.add(Slovakia);
			DefaultTreeModel tmodel = new DefaultTreeModel(root);
			this.jTree.setModel(tmodel);
			this.jTree.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) HelpFrame.this.jTree
							.getLastSelectedPathComponent();
					if (node.getUserObject() == "版本宣告") {
						HelpFrame.this.jLabel.setText("版本宣告");
						HelpFrame.this.jTextPane.setText(
								"1.本游戏为免费软件。\n\n2.非经过作者(以下简称制作群)同意，不得将此游戏应用于商业用途。\n\n3.因为制作群的美术和音乐天份不佳，故作品中使用到的图案、配乐和音效某些是取自于网路资源，这些版权为原作者所有。如有不慎侵权，请来函告知，制作群会立即改善。\n\n4.如执行此游戏，而造成玩家系统当机或损坏，制作群不负任何责任。");
					} else if (node.getUserObject() == "系统需求") {
						HelpFrame.this.jLabel.setText("系统需求");
						HelpFrame.this.jTextPane
								.setText("操作系统：Windows9X，WindowNT，WindowsMe，Window2000，WindowXP，Windows2003。");
					} else if (node.getUserObject() == "游戏规则玩法说明") {
						HelpFrame.this.jLabel.setText("游戏规则玩法说明");
						HelpFrame.this.jTextPane.setText(
								"1、玩法说明：\n\n这是针对小朋友制作的神奇宝贝图案版游戏，最主要是训练眼明手快及增强逻辑判断能力。\n\n游戏规则是需选择一对相同的牌连线，但此连线是在避开其他牌子后，呈现的路径以不超过二转弯为主，如符合规定则消除此一对牌而得分。每一局里玩家需要在规定的时间内消除所有的牌子，当完成任务后，方能进行下一关，当出现残局时，游戏会自动重新洗牌，游戏结束后会以玩家的得分多寡来决定是否登录排行榜。\n\n2、生命点数说明：\n\n生命点数，等同于洗牌次数。当出现无解的局面时，游戏会自动扣掉一点生命点数，而重新洗牌。游戏一开始会给玩家一些生命值，每过一关会增加一点。\n玩家使用“重新洗牌”功能，则会扣除1点生命点数。如果生命点数等于0，而且局面出现无解，则游戏结束。\n\n3、提示说明：\n\n使用“提示”功能，游戏会自动显示一组可以消除的牌组。游戏一开始会结玩家一些提示值，每过一关会增加一点。\n\n4、分数说明：\n\n分数上的设计，每过一关，则会依照比例加重给分。\n时间、生命值、提示点数都依照关数，以某种比例给分。\n另外过关也会额外加分（例如第一关+200，第二关+400，第七关+2800）\n\n5、菜单说明：\n\n【提示】：自动显示一组可以消除的牌组，有次数限制。\n【重新洗牌】：扣除一点生命点数，并重新换牌，有次数限制。重新洗牌功能，也可以用來自杀... \n【暂停／取消暂停】：按回车键可暂停时间、隐藏牌面（最小化状态）；再用鼠标点击屏幕底部任务栏中连连看项，可展开牌面并继续游戏。\n【音乐开关】：音乐开关，预设是开。\n【音效开关】：音效开关，预设是开。\n【查看榜单】：观看本机上的排行榜，里面有选项可以消除本机上的所有排行榜。\n【关于】：作者\n\n6、关卡說明（每种难度均有11关）：\n\n第零关 不变化\n第一关 向下\n第二关 向上\n第三关 向右\n第四关 向左\n第五关 左右集中\n第六关 左右分离\n第七关 上下集中\n第八关 上下分离\n第九关 向内集中\n隐藏关 向外分离");

					} else if (node.getUserObject() == "版本更新") {
						HelpFrame.this.jLabel.setText("版本更新");
						HelpFrame.this.jTextPane.setText("2.41a版正在研制中……");
					} else if (node.getUserObject() == "作者说明") {
						HelpFrame.this.jLabel.setText("作者说明");
						HelpFrame.this.jTextPane.setText("作者:核辐射\n飞Q:192.168.2.166");
					} else {
						HelpFrame.this.jLabel.setText("国旗说明");
						HelpFrame.this.jTextPane.setText("了解各国国旗的含义");
						if (node.getUserObject() == "中国") {
							HelpFrame.this.jLabel.setText("中国");
							HelpFrame.this.jLabel
									.setIcon(new ImageIcon(getClass().getResource("/flags/China-Flag.png")));
							HelpFrame.this.jTextPane.setText(
									"五星红旗是《中华人民共和国宪法》规定的中华人民共和国国旗，在1949年7月由曾联松设计。其中红色象征革命；五星呈黄色，有象征中国人为黄种人之意。大星代表中国共产党，四颗小星代表工人、农民、知识分子、民族资产阶级（即原“士、农、工、商”之所谓“四民”，但依共产主义意识形态，顺序被改为“工、农、士、商”）。四颗小星环拱于大星之右，并各有一个角尖正对大星的中心点，象征中国共产党领导下的革命人民大团结和人民对党的拥护。");
						} else if (node.getUserObject() == "阿根廷") {
							HelpFrame.this.jLabel.setText("阿根廷");
							HelpFrame.this.jLabel.setIcon(new ImageIcon(getClass().getResource("/flags/1.png")));
							HelpFrame.this.jTextPane.setText(" ");
						} else if (node.getUserObject() == "巴西") {
							HelpFrame.this.jLabel.setText("巴西");
							HelpFrame.this.jLabel.setIcon(new ImageIcon(getClass().getResource("/flags/2.png")));
							HelpFrame.this.jTextPane.setText(" ");
						}
					}
				}
			});
		}
		return this.jTree;
	}

	private JSplitPane getJSplitPane1() {
		if (this.jSplitPane1 == null) {
			this.jLabel = new JLabel();
			this.jLabel.setText("");
			this.jLabel.setVerticalTextPosition(0);
			this.jLabel.setHorizontalAlignment(0);
			this.jLabel.setFont(new Font("楷体_GB2312", 1, 24));
			this.jLabel.setPreferredSize(new Dimension(48, 50));
			this.jLabel.setVerticalAlignment(0);
			this.jSplitPane1 = new JSplitPane();
			this.jSplitPane1.setOrientation(0);
			this.jSplitPane1.setTopComponent(this.jLabel);
			this.jSplitPane1.setBottomComponent(getJTextPane());
			this.jSplitPane1.setDividerSize(1);
		}
		return this.jSplitPane1;
	}

	private JTextPane getJTextPane() {
		if (this.jTextPane == null) {
			this.jTextPane = new JTextPane();
			this.jTextPane.setEditable(false);
		}
		return this.jTextPane;
	}
}
