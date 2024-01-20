package main;

import javax.swing.JFrame;

public class Main {
	
 public  static void main ( String args[]) {
 
	 JFrame window = new JFrame();
	 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 window.setResizable(false);
	 window.setTitle("2D Adventure");
	 
	 GamePanel gamePanel = new GamePanel();
	 window.add(gamePanel);
	 window.pack();// this fits everything that is in game panel to the window perfectly
	 
	 window.setLocationRelativeTo(null);
	 // display window at center of screen
	 
	 window.setVisible(true);
	 
	 gamePanel.startGameThread();
	 
	 
	 
	 
	 
	 
 }

}
