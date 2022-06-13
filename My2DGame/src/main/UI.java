package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import object.SuperObject;
import object.objHeart;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40=new Font("Arial",Font.PLAIN, 40);
	public boolean messageOn=false;
	public String message="";
	int messageCounter = 0;
	BufferedImage heart_full,heart_half,heart_blank;
	boolean gameFinished=false;
	public int commandNumY=0,commandNumX=0;
	public int titleScreenState=0;
	public int MaxX=1,MaxY=1;
	public String[] nameListPl = new String[2];
	public String[] nameListBg = new String[1];
	public BufferedImage image=null;
	int n=0;
	
	
	
	public UI(GamePanel gp) {
		this.gp=gp;
		nameListPl[0]="boy";
		nameListPl[1]="boy2";
		nameListBg[0]="BackGround3";
		getDimMax(nameListPl);
		SuperObject heart=new objHeart(gp);
		heart_full=heart.image1;
		heart_half=heart.image2;
		heart_blank=heart.image3;
	}
	
	public void getDimMax(String [] list) {
		n=list.length;
		for(;MaxY*MaxY<=n;MaxY++);
		MaxY--;
		MaxX=n/MaxY;
		
	}
	
	public void getNewN(int i) {
		n--;
		for(;i<n;i++) {
			nameListPl[i]=nameListPl[i+1];
		}
		for(;MaxY*MaxY<=n;MaxY++);
		MaxY--;
		MaxX=n/MaxY;
	}
	
	
	public void showMessage(String text) {
		message=text;
		messageOn=true;
	}
	
	public void getImagePl(String name) {
		
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/characters/"+name+"_down_1.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void getImageBg(String name) {
		
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/bGrounds/"+name+".jpg"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		this.g2=g2;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		g2.setColor(Color.white);
		if(gp.gameState==gp.titleState) {
			drawTitleScreen();
		}
		if(gp.gameState==gp.playState) {
			drawPlayerLife();
		}
		if(gp.gameState==gp.pauseState) {
			drawPausedScreen();
			drawPlayerLife();
		}
	}
	
	public void drawPlayerLife() {
		int x=gp.tileSize/2;
		int y=gp.tileSize/2;
		int i=0;
		int n=gp.player1.hp;
		while(i<n/2) {
			g2.drawImage(heart_full,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		if(n%2!=0) {
			g2.drawImage(heart_half,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		while(i<gp.player1.maxHP/2) {
			g2.drawImage(heart_blank,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		
		
		x=gp.screenWidth-5*gp.tileSize-gp.tileSize/2;
		y=gp.tileSize/2;
		i=0;
		if(gp.gameMode==0) {
			n=gp.npc_rival.hp;
		}else {
			n=gp.player2.hp;
		}
		while(i<n/2) {
			g2.drawImage(heart_full,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		if(n%2!=0) {
			g2.drawImage(heart_half,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		while(i<gp.player1.maxHP/2) {
			g2.drawImage(heart_blank,x,y,null);
			i++;
			x+=gp.tileSize;
		}
	}
	
	
	
	public void drawTitleScreen() {
		if(titleScreenState==0) {
			g2.setColor(new Color(70,120,80));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
			String text="FIGTHING GAME";
			int x=getXforCenteredText(text);
			int y=gp.tileSize*2;
			g2.setColor(Color.black);
			g2.drawString(text,x+5,y+5);
			g2.setColor(Color.white);
			g2.drawString(text,x,y);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
			
			text="1 PLAYER";
			x=getXforCenteredText(text);
			y+=gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNumY==0) {
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			text="2 PLAYERS";
			x=getXforCenteredText(text);
			y+=gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNumY==1) {
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			text="QUIT";
			x=getXforCenteredText(text);
			y+=gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNumY==2) {
				g2.drawString(">",x-gp.tileSize,y);
			}
		}else if(titleScreenState==3) {
			g2.setColor(new Color(70,120,80));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
			String text="BackGround:";
			int x=getXforCenteredText(text);
			int y=gp.tileSize*2;
			g2.setColor(Color.black);
			g2.drawString(text,x+5,y+5);
			
			g2.setColor(Color.white);
			int characterDinstanceX=(gp.screenWidth-MaxX*gp.tileSize*4)/MaxX/4;
			x=characterDinstanceX*(4-MaxX+1);
			int characterDinstanceY=0;
			if(MaxX*MaxY==n) {
				characterDinstanceY=(gp.screenHeight-y-gp.tileSize*2)/MaxY/4;
				y+=characterDinstanceX*(4-MaxY+1);
			}else {
				characterDinstanceY=(gp.screenHeight-y-gp.tileSize*2)/(MaxY+1)/4;
				y+=characterDinstanceX*(4-MaxY);
			}
			
			
			for(int i=0;i<MaxY;i++) {
				for(int j=0;j<MaxX;j++) {

					getImageBg(nameListBg[i*MaxX+j]);
					if(i==commandNumY && j==commandNumX) {
						g2.fillRect(x-2,y-2,gp.tileSize*4+4,gp.tileSize*2+4);
					}
					g2.drawImage(image,x,y,gp.tileSize*4,gp.tileSize*2,null);
					x+=gp.tileSize*4+characterDinstanceX;
				}
				x=characterDinstanceX*(4-MaxX+1);
				y+=gp.tileSize+characterDinstanceY;
			}
			for(int j=0;j<n-(MaxX*MaxY);j++) {

				getImageBg(nameListBg[(MaxY-1)*MaxX+j]);
				if(MaxY==commandNumY && j==commandNumX) {
					g2.fillRect(x-2,y-2,gp.tileSize*4+4,gp.tileSize*2+4);
				}
				g2.drawImage(image,x,y,gp.tileSize*4,gp.tileSize*2,null);
				x+=gp.tileSize*4+characterDinstanceX;
			}
				
			
		}else {
			g2.setColor(new Color(70,120,80));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
			String text="";
			if(titleScreenState==1) {
				text="PLAYER 1 CHARACTER:";
			}
			else {
				text="PLAYER 2 CHARACTER:";
			}
			int x=getXforCenteredText(text);
			int y=gp.tileSize*2;
			g2.setColor(Color.black);
			g2.drawString(text,x+5,y+5);
			
			g2.setColor(Color.white);
			int characterDinstanceX=(gp.screenWidth-MaxX*gp.tileSize*2)/MaxX/4;
			x=characterDinstanceX*(4-MaxX+1);
			int characterDinstanceY=0;
			if(MaxX*MaxY==n) {
				characterDinstanceY=(gp.screenHeight-y-gp.tileSize*2)/MaxY/4;
				y+=characterDinstanceX*(4-MaxY+1);
			}else {
				characterDinstanceY=(gp.screenHeight-y-gp.tileSize*2)/(MaxY+1)/4;
				y+=characterDinstanceX*(4-MaxY);
			}
			
			
			for(int i=0;i<MaxY;i++) {
				for(int j=0;j<MaxX;j++) {

					getImagePl(nameListPl[i*MaxX+j]);
					if(i==commandNumY && j==commandNumX) {
						g2.fillRect(x-2,y-2,gp.tileSize*2+4,gp.tileSize*2+4);
					}
					g2.drawImage(image,x,y,gp.tileSize*2,gp.tileSize*2,null);
					x+=gp.tileSize*2+characterDinstanceX;
				}
				x=characterDinstanceX*(4-MaxX+1);
				y+=gp.tileSize+characterDinstanceY;
			}
			for(int j=0;j<n-(MaxX*MaxY);j++) {

				getImagePl(nameListPl[(MaxY-1)*MaxX+j]);
				if(MaxY==commandNumY && j==commandNumX) {
					g2.fillRect(x-2,y-2,gp.tileSize*2+4,gp.tileSize*2+4);
				}
				g2.drawImage(image,x,y,gp.tileSize*2,gp.tileSize*2,null);
				x+=gp.tileSize*2+characterDinstanceX;
			}
		}
		
	}
	
	public void drawPausedScreen() {
		String text="PAUSED";
		int x=getXforCenteredText(text);
		int y=gp.screenHeight/2;
		g2.drawString(text,x,y);
	}
	public int getXforCenteredText(String text) {
		int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x=(gp.screenWidth-length)/2;
		return x;
	}
	
	
	
	
}