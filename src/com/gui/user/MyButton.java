package com.gui.user;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class MyButton extends JButton {
	
	public MyButton(String text) {
		// TODO Auto-generated constructor stub
		super(text);
		setContentAreaFilled(false);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(255, 128, 128));
		//填充圆角矩形区域 也可以为其它的图形
		g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,
				20, 20);
		//必须放在最后 否则画不出来
		super.paintComponent(g);
	}
	
	public void paintBorder(Graphics g) {
		//画边界区域
		g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1,
				20, 20);
	}

}
