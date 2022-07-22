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
	public String text;
	//int distance=gp.screenHeight2/6;	
	
	
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
		if(gp.gameState==gp.pauseState) {
			drawPausedScreen();
			
		}
	}
	
	public void drawPlayerLife() {
		int distance=gp.screenHeight2/18;
		int x=distance/2;
		int y=distance/2;
		int i=0;
		int n=gp.player1.hp;
		while(i<n/2) {
			g2.drawImage(heart_full,x,y,null);
			i+=10;
			x+=distance;
		}
		if(n%2!=0) {
			g2.drawImage(heart_half,x,y,null);
			i+=10;
			x+=distance;
		}
		while(i<gp.player1.maxHP/2) {
			g2.drawImage(heart_blank,x,y,null);
			i+=10;
			x+=distance;
		}
		
		
		x=gp.screenWidth-5*distance-distance/2;
		y=distance/2;
		i=0;
		if(gp.gameMode==0) {
			n=gp.npc_rival.hp;
		}else {
			n=gp.player2.hp;
		}
		while(i<n/2) {
			g2.drawImage(heart_full,x,y,null);
			i+=10;
			x+=distance;
		}
		if(n/10%2!=0) {
			g2.drawImage(heart_half,x,y,null);
			i+=10;
			x+=distance;
		}
		while(i<gp.player1.maxHP/2) {
			g2.drawImage(heart_blank,x,y,null);
			i+=10;
			x+=distance;
		}
	}
	
	
	
	public void drawPlayerStamina() {
		int distance=gp.screenHeight2/18;
		int x=distance/2;
		int y=distance*2;
		int i=0;
		int n=gp.player1.stamina;
		while(i<n/2) {
			g2.drawImage(stamina_full,x,y,null);
			i++;
			x+=distance;
		}
		if(n%2!=0) {
			g2.drawImage(stamina_half,x,y,null);
			i++;
			x+=distance;
		}
		while(i<gp.player1.maxHP/2) {
			g2.drawImage(stamina_blank,x,y,null);
			i++;
			x+=distance;
		}
		
		
		x=gp.screenWidth-5*distance-distance/2;
		y=distance*2;
		i=0;
		if(gp.gameMode==0) {
			n=gp.npc_rival.stamina;
		}else {
			n=gp.player2.stamina;
		}
		while(i<n/2) {
			g2.drawImage(stamina_full,x,y,null);
			i++;
			x+=distance;
		}
		if(n%2!=0) {
			g2.drawImage(stamina_half,x,y,null);
			i++;
			x+=distance;
		}
		while(i<gp.player1.maxHP/2) {
			g2.drawImage(stamina_blank,x,y,null);
			i++;
			x+=distance;
		}
	}
	
	public void titleScreen0() {
		int distance=gp.screenHeight2/16;
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		text="FIGTHING GAME";
		int x=getXforCenteredText(text);
		int y=distance*5;
		g2.setColor(Color.black);
		g2.drawString(text,x+5,y+5);
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		
		text="1 PLAYER";
		x=getXforCenteredText(text);
		y+=distance*3;
		g2.drawString(text,x,y);
		if(commandNumY==0) {
			g2.drawString(">",x-gp.tileSize,y);
		}
		
		text="2 PLAYERS";
		x=getXforCenteredText(text);
		y+=distance;
		g2.drawString(text,x,y);
		if(commandNumY==1) {
			g2.drawString(">",x-gp.tileSize,y);
		}
		
		text="OPTIONS";
		x=getXforCenteredText(text);
		y+=distance;
		g2.drawString(text,x,y);
		if(commandNumY==2) {
			g2.drawString(">",x-gp.tileSize,y);
		}
		
		text="QUIT";
		x=getXforCenteredText(text);
		y+=distance;
		g2.drawString(text,x,y);
		if(commandNumY==3) {
			g2.drawString(">",x-gp.tileSize,y);
		}
	}
	
	public void titleScreen3() {
		int distance=gp.screenHeight2/16;
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		text="BackGround:";
		int x=getXforCenteredText(text);
		int y=distance*2;
		g2.setColor(Color.black);
		g2.drawString(text,x+5,y+5);
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		
		
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
		int distance=gp.screenHeight2/16;
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		
		if(titleScreenState==1) {
			text="PLAYER 1 CHARACTER:";
		}
		else {
			text="PLAYER 2 CHARACTER:";
		}
		int x=getXforCenteredText(text);
		int y=distance*2;
		g2.setColor(Color.black);
		g2.drawString(text,x+5,y+5);
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		
		
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
				
			
		}
		else if(titleScreenState==4) {
			titleScreen4();
				
		}else if(titleScreenState==5) {
			titleScreen5();
				
		}else {
			titleScreen1and2();
		}
		
	}
	
	
	public void titleScreen4() {
		
		g2.setColor(Color.black);
		g2.fillRect(gp.screenWidth2/3,gp.screenHeight2/3,gp.screenWidth2/3,gp.screenHeight2/3);
		g2.setFont(g2.getFont().deriveFont(48F));
		int x=getXforCenteredText(text);
		int y=gp.screenWidth2/2;
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		y+=2*gp.tileSize;
		text="EXIT";
		x=getXforCenteredText(text);
		g2.drawString(text,x,y);
		
	}
	
	public void titleScreen5() {
		
		int distance=gp.screenHeight2/16;
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		text="OPTIONS";
		int x=getXforCenteredText(text);
		int y=distance*5;
		g2.setColor(Color.black);
		g2.drawString(text,x+5,y+5);
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		
		text="VIDEO";
		x=getXforCenteredText(text);
		y+=distance*3;
		g2.drawString(text,x,y);
		if(commandNumY==0) {
			g2.drawString(">",x-gp.tileSize,y);
		}
		text="SOUND";
		x=getXforCenteredText(text);
		y+=distance;
		g2.drawString(text,x,y);
		if(commandNumY==1) {
			g2.drawString(">",x-gp.tileSize,y);
		}
		
		text="MAIN MENU";
		x=getXforCenteredText(text);
		y+=distance;
		g2.drawString(text,x,y);
		if(commandNumY==2) {
			g2.drawString(">",x-gp.tileSize,y);
		}
	}
	
	
	public void drawPausedScreen() {
	
		
		g2.setColor(Color.black);
		g2.fillRect(gp.screenWidth2/3,gp.screenHeight2/3,gp.screenWidth2/3,gp.screenHeight2/3);
		g2.setFont(g2.getFont().deriveFont(48F));
		g2.setColor(Color.white);
		text="RESUME";
		int x=getXforCenteredText(text);
		g2.drawString(text,x,gp.screenHeight2/9*4);
		if(commandNumY==0) {
			g2.drawString(">",x-gp.tileSize,gp.screenHeight2/9*4);
		}
		text="EXIT";
		x=getXforCenteredText(text);
		g2.drawString(text,x,gp.screenHeight2/5*3);
		if(commandNumY==1) {
			g2.drawString(">",x-gp.tileSize,gp.screenHeight2/5*3);
		}
	}
	public int getXforCenteredText(String text) {
		int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x=(gp.screenWidth-length)/2;
		return x;
	}
	
	
	
	
}