package Gomoku;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class WhiteWin extends JFrame{
	JLabel label=new JLabel();
	public WhiteWin(){
		super("获胜信息");
		label.setBounds(150,100,100,50);
		label.setText("白方获胜");
		this.getContentPane().add(label);
		this.setLocation(180,100);
		this.setLayout(null);
		this.setSize(300,300);
	   	this.setVisible(true);
	  // 	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   	this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent ev) {
		      dispose();
		      }
		});
	}
}
