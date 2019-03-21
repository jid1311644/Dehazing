package com.gui.dialog;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import com.gui.MainGUI;
import com.gui.TitleBar;
import com.gui.user.SignInUp;
import com.main.User;

public class MyDialog extends JDialog implements MouseListener, FocusListener, KeyListener {
	
	private Dialog dialog;
	private JFrame frame;
	
	private TitleBar titleBar;
	
	private JLabel jlChangePsw;
	private JButton jbChangeOK;
	private JLabel jlChangeLine;
	private JLabel jlOldPsw;
	private JPasswordField jtfOldPsw;
	private JLabel jlOldLine;
	private JLabel jlNewPsw;
	private JPasswordField jtfNewPsw;
	private JLabel jlNewLine;
	private JLabel jlRenewPsw;
	private JPasswordField jtfRenewPsw;
	private JLabel jlRenewLine;
	
	private JLabel jlAboutUS;
	private JLabel jlAboutUSLine;
	private JTextArea jtaAboutUS;
	
	private JButton jbBack;
	private JButton jbLogout;
	
	private User user;
	
	public MyDialog(JFrame f) {
		// TODO Auto-generated constructor stub
		super(f);
		dialog = this;
		frame = f;
		user = new User();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int w = dimension.width;
		int h = dimension.height;
		int x = (w - 500) / 2;
		int y = (h - 600) / 2;
		setBounds(x, y, 500, 600);
		setUndecorated(true);
		
		titleBar = new TitleBar(600);
		titleBar.setBounds(0, 0, 500, 40);
		titleBar.getJlIcon().setVisible(false);
		titleBar.getJlText().setIcon(new ImageIcon("./icons/personal.png"));
		titleBar.getJlText().setBounds(150, 0, 200, 40);
		titleBar.getJlMinFrame().setVisible(false);
		titleBar.getJlMaxFrame().setVisible(false);
		titleBar.getJlCloseFrame().setBounds(440, 2, 50, 32);
		titleBar.getJlCloseFrame().addMouseListener(this);
		MouseEventListener listener = new MouseEventListener(this);
		titleBar.addMouseListener(listener);
		titleBar.addMouseMotionListener(listener);
		
		jlChangePsw = new JLabel("�޸�����", JLabel.CENTER);
		jlChangePsw.setFont(new Font("����", 1, 18));
		jlChangePsw.setForeground(new Color(255, 128, 128));
		jlChangePsw.setBounds(20, 10, 100, 40);
		
		jbChangeOK = new JButton(new ImageIcon("./icons/change_ok.png"));
		jbChangeOK.setBackground(new Color(250, 250, 250));
		jbChangeOK.setBounds(380, 10, 60, 32);
		jbChangeOK.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128)));
		jbChangeOK.setFocusPainted(false);
		jbChangeOK.addMouseListener(this);
		
		jlChangeLine = new JLabel();
		jlChangeLine.setOpaque(true);
		jlChangeLine.setBackground(new Color(255, 128, 128));
		jlChangeLine.setBounds(12, 50, 450, 1);
		
		jlOldPsw = new JLabel(new ImageIcon("./icons/old_psw_d.png"));
		jlOldPsw.setBounds(56, 80, 90, 30);
		
		jtfOldPsw = new JPasswordField("");
		jtfOldPsw.setBackground(Color.WHITE);
		jtfOldPsw.setFont(new Font("Monaco", 0, 24));
		jtfOldPsw.setForeground(Color.DARK_GRAY);
		jtfOldPsw.setBorder(null);
		jtfOldPsw.setBounds(146, 70, 250, 40);
		jtfOldPsw.addFocusListener(this);
		jtfOldPsw.addKeyListener(this);
		
		jlOldLine = new JLabel();
		jlOldLine.setOpaque(true);
		jlOldLine.setBackground(new Color(154, 154, 154));
		jlOldLine.setBounds(56, 110, 360, 1);
		
		jlNewPsw = new JLabel(new ImageIcon("./icons/new_psw_d.png"));
		jlNewPsw.setBounds(56, 140, 90, 30);
		
		jtfNewPsw = new JPasswordField("");
		jtfNewPsw.setBackground(Color.WHITE);
		jtfNewPsw.setFont(new Font("Monaco", 0, 24));
		jtfNewPsw.setForeground(Color.DARK_GRAY);
		jtfNewPsw.setBorder(null);
		jtfNewPsw.setBounds(146, 130, 250, 40);
		jtfNewPsw.addFocusListener(this);
		jtfNewPsw.addKeyListener(this);
		
		jlNewLine = new JLabel();
		jlNewLine.setOpaque(true);
		jlNewLine.setBackground(new Color(154, 154, 154));
		jlNewLine.setBounds(56, 170, 360, 1);
		
		jlRenewPsw = new JLabel(new ImageIcon("./icons/new_psw_d.png"));
		jlRenewPsw.setBounds(56, 200, 90, 30);
		
		jtfRenewPsw = new JPasswordField("");
		jtfRenewPsw.setBackground(Color.WHITE);
		jtfRenewPsw.setFont(new Font("Monaco", 0, 24));
		jtfRenewPsw.setForeground(Color.DARK_GRAY);
		jtfRenewPsw.setBorder(null);
		jtfRenewPsw.setBounds(146, 190, 250, 40);
		jtfRenewPsw.addFocusListener(this);
		jtfRenewPsw.addKeyListener(this);
		
		jlRenewLine = new JLabel();
		jlRenewLine.setOpaque(true);
		jlRenewLine.setBackground(new Color(154, 154, 154));
		jlRenewLine.setBounds(56, 230, 360, 1);
		
		
		jlAboutUS = new JLabel("��������", JLabel.CENTER);
		jlAboutUS.setFont(new Font("����", 1, 18));
		jlAboutUS.setForeground(new Color(255, 128, 128));
		jlAboutUS.setBounds(20, 280, 100, 40);
		
		jlAboutUSLine = new JLabel();
		jlAboutUSLine.setOpaque(true);
		jlAboutUSLine.setBackground(new Color(255, 128, 128));
		jlAboutUSLine.setBounds(12, 320, 450, 1);
		
		String c = "	��ϵͳ��һ�����ڻ�ȡ��������ĳ��ƺ�����Ϣ�����ϵͳ��Ŀǰ����ʵ��ͼƬ��ȥ�����õ��㷨�ǻ��ڰ�ԭɫ��ȥ���㷨�����幦�����£�\r\n" + 
				"	*��¼��ע�᣺�û�����ͨ��ע�������˺ŵ�¼��ϵͳ\r\n" + 
				"	*��ͼƬ���ڿ��ӻ���������ʾ�û��ӱ��ش򿪵�ͼƬ\r\n" + 
				"	*�޸�ͼƬ��С���û�����ͼƬ�Ĵ�С�����޸�\r\n" + 
				"	*ͼƬ�ü����û���ͼƬ��ѡȡҪ�ü�������ϵͳʵ�ֲü�\r\n" + 
				"	*ͼƬ��ת��Ŀǰ��������ת����˳ʱ��90�Ⱥ;�����ת���û�ѡ��\r\n" + 
				"	*ͼƬ���ȵ��ڣ��û�ͨ���϶��������ı�ͼƬ����\r\n" + 
				"	*ͼƬ�Աȶȵ��ڣ��û�ͨ���϶��������ı�ͼƬ�Աȶ�\r\n" + 
				"	*ͼƬ���Ͷȵ��ڣ��û�ͨ���϶��������ı�ͼƬ���Ͷ�\r\n" + 
				"	*�����������û����β�������ʾ�ϴβ�����ͼƬ\r\n" + 
				"	*�����������û������в�������ʾԭͼ\r\n" + 
				"	*����ͼƬ�������û��޸�֮���ͼƬ�����أ���ԭͼƬ��ͬһ·����\r\n" + 
				"	*�޸����룺�û�������ϵͳ���޸��Լ�������\r\n" + 
				"	*�û���������Ա���ܣ�����ɾ����ע���û�\r\n" + 
				"��������������������������������������������������\r\n" + 
				"		�������ԣ�Java	����ƽ̨��Eclipse\r\n";
		jtaAboutUS = new JTextArea(c);
		jtaAboutUS.setBackground(Color.WHITE);
		jtaAboutUS.setFont(new Font("����", 0, 16));
		jtaAboutUS.setForeground(Color.DARK_GRAY);
		jtaAboutUS.setEditable(false);
		jtaAboutUS.setMargin(new Insets(10, 10, 20, 10));
		jtaAboutUS.setTabSize(4);
		jtaAboutUS.setLineWrap(true);
		jtaAboutUS.setBounds(12, 330, 450, 500);
		
		JPanel jpContent = new JPanel(null);
		jpContent.setBackground(Color.WHITE);
		jpContent.setPreferredSize(new Dimension(470, 800));
		jpContent.add(jlChangePsw);
		jpContent.add(jbChangeOK);
		jpContent.add(jlChangeLine);
		jpContent.add(jlOldPsw);
		jpContent.add(jtfOldPsw);
		jpContent.add(jlOldLine);
		jpContent.add(jlNewPsw);
		jpContent.add(jtfNewPsw);
		jpContent.add(jlNewLine);
		jpContent.add(jlRenewPsw);
		jpContent.add(jtfRenewPsw);
		jpContent.add(jlRenewLine);
		jpContent.add(jlAboutUS);
		jpContent.add(jlAboutUSLine);
		jpContent.add(jtaAboutUS);

		JScrollPane jspContent = new JScrollPane(jpContent);
		jspContent.setBounds(5, 40, 490, 490);
		jspContent.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128)));
		jspContent.doLayout();
		JScrollBar bar = jspContent.getVerticalScrollBar();
		bar.setValue(bar.getMinimum());
		
		jbBack = new JButton(new ImageIcon("./icons/back.png"));
		jbBack.setHorizontalAlignment(JButton.CENTER);
		jbBack.setBackground(new Color(250, 250, 250));
		jbBack.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128)));
		jbBack.setFocusPainted(false);
		jbBack.setBounds(260, 542, 90, 40);
		jbBack.addMouseListener(this);
		
		jbLogout = new JButton(new ImageIcon("./icons/logout.png"));
		jbLogout.setHorizontalAlignment(JButton.CENTER);
		jbLogout.setBackground(new Color(250, 250, 250));
		jbLogout.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128)));
		jbLogout.setFocusPainted(false);
		jbLogout.setBounds(370, 542, 90, 40);
		jbLogout.addMouseListener(this);
	
		JPanel jPanel = new JPanel(null);
		jPanel.setBackground(new Color(255, 240, 240));
		jPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 5));
		
		jPanel.add(titleBar);
		jPanel.add(jspContent);
		jPanel.add(jbBack);
		jPanel.add(jbLogout);
		
		this.add(jPanel);
		
	}
	

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jtfOldPsw)) {
			jlOldPsw.setIcon(new ImageIcon("./icons/old_psw.png"));
			jlOldLine.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jtfNewPsw)) {
			jlNewPsw.setIcon(new ImageIcon("./icons/new_psw.png"));
			jlNewLine.setBackground(new Color(255, 128, 128));
		}
		else if(e.getSource().equals(jtfRenewPsw)) {
			jlRenewPsw.setIcon(new ImageIcon("./icons/new_psw.png"));
			jlRenewLine.setBackground(new Color(255, 128, 128));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jtfOldPsw)) {
			jlOldPsw.setIcon(new ImageIcon("./icons/old_psw_d.png"));
			jlOldLine.setBackground(new Color(154, 154, 154));
		}
		else if(e.getSource().equals(jtfNewPsw)) {
			jlNewPsw.setIcon(new ImageIcon("./icons/new_psw_d.png"));
			jlNewLine.setBackground(new Color(154, 154, 154));
		}
		else if(e.getSource().equals(jtfRenewPsw)) {
			jlRenewPsw.setIcon(new ImageIcon("./icons/new_psw_d.png"));
			jlRenewLine.setBackground(new Color(154, 154, 154));
		}
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyChar = e.getKeyChar();
		byte[] bs = (e.getKeyChar() + "").getBytes();
		for(int i = 0; i < bs.length; i++) {	//��ֹ���뺺��
			if(bs[i] >= (byte)0x81 && bs[i] <= (byte)0xfe) {
				e.consume();
			}
		}
		if((keyChar >= 48 && keyChar <= 57)	//����0~9
				|| (keyChar >= 65 && keyChar >= 90)	//��ĸA~Z
				|| (keyChar >= 97 && keyChar >= 122)	//��ĸa~z
				|| keyChar == 95) {	//�»���_
		}
		else {
			e.consume();
		}
		if(e.getSource().equals(jtfOldPsw)) {
			if(jtfOldPsw.getPassword().length > 16) {
				e.consume();
			}
		}
		else if(e.getSource().equals(jtfNewPsw)) {
			if(jtfNewPsw.getPassword().length > 16) {
				e.consume();
			}
		}
		else if(e.getSource().equals(jtfRenewPsw)) {
			if(jtfRenewPsw.getPassword().length > 16) {
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
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(titleBar.getJlCloseFrame())) {
			String oldPsw = new String(jtfOldPsw.getPassword());
			String newPsw = new String(jtfNewPsw.getPassword());
			String renewPsw = new String(jtfRenewPsw.getPassword());
			if(oldPsw.equals("") && newPsw.equals("") && renewPsw.equals("")) {
				this.setVisible(false);
			}
			else {
				int n = JOptionPane.showConfirmDialog(dialog, "Something has been modified. Back now?"
						, "", JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					this.setVisible(false);
				}
			}
		}
		
		else if(e.getSource().equals(jbChangeOK)) {
			String name = MainGUI.userName;
			String oldPsw = new String(jtfOldPsw.getPassword());
			String newPsw = new String(jtfNewPsw.getPassword());
			String renewPsw = new String(jtfRenewPsw.getPassword());
			if(oldPsw.equals("") || newPsw.equals("") || renewPsw.equals("")) {
				
			}
			else {
				if(!newPsw.equals(renewPsw)) {
					JOptionPane.showMessageDialog(dialog, "The new passwords must be consistent!"
							, "", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(!user.login(name, oldPsw)) {
						JOptionPane.showMessageDialog(dialog, "The old password error!"
								, "", JOptionPane.ERROR_MESSAGE);
					}
					else {
						int n = JOptionPane.showConfirmDialog(dialog, "Change password new?"
								, "", JOptionPane.YES_NO_OPTION);
						if(n == 0) {
							user.changePsw(name, newPsw);
							jtfOldPsw.setText("");
							jtfNewPsw.setText("");
							jtfRenewPsw.setText("");
						}
					}
				}
			}
		}
		else if(e.getSource().equals(jbBack)) {
			String oldPsw = new String(jtfOldPsw.getPassword());
			String newPsw = new String(jtfNewPsw.getPassword());
			String renewPsw = new String(jtfRenewPsw.getPassword());
			if(oldPsw.equals("") && newPsw.equals("") && renewPsw.equals("")) {
				this.setVisible(false);
			}
			else {
				int n = JOptionPane.showConfirmDialog(dialog, "Something has been modified. Back now?"
						, "", JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					this.setVisible(false);
				}
			}
		}
		else if(e.getSource().equals(jbLogout)) {
			String oldPsw = new String(jtfOldPsw.getPassword());
			String newPsw = new String(jtfNewPsw.getPassword());
			String renewPsw = new String(jtfRenewPsw.getPassword());
			if(oldPsw.equals("") && newPsw.equals("") && renewPsw.equals("") && !MainGUI.isOpenImage) {
				int n = JOptionPane.showConfirmDialog(dialog, "Logout now?"
						, "", JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					this.setVisible(false);
					frame.setVisible(false);
					SignInUp inUp = new SignInUp();
					inUp = new SignInUp();
					inUp.setVisible(true);
				}
			}
			else {
				int n = JOptionPane.showConfirmDialog(dialog, "Something has been modified. Logout now?"
						, "", JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					this.setVisible(false);
					frame.setVisible(false);
					SignInUp inUp = new SignInUp();
					inUp = new SignInUp();
					inUp.setVisible(true);
				}
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
	
	
	class MouseEventListener implements MouseInputListener {
		
		Point origin = new Point();
		MyDialog dialog;
		
		public MouseEventListener(MyDialog dialog) {
			// TODO Auto-generated constructor stub
			this.dialog = dialog;
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
			Point p = this.dialog.getLocation();
			if(e.getSource().equals(dialog.titleBar)) {
				this.dialog.setLocation(
						p.x + (e.getX() - origin.x), 
						p.y + (e.getY() - origin.y));
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
