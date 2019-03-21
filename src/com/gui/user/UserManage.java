package com.gui.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import com.gui.TitleBar;
import com.gui.user.SignInUp.MouseEventListener;
import com.main.User;

public class UserManage extends JFrame implements MouseListener, ActionListener {
	
	private JFrame frame;
	
	private TitleBar titleBar;
	private JTable jtUsers;
	private JTextField jtfFind;
	private JButton jbFind;
	private JLabel jlName;
	private JLabel jlPower;
	private JButton jbDelete;
	private JButton jbBack;
	
	private User user;
	private myTableModel model;
	private Vector<Object> vectorName;
	
	public UserManage() {
		// TODO Auto-generated constructor stub
		user = new User();
		frame = this;
		
		setSize(600, 500);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		int x = (width - 600) / 2;
		int y = (height - 500) / 2;
		setLocation(x, y);
		setUndecorated(true);
		
		titleBar = new TitleBar(600);
		titleBar.setBounds(0, 0, 600, 40);
		titleBar.getJlIcon().setVisible(false);
		titleBar.getJlText().setIcon(new ImageIcon("./icons/user_manage.png"));
		titleBar.getJlText().setBounds(200, 0, 200, 40);
		titleBar.getJlMinFrame().setBounds(500, 2, 40, 32);
		titleBar.getJlMaxFrame().setVisible(false);
		titleBar.getJlCloseFrame().setBounds(540, 2, 50, 32);
		titleBar.getJlMinFrame().addMouseListener(this);
		titleBar.getJlCloseFrame().addMouseListener(this);
		MouseEventListener listener = new MouseEventListener(this);
		titleBar.addMouseListener(listener);
		titleBar.addMouseMotionListener(listener);
		
		String[][] row = new String[14][2];
		String[] column = {"用户名", "权限"};
		model = new myTableModel(row, column);//两个参数row[i][j]表示列表为i行j列，column[]为列标题
		int size = user.getUsers().length;
		Vector<Object> vectorData = setTableData(size, user.getUsers(), user.getPowers());//TableData方法实现，数组的写入
		vectorName = new Vector<>();
		vectorName.add(column[0]);
		vectorName.add(column[1]);
		model.setDataVector(vectorData, vectorName);//将列表数据和标题写入
		jtUsers = new JTable();
		jtUsers.setModel(model);//table加载
		jtUsers.setFont(new Font("", 0, 14));
		jtUsers.setForeground(Color.DARK_GRAY);
		jtUsers.setRowHeight(24);
		jtUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置列表只能单行选中
		jtUsers.addMouseListener(this);
		JScrollPane jspTable = new JScrollPane(jtUsers);
		jspTable.getViewport().setBackground(new Color(255, 250, 250));
		jspTable.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jspTable.setBounds(30, 50, 300, 361);
		
		jtfFind = new JTextField();
		jtfFind.setFont(new Font("Monaco", 0, 16));
		jtfFind.setBackground(Color.WHITE);
		jtfFind.setForeground(Color.DARK_GRAY);
		jtfFind.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jtfFind.setBounds(360, 100, 150, 30);
		jtfFind.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyChar = e.getKeyChar();
				byte[] bs = (e.getKeyChar() + "").getBytes();
				for(int i = 0; i < bs.length; i++) {	//禁止输入汉字
					if(bs[i] >= (byte)0x81 && bs[i] <= (byte)0xfe) {
						e.consume();
					}
				}
				if((keyChar >= 48 && keyChar <= 57)	//数字0~9
						|| (keyChar >= 65 && keyChar >= 90)	//字母A~Z
						|| (keyChar >= 97 && keyChar >= 122)	//字母a~z
						|| keyChar == 95) {	//下划线_
				}
				else {
					e.consume();
				}
				
				if(jtfFind.getText().length() > 16) {
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		jbFind = new JButton(new ImageIcon("./icons/search.png"));
		jbFind.setHorizontalAlignment(JButton.CENTER);
		jbFind.setBackground(Color.WHITE);
		jbFind.setFocusPainted(false);
		jbFind.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 1));
		jbFind.setBounds(520, 94, 40, 40);
		jbFind.addActionListener(this);
		
		jlName = new JLabel("");
		jlName.setHorizontalAlignment(JLabel.CENTER);
		jlName.setFont(new Font("Monaco", 0, 18));
		jlName.setForeground(Color.DARK_GRAY);
		jlName.setBounds(360, 170, 190, 40);
		
		jlPower = new JLabel("");
		jlPower.setHorizontalAlignment(JLabel.CENTER);
		jlPower.setFont(new Font("宋体", 0, 18));
		jlPower.setForeground(Color.DARK_GRAY);
		jlPower.setBounds(360, 210, 190, 40);
		
		jbDelete = new JButton(new ImageIcon("./icons/user_delete.png"));
		jbDelete.setHorizontalAlignment(JButton.CENTER);
		jbDelete.setBackground(new Color(255, 250, 250));
		jbDelete.setFocusPainted(false);
		jbDelete.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 2));
		jbDelete.setBounds(420, 300, 80, 80);
		jbDelete.addActionListener(this);
		
		jbBack = new JButton(new ImageIcon("./icons/logout.png"));
		jbBack.setHorizontalAlignment(JButton.CENTER);
		jbBack.setBackground(new Color(255, 250, 250));
		jbBack.setFocusPainted(false);
		jbBack.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 2));
		jbBack.setBounds(520, 420, 54, 54);
		jbBack.addActionListener(this);
		
		JPanel jPanel = new JPanel(null);
		jPanel.setBackground(Color.WHITE);
		jPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 5, true));
		
		jPanel.add(titleBar);
		jPanel.add(jspTable);
		jPanel.add(jtfFind);
		jPanel.add(jbFind);
		jPanel.add(jlName);
		jPanel.add(jlPower);
		jPanel.add(jbDelete);
		jPanel.add(jbBack);
		
		this.getContentPane().add(jPanel);
		
	}
	
	Vector<Object> setTableData(int num, String[] name, String[] power){
		Vector<Object> vectorData = new Vector<>();
		for(int i = 0; i < num; i++){
			Vector<Object> vectorRow = new Vector<>();
			vectorRow.add(name[i]);
			switch(power[i]){
			case "0":
				vectorRow.add("管理员");
				break;
			case "1":
				vectorRow.add("普通用户");
				break;
			default:
				break;
			}
			vectorData.add(vectorRow);
		}
		return vectorData;
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(titleBar.getJlMinFrame())) {
			frame.setExtendedState(JFrame.ICONIFIED);
		}
		else if(e.getSource().equals(titleBar.getJlCloseFrame())) {
			System.exit(0);
		}
		
		else if(e.getSource().equals(jtUsers)) {
			int row = jtUsers.getSelectedRow();
			int column = jtUsers.getSelectedColumn();
			if(column == 0) {
				jlName.setText((String) jtUsers.getValueAt(row, column));
				jlPower.setText((String) jtUsers.getValueAt(row, column + 1));
			}
			else {
				jlName.setText((String) jtUsers.getValueAt(row, column - 1));
				jlPower.setText((String) jtUsers.getValueAt(row, column));
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
		if(e.getSource().equals(jbFind)) {
			String name = jtfFind.getText();
			if(name.equals("")) {
				
			}
			else {
				int a = user.isExist(name);
				if(a == -1) {
					JOptionPane.showMessageDialog(this, "Username does't exist！",
							"", JOptionPane.ERROR_MESSAGE);
				}
				else {
					jlName.setText(name);
					if(a == 0 && name.equals("admin")) {
						jlPower.setText("管理员");
					}
					else {
						jlPower.setText("普通用户");
					}
				}
			}
		}
		
		else if(e.getSource().equals(jbDelete)) {
			String name = jlName.getText();
			String power = jlPower.getText();
			if(name.equals("") || power.equals("")) {
				
			}
			else {
				if(name.equals("admin") || power.equals("管理员")) {
					JOptionPane.showMessageDialog(this, 
							"You can't delete yourself！","", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int n = JOptionPane.showConfirmDialog(this, 
							"Are you sure to delete Username:" + name + " ?","",JOptionPane.YES_NO_OPTION);
					if(n == 0) {
						int i = user.isExist(name);
						user.deleteUser(name);
						model.removeRow(i);
					}
				}
			}
		}
		
		else if(e.getSource().equals(jbBack)) {
			int n = JOptionPane.showConfirmDialog(this, 
					"Are you sure to logout?", "", JOptionPane.YES_NO_OPTION);
			if(n == 0) {
				SignInUp inUp = new SignInUp();
				inUp.setVisible(true);
				this.setVisible(false);
			}
		}
		
	}
	
	
	class MouseEventListener implements MouseInputListener {
		
		Point origin = new Point();
		UserManage frame;
		
		public MouseEventListener(UserManage frame) {
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
			if(e.getSource().equals(frame.titleBar)) {
				this.frame.setLocation(
						p.x + (e.getX() - origin.x), 
						p.y + (e.getY() - origin.y));
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//类myTableClass继承自DefaultTableModel
	class myTableModel extends DefaultTableModel {
		public myTableModel(Object[][] data, Object[] columnNames){
			super(data, columnNames);//继承父类的方法，保证可以正常初始化
		}
		//重写DefaultTableModel的isCellEditable方法，作用是单元格只可以选中不可以编辑
		public boolean isCellEditable(int row,int column){
			return false;
		}
	}
	
	
//	public static void main(String[] args) {
//		UserManage in = new UserManage();
//		in.setVisible(true);
//	}
//	

}
