package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import object.Projectile;

public class NPC_Rival extends Entity{
	
	boolean rangeAttack=false;
	public int actionLockCounter=0;
	public NPC_Rival(GamePanel gp) {
		super(gp);
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x=gp.tileSize*14;
		direction="left";
		name="";
		
	}
	
	public void getNPCImage() {
		int imgSize=gp.tileSize;
		
		
		jumpLeft=setup(name+"_left_2",imgSize,imgSize);
		jumpRight=setup(name+"_right_2",imgSize,imgSize);
		fallLeft=setup(name+"_left_2",imgSize,imgSize);
		fallRight=setup(name+"_right_2",imgSize,imgSize);
		crouchLeft=setup(name+"_down_1",imgSize,imgSize);
		crouchRight=setup(name+"_down_2",imgSize,imgSize);
		left1=setup(name+"_left_1",imgSize,imgSize);
		left2=setup(name+"_left_2",imgSize,imgSize);
		right1=setup(name+"_right_1",imgSize,imgSize);
		right2=setup(name+"_right_2",imgSize,imgSize);
		lightAttackRight1=setup("boy_attack_right_1",2*imgSize,imgSize);
		lightAttackRight2=setup("boy_attack_right_2",2*imgSize,imgSize);
		lightAttackLeft1=setup("boy_attack_left_1",2*imgSize,imgSize);
		lightAttackLeft2=setup("boy_attack_left_2",2*imgSize,imgSize);
		heavyAttackRight1=setup("boy_attack_right_1",2*imgSize,imgSize);
		heavyAttackRight2=setup("boy_attack_right_2",2*imgSize,imgSize);
		heavyAttackRight3=setup("boy_attack_right_2",2*imgSize,imgSize);
		heavyAttackLeft1=setup("boy_attack_left_1",2*imgSize,imgSize);
		heavyAttackLeft2=setup("boy_attack_left_2",2*imgSize,imgSize);
		heavyAttackLeft3=setup("boy_attack_left_2",2*imgSize,imgSize);
		blockLeft=setup(name+"_right_2",imgSize,imgSize);
		blockRight=setup(name+"_left_2",imgSize,imgSize);
	}
	
	public void setAction() {
		
		actionLockCounter++;
		if(actionLockCounter==120) {
			Random random= new Random();
			int i=random.nextInt(125)+1;
			if(i<=25) {
				
			}/*else if
			if(i<=50) {
				rangeAttack=true;
			}*/else if(i<=50) {
				direction="left";
				spriteCounter=0;
			}	
			else if(i<=75) {
				direction="right";
				spriteCounter=0;
			}
			else if(i<=100) {
				lightAttacking=true;
				spriteCounter=0;
			}
			else if(i<=125) {
				heavyAttacking=true;
				spriteCounter=0;
			}
			else if(i<=150) {
				blocking=true;
				spriteCounter=0;
			}
			else if(i<=175) {
				crouch=true;
				spriteCounter=0;
			}
			else if(i<=200) {
				jump=true;
				spriteCounter=0;
			}
			actionLockCounter=0;
		}
		collisionOn=gp.ccheck.CheckCollision(this);
		if(collisionOn==false) {
			collisionOn=gp.ccheck.CheckEntity(this, gp.player1);
		}
	}
	
	public void update() {
		setAction();
		if(hp<=0) {
			//gp.ui.showMessage("Player dead");
		}else if(jump==true) {
			if(jumpDirection=="") {
				jump1Action();
			}else {
				jump2Action();
			}					
	
		}else if(crouch==true){
			crouchAction();
		}else if(lightAttacking==true) {
			lightAttack2();
		}else if(heavyAttacking==true) {
			heavyAttack2();
		}else {
			bundingAction();
					
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
	
	public void crouchAction() {
		spriteCounter++;
		if(spriteCounter==20) {
			solidArea.y-=gp.tileSize/2;
			crouch=false;
			spriteCounter=0;
		}
	}
	
	public void jump2Action() {
		spriteCounter++;
		if(gp.gameMode==0) {
			collisionOn=gp.ccheck.CheckEntity(this, gp.npc_rival);
		}else {
			collisionOn=gp.ccheck.CheckEntity(this, gp.player2);
		}
	
		if(collisionOn==false && jumpDirection=="left") {
			if(spriteCounter<=10) {
				y-=speed;
				x-=speed;
			}else if(spriteCounter<20) {
				y+=speed;
				x-=speed;
			}else {
				jump=false;
				jumpDirection="";
				spriteCounter=0;
			}
		}
		
		if(collisionOn==false && jumpDirection=="right") {
			if(spriteCounter<=10) {
				y-=speed;
				x+=speed;
			}else if(spriteCounter<20) {
				y+=speed;
				x+=speed;
			}else {
				jump=false;
				jumpDirection="";
				spriteCounter=0;
			}
		}
	}
	
	
	public void jump1Action() {
		
		spriteCounter++;
		if(gp.gameMode==0) {
			collisionOn=gp.ccheck.CheckEntity(this, gp.npc_rival);
		}else {
			collisionOn=gp.ccheck.CheckEntity(this, gp.player2);
		}
	
		if(collisionOn==false) {
			if(spriteCounter<=10) {
				y-=speed;
			}else if(spriteCounter<20) {
				y+=speed;
			}else {
				jump=false;
				jumpDirection="";
				spriteCounter=0;
			}
		}
	}
	
	
	
	public void bundingAction(){
	
		
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
		if(staminaRecover>240) {
			stamina++;
			staminaRecover=0;
		}
		
		
		if(rangeAttack==true && stamina>0) {
			projectile=new Projectile(projectileName, gp);
			projectile.set(x,y, direction,this);
			gp.projectileList.add(projectile);
			gp.playSE(1);
			stamina--;
			staminaRecover=0;
			rangeAttack=false;
			
		}
		
	}
	
	
	
	
	public void draw(Graphics2D g2) {
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
		
	}
	

}
