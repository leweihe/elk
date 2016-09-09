package com.newclear.game.frame;

import java.awt.BorderLayout;
import java.awt.Font;
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

public class Help extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JSplitPane jSplitPane = null;
	private JTree jTree = null;
	private JSplitPane jSplitPane1 = null;
	private JLabel jLabel = null;
	private JTextPane jTextPane = null;

	public Help() {
		initialize();
	}

	private void initialize() {
		setSize(500, 500);
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
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("说明");
			final DefaultMutableTreeNode a = new DefaultMutableTreeNode("版本宣告");
			final DefaultMutableTreeNode b = new DefaultMutableTreeNode("系统需求");
			final DefaultMutableTreeNode c = new DefaultMutableTreeNode("游戏规则玩法说明");
			final DefaultMutableTreeNode d = new DefaultMutableTreeNode("版本更新");
			DefaultMutableTreeNode e = new DefaultMutableTreeNode("作者说明");
			root.add(a);
			root.add(b);
			root.add(c);
			root.add(d);
			root.add(e);
			DefaultTreeModel tmodel = new DefaultTreeModel(root);
			this.jTree.setModel(tmodel);
			this.jTree.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) Help.this.jTree
							.getLastSelectedPathComponent();
					if (node.equals(a)) {
						Help.this.jLabel.setText("版本宣告");
						Help.this.jTextPane.setText(
								"1.本游戏为免费软件。\n\n2.非经过作者(以下简称制作群)同意，不得将此游戏应用于商业用途。\n\n3.因为制作群的美术和音乐天份不佳，故作品中使用到的图案、配乐和音效某些是取自于网路资源，这些版权为原作者所有。如有不慎侵权，请来函告知，制作群会立即改善。\n\n4.如执行此游戏，而造成玩家系统当机或损坏，制作群不负任何责任。");
					} else if (node.equals(b)) {
						Help.this.jLabel.setText("系统需求");
						Help.this.jTextPane
								.setText("操作系统：Windows9X，WindowNT，WindowsMe，Window2000，WindowXP，Windows2003。");
					} else if (node.equals(c)) {
						Help.this.jLabel.setText("游戏规则玩法说明");
						Help.this.jTextPane.setText(
								"1、玩法说明：\n\n这是针对小朋友制作的神奇宝贝图案版游戏，最主要是训练眼明手快及增强逻辑判断能力。\n\n游戏规则是需选择一对相同的牌连线，但此连线是在避开其他牌子后，呈现的路径以不超过二转弯为主，如符合规定则消除此一对牌而得分。每一局里玩家需要在规定的时间内消除所有的牌子，当完成任务后，方能进行下一关，当出现残局时，游戏会自动重新洗牌，游戏结束后会以玩家的得分多寡来决定是否登录排行榜。\n\n2、生命点数说明：\n\n生命点数，等同于洗牌次数。当出现无解的局面时，游戏会自动扣掉一点生命点数，而重新洗牌。游戏一开始会给玩家一些生命值，每过一关会增加一点。\n玩家使用“重新洗牌”功能，则会扣除1点生命点数。如果生命点数等于0，而且局面出现无解，则游戏结束。\n\n3、提示说明：\n\n使用“提示”功能，游戏会自动显示一组可以消除的牌组。游戏一开始会结玩家一些提示值，每过一关会增加一点。\n\n4、分数说明：\n\n分数上的设计，每过一关，则会依照比例加重给分。\n时间、生命值、提示点数都依照关数，以某种比例给分。\n另外过关也会额外加分（例如第一关+200，第二关+400，第七关+2800）\n\n5、菜单说明：\n\n【提示】：自动显示一组可以消除的牌组，有次数限制。\n【重新洗牌】：扣除一点生命点数，并重新换牌，有次数限制。重新洗牌功能，也可以用來自杀... \n【暂停／取消暂停】：按回车键可暂停时间、隐藏牌面（最小化状态）；再用鼠标点击屏幕底部任务栏中连连看项，可展开牌面并继续游戏。\n【音乐开关】：音乐开关，预设是开。\n【音效开关】：音效开关，预设是开。\n【查看榜单】：观看本机上的排行榜，里面有选项可以消除本机上的所有排行榜。\n【关于】：作者\n\n6、关卡說明（每种难度均有11关）：\n\n第０关 不变化\n第１关 向下\n第２关 向左\n第３关 上下分离\n第４关 左右分离\n第５关 上下集中\n第６关 左右集中\n第７关 上左下右\n第８关 左下右上\n第９关 向外扩散\n第１０关 向内集中");

					} else if (node.equals(d)) {
						Help.this.jLabel.setText("版本更新");
						Help.this.jTextPane.setText("2.0版正在研制中……");
					} else {
						Help.this.jLabel.setText("作者说明");
						Help.this.jTextPane.setText("作者:何乐为\n飞Q:192.168.2.166");
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
			this.jLabel.setPreferredSize(new java.awt.Dimension(48, 50));
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
