package entity;

import java.io.IOException;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import main.*;
import object.Projectile;

public class Player extends Entity{

	KeyHandler keyH;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH=keyH;
	}
	
	
	public void setDefaultValues() {
		x=gp.tileSize*14;
		direction="left";
		name="";
	}
	
	public void getPlayerImage() {
		int imgSize=gp.tileSize;
		
		
		//jumpLeft=setup(name+"_jump_left",imgSize,imgSize);
		jumpLeft=setup(name+"_left_2",imgSize,imgSize);
		//jumpRight=setup(name+"_jump_right",imgSize,imgSize);
		jumpRight=setup(name+"_right_2",imgSize,imgSize);
		//fallLeft=setup(name+"_fall_left",imgSize,imgSize);
		fallLeft=setup(name+"_left_2",imgSize,imgSize);
		//fallRight=setup(name+"_fall_right",imgSize,imgSize);
		fallRight=setup(name+"_right_2",imgSize,imgSize);
		//crouchLeft=setup(name+"_crouch_left",imgSize,imgSize);
		crouchLeft=setup(name+"_down_1",imgSize,imgSize);
		//crouchRight=setup(name+"_crouch_right",imgSize,imgSize);
		crouchRight=setup(name+"_down_2",imgSize,imgSize);
		left1=setup(name+"_left_1",imgSize,imgSize);
		left2=setup(name+"_left_2",imgSize,imgSize);
		right1=setup(name+"_right_1",imgSize,imgSize);
		right2=setup(name+"_right_2",imgSize,imgSize);
		//lightAttackRight1=setup(name+"_light_attack_right_1",2*imgSize,imgSize);
		lightAttackRight1=setup("boy_attack_right_1",2*imgSize,imgSize);
		//lightAttackRight2=setup(name+"_light_attack_right_2",2*imgSize,imgSize);
		lightAttackRight2=setup("boy_attack_right_2",2*imgSize,imgSize);
		//lightAttackLeft1=setup(name+"_light_attack_left_1",2*imgSize,imgSize);
		lightAttackLeft1=setup("boy_attack_left_1",2*imgSize,imgSize);
		//lightAttackLeft2=setup(name+"_light_attack_left_2",2*imgSize,imgSize);
		lightAttackLeft2=setup("boy_attack_left_2",2*imgSize,imgSize);
		//heavyAttackRight1=setup(name+"_heavy_attack_right_1",2*imgSize,imgSize);
		heavyAttackRight1=setup("boy_attack_right_1",2*imgSize,imgSize);
		//heavyAttackRight2=setup(name+"_heavy_attack_right_2",2*imgSize,imgSize);
		heavyAttackRight2=setup("boy_attack_right_2",2*imgSize,imgSize);
		//heavyAttackRight3=setup(name+"_heavy_attack_right_3",2*imgSize,2*imgSize);
		heavyAttackRight3=setup("boy_attack_right_2",2*imgSize,imgSize);
		//heavyAttackLeft1=setup(name+"_heavy_attack_left_1",2*imgSize,imgSize);
		heavyAttackLeft1=setup("boy_attack_left_1",2*imgSize,imgSize);
		//heavyAttackLeft2=setup(name+"_heavy_attack_left_2",2*imgSize,imgSize);
		heavyAttackLeft2=setup("boy_attack_left_2",2*imgSize,imgSize);
		//heavyAttackLeft3=setup(name+"_heavy_attack_left_3",2*imgSize,2*imgSize);
		heavyAttackLeft3=setup("boy_attack_left_2",2*imgSize,imgSize);
		//blockLeft=setup(name+"_block_left",imgSize,imgSize);
		blockLeft=setup(name+"_right_2",imgSize,imgSize);
		//blockRight=setup(name+"_block_right",imgSize,imgSize);
		blockRight=setup(name+"_left_2",imgSize,imgSize);
		
			
	}
	
	
	public void update1() {
		
		if(jump==false && y>gp.screenHeight2/5*3) {
			if(gp.gameMode==0) {
				if(y-gp.tileSize>gp.npc_rival.y) {
					collisionOn=gp.ccheck.CheckEntity(this, gp.npc_rival);
				}
			}else {
				if(y-gp.tileSize>gp.player2.y) {
					collisionOn=gp.ccheck.CheckEntity(this, gp.player2);
				}
			}
			if(collisionOn==false ) {
				y-=speed;
			}
		}
		else if(hp<=0) {
			//gp.ui.showMessage("Player dead");
		}else if(jump==true) {
			if(jumpDirection=="") {
				jump11Action();
			}else {
				jump12Action();
			}					
	
		}else if(crouch==true){
			crouchAction();
		}else if(lightAttacking==true) {
			lightAttack1();
		}else if(heavyAttacking==true) {
			heavyAttack1();
		}else {
			bundingAction1();
					
		}
		
	}
	public void lightAttack1() {
		spriteCounter++;
		if(spriteCounter<=5) {
			spriteNum=1;
		}else if(spriteCounter<=25) {
			spriteNum=2;
			attackArea.y=y+12;
			if(direction=="left") {
				attackArea.x=x-gp.tileSize;
			}else {
				attackArea.x=x+gp.tileSize;
			}
			if(gp.gameMode==0) {
				attackOn=gp.ccheck.CheckAttack(this,gp.npc_rival);
				if(attackOn==true && gp.npc_rival.invincible==false) {
					gp.npc_rival.hp-=1;
					gp.npc_rival.invincible=true;
				}
			}else {
				attackOn=gp.ccheck.CheckAttack(this,gp.player2);
				if(attackOn==true && gp.player2.invincible==false) {
					gp.player2.hp-=1;
					gp.player2.invincible=true;
				}
			}
				
				
		}else if(spriteCounter<=30){
			spriteNum=1;
				
		}else {
			spriteCounter=0;
			lightAttacking=false;
			stamina--;
			if(gp.gameMode==0) {
				gp.npc_rival.invincible=false;
			}
			else {
				gp.player2.invincible=false;
			}
				
		}
	}
	
	
	public void heavyAttack1() {
		spriteCounter++;
		if(spriteCounter<=20) {
			spriteNum=1;
		}if(spriteCounter<=40) {
			spriteNum=2;	
		}else if(spriteCounter<=60) {
			spriteNum=3;
			attackArea.y=y+12;
			if(direction=="left") {
				attackArea.x=x-gp.tileSize;
			}else {
				attackArea.x=x+gp.tileSize;
			}
			if(gp.gameMode==0) {
				attackOn=gp.ccheck.CheckAttack(this,gp.npc_rival);
				if(attackOn==true && gp.npc_rival.invincible==false) {
					gp.npc_rival.hp-=2;
					gp.npc_rival.invincible=true;
				}
			}else {
				attackOn=gp.ccheck.CheckAttack(this,gp.player2);
				if(attackOn==true && gp.player2.invincible==false) {
					gp.player2.hp-=2;
					gp.player2.invincible=true;
				}
			}
				
				
		}else if(spriteCounter<=70){
			spriteNum=1;
				
		}else {
			spriteCounter=0;
			heavyAttacking=false;
			stamina-=2;
			if(gp.gameMode==0) {
				gp.npc_rival.invincible=false;
			}
			else {
				gp.player2.invincible=false;
			}
				
		}
	}
	
	public boolean check1Landing() {
		if(y>gp.screenHeight2/5*3) {
			if(gp.gameMode==0) {
				if(gp.ccheck.CheckEntity(this, gp.npc_rival)==true) {
					if(y>gp.npc_rival.y) {
						return true;
					}
				}
				return false;
				
			}else {
				if(gp.ccheck.CheckEntity(this, gp.player2)==true) {
					if(y>gp.player2.y) {
						return true;
					}
				}
				return false;
			}
		}
		return true;
	}
	
	
	public void jump11Action() {
		
		spriteCounter++;
		if(gp.gameMode==0) {
			collisionOn=gp.ccheck.CheckEntity(this, gp.npc_rival);
		}else {
			collisionOn=gp.ccheck.CheckEntity(this, gp.player2);
		}
	
		if(collisionOn==false) {
			if(spriteCounter<=20) {
				y-=speed;
			}else if(spriteCounter<40) {
				y+=speed;
			}else {
				jump=false;
				jumpDirection="";
				spriteCounter=0;
			}
		}
	}
	
	public void jump12Action() {
		spriteCounter++;
		if(gp.gameMode==0) {
			collisionOn=gp.ccheck.CheckEntity(this, gp.npc_rival);
		}else {
			collisionOn=gp.ccheck.CheckEntity(this, gp.player2);
		}
	
		if(collisionOn==false && jumpDirection=="left") {
			collisionOn=gp.ccheck.CheckCollision(this);
			if(spriteCounter<=20) {
				y-=speed;
				if(collisionOn==false) {
					x-=speed;
				}
				
			}else if(spriteCounter<40) {
				y+=speed;
				if(collisionOn==false) {
					x-=speed;
				}
			}else {
				jump=false;
				jumpDirection="";
				spriteCounter=0;
			}
		}
		
		if(collisionOn==false && jumpDirection=="right") {
			if(spriteCounter<=20) {
				y-=speed;
				x+=speed;
			}else if(spriteCounter<40) {
				y+=speed;
				x+=speed;
			}else {
				jump=false;
				jumpDirection="";
				spriteCounter=0;
			}
		}
	}
	
	public void crouchAction() {
		spriteCounter++;
		if(spriteCounter==20) {
			solidArea.y-=gp.tileSize/2;
			crouch=false;
			spriteCounter=0;
		}
	}
	
	public void bundingAction1(){
		if(keyH.jump1Pressed==true || keyH.crouch1Pressed==true || keyH.left1Pressed==true ||
				keyH.right1Pressed==true || keyH.lightAttack1==true|| keyH.heavyAttack1==true|| keyH.block1==true) {
			if(keyH.jump1Pressed==true) {
				jump=true;
				spriteCounter=0;
				if(keyH.left1Pressed==true) {
					jumpDirection="left";
				}else if(keyH.right1Pressed==true) {
					jumpDirection="right";
				}
				
			}else if(keyH.lightAttack1==true) {
				lightAttacking=true;
				spriteCounter=0;
			}else if(keyH.heavyAttack1==true) {
				heavyAttacking=true;
				spriteCounter=0;
			}else if(keyH.block1==true) {
				blocking=true;
				spriteCounter=0;
			}else if(keyH.crouch1Pressed==true) {
				crouch=true;
				solidArea.y+=gp.tileSize/2;
				solidArea.x=x+6;// dc am nevoie si de el aici
				spriteCounter=0;
			}
			else if(keyH.left1Pressed==true) {
				direction="left";
			}
			else if(keyH.right1Pressed==true) {
				direction="right";
			}
		
			collisionOn=gp.ccheck.CheckCollision(this);
		
			if(collisionOn==false) {
				if(gp.gameMode==0) {
					collisionOn=gp.ccheck.CheckEntity(this, gp.npc_rival);
				}else {
					collisionOn=gp.ccheck.CheckEntity(this, gp.player2);
				}
			
			}
	
		
			if(collisionOn==false && jump==false && crouch==false) {
				switch(direction) {
				case "left": x-=speed;break;
				case "right": x+=speed;break;
				}
				solidArea.x=x+6;
				solidArea.y=y+2;
			}
			//gp.eHandler.checkEvent();
	
			spriteCounter++;
			if(stamina<10) {
				staminaRecover++;
			}
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
			if(stamina<10) {
				staminaRecover++;
			}
			if(standCounter>20) {
				standCounter=0;
				spriteNum=1;
			}
		}
		if(staminaRecover>240) {
			stamina++;
			staminaRecover=0;
		}
		
		
		if(keyH.shot1KeyPressed==true && stamina>0) {
			projectile=new Projectile(projectileName, gp);
			projectile.set(x,y, direction,this);
			gp.projectileList.add(projectile);
			gp.playSE(1);
			stamina--;
			staminaRecover=0;
			keyH.shot1KeyPressed=false;
		}
	}
	
	
	public void update2() {
		
		if(jump==false) {
			if(y-gp.tileSize>gp.player1.y) {
				collisionOn=gp.ccheck.CheckEntity(this, gp.player1);
			}
			if(collisionOn==false && y>gp.screenHeight2/5*3) {
				y-=speed;
			}
		}
		if(hp<=0) {
			//gp.ui.showMessage("Player dead");
		}else if(jump==true) {
			if(jumpDirection=="") {
				jump21Action();
			}else {
				jump22Action();
			}					
	
		}else if(crouch==true){
			crouchAction();
		}else if(lightAttacking==true) {
			lightAttack2();
		}else if(heavyAttacking==true) {
			heavyAttack2();
		}else {
			bundingAction2();
					
		}
		
		
	}
	
	public void lightAttack2() {
		spriteCounter++;
		if(spriteCounter<=5) {
			spriteNum=1;
		}else if(spriteCounter<=25) {
			spriteNum=2;
			attackArea.y=y+12;
			if(direction=="left") {
				attackArea.x=x-gp.tileSize;
			}else {
				attackArea.x=x+gp.tileSize;
			}
			attackOn=gp.ccheck.CheckAttack(this,gp.player1);
			if(attackOn==true && gp.player1.invincible==false) {
				gp.player1.hp-=1;
				gp.player1.invincible=true;
			}
				
				
		}else if(spriteCounter<=30){
			spriteNum=1;
				
		}else {
			spriteCounter=0;
			lightAttacking=false;
			stamina--;
			gp.player1.invincible=false;
				
		}
	}
	
	public void jump21Action() {
		
		spriteCounter++;
		collisionOn=gp.ccheck.CheckEntity(this, gp.player1);
	
		if(collisionOn==false) {
			if(spriteCounter<=20) {
				y-=speed;
			}else if(spriteCounter<40) {
				y+=speed;
			}else {
				jump=false;
				jumpDirection="";
				spriteCounter=0;
			}
		}
	}
	
	public void jump22Action() {
		spriteCounter++;
		collisionOn=gp.ccheck.CheckEntity(this, gp.player1);
	
		if(collisionOn==false && jumpDirection=="left") {
			if(spriteCounter<=20) {
				y-=speed;
				x-=speed;
			}else if(spriteCounter<40) {
				y+=speed;
				x-=speed;
			}else {
				jump=false;
				jumpDirection="";
				spriteCounter=0;
			}
		}
		
		if(collisionOn==false && jumpDirection=="right") {
			if(spriteCounter<=20) {
				y-=speed;
				x+=speed;
			}else if(spriteCounter<40) {
				y+=speed;
				x+=speed;
			}else {
				jump=false;
				jumpDirection="";
				spriteCounter=0;
			}
		}
	}
	
	
	public boolean check2Landing() {
		if(y>gp.screenHeight2/5*3) {
			
			if(gp.ccheck.CheckEntity(this, gp.player2)==true) {
				if(y>gp.player2.y) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	
	
	public void heavyAttack2() {
		spriteCounter++;
		if(spriteCounter<=20) {
			spriteNum=1;
		}if(spriteCounter<=40) {
			spriteNum=2;	
		}else if(spriteCounter<=60) {
			spriteNum=3;
			attackArea.y=y+12;
			if(direction=="left") {
				attackArea.x=x-gp.tileSize;
			}else {
				attackArea.x=x+gp.tileSize;
			}
			attackOn=gp.ccheck.CheckAttack(this,gp.player1);
			if(attackOn==true && gp.player1.invincible==false) {
				gp.player1.hp-=2;
				gp.player1.invincible=true;
			}	
				
		}else if(spriteCounter<=70){
			spriteNum=1;
				
		}else {
			spriteCounter=0;
			heavyAttacking=false;
			stamina-=2;
			gp.player1.invincible=false;
				
		}
	}
	
	
	public void bundingAction2(){
		if(keyH.jump2Pressed==true || keyH.crouch2Pressed==true || keyH.left2Pressed==true ||
				keyH.right2Pressed==true || keyH.lightAttack2==true|| keyH.heavyAttack2==true|| keyH.block2==true) {
			if(keyH.jump2Pressed==true) {
				jump=true;
				spriteCounter=0;
				if(keyH.left1Pressed==true) {
					jumpDirection="left";
				}else if(keyH.right1Pressed==true) {
					jumpDirection="right";
				}
				
			}else if(keyH.lightAttack2==true) {
				lightAttacking=true;
				spriteCounter=0;
			}else if(keyH.heavyAttack2==true) {
				heavyAttacking=true;
				spriteCounter=0;
			}else if(keyH.block2==true) {
				blocking=true;
				spriteCounter=0;
			}else if(keyH.crouch2Pressed==true) {
				crouch=true;
				solidArea.y+=gp.tileSize/2;
				solidArea.x=x+6;// dc am nevoie si de el aici
				spriteCounter=0;
			}
			else if(keyH.left2Pressed==true) {
				direction="left";
			}
			else if(keyH.right2Pressed==true) {
				direction="right";
			}
		
			collisionOn=gp.ccheck.CheckCollision(this);
		
			if(collisionOn==false) {
				if(gp.gameMode==0) {
					collisionOn=gp.ccheck.CheckEntity(this, gp.npc_rival);
				}else {
					collisionOn=gp.ccheck.CheckEntity(this, gp.player2);
				}
			
			}
	
		
			if(collisionOn==false && jump==false && crouch==false) {
				switch(direction) {
				case "left": x-=speed;break;
				case "right": x+=speed;break;
				}
				solidArea.x=x+6;
				solidArea.y=y+2;
			}
			//gp.eHandler.checkEvent();
	
			spriteCounter++;
			if(stamina<10) {
				staminaRecover++;
			}
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
			if(stamina<10) {
				staminaRecover++;
			}
			if(standCounter>20) {
				standCounter=0;
				spriteNum=1;
			}
		}
		if(staminaRecover>240) {
			stamina++;
			staminaRecover=0;
		}
		
		
		if(keyH.shot1KeyPressed==true && stamina>0) {
			projectile=new Projectile(projectileName, gp);
			projectile.set(x,y, direction,this);
			gp.projectileList.add(projectile);
			gp.playSE(1);
			stamina--;
			staminaRecover=0;
			keyH.shot1KeyPressed=false;
		}
	}
	
	
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.white);
		//g2.fillRect(x,y,gp.tileSize,gp.tileSize);
		BufferedImage image=null;
		switch(direction) {
		case"left":
			if(lightAttacking==true) {
				if(spriteNum==1) {image=lightAttackLeft1;}
				if(spriteNum==2) {image=lightAttackLeft2;}
			}else if(crouch==true){
				image=crouchLeft;
			}else if(heavyAttacking==true){
				if(spriteNum==1) {image=heavyAttackLeft1;}
				if(spriteNum==2) {image=heavyAttackLeft2;}
				if(spriteNum==3) {image=heavyAttackLeft3;}
			}else if(blocking==true){
				image=blockLeft;
			}else {
				if(spriteNum==1) {image=left1;}
				if(spriteNum==2) {image=left2;}
			}	
			break;
		case"right":
			if(lightAttacking==true) {
				if(spriteNum==1) {image=lightAttackRight1;}
				if(spriteNum==2) {image=lightAttackRight2;}
			}else if(crouch==true){
				image=crouchRight;
			}else if(heavyAttacking==true){
				if(spriteNum==1) {image=heavyAttackRight1;}
				if(spriteNum==2) {image=heavyAttackRight2;}
				if(spriteNum==3) {image=heavyAttackRight3;}
			}else if(blocking==true){
				image=blockRight;
			}else {
				if(spriteNum==1) {image=right1;}
				if(spriteNum==2) {image=right2;}
			}	
			break;
		}
		if(lightAttacking==true && direction == "left") {
			g2.drawImage(image,x-gp.tileSize,y,null);
		}else if(lightAttacking==true && direction == "left"){
			//g2.drawImage(image,x-gp.tileSize,y-gp.tileSize,null);
			g2.drawImage(image,x-gp.tileSize,y,null);
		}else {
			g2.drawImage(image,x,y,null);
		}
		int x2=solidArea.x, y2=solidArea.y;
		g2.fillRect(x2,y2,gp.tileSize-12,gp.tileSize-2);
		
		
	}
}
