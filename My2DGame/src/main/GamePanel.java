package main;

import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.*;
//import java.awt.GraphicsEnvironment;
import javax.swing.*;
import java.awt.image.BufferedImage;
import backGround.BackManager;
import entity.NPC_Rival;
import entity.Player;
import object.Projectile;

public class GamePanel extends JPanel implements Runnable{

	final int originalTileSize=24;
	final int scale=2;
	public final int tileSize=originalTileSize * scale;
	final int maxScreenCol=32;
	final int maxScreenRow=18;
	public final int screenWidth= tileSize*maxScreenCol;
	public final int screenHeight= tileSize*maxScreenRow;
	public int screenWidth2=screenWidth;
	public int screenHeight2=screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	
	int FPS=60;
	
	
	public BackManager bG=new BackManager(this);
	KeyHandler keyH=new KeyHandler(this);
	Sound music=new Sound();
	Sound se=new Sound();
	public UI ui=new UI(this);
	//public EventHandler eHandler = new EventHandler(this);
	public CollisionChecker ccheck= new CollisionChecker(this);
	Thread gameThread;
	
	public ArrayList<Projectile> projectileList= new ArrayList<>();
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
		player2.setDefaultValues();
	}
	
	public void setNPC() {
		npc_rival= new NPC_Rival(this);
	}
	
	public void setupGame() {
		playMusic(0);
		gameState=titleState;
		tempScreen= new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2=(Graphics2D)tempScreen.getGraphics();
		//setFullScreen();
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
				drawToTempScreen();
				drawToScreen();
				delta=0;
				drawCount++;
				
			}
			if(timer>=1000000000) {
				System.out.println("FPS:"+ FPS);
				drawCount=0;
				timer=0;
			}
		}
	}
	
	public void update() {
		
			
		if(gameState == playState) {
			
			
			if(gameMode==0) {
				npc_rival.update();
				player1.update0();
			}else {
				player2.update2();
				player1.update1();
			}
			
			for(int i=0;i<projectileList.size();i++) {
				projectileList.get(i).update();
				if(projectileList.get(i).alive==false){
					projectileList.remove(i);
				}
				
			}
			
		}
		else {
			
		}
	}
	
	public void setFullScreen() {
		//GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
		//GraphicsDevice gd= ge.getDefaultScreenDevice();
		//gd.setFullScreenWindow(Main.window);
		screenWidth2=Main.window.getWidth();
		screenHeight2=Main.window.getHeight();
	}
	
	public void drawToTempScreen() {
		if(gameState==titleState) {
			ui.draw(g2);
		}
		else {
			g2.setColor(Color.black);
			g2.fillRect(0,0,screenWidth2,screenHeight2);
			bG.draw(g2);
			player1.draw(g2);
			if(gameMode==0) {
				npc_rival.draw(g2);
				
			}else {
				player2.draw(g2);
			}
			
			for(int i=0;i<projectileList.size();i++) {
				projectileList.get(i).draw(g2);
			}
			
			ui.draw(g2);
		}
		
	}
	
	public void drawToScreen() {
		Graphics g= getGraphics();
		g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
		g.dispose();
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
