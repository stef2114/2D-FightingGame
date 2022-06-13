package main;

import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;

import backGround.BackManager;
import entity.NPC_Rival;
import entity.Player;

public class GamePanel extends JPanel implements Runnable{

	final int originalTileSize=24;
	final int scale=2;
	public final int tileSize=originalTileSize * scale;
	final int maxScreenCol=32;
	final int maxScreenRow=18;
	public final int screenWidth= tileSize*maxScreenCol;
	public final int screenHeight= tileSize*maxScreenRow;
	
	int FPS=60;
	
	
	public BackManager bG=new BackManager(this);
	KeyHandler keyH=new KeyHandler(this);
	Sound music=new Sound();
	Sound se=new Sound();
	public UI ui=new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	public CollisionChecker ccheck= new CollisionChecker(this);
	Thread gameThread;
	
	public Player player1= new Player(this,keyH);
	public Player player2;
	public NPC_Rival npc_rival;
	public int gameState;
	public final int titleState=0;
	public final int playState=1;
	public final int pauseState=2;
	public int gameMode;
	public final int onePlayerMode=0;
	public final int twoPlayerMode=1;
	public int spriteCounter=0;
	public int spriteNum=1;
	int standCounter=0;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setPlayer2() {
		player2= new Player(this,keyH);
		player2.x=tileSize*14;
		player2.y=tileSize*7;
		player2.name="boy2";
		player2.getPlayerImage();
		player2.hp=10;
		
	}
	
	public void setNPC() {
		npc_rival= new NPC_Rival(this);
		npc_rival.x=tileSize*14;
		npc_rival.y=tileSize*7;
		npc_rival.hp=10;
		
	}
	
	public void setupGame() {
		playMusic(0);
		gameState=titleState;
	}
	
	public void startGameThread() {
		
		gameThread=new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval= 1000000000/FPS;
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount=0;
		while(gameThread!=null) {
			currentTime=System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
			timer+=(currentTime-lastTime);
			lastTime=currentTime;
			
			if(delta>=1) {
				update();
				repaint();
				delta=0;
				drawCount++;
				
			}
			if(timer>=1000000000) {
				System.out.println("FPS:"+ screenWidth);
				drawCount=0;
				timer=0;
			}
		}
	}
	
	public void update() {
		
		if(gameState == playState) {
			
			player1.update1();
			if(gameMode==0) {
				npc_rival.update();
			}else {
				player2.update2();
			}
			
		}
		else {
			
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		if(gameState==titleState) {
			ui.draw(g2);
		}
		else {
			
			bG.draw(g2);
			player1.draw(g2);
			if(gameMode==0) {
				npc_rival.draw(g2);
			}else {
				player2.draw(g2);
			}
			
			ui.draw(g2);
			g2.dispose();
		}
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}
