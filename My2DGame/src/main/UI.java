package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import object.objHeart;
import object.objStamina;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	BufferedImage heart_full,heart_half,heart_blank,stamina_full,stamina_half,stamina_blank;
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
		//nameListBg[1]="BackGround3";
		//nameListBg[2]="BackGround3";
		//nameListBg[3]="BackGround3";
		//nameListBg[4]="BackGround3";
		//nameListBg[5]="BackGround3";
		getDimMax(nameListPl);
		objHeart heart=new objHeart(gp);
		heart_full=heart.image1;
		heart_half=heart.image2;
		heart_blank=heart.image3;
		objStamina stamina= new objStamina(gp);
		stamina_full=stamina.image1;
		stamina_half=stamina.image3;
		stamina_blank=stamina.image3;
		
		
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
			drawPlayerStamina();
		}
		if(gp.gameState==gp.optionState) {
			drawPlayerLife();
			drawOptionScreen();
			
		}
		/*if(gp.gameState==gp.pauseState) {
			drawPausedScreen();
			drawPlayerLife();
		}*/
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
	
	
	
	public void drawPlayerStamina() {
		int x=gp.tileSize/2;
		int y=gp.tileSize*2;
		int i=0;
		int n=gp.player1.stamina;
		while(i<n/2) {
			g2.drawImage(stamina_full,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		if(n%2!=0) {
			g2.drawImage(stamina_half,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		while(i<gp.player1.maxHP/2) {
			g2.drawImage(stamina_blank,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		
		
		x=gp.screenWidth-5*gp.tileSize-gp.tileSize/2;
		y=gp.tileSize*2;
		i=0;
		if(gp.gameMode==0) {
			n=gp.npc_rival.stamina;
		}else {
			n=gp.player2.stamina;
		}
		while(i<n/2) {
			g2.drawImage(stamina_full,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		if(n%2!=0) {
			g2.drawImage(stamina_half,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		while(i<gp.player1.maxHP/2) {
			g2.drawImage(stamina_blank,x,y,null);
			i++;
			x+=gp.tileSize;
		}
	}
	
	public void titleScreen0() {
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text="FIGTHING GAME";
		int x=getXforCenteredText(text);
		int y=gp.tileSize*6;
		g2.setColor(Color.black);
		g2.drawString(text,x+5,y+5);
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		
		text="1 PLAYER";
		x=getXforCenteredText(text);
		y+=5*gp.tileSize;
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
	}
	
	public void titleScreen3() {
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		String text="BackGround:";
		int x=getXforCenteredText(text);
		int y=gp.tileSize*2;
		g2.setColor(Color.black);
		g2.drawString(text,x+5,y+5);
		
		g2.setColor(Color.white);
		
		int aux=0;
		int bgDinstanceY=0;
		int LengthY=0;
		if(MaxX*MaxY==n) {
			LengthY=(gp.screenHeight-y)/(MaxY+1);
			aux=LengthY/gp.tileSize;
			bgDinstanceY=(2*LengthY-gp.tileSize*aux)/(MaxY+3);
			LengthY=gp.tileSize*aux;
			y+=bgDinstanceY*2;
		}else {
			LengthY=(gp.screenHeight-y)/(MaxY+2);
			aux=LengthY/gp.tileSize;
			bgDinstanceY=(2*LengthY-gp.tileSize*aux)/(MaxY+3);
			LengthY=gp.tileSize*aux;
			y+=bgDinstanceY*2;
		}
		
		int LengthX=gp.screenWidth/(MaxX+1);
		int bgDinstanceX=2*(LengthX-LengthY)/(MaxX+3);
		LengthX=2*LengthY;
		x=bgDinstanceX*2;
		
		
		for(int i=0;i<MaxY;i++) {
			for(int j=0;j<MaxX;j++) {

				getImageBg(nameListBg[i*MaxX+j]);
				if(i==commandNumY && j==commandNumX) {
					g2.fillRect(x-(LengthX/20),y-(LengthY/20),LengthX+(LengthX/10),LengthY+(LengthY/10));
				}
				g2.drawImage(image,x,y,LengthX,LengthY,null);
				x+=LengthX+bgDinstanceX;
			}
			x=bgDinstanceX*2;
			y+=LengthY+bgDinstanceY;
		}
		for(int j=0;j<n-(MaxX*MaxY);j++) {

			getImageBg(nameListBg[(MaxY-1)*MaxX+j]);
			if(MaxY==commandNumY && j==commandNumX) {
				g2.fillRect(x-(LengthX/20),y-(LengthY/20),LengthX+(LengthX/10),LengthY+(LengthY/10));
			}
			g2.drawImage(image,x,y,LengthX,LengthY,null);
			x+=LengthX+bgDinstanceX;
		}
	}
	
	public void titleScreen1and2() {
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		
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
		
		int aux=0;
		int bgDinstanceY=0;
		int LengthY=0;
		if(MaxX*MaxY==n) {
			LengthY=(gp.screenHeight-y)/(MaxY+1);
			aux=LengthY/gp.tileSize;
			bgDinstanceY=(2*LengthY-gp.tileSize*aux)/(MaxY+3);
			LengthY=gp.tileSize*aux;
			y+=bgDinstanceY*2;
		}else {
			LengthY=(gp.screenHeight-y)/(MaxY+2);
			aux=LengthY/gp.tileSize;
			bgDinstanceY=(2*LengthY-gp.tileSize*aux)/(MaxY+3);
			LengthY=gp.tileSize*aux;
			y+=bgDinstanceY*2;
		}
		
		
		int LengthX=gp.screenWidth/(MaxX+1);
		int bgDinstanceX=(2*LengthX-LengthY)/(MaxX+3);
		LengthX=LengthY;
		x=bgDinstanceX*2;
		
		
		for(int i=0;i<MaxY;i++) {
			for(int j=0;j<MaxX;j++) {

				getImagePl(nameListPl[i*MaxX+j]);
				if(i==commandNumY && j==commandNumX) {
					g2.fillRect(x-(LengthX/20),y-(LengthY/20),LengthX+(LengthX/10),LengthY+(LengthY/10));
				}
				g2.drawImage(image,x,y,LengthX,LengthY,null);
				x+=LengthX+bgDinstanceX;
			}
			x=bgDinstanceX*2;
			y+=LengthY+bgDinstanceY;
		}
		for(int j=0;j<n-(MaxX*MaxY);j++) {

			getImagePl(nameListPl[(MaxY-1)*MaxX+j]);
			if(MaxY==commandNumY && j==commandNumX) {
				g2.fillRect(x-(LengthX/20),y-(LengthY/20),LengthX+(LengthX/10),LengthY+(LengthY/10));
			}
			g2.drawImage(image,x,y,LengthX,LengthY,null);
			x+=LengthX+bgDinstanceX;
		}
	}
	
	public void drawTitleScreen() {
		if(titleScreenState==0) {
			titleScreen0();
		}
		
		
		else if(titleScreenState==3) {
			titleScreen3();
				
			
		}else {
			titleScreen1and2();
		}
		
	}
	
	public void drawOptionScreen() {
		g2.setColor(Color.black);
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(32F));
		int frameX=gp.screenWidth2/4;
		int frameY=gp.screenHeight2/4;
		int frameWidth=frameX*2;
		int frameHeight=frameY*2;
		g2.fillRect(frameX,frameY,frameWidth,frameHeight);
		g2.setColor(Color.white);
		String text="";
		text="OPTIONS";
		int x=getXforCenteredText(text);
		g2.drawString(text,gp.screenWidth2/2,gp.screenHeight2/2);
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