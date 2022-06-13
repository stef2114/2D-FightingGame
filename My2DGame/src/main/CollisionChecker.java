package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp=gp;
	}
	
	public boolean CheckCollision(Entity entity) {
		
		switch(entity.direction) {
		case "left":
			if(entity.x< 0+entity.speed) {
				return true;
			}
			break;
		case "right":
			if(entity.x+gp.tileSize>gp.screenWidth-entity.speed) {
				return true;
			}
			break;
		
		}
		return false;
	}
	
	public boolean CheckEntity(Entity player, Entity target) {
		switch(player.direction) {
		case "up":
			player.solidArea.y-=player.speed;
			if(player.solidArea.intersects(target.solidArea)){
				return true;
			}
			break;
		case "down":
			player.solidArea.y+=player.speed;
			if(player.solidArea.intersects(target.solidArea)){
				return true;
			}
			break;
		case "left":
			player.solidArea.x-=player.speed;
			if(player.solidArea.intersects(target.solidArea)){
				return true;
			}
			break;
		case "right":
			player.solidArea.x+=player.speed;
			if(player.solidArea.intersects(target.solidArea)){
				return true;
			}
			break;
		
		}
		return false;
	}
}
