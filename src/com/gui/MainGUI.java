package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.spi.CurrencyNameProvider;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputListener;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.gui.dialog.MyDialog;
import com.gui.display.Display;
import com.gui.menu.MainButton;
import com.gui.menu.Menu;
import com.gui.user.SignInUp;
import com.main.BrightnessControl;
import com.main.ContrastControl;
import com.main.CutImage;
import com.main.DarkChannelPriorDehaze;
import com.main.ModifySize;
import com.main.OpenImage;
import com.main.RotateImage;
import com.main.SaturationControl;
import com.main.utils.BufImgToMat;
import com.main.utils.MatToBufImg;

public class MainGUI extends JFrame implements MouseListener, ActionListener {
	
	private JFrame frame;
	private int width;
	private int height;
	
	private MyDialog dialog;
	
	private TitleBar titleBar;
	private Menu menu;
	private Display display;
	private JScrollPane jspDisplay;

	private Dimension minDisplayImageSize;
	private Dimension maxDisplayImageSize;
	
	private Dimension currentSize;
	private Point currentLocation;
	private int cutClick = -1;
	
	private boolean isMax;
	
	private String path;
	public static LinkedList<Mat> images = new LinkedList<>();
	private String fileExtension = "";
	
	public static String userName;
	public static boolean isOpenImage = false;
	
	public MainGUI(int width, int height) {
		// TODO Auto-generated constructor stub
		frame = this;
		dialog = new MyDialog(frame);
		dialog.setModal(true);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int w = dimension.width;
		int h = dimension.height;
		int x = (w - width) / 2;
		int y = (h - height) / 2;
		setLocation(x, y);
		currentLocation = frame.getLocation();
		setSize(width, height);
		currentSize = frame.getSize();
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(w, h));
		setUndecorated(true);
		isMax = false;
		
		minDisplayImageSize = new Dimension(980, 800);
		maxDisplayImageSize = new Dimension(w - 250, h - 150);
		
		MouseEventListener listener = new MouseEventListener(this);
		
		titleBar = new TitleBar(width);
		titleBar.setBounds(0, 0, width, 40);
		titleBar.getJlMinFrame().addMouseListener(this);
		titleBar.getJlMaxFrame().addMouseListener(this);
		titleBar.getJlCloseFrame().addMouseListener(this);
		titleBar.addMouseListener(listener);
		titleBar.addMouseMotionListener(listener);
		
		menu = new Menu();
		menu.getIcon().setBounds(5, height - 40  - 200 - 5, 200, 200);
		menu.getIcon().addMouseListener(this);
		menu.getLine().setBounds(205, 0, 1, height - 40);
		menu.getBorderLeft().setBounds(0, 0, 5, height - 40);
		//menu.getBorderLeft().addMouseListener(listener);
		//menu.getBorderLeft().addMouseMotionListener(listener);
		menu.getBorderBottom().setBounds(0, height - 40 - 5, 206, 5);
		//menu.getBorderBottom().addMouseListener(listener);
		//menu.getBorderBottom().addMouseMotionListener(listener);
		menu.setBounds(0, 40, 206, height - 40);
		for(int i = 0; i < 8; i++) {
			menu.getBtns().get(i).addMouseListener(this);
		}
		
		display = new Display();
		display.setBounds(206, 40, width - 206, height - 40);
		display.getBorderBottom().setBounds(0, height - 40 - 5, width - 206, 5);
		//display.getBorderBottom().addMouseListener(listener);
		//display.getBorderBottom().addMouseMotionListener(listener);
		display.getBorderRight().setBounds(width - 206 - 5, 0, 5, height - 40);
		//display.getBorderRight().addMouseListener(listener);
		//display.getBorderRight().addMouseMotionListener(listener);
		display.getOptionBar().setBounds(0, 0, display.getWidth() - 5, 40);
		display.getOptionBar().changeWidth(display.getWidth() - 5);
		display.getOptionBar().initComponentsVisible();
//		display.getOptionBar().setOption(MainButton.SIZE);
		JLabel displayLine = new JLabel();
		displayLine.setOpaque(true);
		displayLine.setBackground(new Color(255, 128, 128));
		displayLine.setBounds(0, 40, display.getWidth(), 1);
		display.add(displayLine);

		jspDisplay = new JScrollPane(display.getDisplayImage());
		jspDisplay.setBorder(null);
		jspDisplay.setBounds(20, 60, display.getWidth() - 45, display.getHeight() - 80);
		jspDisplay.getViewport().setBackground(Color.WHITE);
		display.add(jspDisplay);

		display.getOptionBar().getJbSizeOK().addActionListener(this);
		display.getDisplayImage().addMouseListener(this);
		display.getOptionBar().getJbCutOK().addActionListener(this);
		display.getOptionBar().getJbClockWise().addActionListener(this);
		display.getOptionBar().getJbMirror().addActionListener(this);
		display.getOptionBar().getJbLightOK().addActionListener(this);
		display.getOptionBar().getJbContrastOK().addActionListener(this);
		display.getOptionBar().getJbSaturationOK().addActionListener(this);
		display.getOptionBar().getJbUndo().addActionListener(this);
		display.getOptionBar().getJbRedo().addActionListener(this);
		display.getOptionBar().getJbSave().addActionListener(this);
		display.getOptionBar().getJbCancel().addActionListener(this);
		
		JPanel jPanel = new JPanel(null);
		jPanel.add(titleBar);
		jPanel.add(menu);
		jPanel.add(display);
		
		this.setBackground(Color.WHITE);
		this.getContentPane().add(jPanel);
		this.setVisible(true);
		
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
				int width = frame.getWidth();
				int height = frame.getHeight();
				
				titleBar.setComponentLocation(width);
				
				menu.getIcon().setBounds(5, height - 40  - 200 - 5, 200, 200);
				menu.getLine().setBounds(205, 0, 1, height - 40);
				menu.getBorderLeft().setBounds(0, 0, 5, height - 40);
				menu.getBorderBottom().setBounds(0, height - 40 - 5, 206, 5);
				menu.setBounds(0, 40, 206, height - 40);
				
				display.getBorderBottom().setBounds(0, height - 40 -5, width - 206, 5);
				display.getBorderRight().setBounds(width - 206 - 5, 0, 5, height - 40);
				display.setBounds(206, 40, width - 206, height - 40);
				display.getOptionBar().changeWidth(display.getWidth() - 5);
				displayLine.setSize(display.getWidth(), 1);
				jspDisplay.setBounds(20, 60, display.getWidth() - 45, display.getHeight() - 80);
				jspDisplay.setViewportView(display.getDisplayImage());
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void setFrameSize(int width, int height) {
		this.setSize(width, height);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int w = dimension.width;
		int h = dimension.height;
		int x = (w - width) / 2;
		int y = (h - height) / 2;
		this.setLocation(x, y);
	}
	
	public Display getDisplay() {
		return display;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(titleBar.getJlMinFrame())) {
			frame.setExtendedState(JFrame.ICONIFIED);
		}
		else if(e.getSource().equals(titleBar.getJlMaxFrame())) {
			
			Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(
					this.getGraphicsConfiguration());
			int w = Toolkit.getDefaultToolkit().getScreenSize().width - insets.left - insets.right;
			int h = Toolkit.getDefaultToolkit().getScreenSize().height - insets.top - insets.bottom;
			
			if(frame.getWidth() == w && frame.getHeight() == h
					&& frame.getX() == insets.left && frame.getY() == insets.top) {
				frame.setSize(currentSize);
				frame.setLocation(currentLocation);
				isMax = false;
				
			}
			else {
				currentSize = frame.getSize();
				currentLocation = frame.getLocation();
				frame.setSize(new Dimension(w, h));
				frame.setLocation(new Point(insets.left, insets.top));
				isMax = true;
			
			}
		}
		else if(e.getSource().equals(titleBar.getJlCloseFrame())) {
			if(isOpenImage) {
				int n = JOptionPane.showConfirmDialog(dialog, "Something has been modified. Exit now?"
						, "", JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					System.exit(0);
				}
			}
			else {
				int n = JOptionPane.showConfirmDialog(dialog, "Exit now?"
						, "", JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					System.exit(0);
				}
			}
		}
		
		else if(e.getSource().equals(display.getDisplayImage())){
			if(cutClick != -1) {
				int labelWidth = display.getDisplayImage().getWidth();
				int labelHeight = display.getDisplayImage().getHeight();
				int imageWidth = display.getDisplayImage().getIcon().getIconWidth();
				int imageHeight = display.getDisplayImage().getIcon().getIconHeight();
					
				int x = 0, y = 0;
				if(labelWidth > imageWidth && labelHeight > imageHeight) {
					x = (labelWidth - imageWidth) / 2;
					y = (labelHeight - imageHeight) / 2;
				}
				else if(labelWidth <= imageWidth && labelHeight > imageHeight) {
					y = (labelHeight - imageHeight) / 2;
				}
				else if(labelWidth > imageWidth && labelHeight <= imageHeight) {
					x = (labelWidth - imageWidth) / 2;
				}
				else if(labelWidth <= imageWidth && labelHeight <= imageHeight) {
					
				}
				if(e.getX() >= x && e.getY() >= y 
						&& e.getX() <= x + imageWidth && e.getY() <= y + imageHeight) {
					int clickX = e.getX() - x;
					int clickY = e.getY() - y;
					if(cutClick == 0) {
						display.getOptionBar().getJtfCutPoint().setText(clickX + " , " + clickY);
						cutClick = 1;
					}
					else if(cutClick == 1) {
						String[] t = display.getOptionBar().getJtfCutPoint().getText().split(" , ");
						int imageX = Integer.parseInt(t[0]);
						int imageY = Integer.parseInt(t[1]);
						int imageW, imageH;
						if(clickX > imageX && clickY > imageY) {
							imageW = clickX - imageX;
							imageH = clickY - imageY;
							display.getOptionBar().getJtfCutDimension()
									.setText(imageW + " * " + imageH);
							cutClick = 0;
						}
						else if(clickX > imageX && clickY < imageY) {
							imageW = clickX - imageX;
							imageH = imageY - clickY;
							display.getOptionBar().getJtfCutDimension()
								.setText(imageW + " * " + imageH);
							display.getOptionBar().getJtfCutPoint()
								.setText(imageX + " , " + clickY);
							cutClick = 0;
						}
						else if(clickX < imageX && clickY > imageY) {
							imageW = imageX - clickX;
							imageH = clickY - imageY;
							display.getOptionBar().getJtfCutDimension()
								.setText(imageW + " * " + imageH);
							display.getOptionBar().getJtfCutPoint()
								.setText(clickX + " , " + imageY);
							cutClick = 0;
						}
						else if(clickX < imageX && clickY < imageY) {
							imageW = imageX - clickX;
							imageH = imageY - clickY;
							display.getOptionBar().getJtfCutDimension()
								.setText(imageW + " * " + imageH);
							display.getOptionBar().getJtfCutPoint()
								.setText(clickX + " , " + clickY);
							cutClick = 0;
						}
					}
				}
			}
		}
		
		else if(e.getSource().equals(menu.getIcon())) {
			dialog.setVisible(true);
		}
		
		//功能实现
		else {
			switch(menu.getBtns().indexOf(e.getSource())) {
				case MainButton.OPEN:
					if(menu.getBtns().get(0).getCanChoose()) {
						cutClick = -1;
						OpenImage openImage = new OpenImage(frame);
						if(openImage.open()) {
							isOpenImage = true;
							menu.getBtns().get(0).setCanChoose(false);
							menu.getBtns().get(1).setCanChoose(true);
							menu.getBtns().get(2).setCanChoose(true);
							menu.getBtns().get(3).setCanChoose(true);
							menu.getBtns().get(4).setCanChoose(true);
							menu.getBtns().get(5).setCanChoose(true);
							menu.getBtns().get(6).setCanChoose(true);
							menu.getBtns().get(7).setCanChoose(true);
							
							Mat image = openImage.getImage();
							images.add(image);
							path = openImage.getPath();
							fileExtension = openImage.getFileExtension();
							BufferedImage bi = new MatToBufImg(image, fileExtension).getImage();
							display.setDisplayImage(new ImageIcon(bi));
						}
					}
					break;
					
				case MainButton.SIZE:
					if(menu.getBtns().get(1).getCanChoose()) {
						display.getOptionBar().setOption(MainButton.SIZE);
						cutClick = -1;
					}
					break;
					
				case MainButton.CUT:
					if(menu.getBtns().get(2).getCanChoose()) {
						display.getOptionBar().setOption(MainButton.CUT);
						cutClick = 0;
					}
					break;
					
				case MainButton.ROTATE:
					if(menu.getBtns().get(3).getCanChoose()) {
						display.getOptionBar().setOption(MainButton.ROTATE);
						cutClick = -1;
					}
					break;
					
				case MainButton.LIGHT:
					if(menu.getBtns().get(4).getCanChoose()) {
						display.getOptionBar().setOption(MainButton.LIGHT);
						cutClick = -1;
						
					}
					break;
					
				case MainButton.CONTRAST:
					if(menu.getBtns().get(5).getCanChoose()) {
						display.getOptionBar().setOption(MainButton.CONTRAST);
						cutClick = -1;
					}
					break;
					
				case MainButton.SATURATION:
					if(menu.getBtns().get(6).getCanChoose()) {
						display.getOptionBar().setOption(MainButton.SATURATION);
						cutClick = -1;
					}
					break;
					
				case MainButton.DEHAZING:
					if(menu.getBtns().get(7).getCanChoose()) {
						display.getOptionBar().initComponentsVisible();
						cutClick = -1;
						
						DarkChannelPriorDehaze dehaze = new DarkChannelPriorDehaze();
						Mat newImage = dehaze.darkChannelPriorDehaze(images.peekLast());
						images.add(newImage);
						BufferedImage bi = new MatToBufImg(newImage, fileExtension).getImage();
						display.setDisplayImage(new ImageIcon(bi));
					}
					break;

				default:
					break;
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(display.getOptionBar().getJbSizeOK())) {
			String wt = display.getOptionBar().getJtfSizeWidth().getText();
			String ht = display.getOptionBar().getJtfSizeHeight().getText();
			if(wt.equals("") || ht.equals("")) {
				JOptionPane.showMessageDialog(display,
						"Illegal Input!", "", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int w = Integer.parseInt(wt);
				int h = Integer.parseInt(ht);
				if(w == 0 || h == 0) {
					JOptionPane.showMessageDialog(display,
							"Illegal Input!", "", JOptionPane.ERROR_MESSAGE);
				}
				else {
					ModifySize size = new ModifySize();
					Mat newImage = size.size(images.peekLast(), new Dimension(w, h));
					images.add(newImage);
					BufferedImage bi = new MatToBufImg(newImage, fileExtension).getImage();
					display.setDisplayImage(new ImageIcon(bi));
				}
			}
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbCutOK())) {
			String pt = display.getOptionBar().getJtfCutPoint().getText();
			String dt = display.getOptionBar().getJtfCutDimension().getText();
			if(pt.equals("") || dt.equals("")) {
				JOptionPane.showMessageDialog(display,
						"Illegal Input!", "", JOptionPane.ERROR_MESSAGE);
			}
			else {
				String[] pts = pt.split(" , ");
				String[] dts = dt.split(" ");
				
				Point p = new Point(Integer.parseInt(pts[0]), Integer.parseInt(pts[1]));
				Dimension d = new Dimension(Integer.parseInt(dts[0]), Integer.parseInt(dts[2]));
				
				
				CutImage cut = new CutImage();
				Mat newImage = cut.cut(images.peekLast(), p, d);
				images.add(newImage);
				BufferedImage bi = new MatToBufImg(newImage, fileExtension).getImage();
				display.setDisplayImage(new ImageIcon(bi));
			}
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbClockWise())) {
			Mat newImage = new RotateImage().rotate(images.peekLast(), RotateImage.CLOCKWISE);
			images.add(newImage);
			BufferedImage bi = new MatToBufImg(newImage, fileExtension).getImage();
			display.setDisplayImage(new ImageIcon(bi));
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbMirror())) {
			Mat newImage = new RotateImage().rotate(images.peekLast(), RotateImage.MIRROR);
			images.add(newImage);
			BufferedImage bi = new MatToBufImg(newImage, fileExtension).getImage();
			display.setDisplayImage(new ImageIcon(bi));
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbLightOK())) {
			int light = display.getOptionBar().getLightNum();
			BufferedImage image = new MatToBufImg(images.peekLast(), fileExtension).getImage();
			BufferedImage bi = new BrightnessControl().light(image, light);
			
			Mat newImage = new BufImgToMat(bi, 16).getMat();
			images.add(newImage);
			display.setDisplayImage(new ImageIcon(bi));
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbContrastOK())) {
			int constrast = display.getOptionBar().getContrastNum();
			BufferedImage image = new MatToBufImg(images.peekLast(), fileExtension).getImage();
			BufferedImage bi = new ContrastControl().contrast(image, constrast);
			
			Mat newImage = new BufImgToMat(bi, 16).getMat();
			images.add(newImage);
			display.setDisplayImage(new ImageIcon(bi));
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbSaturationOK())) {
			int saturation = display.getOptionBar().getSaturationNum();
			BufferedImage image = new MatToBufImg(images.peekLast(), fileExtension).getImage();
			BufferedImage bi = new SaturationControl().saturation(image, saturation);
			
			Mat newImage = new BufImgToMat(bi, 16).getMat();
			images.add(newImage);
			display.setDisplayImage(new ImageIcon(bi));
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbUndo())) {
			if(images.size() > 1) {
				images.pollLast();
				BufferedImage bi = new MatToBufImg(images.peekLast(), fileExtension).getImage();
				display.setDisplayImage(new ImageIcon(bi));
			}
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbRedo())) {
			if(images.size() > 1) {
				Mat m = images.peek();
				images.clear();
				images.add(m);
				System.out.println(images.size());
				BufferedImage bi = new MatToBufImg(images.peekLast(), fileExtension).getImage();
				display.setDisplayImage(new ImageIcon(bi));
			}
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbSave())) {
			
			if(menu.getBtns().get(0).getCanChoose() || images.size() <= 1) {
				
			}
			else {
				int n = JOptionPane.showConfirmDialog(display, "Save new image?", "Confirm", JOptionPane.WARNING_MESSAGE);
				if(n == 0) {
					isOpenImage = false;
					display.setDisplayImage(null);
					
					String newPath = path.substring(0, path.length() - 4) + "_newimage" + fileExtension;
					Imgcodecs.imwrite(newPath, images.peekLast());
					
					String s = "Save successfully！Save as:\n" + newPath;
					JOptionPane.showMessageDialog(display, s, "", JOptionPane.INFORMATION_MESSAGE);
					
					menu.getBtns().get(0).setCanChoose(true);
					menu.getBtns().get(1).setCanChoose(false);
					menu.getBtns().get(2).setCanChoose(false);
					menu.getBtns().get(3).setCanChoose(false);
					menu.getBtns().get(4).setCanChoose(false);
					menu.getBtns().get(5).setCanChoose(false);
					menu.getBtns().get(6).setCanChoose(false);
					menu.getBtns().get(7).setCanChoose(false);
				}
			}
			
		}
		
		else if(e.getSource().equals(display.getOptionBar().getJbCancel())) {
			
			if(menu.getBtns().get(0).getCanChoose()) {
				
			}
			else {
				int n = JOptionPane.showConfirmDialog(display, "Give up this image?", "Confirm", JOptionPane.WARNING_MESSAGE);
				if(n == 0) {
					isOpenImage = false;
					display.setDisplayImage(null);
					images.clear();
					menu.getBtns().get(0).setCanChoose(true);
					menu.getBtns().get(1).setCanChoose(false);
					menu.getBtns().get(2).setCanChoose(false);
					menu.getBtns().get(3).setCanChoose(false);
					menu.getBtns().get(4).setCanChoose(false);
					menu.getBtns().get(5).setCanChoose(false);
					menu.getBtns().get(6).setCanChoose(false);
					menu.getBtns().get(7).setCanChoose(false);
				}
			}
			
		}
		
	}
	
	
	
	class MouseEventListener implements MouseInputListener {
		
		Point origin = new Point();
		MainGUI frame;
		
		public MouseEventListener(MainGUI frame) {
			// TODO Auto-generated constructor stub
			this.frame = frame;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			origin.x = e.getX();
			origin.y = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			Point p = this.frame.getLocation();
			Dimension d = this.frame.getSize();
			if(e.getSource().equals(frame.titleBar) && frame.getCursor().getType() == Cursor.DEFAULT_CURSOR) {
				if(!isMax) {
					this.frame.setLocation(
					p.x + (e.getX() - origin.x), 
					p.y + (e.getY() - origin.y));
				}
			}
			else if(frame.getCursor().getType() == Cursor.N_RESIZE_CURSOR) {
				int changeH = d.height - (e.getY() - origin.y);
				if(changeH > frame.getMinimumSize().getHeight() && changeH < frame.getMaximumSize().getHeight()) {
					this.frame.setLocation(p.x, p.y + (e.getY() - origin.y));
					this.frame.setSize(d.width, changeH);
				}
			}
			else if(frame.getCursor().getType() == Cursor.S_RESIZE_CURSOR) {
				int changeH = e.getY();
				if(changeH > frame.getMinimumSize().getHeight() && changeH < frame.getMaximumSize().getHeight()) {
					this.frame.setLocation(p.x, p.y);
					this.frame.setSize(d.width, changeH);
				}
			}
			else if(frame.getCursor().getType() == Cursor.W_RESIZE_CURSOR) {
				int changeW = d.width - (e.getX() - origin.x);
//				System.out.println(e.getX() + " " + origin.x + " " + d.width + " " + changeW);
				if(changeW > frame.getMinimumSize().getWidth() && changeW < frame.getMaximumSize().getWidth()) {
					this.frame.setLocation(p.x + (e.getX() - origin.x), p.y);
					this.frame.setSize(changeW, d.height);
				}
			}
			else if(frame.getCursor().getType() == Cursor.E_RESIZE_CURSOR) {
				int changeW = e.getX();
				if(changeW > frame.getMinimumSize().getWidth() && changeW < frame.getMaximumSize().getWidth()) {
					this.frame.setLocation(p.x, p.y);
					this.frame.setSize(changeW, d.height);
				}
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX();
			int y = e.getY();
			int w = frame.getWidth();
			int h = frame.getHeight();
			if(!isMax) {
				if(x > 10 && x < w - 160 && y > 0 && y < 10) {
					frame.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
				}
				else if(x > 0 && x < 3 && y > 10 && y < h - 10) {
					frame.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
				}
				else if(x > 10 && x < w - 10 && y > h - 10 && y < h) {
					frame.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
				}
				else if(x > w - 10 && x < w && y > 10 && y < h - 10) {
					frame.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
				}
				else {
					frame.setCursor(Cursor.getDefaultCursor());
				}
			}
			
		}
		
	}
	 
	
//	public static void main(String[] args) {
//		MainGUI gui = new MainGUI(1100, 900);
//	}

	
}
