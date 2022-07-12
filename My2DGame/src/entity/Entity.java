package entity;

import java.awt.image.*;
import java.io.IOException;
import java.awt.*;


import javax.imageio.ImageIO;

import main.CollisionChecker;
import main.GamePanel;
import main.UtilityTool;
import object.Projectile;

public class Entity {

	GamePanel gp;
	public int x,y;
	public int speed;
	public final int maxHP=10,maxSTAMINA=10;
	public int hp,stamina,staminaRecover=0;
	public BufferedImage left1,left2,jumpLeft,jumpRight,fallLeft,fallRight,crouchLeft,crouchRight,right1,right2,
	lightAttackRight1,lightAttackRight2,lightAttackLeft1,lightAttackLeft2,heavyAttackRight1,heavyAttackRight2,
	heavyAttackRight3,heavyAttackLeft1,heavyAttackLeft2,heavyAttackLeft3,blockLeft,blockRight;
	public String direction="right";
	public String projectileName="";
	public boolean collisionOn=false;
	int standCounter=0;
	public Rectangle solidArea;
	public Rectangle attackArea;
	public int spriteCounter=0;
	public int spriteNum=1;
	public String name="";
	public boolean lightAttacking=false;
	public boolean heavyAttacking=false;
	public boolean attackOn=false;
	public boolean blocking=false;
	public boolean invincible=false;
	public Projectile projectile;
	public boolean relesaseRangeAttack=false;
	public boolean jump=false;
	public String jumpDirection="";
	public boolean crouch=false;
	
	
	public Entity(GamePanel gp) {
		this.gp=gp;
		y=gp.screenHeight2/5*3;
		x=gp.screenWidth2/8;
		solidArea=new Rectangle(6,2,gp.tileSize-12,gp.tileSize);
		attackArea=new Rectangle(0,12,gp.tileSize,gp.tileSize-12);
		hp=maxHP;
		stamina=maxSTAMINA;
		speed=gp.screenWidth/480;
	}
	
	public BufferedImage setup(String imageName, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image =null;
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/characters/"+imageName+".png"));
			image=uTool.scaleImage(image,width,height);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
