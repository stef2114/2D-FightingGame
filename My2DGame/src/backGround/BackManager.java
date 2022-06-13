package backGround;

import java.awt.Dimension;
import java.io.IOException;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import main.*;

public class BackManager {
	
	GamePanel gp;
	public BackGr bg;
	public String name="BackGround3";
	
	public BackManager(GamePanel gp) {
		this.gp=gp;
		bg=new BackGr();
		getTileImage();
	}

	public void getTileImage() {
		setup(name);
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		try {
			bg.image=ImageIO.read(getClass().getResourceAsStream("/bGrounds/"+imageName+".jpg"));
			bg.image=uTool.scaleImage(bg.image,gp.screenWidth,gp.screenHeight/2);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return bg.image;
	}
	
	
	public void draw(Graphics2D g2) {
		g2.drawImage(bg.image,0,gp.screenHeight/4,null);
	}
}
