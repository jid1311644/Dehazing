package com.gui.display;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.opencv.core.Mat;

import com.gui.MainGUI;
import com.gui.menu.MainButton;

public class OptionBar extends JPanel implements KeyListener, ChangeListener {
	
	private JLabel jlSizeWidth;
	private JTextField jtfSizeWidth;
	private JLabel jlSizeHeight;
	private JTextField jtfSizeHeight;
	private JCheckBox jcbSizeWH;
	private JButton jbSizeOK;
	private LinkedList<Component> size;
	private boolean isLock = true;
	
	private JLabel jlCutPoint;
	private JTextField jtfCutPoint;
	private JLabel jlCutDimension;
	private JTextField jtfCutDimension;
	private JLabel jlCutExplain;
	private JButton jbCutOK;
	private LinkedList<Component> cut;
	
	private JButton jbClockWise;
	private JButton jbMirror;
	private LinkedList<Component> rotate;
	
	private JLabel jlLight;
	private JSlider jsLight;
	private JLabel jlLightNum;
	private JButton jbLightOK;
	private LinkedList<Component> light;
	private int lightNum = 0;
	
	private JLabel jlContrast;
	private JSlider jsContrast;
	private JLabel jlContrastNum;
	private JButton jbContrastOK;
	private LinkedList<Component> contrast;
	private int contrastNum = 0;
	
	private JLabel jlSaturation;
	private JSlider jsSaturation;
	private JLabel jlSaturationNum;
	private JButton jbSaturationOK;
	private LinkedList<Component> saturation;
	private int saturationNum = 0;
	
	private JButton jbUndo;
	private JButton jbRedo;
	private JButton jbSave;
	private JButton jbCancel;
	
	private ArrayList<LinkedList<Component>> options;
	
	private Mat currentImage;
	
	public OptionBar(int width, int height) {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		this.setSize(width, height);
		this.setBackground(new Color(250, 250, 250));
		
		initSize();
		initCut();
		initRotate();
		initLight();
		initContrast();
		initSaturation();
		options = new ArrayList<>();
		options.add(size);
		options.add(cut);
		options.add(rotate);
		options.add(light);
		options.add(contrast);
		options.add(saturation);

		initComponentsVisible();
		
		jbUndo = new JButton();
		jbUndo.setHorizontalAlignment(JButton.CENTER);
		jbUndo.setIcon(new ImageIcon("./icons/undo.png"));
		jbUndo.setText("撤销");
		jbUndo.setFont(new Font("宋体", 0, 14));
		jbUndo.setForeground(new Color(255, 128, 128));
		jbUndo.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbUndo.setBackground(new Color(250, 250, 250));
		jbUndo.setFocusPainted(false);
		jbUndo.setBounds(width - 330, 7, 60, 26);
		
		jbRedo = new JButton();
		jbRedo.setHorizontalAlignment(JButton.CENTER);
		jbRedo.setIcon(new ImageIcon("./icons/redo.png"));
		jbRedo.setText("原图");
		jbRedo.setFont(new Font("宋体", 0, 14));
		jbRedo.setForeground(new Color(255, 128, 128));
		jbRedo.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbRedo.setBackground(new Color(250, 250, 250));
		jbRedo.setFocusPainted(false);
		jbRedo.setBounds(width - 250, 7, 60, 26);
		
		jbSave = new JButton();
		jbSave.setHorizontalAlignment(JButton.CENTER);
		jbSave.setIcon(new ImageIcon("./icons/save.png"));
		jbSave.setText("保存");
		jbSave.setFont(new Font("宋体", 0, 14));
		jbSave.setForeground(new Color(255, 128, 128));
		jbSave.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbSave.setBackground(new Color(250, 250, 250));
		jbSave.setFocusPainted(false);
		jbSave.setBounds(width - 170, 7, 60, 26);
		
		jbCancel = new JButton();
		jbCancel.setHorizontalAlignment(JButton.CENTER);
		jbCancel.setIcon(new ImageIcon("./icons/cancel.png"));
		jbCancel.setText("取消");
		jbCancel.setFont(new Font("宋体", 0, 14));
		jbCancel.setForeground(new Color(255, 128, 128));
		jbCancel.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbCancel.setBackground(new Color(250, 250, 250));
		jbCancel.setFocusPainted(false);
		jbCancel.setBounds(width - 90, 7, 60, 26);

		this.add(jbUndo);
		this.add(jbRedo);
		this.add(jbSave);
		this.add(jbCancel);
	}

	public void setOption(int optionID) {
		this.currentImage = MainGUI.images.peekLast();
		System.out.println(MainGUI.images.size());
		System.out.println(currentImage.width() + " " + currentImage.height());
		switch (optionID) {
		case MainButton.SIZE:
			setComponentsVisible(size, true);
			for(int i = 0; i < options.size(); i++) {
				if(i + 1 != MainButton.SIZE) {
					setComponentsVisible(options.get(i), false);
				}
			}
			jtfSizeWidth.setText(currentImage.width() + "");
			jtfSizeHeight.setText(currentImage.height() + "");
			break;
		case MainButton.CUT:
			setComponentsVisible(cut, true);
			for(int i = 0; i < options.size(); i++) {
				if(i + 1 != MainButton.CUT) {
					setComponentsVisible(options.get(i), false);
				}
			}
			break;
		case MainButton.ROTATE:
			setComponentsVisible(rotate, true);
			for(int i = 0; i < options.size(); i++) {
				if(i + 1 != MainButton.ROTATE) {
					setComponentsVisible(options.get(i), false);
				}
			}
			break;
		case MainButton.LIGHT:
			setComponentsVisible(light, true);
			for(int i = 0; i < options.size(); i++) {
				if(i + 1 != MainButton.LIGHT) {
					setComponentsVisible(options.get(i), false);
				}
			}
			break;
		case MainButton.CONTRAST:
			setComponentsVisible(contrast, true);
			for(int i = 0; i < options.size(); i++) {
				if(i + 1 != MainButton.CONTRAST) {
					setComponentsVisible(options.get(i), false);
				}
			}
			break;
		case MainButton.SATURATION:
			setComponentsVisible(saturation, true);
			for(int i = 0; i < options.size(); i++) {
				if(i + 1 != MainButton.SATURATION) {
					setComponentsVisible(options.get(i), false);
				}
			}
			break;

		default:
			break;
		}
	}
	
	private void setComponentsVisible(LinkedList<Component> components, boolean isVisible) {
		for(int i = 0; i < components.size(); i++) {
			components.get(i).setVisible(isVisible);
		}
	}
	
	private void initSize() {
		
		jlSizeWidth = new JLabel("宽", JLabel.CENTER);
		jlSizeWidth.setFont(new Font("宋体", Font.BOLD, 18));
		jlSizeWidth.setForeground(new Color(255, 128, 128));
		jlSizeWidth.setBounds(10, 0, 20, 40);
		
		jtfSizeWidth = new JTextField("");
		jtfSizeWidth.setFont(new Font("", 0, 15));
		jtfSizeWidth.setForeground(Color.DARK_GRAY);
		jtfSizeWidth.setBackground(Color.WHITE);
		jtfSizeWidth.setHorizontalAlignment(JTextField.CENTER);
		jtfSizeWidth.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1, true));
		jtfSizeWidth.setBounds(34, 8, 50, 24);
		jtfSizeWidth.addKeyListener(this);
		
		jlSizeHeight = new JLabel("高", JLabel.CENTER);
		jlSizeHeight.setFont(new Font("宋体", Font.BOLD, 18));
		jlSizeHeight.setForeground(new Color(255, 128, 128));
		jlSizeHeight.setBounds(96, 0, 20, 40);
		
		jtfSizeHeight = new JTextField("");
		jtfSizeHeight.setFont(new Font("", 0, 15));
		jtfSizeHeight.setForeground(Color.DARK_GRAY);
		jtfSizeHeight.setBackground(Color.WHITE);
		jtfSizeHeight.setHorizontalAlignment(JTextField.CENTER);
		jtfSizeHeight.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1, true));
		jtfSizeHeight.setBounds(120, 8, 50, 24);
		jtfSizeHeight.addKeyListener(this);
		
		jcbSizeWH = new JCheckBox("锁定宽高比例");
		jcbSizeWH.setFont(new Font("宋体", 0, 14));
		jcbSizeWH.setForeground(new Color(255, 128, 128));
		jcbSizeWH.setBackground(new Color(250, 250, 250));
		jcbSizeWH.setSelected(true);
		jcbSizeWH.setBounds(176, 16, 110, 14);
		jcbSizeWH.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(jcbSizeWH.isSelected()) {
					isLock = true;
				}
				else {
					isLock = false;
				}
			}
		});
		
		jbSizeOK = new JButton(new ImageIcon("./icons/ok.png"));
		jbSizeOK.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbSizeOK.setBackground(new Color(250, 250, 250));
		jbSizeOK.setFocusPainted(false);
		jbSizeOK.setBounds(300, 8, 24, 24);
		
		this.add(jlSizeWidth);
		this.add(jtfSizeWidth);
		this.add(jlSizeHeight);
		this.add(jtfSizeHeight);
		this.add(jcbSizeWH);
		this.add(jbSizeOK);
		
		size = new LinkedList<>();
		size.add(jlSizeWidth);
		size.add(jtfSizeWidth);
		size.add(jlSizeHeight);
		size.add(jtfSizeHeight);
		size.add(jcbSizeWH);
		size.add(jbSizeOK);
	}
	
	public JTextField getJtfSizeWidth() {
		return jtfSizeWidth;
	}

	public JTextField getJtfSizeHeight() {
		return jtfSizeHeight;
	}

	public JButton getJbSizeOK() {
		return jbSizeOK;
	}

	private void initCut() {
		
		jlCutPoint = new JLabel("位置", JLabel.CENTER);
		jlCutPoint.setFont(new Font("宋体", Font.BOLD, 18));
		jlCutPoint.setForeground(new Color(255, 128, 128));
		jlCutPoint.setBounds(10, 0, 40, 40);
		
		jtfCutPoint = new JTextField("");
		jtfCutPoint.setFont(new Font("", 0, 15));
		jtfCutPoint.setEditable(false);
		jtfCutPoint.setForeground(Color.DARK_GRAY);
		jtfCutPoint.setBackground(Color.WHITE);
		jtfCutPoint.setHorizontalAlignment(JTextField.CENTER);
		jtfCutPoint.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1, true));
		jtfCutPoint.setBounds(50, 8, 100, 24);
		
		jlCutDimension = new JLabel("大小", JLabel.CENTER);
		jlCutDimension.setFont(new Font("宋体", Font.BOLD, 18));
		jlCutDimension.setForeground(new Color(255, 128, 128));
		jlCutDimension.setBounds(160, 0, 40, 40);
		
		jtfCutDimension = new JTextField("");
		jtfCutDimension.setFont(new Font("", 0, 15));
		jtfCutDimension.setEditable(false);
		jtfCutDimension.setForeground(Color.DARK_GRAY);
		jtfCutDimension.setBackground(Color.WHITE);
		jtfCutDimension.setHorizontalAlignment(JTextField.CENTER);
		jtfCutDimension.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1, true));
		jtfCutDimension.setBounds(200, 8, 100, 24);
		
		jlCutExplain = new JLabel("*点击图片选择裁剪区域", JLabel.CENTER);
		jlCutExplain.setFont(new Font("宋体", 0, 12));
		jlCutExplain.setForeground(new Color(255, 128, 128));
		jlCutExplain.setBounds(310, 16, 130, 14);
		
		jbCutOK = new JButton(new ImageIcon("./icons/ok.png"));
		jbCutOK.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbCutOK.setBackground(new Color(250, 250, 250));
		jbCutOK.setFocusPainted(false);
		jbCutOK.setBounds(450, 8, 24, 24);
		
		this.add(jlCutPoint);
		this.add(jtfCutPoint);
		this.add(jlCutDimension);
		this.add(jtfCutDimension);
		this.add(jlCutExplain);
		this.add(jbCutOK);
		
		cut = new LinkedList<>();
		cut.add(jlCutPoint);
		cut.add(jtfCutPoint);
		cut.add(jlCutDimension);
		cut.add(jtfCutDimension);
		cut.add(jlCutExplain);
		cut.add(jbCutOK);
	}
	
	public JTextField getJtfCutPoint() {
		return jtfCutPoint;
	}

	public JTextField getJtfCutDimension() {
		return jtfCutDimension;
	}

	public JButton getJbCutOK() {
		return jbCutOK;
	}

	private void initRotate() {
		jbClockWise = new JButton(new ImageIcon("./icons/clockwise.png"));
		jbClockWise.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbClockWise.setBackground(Color.WHITE);
		jbClockWise.setFocusPainted(false);
		jbClockWise.setBounds(50, 5, 30, 30);
		
		jbMirror = new JButton(new ImageIcon("./icons/mirror.png"));
		jbMirror.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbMirror.setBackground(Color.WHITE);
		jbMirror.setFocusPainted(false);
		jbMirror.setBounds(100, 5, 30, 30);
		
		this.add(jbClockWise);
		this.add(jbMirror);
		
		rotate = new LinkedList<>();
		rotate.add(jbClockWise);
		rotate.add(jbMirror);
		
	}
	
	public JButton getJbClockWise() {
		return jbClockWise;
	}

	public JButton getJbMirror() {
		return jbMirror;
	}

	private void initLight() {
		jlLight = new JLabel("亮 度", JLabel.CENTER);
		jlLight.setFont(new Font("宋体", Font.BOLD, 18));
		jlLight.setForeground(new Color(255, 128, 128));
		jlLight.setBounds(10, 0, 60, 40);
		
		jsLight = new JSlider(JSlider.HORIZONTAL);
		jsLight.setOpaque(true);
		jsLight.setBackground(new Color(250, 250, 250));
		jsLight.setMinimum(-100);
		jsLight.setMaximum(100);
		jsLight.setValue(0);
		jsLight.setExtent(0);
		jsLight.setBounds(70, 5, 200, 30);
		jsLight.addChangeListener(this);
		
		jlLightNum = new JLabel("0", JLabel.CENTER);
		jlLightNum.setFont(new Font("宋体", 0, 18));
		jlLightNum.setForeground(new Color(255, 128, 128));
		jlLightNum.setBounds(270, 5, 40, 30);
		
		jbLightOK = new JButton(new ImageIcon("./icons/ok.png"));
		jbLightOK.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbLightOK.setBackground(new Color(250, 250, 250));
		jbLightOK.setFocusPainted(false);
		jbLightOK.setBounds(320, 8, 24, 24);
		
		this.add(jlLight);
		this.add(jsLight);
		this.add(jlLightNum);
		this.add(jbLightOK);
		
		light = new LinkedList<>();
		light.add(jlLight);
		light.add(jsLight);
		light.add(jlLightNum);
		light.add(jbLightOK);
		
	}
	
	public int getLightNum() {
		return lightNum;
	}

	public JButton getJbLightOK() {
		return jbLightOK;
	}

	private void initContrast() {
		jlContrast = new JLabel("对比度", JLabel.CENTER);
		jlContrast.setFont(new Font("宋体", Font.BOLD, 18));
		jlContrast.setForeground(new Color(255, 128, 128));
		jlContrast.setBounds(10, 0, 60, 40);
		
		jsContrast = new JSlider(JSlider.HORIZONTAL);
		jsContrast.setOpaque(true);
		jsContrast.setBackground(new Color(250, 250, 250));
		jsContrast.setMinimum(-100);
		jsContrast.setMaximum(100);
		jsContrast.setValue(0);
		jsContrast.setExtent(0);
		jsContrast.setBounds(70, 5, 200, 30);
		jsContrast.addChangeListener(this);
		
		jlContrastNum = new JLabel("0", JLabel.CENTER);
		jlContrastNum.setFont(new Font("宋体", 0, 18));
		jlContrastNum.setForeground(new Color(255, 128, 128));
		jlContrastNum.setBounds(270, 5, 40, 30);
		
		jbContrastOK = new JButton(new ImageIcon("./icons/ok.png"));
		jbContrastOK.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbContrastOK.setBackground(new Color(250, 250, 250));
		jbContrastOK.setFocusPainted(false);
		jbContrastOK.setBounds(320, 8, 24, 24);
		
		this.add(jlContrast);
		this.add(jsContrast);
		this.add(jlContrastNum);
		this.add(jbContrastOK);
		
		contrast = new LinkedList<>();
		contrast.add(jlContrast);
		contrast.add(jsContrast);
		contrast.add(jlContrastNum);
		contrast.add(jbContrastOK);
		
	}
	
	public int getContrastNum() {
		return contrastNum;
	}

	public JButton getJbContrastOK() {
		return jbContrastOK;
	}

	private void initSaturation() {
		jlSaturation = new JLabel("饱和度", JLabel.CENTER);
		jlSaturation.setFont(new Font("宋体", Font.BOLD, 18));
		jlSaturation.setForeground(new Color(255, 128, 128));
		jlSaturation.setBounds(10, 0, 60, 40);
		
		jsSaturation = new JSlider(JSlider.HORIZONTAL);
		jsSaturation.setOpaque(true);
		jsSaturation.setBackground(new Color(250, 250, 250));
		jsSaturation.setMinimum(-100);
		jsSaturation.setMaximum(100);
		jsSaturation.setValue(0);
		jsSaturation.setExtent(0);
		jsSaturation.setBounds(70, 5, 200, 30);
		jsSaturation.addChangeListener(this);
		
		jlSaturationNum = new JLabel("0", JLabel.CENTER);
		jlSaturationNum.setFont(new Font("宋体", 0, 18));
		jlSaturationNum.setForeground(new Color(255, 128, 128));
		jlSaturationNum.setBounds(270, 5, 40, 30);
		
		jbSaturationOK = new JButton(new ImageIcon("./icons/ok.png"));
		jbSaturationOK.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbSaturationOK.setBackground(new Color(250, 250, 250));
		jbSaturationOK.setFocusPainted(false);
		jbSaturationOK.setBounds(320, 8, 24, 24);
		
		this.add(jlSaturation);
		this.add(jsSaturation);
		this.add(jlSaturationNum);
		this.add(jbSaturationOK);
		
		saturation = new LinkedList<>();
		saturation.add(jlSaturation);
		saturation.add(jsSaturation);
		saturation.add(jlSaturationNum);
		saturation.add(jbSaturationOK);
		
	}
	
	public int getSaturationNum() {
		return saturationNum;
	}

	public JButton getJbSaturationOK() {
		return jbSaturationOK;
	}

	public void initComponentsVisible() {
		for(int i = 0; i < options.size(); i++) {
			setComponentsVisible(options.get(i), false);
		}
	}
	
	public JButton getJbUndo() {
		return jbUndo;
	}

	public JButton getJbRedo() {
		return jbRedo;
	}

	public JButton getJbSave() {
		return jbSave;
	}

	public JButton getJbCancel() {
		return jbCancel;
	}

	public void changeWidth(int width) {
		this.setSize(width, 40);
		jbUndo.setBounds(width - 290, 7, 60, 26);
		jbRedo.setBounds(width - 220, 7, 60, 26);
		jbSave.setBounds(width - 150, 7, 60, 26);
		jbCancel.setBounds(width - 80, 7, 60, 26);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jtfSizeWidth) || e.getSource().equals(jtfSizeHeight)) {
			int keyChar = e.getKeyChar();
			if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
				
			}
			else {
				e.consume();
			}
			//输入长度不超过4位
			if(jtfSizeWidth.getText().length() > 3) {
				e.consume();
			}
			if(jtfSizeHeight.getText().length() > 3) {
				e.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		double p = currentImage.width() / (currentImage.height() + 0.0);
		if(e.getSource().equals(jtfSizeWidth)) {
			String t = jtfSizeWidth.getText();
			if(isLock) {
				int w = 0;
				if(!t.equals("")) {
					w = Integer.parseInt(t);
				}
				double h = w / p;
				jtfSizeHeight.setText((int) h + "");
			}
		}
		else if(e.getSource().equals(jtfSizeHeight)) {
			String t = jtfSizeHeight.getText();
			if(isLock) {
				t = jtfSizeHeight.getText();
				int h = 0;
				if(!t.equals("")) {
					h = Integer.parseInt(t);
				}
				double w = h * p;
				jtfSizeWidth.setText((int) w + "");
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jsLight)) {
			lightNum = jsLight.getValue();
			jlLightNum.setText(lightNum + "");
		}
		else if(e.getSource().equals(jsContrast)) {
			contrastNum = jsContrast.getValue();
			jlContrastNum.setText(contrastNum + "");
		}
		else if(e.getSource().equals(jsSaturation)) {
			saturationNum = jsSaturation.getValue();
			jlSaturationNum.setText(saturationNum + "");
		}
	}
	

}
