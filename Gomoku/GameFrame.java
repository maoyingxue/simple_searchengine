package Gomoku;
import javax.swing.*;

class GameFrame extends JFrame {
	   public GameFrame() {
	     setTitle("������");
	     setSize(400, 420);
	     setResizable(false);

	     PaintPanel panel = new PaintPanel();
	     getContentPane().add(panel);
	   }
	}
