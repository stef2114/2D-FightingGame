package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener{

	public boolean up1Pressed, down1Pressed,left1Pressed,right1Pressed,
	up2Pressed, down2Pressed,left2Pressed,right2Pressed;
	GamePanel gp;
	public KeyHandler(GamePanel gp) {
		this.gp=gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();
		
		if(gp.gameState==gp.titleState) {
			if(gp.ui.titleScreenState==0) {
				switch(code) {
				case KeyEvent.VK_UP:
					if(gp.ui.commandNumY>0) {
						gp.ui.commandNumY--;
					}
					break;
				case KeyEvent.VK_DOWN:
					if(gp.ui.commandNumY<2) {
						gp.ui.commandNumY++;
					}
					break;
				case KeyEvent.VK_ENTER:
					if(gp.ui.commandNumY==2) {
						System.exit(0);
					}
					else {
						gp.gameMode=gp.ui.commandNumY;
						gp.ui.commandNumY=0;
						if(gp.gameMode==0) {
							gp.setNPC();
						}else {
							gp.setPlayer2();
						}
						gp.ui.titleScreenState=1;
					}
					break;
					
				}
			}
			else {
				switch(code) {
				case KeyEvent.VK_UP:
					if(gp.ui.commandNumY>0) {
						gp.ui.commandNumY--;
					}
					break;
				case KeyEvent.VK_DOWN:
					if(gp.ui.MaxX*gp.ui.MaxY==gp.ui.n) {
						if(gp.ui.commandNumY<gp.ui.MaxY-1) {
							gp.ui.commandNumY++;
						}
					}else {
						if(gp.ui.commandNumY<gp.ui.MaxY) {
							gp.ui.commandNumY++;
						}
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(gp.ui.commandNumX<gp.ui.MaxX-1) {
						gp.ui.commandNumX++;
					}
					break;
				case KeyEvent.VK_LEFT:
					if(gp.ui.commandNumX>0) {
						gp.ui.commandNumX--;
					}
					break;
				case KeyEvent.VK_ENTER:
					if(gp.ui.titleScreenState==1) {
						int i=gp.ui.commandNumY*gp.ui.MaxX+gp.ui.commandNumX;
						gp.player1.name=gp.ui.nameListPl[i];
						gp.ui.getNewN(i);
						gp.ui.titleScreenState=2;
						gp.ui.commandNumX=0;
						gp.ui.commandNumY=0;
						
					}else if(gp.ui.titleScreenState==2) {
						if(gp.gameMode==0) {
							gp.player1.name=gp.ui.nameListPl[gp.ui.commandNumY*gp.ui.MaxX+gp.ui.commandNumX];
						}else {
							gp.npc_rival.name=gp.ui.nameListPl[gp.ui.commandNumY*gp.ui.MaxX+gp.ui.commandNumX];
						}
						gp.ui.getDimMax(gp.ui.nameListBg);
						gp.ui.titleScreenState=3;
						//gp.gameState=gp.playState;
						gp.ui.commandNumX=0;
						gp.ui.commandNumY=0;
					}else {
						gp.bG.name=gp.ui.nameListBg[gp.ui.commandNumY*gp.ui.MaxX+gp.ui.commandNumX];
						System.out.println(gp.ui.nameListBg[gp.ui.commandNumY*gp.ui.MaxX+gp.ui.commandNumX]);
						gp.gameState=gp.playState;
					}
					
					break;
					
				}
			}
			
			
			
		}
		
		
		if(gp.gameState==gp.playState) {
			switch(code) {
			case KeyEvent.VK_A:
				left1Pressed=true;
				break;
			case KeyEvent.VK_D:
				right1Pressed=true;
				break;
			case KeyEvent.VK_W:
				up1Pressed=true;
				break;
			case KeyEvent.VK_S:
				down1Pressed=true;
				break;
			case KeyEvent.VK_J:
				left2Pressed=true;
				break;
			case KeyEvent.VK_L:
				right2Pressed=true;
				break;
			case KeyEvent.VK_I:
				up2Pressed=true;
				break;
			case KeyEvent.VK_K:
				down2Pressed=true;
				break;
			case KeyEvent.VK_P:
				gp.gameState=gp.pauseState;
				break;
			}
		}
		
		if(gp.gameState==gp.pauseState) {
			if(code==KeyEvent.VK_P) {
				gp.gameState=gp.playState;
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code=e.getKeyCode();
		
		switch(code) {
		case KeyEvent.VK_A:
			left1Pressed=false;
			break;
		case KeyEvent.VK_D:
			right1Pressed=false;
			break;
		case KeyEvent.VK_W:
			up1Pressed=false;
			break;
		case KeyEvent.VK_S:
			down1Pressed=false;
			break;
		case KeyEvent.VK_J:
			left2Pressed=false;
			break;
		case KeyEvent.VK_L:
			right2Pressed=false;
			break;
		case KeyEvent.VK_I:
			up2Pressed=false;
			break;
		case KeyEvent.VK_K:
			down2Pressed=false;
			break;
		}
		
	}

}
