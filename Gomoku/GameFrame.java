package Gomoku;
import javax.swing.*;

class GameFrame extends JFrame {
	   public GameFrame() {
	     setTitle("Îå×ÓÆå");
	     setSize(400, 420);
	     setResizable(false);

	     PaintPanel panel = new PaintPanel();
	     getContentPane().add(panel);
	   }
	}
