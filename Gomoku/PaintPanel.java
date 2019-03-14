package Gomoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

class PaintPanel extends JPanel {
	   ArrayList Items = new ArrayList();
       int[][] board;    //0表示无子，1表示黑子，2表示白子
       int turn;         //控制权，0表示黑子落子，1表示白子落子
       int moves;        //记录棋子数目
       
	   public PaintPanel() {
	     Initial();
	     setBackground(Color.pink);
	     setLayout(new BorderLayout());
	     ButtonPanel buttonPanel = new ButtonPanel();
	     add(buttonPanel, BorderLayout.NORTH);   
	     addMouseListener(new MouseHandler());
	     JTextField statusText;        
         statusText=new JTextField("黑棋先落子，任一方先连成五子，则赢得此局！");
         statusText.setEditable(false);
         statusText.setHorizontalAlignment(JTextField.CENTER);
         add(statusText,BorderLayout.SOUTH);
	   }
	   
       public void Initial(){
         board=new int[16][16];
         turn=0;
         for(int i=0;i<=15;i++){
            for(int j=0;j<=15;j++){
                board[i][j]=0;
            }
         }
        moves=0; 
       }
       
	   public void paintComponent(Graphics g) {
	     int startX = 50;
	     int startY = 50;
	     boolean a= false;
	     super.paintComponent(g);
	     Graphics2D g2 = (Graphics2D)g;

	     for(int i=0; i<16; i++) {
	       g2.draw(new Line2D.Double(startX, startY+20*i, startX+300, startY+20*i));  //创建盘面
	       g2.draw(new Line2D.Double(startX+20*i, startY, startX+20*i, startY+300));
	     }
	     for(int i=0; i<Items.size(); i++) {
	        a= !a;
	       if(a)
	         g2.setColor(Color.BLACK);
	       else
	         g2.setColor(Color.WHITE);
	       g2.fill((Ellipse2D)Items.get(i));
	     }
	   }
	   	   
	   void Judge(int player,int[][] p){  //判断胜负
		boolean a=false;
	   	for(int i=0;i<16;i++){
	   		for(int j=0;j<12;j++){
	   			if(p[i][j]==player&&p[i][j+1]==player&&p[i][j+2]==player
	   			   &&p[i][j+3]==player&&p[i][j+4]==player)
	   			{
	   				a=true;	
	   			}
	   		}
	   	}
		for(int j=0;j<16;j++){
	   		for(int i=0;i<12;i++){
	   			if(p[i][j]==player&&p[i+1][j]==player&&p[i+2][j]==player
	   			   &&p[i+3][j]==player&&p[i+4][j]==player)
	   			{
	   				a=true;	
	   			}
	   		}
	   	}
		for(int i=0;i<12;i++){
	   		for(int j=0;j<12;j++){
	   			if(p[i][j]==player&&p[i+1][j+1]==player&&p[i+2][j+2]==player
	   			   &&p[i+3][j+3]==player&&p[i+4][j+4]==player)
	   			{
	   				a=true;
	   			}
	   		}
	   	}
		for(int i=4;i<16;i++){
	   		for(int j=0;j<16;j++){
	   			if(p[i][j]==player&&p[i-1][j+1]==player&&p[i-2][j+2]==player
	   			   &&p[i-3][j+3]==player&&p[i-4][j+4]==player)
	   			{
	   				a=true;	
	   			}
	   		}
	   	}
		if(a==true)
		{
			 Items.clear();
	         repaint();
	         Initial();
	         if(player==1) { new BlackWin();}       //执黑子获胜
	         if(player==2) { new WhiteWin();}       //执白子获胜
		}
	   }
	   
	   private class ButtonPanel extends JPanel {
	     public ButtonPanel() {
	       JButton reset = new JButton("Reset");
	       add(reset);
	       JButton quit = new JButton("Quit");
	       add(quit); 
	       
	       ResetEvent listenerR = new ResetEvent();
	       reset.addMouseListener(listenerR);
	       QuitEvent listenerQ = new QuitEvent();
	       quit.addMouseListener(listenerQ);
	     }

	     private class QuitEvent extends MouseAdapter {
	       public void mouseClicked(MouseEvent event) {
	         System.exit(1);
	       }
	     }

	     private class ResetEvent extends MouseAdapter {
	       public void mouseClicked(MouseEvent event) {
	         Items.clear();
	         repaint();
	         Initial();
	       }
	     }
	   }
	   
       private class MouseHandler extends MouseAdapter {        //落子
         public void mousePressed(MouseEvent event) {
           int x = event.getX();
           int y = event.getY();
           paintItem(x, y);
           repaint();
         }
         void paintItem(int x, int y) {
             int player=0; 
            
                     
             if(x > 50 && x < 350 && y >50 && y < 350) {
             int X = x / 20;
             int Y = y / 20;
             int centerX = X*20 + 10;
             int centerY = Y*20 + 10;
             int i=(centerX-50)/20;
             int j=(centerY-50)/20;
             if(board[i][j]==0)
             {
                if(turn==0)
                {                             //黑棋落子                 
                    board[i][j]=1;
                    turn=1;
                }
                else
                {                             //白棋落子                   
                    board[i][j]=2;
                    turn=0;
                }
                Ellipse2D ellipse = new Ellipse2D.Double();
                ellipse.setFrameFromCenter(centerX, centerY, centerX+8, centerY+8);
                Items.add(ellipse);
                player=board[i][j];
                moves++;
             }
             //判断胜负;
             if(moves<256){
                 Judge(player,board);
             }else {
                 new Draw();         //表示平局
             }
           }
         }
       }
	}


