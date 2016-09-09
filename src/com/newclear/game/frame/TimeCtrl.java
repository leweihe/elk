package com.newclear.game.frame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

public class TimeCtrl extends Thread {
	private Thread thread;
	private static JProgressBar timeProgressBar;

	private int i;
	public boolean b;

	public TimeCtrl() {
		getTimeProgressBar();
	}

	public static JProgressBar getTimeProgressBar() {
		if (timeProgressBar == null) {
			timeProgressBar = new JProgressBar();
			timeProgressBar.setMaximum(120);
			timeProgressBar.setValue(120);
			timeProgressBar.setStringPainted(true);
			timeProgressBar.setString("时间120秒！");
			timeProgressBar.setMaximumSize(new Dimension(120, 14));
			timeProgressBar.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.red));
			timeProgressBar.setName("jProgressBarTime");
			timeProgressBar.setPreferredSize(new Dimension(360, 18));
			return timeProgressBar;
		} else {
			return timeProgressBar;
		}
	}

	public void startButton() {
		thread = new Thread(new Runnable() {

			public void run() {

				for (int i = TimeCtrl.timeProgressBar.getValue(); i >= TimeCtrl.timeProgressBar.getMinimum(); i--) {
					try {
						Thread.sleep(1000L);
						TimeCtrl.timeProgressBar.setValue(i);
						double p = TimeCtrl.timeProgressBar.getPercentComplete();
						String s = "剩余" + Math.round(p * 120.0D) + "秒";
						TimeCtrl.timeProgressBar.setStringPainted(true);
						TimeCtrl.timeProgressBar.setString(s);
						if (i == 0) {
							TimeCtrl.this.b = true;
							TimeCtrl.timeProgressBar.setName("over");
							TimeCtrl.timeProgressBar.setString("游戏结束");
						}

					} catch (Exception localException) {
					}
				}
			}
		});
		thread.start();
	}

	public void stopButton() {
		if (thread != null) {
			while (thread.isAlive()) {
				thread.interrupt();
			}
		}
	}

	public void stopThread() {
		if (thread != null) {
			thread = null;
		}
	}

	public Thread getThread() {
		return thread;
	}

	public int getI() {
		return this.i;
	}

	public void setI(int i) {
		this.i = i;
	}
}