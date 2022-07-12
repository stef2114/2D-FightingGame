package object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class objStamina {

	GamePanel gp;
	public BufferedImage image1,image2,image3;
	public String name="";
	UtilityTool uTool = new UtilityTool();
	public objStamina(GamePanel gp) {
		this.gp=gp;	
		name="StaminaCrystal";
		try{
			image1=ImageIO.read(getClass().getResourceAsStream("/objects/manacrystal_full.png"));
			//image2=ImageIO.read(getClass().getResourceAsStream("/objects/manacrystal_half.png"));
			image3=ImageIO.read(getClass().getResourceAsStream("/objects/manacrystal_blank.png"));
			image1=uTool.scaleImage(image1,gp.tileSize,gp.tileSize);
			//image2=uTool.scaleImage(image2,gp.tileSize,gp.tileSize);
			image3=uTool.scaleImage(image3,gp.tileSize,gp.tileSize);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
