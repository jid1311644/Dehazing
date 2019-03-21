import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gui.dialog.MyDialog;

public class Test extends JFrame {

	MyDialog dialog;
	
	public Test() {
		// TODO Auto-generated constructor stub
		setSize(800, 800);
		setLocation(400, 200);
		
		dialog = new MyDialog(this);
		dialog.setModal(true);
		
		JButton button = new JButton("dialog");
		button.setBounds(50, 50, 100, 50);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.setVisible(true);
			}
		});
		
		JPanel jPanel = new JPanel(null);
		jPanel.setBackground(Color.WHITE);
		jPanel.add(button);
		
		this.getContentPane().add(jPanel);
		

		setVisible(true);
		
	}
	

	
	public static void main(String[] args) {
		Test test = new Test();
	}
	
}
