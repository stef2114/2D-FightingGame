package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_Rival extends Entity{

	public NPC_Rival(GamePanel gp) {
		super(gp);
		direction = "down";
		speed=1;
		solidArea=new Rectangle(8,8,gp.tileSize-16,gp.tileSize-8);
		getNPCImage();
	}
	
	public void getNPCImage() {
		up1=setup("boy2_up_1");
		up2=setup("boy2_up_2");
		down1=setup("boy2_down_1");
		down2=setup("boy2_down_2");
		left1=setup("boy2_left_1");
		left2=setup("boy2_left_2");
		right1=setup("boy2_right_1");
		right2=setup("boy2_right_2");
			
	}
	
	public void setAction() {
		
		actionLockCounter++;
		if(actionLockCounter==120) {
			Random random= new Random();
			int i=random.nextInt(100)+1;
			if(i<=25) {
				direction="up";
			}
			if(i<=50 && i>25) {
				direction="down";
			}
			if(i<=75 && i>50) {
				direction="left";
			}	
			if(i>75) {
				direction="right";
			}
			actionLockCounter=0;
		}
		solidArea.x=x+8;
		solidArea.y=y+8;
		collisionOn=gp.ccheck.CheckCollision(this);
		if(collisionOn==false) {
			collisionOn=gp.ccheck.CheckEntity(this, gp.player1);
		}
	}
	
	public void update() {
		setAction();
		
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
		
	}
	
	public void draw(Graphics2D g2) {
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
		g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
		
	}

}
