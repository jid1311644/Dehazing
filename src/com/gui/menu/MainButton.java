package com.gui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainButton extends JPanel {
	
	public final static int OPEN = 0;		//打开图片
	public final static int SIZE = 1;		//调整图片尺寸
	public final static int CUT = 2;		//裁剪
	public final static int ROTATE = 3;		//旋转
	public final static int LIGHT = 4;		//亮度
	public final static int CONTRAST = 5;	//对比度
	public final static int SATURATION = 6;	//饱和度
	public final static int DEHAZING = 7;	//去雾
	
	//对功能button的图片进行封装 0为普通状态 1为悬浮状态 2为不可用状态
	private ImageIcon open0 = new ImageIcon("./icons/open0.png");
	private ImageIcon open1 = new ImageIcon("./icons/open1.png");
	private ImageIcon open2 = new ImageIcon("./icons/open2.png");
	private LinkedList<ImageIcon> open = new LinkedList<>();
	
	private ImageIcon size0 = new ImageIcon("./icons/size0.png");
	private ImageIcon size1 = new ImageIcon("./icons/size1.png");
	private ImageIcon size2 = new ImageIcon("./icons/size2.png");
	private LinkedList<ImageIcon> size = new LinkedList<>();

	private ImageIcon cut0 = new ImageIcon("./icons/cut0.png");
	private ImageIcon cut1 = new ImageIcon("./icons/cut1.png");
	private ImageIcon cut2 = new ImageIcon("./icons/cut2.png");
	private LinkedList<ImageIcon> cut = new LinkedList<>();

	private ImageIcon rotate0 = new ImageIcon("./icons/rotate0.png");
	private ImageIcon rotate1 = new ImageIcon("./icons/rotate1.png");
	private ImageIcon rotate2 = new ImageIcon("./icons/rotate2.png");
	private LinkedList<ImageIcon> rotate = new LinkedList<>();

	private ImageIcon light0 = new ImageIcon("./icons/light0.png");
	private ImageIcon light1 = new ImageIcon("./icons/light1.png");
	private ImageIcon light2 = new ImageIcon("./icons/light2.png");
	private LinkedList<ImageIcon> light = new LinkedList<>();

	private ImageIcon contrast0 = new ImageIcon("./icons/contrast0.png");
	private ImageIcon contrast1 = new ImageIcon("./icons/contrast1.png");
	private ImageIcon contrast2 = new ImageIcon("./icons/contrast2.png");
	private LinkedList<ImageIcon> contrast = new LinkedList<>();

	private ImageIcon saturation0 = new ImageIcon("./icons/saturation0.png");
	private ImageIcon saturation1 = new ImageIcon("./icons/saturation1.png");
	private ImageIcon saturation2 = new ImageIcon("./icons/saturation2.png");
	private LinkedList<ImageIcon> saturation = new LinkedList<>();

	private ImageIcon dehazing0 = new ImageIcon("./icons/dehazing0.png");
	private ImageIcon dehazing1 = new ImageIcon("./icons/dehazing1.png");
	private ImageIcon dehazing2 = new ImageIcon("./icons/dehazing2.png");
	private LinkedList<ImageIcon> dehazing = new LinkedList<>();

	private ArrayList<LinkedList<ImageIcon>> btnIcons;
	private ArrayList<String> btnTexts;
	
	private JLabel icon;
	private JLabel text;
	
	private int buttonID;
	private boolean canChoose;
	
	public MainButton(int buttonID, boolean canChoose) {
		// TODO Auto-generated constructor stub
		this.buttonID = buttonID;
		this.canChoose = canChoose;
		
		//将每个功能button的图片ImageIcon封装
		open.add(open0);
		open.add(open1);
		open.add(open2);
		
		size.add(size0);
		size.add(size1);
		size.add(size2);
		
		cut.add(cut0);
		cut.add(cut1);
		cut.add(cut2);
		
		rotate.add(rotate0);
		rotate.add(rotate1);
		rotate.add(rotate2);
		
		light.add(light0);
		light.add(light1);
		light.add(light2);
		
		contrast.add(contrast0);
		contrast.add(contrast1);
		contrast.add(contrast2);
		
		saturation.add(saturation0);
		saturation.add(saturation1);
		saturation.add(saturation2);
		
		dehazing.add(dehazing0);
		dehazing.add(dehazing1);
		dehazing.add(dehazing2);
		
		//将所有button的图片和图片下方的文字封装
		btnIcons = new ArrayList<>();
		btnIcons.add(open);
		btnIcons.add(size);
		btnIcons.add(cut);
		btnIcons.add(rotate);
		btnIcons.add(light);
		btnIcons.add(contrast);
		btnIcons.add(saturation);
		btnIcons.add(dehazing);
		
		btnTexts = new ArrayList<>();
		btnTexts.add("打开图片");
		btnTexts.add("尺  寸");
		btnTexts.add("裁  剪");
		btnTexts.add("旋  转");
		btnTexts.add("亮  度");
		btnTexts.add("对比度");
		btnTexts.add("饱和度");
		btnTexts.add("去  雾");
		
		icon = new JLabel();
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(0, 0, 100, 69);
		text = new JLabel(btnTexts.get(buttonID));
		//text.setHorizontalAlignment(JLabel.CENTER);
		text.setFont(new Font("宋体", 0, 16));
		text.setBounds(100, 0, 100, 69);
		
		setCanChoose(canChoose);
		
		JLabel line = new JLabel();
		line.setOpaque(true);
		line.setBackground(new Color(255, 128, 128));
		line.setBounds(15, 69, 170, 1);
		
		this.setLayout(null);
		this.setSize(new Dimension(200, 70));
		this.setBackground(Color.WHITE);
		this.add(icon);
		this.add(text);
		this.add(line);
		
	}
	
	public void setCanChoose(boolean choose) {
		// TODO Auto-generated method stub
		this.canChoose = choose;
		if(canChoose){
			icon.setIcon(btnIcons.get(buttonID).get(0));
			text.setForeground(Color.DARK_GRAY);
			this.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					if(canChoose)
						icon.setIcon(btnIcons.get(buttonID).get(1));
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					if(canChoose)
						icon.setIcon(btnIcons.get(buttonID).get(0));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					if(canChoose)
						icon.setIcon(btnIcons.get(buttonID).get(0));
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					if(canChoose)
						icon.setIcon(btnIcons.get(buttonID).get(1));
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		else{
			icon.setIcon(btnIcons.get(buttonID).get(2));
			text.setForeground(Color.GRAY);
		}
	}
	
	public boolean getCanChoose() {
		return canChoose;
	}

}
