package entity;

import java.awt.image.*;
import java.io.IOException;
import java.awt.*;


import javax.imageio.ImageIO;

import main.CollisionChecker;
import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public int x,y;
	public int speed;
	public final int maxHP=10,maxSTAMINA=10;
	public int hp,stamina;
	public BufferedImage left1,left2,up1,up2,down1,down2,right1,right2;
	public String direction="";
	public boolean collisionOn=false;
	public int actionLockCounter=0;
	public Rectangle solidArea;
	public int spriteCounter=0;
	public int spriteNum=1;
	public String name="boy";
	
	public Entity(GamePanel gp) {
		this.gp=gp;
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image =null;
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/characters/"+imageName+".png"));
			image=uTool.scaleImage(image,gp.tileSize,gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
