package main;

import java.awt.image.*;
import java.io.IOException;
import java.awt.*;


import javax.imageio.ImageIO;

import main.CollisionChecker;
import main.GamePanel;
import main.UtilityTool;


public class EventHandler {

	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	public EventHandler(GamePanel gp) {
		this.gp=gp;
		eventRect=new Rectangle(23,23,2,2);
		eventRectDefaultX=eventRect.x;
		eventRectDefaultY=eventRect.y;
	}
	
	public void checkEvent() {
		if(hit(4,7,"right")==true) {
			damagePit(gp.pauseState);
		}
	}
	public boolean hit(int eventCol,int eventRow, String reqDirection) {
		
		eventRect.x=eventCol*gp.tileSize+eventRect.x;
		eventRect.y=eventRow*gp.tileSize+eventRect.y;
		
		boolean hit=false;
		if(gp.player1.solidArea.intersects(eventRect)) {
			if(gp.player1.direction.contentEquals(reqDirection)||
					reqDirection.contentEquals("any")) {
				hit=true;
			}
		}
		
		eventRect.x=eventRectDefaultX;
		eventRect.y=eventRectDefaultY;
		return hit;
	}
	
	public void damagePit(int gameState) {
		gp.gameState=gameState;
		gp.player1.hp-=1;
	}
}
