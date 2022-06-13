package entity;

import java.io.IOException;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import main.*;

public class Player extends Entity{

	KeyHandler keyH;
	int standCounter=0;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH=keyH;
		solidArea=new Rectangle(8,8,gp.tileSize-16,gp.tileSize-8);
		setDefaultValues();
		getPlayerImage();
		
	}
	
	
	public void setDefaultValues() {
		x=gp.tileSize*2;
		y=gp.tileSize*7;
		speed=4;
		hp=10;
		stamina=10;
		direction="down";
	}
	
	public void getPlayerImage() {
		up1=setup(name+"_up_1");
		up2=setup(name+"_up_1");
		down1=setup(name+"_down_1");
		down2=setup(name+"_down_2");
		left1=setup(name+"_left_1");
		left2=setup(name+"_left_2");
		right1=setup(name+"_right_1");
		right2=setup(name+"_right_2");
			
	}
	
	
	public void update1() {
		
		if(hp<=0) {
			gp.ui.showMessage("Player dead");
		}else {
			
			
			if(keyH.up1Pressed==true || keyH.down1Pressed==true 
				|| keyH.left1Pressed==true || keyH.right1Pressed==true) {
				if(keyH.up1Pressed==true) {
					direction="up";
				}
				else if(keyH.down1Pressed==true) {
					direction="down";
				}
				else if(keyH.left1Pressed==true) {
					direction="left";
				}
				else if(keyH.right1Pressed==true) {
					direction="right";
				}
				
				solidArea.x=x+8;
				solidArea.y=y+8;
				collisionOn=gp.ccheck.CheckCollision(this);
				
				if(collisionOn==false) {
					if(gp.gameMode==0) {
						collisionOn=gp.ccheck.CheckEntity(this, gp.npc_rival);
					}else {
						collisionOn=gp.ccheck.CheckEntity(this, gp.player2);
					}
					
				}
				
				gp.eHandler.checkEvent();
				
				if(collisionOn==false) {
					switch(direction) {
					case "up": y-=speed;break;
					case "down": y+=speed;break;
					case "left": x-=speed;break;
					case "right": x+=speed;break;
					}
				}
			
				spriteCounter++;
				if(spriteCounter>8) {
					if(spriteNum==1) {
						spriteNum=2;
					}
					else if(spriteNum==2) {
						spriteNum=1;
					}
					spriteCounter=0;
				}
			}else {
				standCounter++;
				if(standCounter>20) {
					standCounter=0;
					spriteNum=1;
				}
			}
		}
		
		
	}
	
	
public void update2() {
		
		if(hp<=0) {
			gp.ui.showMessage("Player dead");
		}else {
			
			
			if(keyH.up2Pressed==true || keyH.down2Pressed==true 
					|| keyH.left2Pressed==true || keyH.right2Pressed==true) {
					if(keyH.up2Pressed==true) {
						direction="up";
					}
					else if(keyH.down2Pressed==true) {
						direction="down";
					}
					else if(keyH.left2Pressed==true) {
						direction="left";
					}
					else if(keyH.right2Pressed==true) {
						direction="right";
					}
					
					solidArea.x=x+8;
					solidArea.y=y+8;
					collisionOn=gp.ccheck.CheckCollision(this);
					
					if(collisionOn==false) {
						if(gp.gameMode==0) {
							collisionOn=gp.ccheck.CheckEntity(this, gp.npc_rival);
						}else {
							collisionOn=gp.ccheck.CheckEntity(this, gp.player1);
						}
						
					}
					
					if(collisionOn==false) {
						switch(direction) {
						case "up": y-=speed;break;
						case "down": y+=speed;break;
						case "left": x-=speed;break;
						case "right": x+=speed;break;
						}
					}
				
					spriteCounter++;
					if(spriteCounter>8) {
						if(spriteNum==1) {
							spriteNum=2;
						}
						else if(spriteNum==2) {
							spriteNum=1;
						}
						spriteCounter=0;
					}
				}else {
					standCounter++;
					if(standCounter>20) {
						standCounter=0;
						spriteNum=1;
					}
				}
		}
		
		
	}
	
	
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.white);
		//g2.fillRect(x,y,gp.tileSize,gp.tileSize);
		BufferedImage image=null;
		switch(direction) {
		case"up":
			if(spriteNum==1) {
				image=up1;
			}
			if(spriteNum==2) {
				image=up2;
			}
			break;
		case"down":
			if(spriteNum==1) {
				image=down1;
			}
			if(spriteNum==2) {
				image=down2;
			}
			break;
		case"left":
			if(spriteNum==1) {
				image=left1;
			}
			if(spriteNum==2) {
				image=left2;
			}
			break;
		case"right":
			if(spriteNum==1) {
				image=right1;
			}
			if(spriteNum==2) {
				image=right2;
			}
			break;
		}
		g2.drawImage(image,x,y,null);
		
		
	}
}
