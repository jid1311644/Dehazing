package com.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleBar extends JPanel implements MouseListener {
	
	private JLabel jlIcon;
	private JLabel jlText;
	
	private JLabel jlMinFrame;
	private JLabel jlMaxFrame;
	private JLabel jlCloseFrame;
	
	public TitleBar(int width) {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		this.setPreferredSize(new Dimension(width, 40));
		this.setBackground(new Color(255, 128, 128));
		
		int x0 = (width - 240) / 2;
		jlIcon = new JLabel(new ImageIcon("./icons/title_icon.png"));
		jlIcon.setHorizontalAlignment(JLabel.CENTER);
		jlIcon.setBounds(x0, 0, 40, 40);
		jlText = new JLabel(new ImageIcon("./icons/title_text.png"));
		jlText.setHorizontalAlignment(JLabel.CENTER);
		jlText.setBounds(x0 + 40, 0, 200, 40);
		
		int x1 = width - 140;
		jlMinFrame = new JLabel(new ImageIcon("./icons/title_min.png"));
		jlMinFrame.setHorizontalAlignment(JLabel.CENTER);
		jlMinFrame.setOpaque(true);
		jlMinFrame.setBackground(new Color(255, 128, 128));
		jlMinFrame.setBounds(x1, 2, 40, 32);
		jlMinFrame.addMouseListener(this);
		
		jlMaxFrame = new JLabel(new ImageIcon("./icons/title_max.png"));
		jlMaxFrame.setHorizontalAlignment(JLabel.CENTER);
		jlMaxFrame.setOpaque(true);
		jlMaxFrame.setBackground(new Color(255, 128, 128));
		jlMaxFrame.setBounds(x1 + 40, 2, 40, 32);
		jlMaxFrame.addMouseListener(this);
		
		jlCloseFrame = new JLabel(new ImageIcon("./icons/title_close.png"));
		jlCloseFrame.setHorizontalAlignment(JLabel.CENTER);
		jlCloseFrame.setOpaque(true);
		jlCloseFrame.setBackground(new Color(255, 128, 128));
		jlCloseFrame.setBounds(x1 + 80, 2, 50, 32);
		jlCloseFrame.addMouseListener(this);
		
		this.add(jlIcon);
		this.add(jlText);
		this.add(jlMinFrame);
		this.add(jlMaxFrame);
		this.add(jlCloseFrame);
		
	}

	public JLabel getJlIcon() {
		return jlIcon;
	}

	public JLabel getJlText() {
		return jlText;
	}

	public JLabel getJlMinFrame() {
		return jlMinFrame;
	}

	public JLabel getJlMaxFrame() {
		return jlMaxFrame;
	}

	public JLabel getJlCloseFrame() {
		return jlCloseFrame;
	}
	
	public void setComponentLocation(int width) {
		int x0 = (width - 240) / 2;
		jlIcon.setBounds(x0, 0, 40, 40);
		jlText.setBounds(x0 + 40, 0, 200, 40);
		
		int x1 = width - 140;
		jlMinFrame.setBounds(x1, 2, 40, 32);
		jlMaxFrame.setBounds(x1 + 40, 2, 40, 32);
		jlCloseFrame.setBounds(x1 + 80, 2, 50, 32);
		
		this.setBounds(0, 0, width, 40);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jlMinFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(249, 108, 108));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(255, 128, 128));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jlMaxFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(255, 128, 128));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(249, 108, 108));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jlCloseFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(255, 128, 128));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(255, 128, 128));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(249, 108, 108));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jlMinFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min_choose.png"));
			jlMinFrame.setBackground(Color.WHITE);
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(255, 128, 128));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jlMaxFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(255, 128, 128));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max_choose.png"));
			jlMaxFrame.setBackground(Color.WHITE);
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jlCloseFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(255, 128, 128));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(255, 128, 128));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close_choose.png"));
			jlCloseFrame.setBackground(Color.WHITE);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jlMinFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min_choose.png"));
			jlMinFrame.setBackground(Color.WHITE);
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(255, 128, 128));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jlMaxFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(255, 128, 128));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max_choose.png"));
			jlMaxFrame.setBackground(Color.WHITE);
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jlCloseFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(255, 128, 128));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(255, 128, 128));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close_choose.png"));
			jlCloseFrame.setBackground(Color.WHITE);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jlMinFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(255, 128, 128));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(255, 128, 128));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jlMaxFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(255, 128, 128));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(255, 128, 128));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jlCloseFrame)) {
			jlMinFrame.setIcon(new ImageIcon("./icons/title_min.png"));
			jlMinFrame.setBackground(new Color(255, 128, 128));
			jlMaxFrame.setIcon(new ImageIcon("./icons/title_max.png"));
			jlMaxFrame.setBackground(new Color(255, 128, 128));
			jlCloseFrame.setIcon(new ImageIcon("./icons/title_close.png"));
			jlCloseFrame.setBackground(new Color(255, 128, 128));
		}
	}

}
