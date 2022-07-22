package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class Projectile {

	String name="fireball",direction;
	GamePanel gp;
	int speed,x,y;
	public boolean alive=false;
	BufferedImage left1,left2,right1,right2;
	Entity user;
	public Rectangle solidArea;
	int spriteCounter=0,spriteNum;
	public boolean collisionOn=false;
	
	public Projectile(String name,GamePanel gp) {
		//this.name=name;
		this.gp=gp;
		speed=gp.screenWidth2/384;//
		speed=5;
		solidArea=new Rectangle(6,2,gp.tileSize-12,gp.tileSize);
		getImage();
		
	}
	
	public void set(int x, int y, String direction, Entity user) {
		alive=true;
		this.x=x;
		this.y=y;
		this.direction=direction;
		this.user=user;
	}
	
	public void update() {
		switch(direction) {
		case "left": x-=speed;break;
		case "right": x+=speed;break;
			
		}
		solidArea.x=x+6;
		
		if(user==gp.player1) {
			if(gp.gameMode==0) {
				collisionOn=CheckEntity(gp.npc_rival);
				if(collisionOn==true) {
					if(gp.npc_rival.blocking==true) {
						gp.npc_rival.hp-=5;
					}else {
						gp.npc_rival.hp-=10;
					}
				}
			}else {
				collisionOn=CheckEntity(gp.player2);
				if(collisionOn==true) {
					if(gp.player2.blocking==true) {
						gp.player2.hp-=5;
					}else {
						gp.player2.hp-=10;
					}
				}
			}
				
		}else {
			collisionOn=CheckEntity(gp.player1);
			if(collisionOn==true) {
				if(gp.player1.blocking==true) {
					gp.player1.hp-=5;
				}else {
					gp.player1.hp-=10;
				}
			}
		}
		if(collisionOn==false) {
			collisionOn=CheckCollision();
		}
		if(collisionOn==true) {
			alive=false;
		}
		
		
		spriteCounter++;
		if(spriteCounter>12) {
			if(spriteNum==1) {
				spriteNum=2;
			}
			else if(spriteNum==2) {
				spriteNum=1;
			}
			spriteCounter=0;
		}
	}
	
	public void getImage() {
		left1=setup(name+"_left_1",gp.tileSize,gp.tileSize);
		left2=setup(name+"_left_2",gp.tileSize,gp.tileSize);
		right1=setup(name+"_right_1",gp.tileSize,gp.tileSize);
		right2=setup(name+"_right_2",gp.tileSize,gp.tileSize);
	}
	
	
	public BufferedImage setup(String imageName, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image =null;
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/attacks/"+imageName+".png"));
			image=uTool.scaleImage(image,width,height);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public boolean CheckCollision() {
		
		switch(direction) {
		case "left":
			if(x< speed) {
				return true;
			}
			break;
		case "right":
			if(x+gp.tileSize>gp.screenWidth-speed) {
				return true;
			}
			break;
		
		}
		return false;
	}
	
	public boolean CheckEntity(Entity user) {
		switch(direction) {
		case "left":
			if(user.solidArea.intersects(solidArea)){
				return true;
			}
			break;
		case "right":
			if(user.solidArea.intersects(solidArea)){
				return true;
			}
			break;
		
		}
		return false;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image=null;
		switch(direction) {
		case"left":
			if(spriteNum==1) {image=left1;}
			if(spriteNum==2) {image=left2;}
			break;
		case"right":
			if(spriteNum==1) {image=right1;}
			if(spriteNum==2) {image=right2;}
		break;
		}
		g2.drawImage(image,0,gp.screenHeight/4,null);
	}
	
	
}
