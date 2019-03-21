package com.gui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Menu extends JPanel {
	
	public final static int OPEN = 0;		//打开图片
	public final static int SIZE = 1;		//调整图片尺寸
	public final static int CUT = 2;		//裁剪
	public final static int ROTATE = 3;		//旋转
	public final static int LIGHT = 4;		//亮度
	public final static int CONTRAST = 5;	//对比度
	public final static int SATURATION = 6;	//饱和度
	public final static int DEHAZING = 7;	//去雾
	
	private static MainButton btnOpen;
	private static MainButton btnSize;
	private static MainButton btnCut;
	private static MainButton btnRotate;
	private static MainButton btnLight;
	private static MainButton btnContrast;
	private static MainButton btnSaturation;
	private static MainButton btnDehazing;
	private LinkedList<MainButton> btns = new LinkedList<>();
	
	private JLabel borderLeft;
	private JLabel borderBottom;
	private JLabel icon;
	private JLabel line;

	
	public Menu() {
		// TODO Auto-generated constructor stub
		
		btnOpen = new MainButton(OPEN, true);
		btnOpen.setBounds(5, 0, 200, 70);
		btnSize = new MainButton(SIZE, false);
		btnSize.setBounds(5, 70, 200, 70);
		btnCut = new MainButton(CUT, false);
		btnCut.setBounds(5, 140, 200, 70);
		btnRotate = new MainButton(ROTATE, false);
		btnRotate.setBounds(5, 210, 200, 70);
		btnLight = new MainButton(LIGHT, false);
		btnLight.setBounds(5, 280, 200, 70);
		btnContrast = new MainButton(CONTRAST, false);
		btnContrast.setBounds(5, 350, 200, 70);
		btnSaturation = new MainButton(SATURATION, false);
		btnSaturation.setBounds(5, 420, 200, 70);
		btnDehazing = new MainButton(DEHAZING, false);
		btnDehazing.setBounds(5, 490, 200, 70);
		
		btns.add(btnOpen);
		btns.add(btnSize);
		btns.add(btnCut);
		btns.add(btnRotate);
		btns.add(btnLight);
		btns.add(btnContrast);
		btns.add(btnSaturation);
		btns.add(btnDehazing);
		
		icon = new JLabel(new ImageIcon("./icons/fog.png"));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setBounds(5, 600, 200, 200);
		icon.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				icon.setIcon(new ImageIcon("./icons/fog_big.png"));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				icon.setIcon(new ImageIcon("./icons/fog.png"));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				icon.setIcon(new ImageIcon("./icons/fog.png"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				icon.setIcon(new ImageIcon("./icons/fog_big.png"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		line = new JLabel();
		line.setOpaque(true);
		line.setBackground(new Color(255, 128, 128));
		line.setBounds(205, 0, 1, 800);
		
		borderLeft = new JLabel();
		borderLeft.setOpaque(true);
		borderLeft.setBackground(new Color(255, 128, 128));
		borderLeft.setBounds(0, 0, 5, 800);
		
		borderBottom = new JLabel();
		borderBottom.setOpaque(true);
		borderBottom.setBackground(new Color(255, 128, 128));
		borderBottom.setBounds(0, 795, 206, 5);
		
		this.setSize(new Dimension(206, 805));
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.add(btnOpen);
		this.add(btnSize);
		this.add(btnCut);
		this.add(btnRotate);
		this.add(btnLight);
		this.add(btnContrast);
		this.add(btnSaturation);
		this.add(btnDehazing);
		this.add(icon);
		this.add(line);
		this.add(borderLeft);
		this.add(borderBottom);
		
	}


	public LinkedList<MainButton> getBtns() {
		return btns;
	}


	public JLabel getIcon() {
		return icon;
	}


	public JLabel getLine() {
		return line;
	}


	public JLabel getBorderLeft() {
		return borderLeft;
	}


	public JLabel getBorderBottom() {
		return borderBottom;
	}



}
