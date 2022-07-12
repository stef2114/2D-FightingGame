package main;

import javax.swing.*;



public class Main {
	

	public static JFrame window;
	
	
	public static void main(String[] args) {
		window= new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Game");
		//window.setUndecorated(true);
		
		GamePanel gamepanel = new GamePanel();
		window.add(gamepanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamepanel.setupGame();
		gamepanel.startGameThread();
	}

}
