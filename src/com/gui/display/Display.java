package com.gui.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Display extends JPanel {
	

	private JLabel borderBottom;
	private JLabel borderRight;
	
	private OptionBar optionBar;
	private JLabel displayImage;
	
	public Display() {
		// TODO Auto-generated constructor stub
		borderRight = new JLabel();
		borderRight.setOpaque(true);
		borderRight.setBackground(new Color(255, 128, 128));
		
		borderBottom = new JLabel();
		borderBottom.setOpaque(true);
		borderBottom.setBackground(new Color(255, 128, 128));
		
		optionBar = new OptionBar(this.getWidth(), 40);
		
		displayImage = new JLabel();
		displayImage.setHorizontalAlignment(JLabel.CENTER);
		
		this.setLayout(null);
		this.setBackground(new Color(255,255,255));
		this.add(optionBar);
		//this.add(displayImage);
		this.add(borderBottom);
		this.add(borderRight);
	}
	

	public OptionBar getOptionBar() {
		return optionBar;
	}
	
	public JLabel getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(ImageIcon image) {
		
		this.displayImage.setIcon(image);
	}

	public JLabel getBorderBottom() {
		return borderBottom;
	}

	public JLabel getBorderRight() {
		return borderRight;
	}


}
