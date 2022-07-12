package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener{

	public boolean jump1Pressed, crouch1Pressed,left1Pressed,right1Pressed,
	jump2Pressed, crouch2Pressed,left2Pressed,right2Pressed;
	public boolean shot1KeyPressed,shot2KeyPressed,lightAttack1,lightAttack2,heavyAttack1,heavyAttack2,
	block1,block2;
	GamePanel gp;
	public KeyHandler(GamePanel gp) {
		this.gp=gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public void titleScreen0(int code) {
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
	
	
	
	public void otherTitleScreen(int code) {
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
				gp.player1.getPlayerImage();
				gp.ui.getNewN(i);
				gp.ui.titleScreenState=2;
				gp.ui.commandNumX=0;
				gp.ui.commandNumY=0;
				
			}else if(gp.ui.titleScreenState==2) {
				if(gp.gameMode==0) {
					gp.npc_rival.name=gp.ui.nameListPl[gp.ui.commandNumY*gp.ui.MaxX+gp.ui.commandNumX];
					gp.npc_rival.getNPCImage();
				}else {
					gp.player2.name=gp.ui.nameListPl[gp.ui.commandNumY*gp.ui.MaxX+gp.ui.commandNumX];
					gp.player2.getPlayerImage();
				}
				gp.ui.getDimMax(gp.ui.nameListBg);
				gp.ui.titleScreenState=3;
				
				gp.ui.commandNumX=0;
				gp.ui.commandNumY=0;
			}else {
				gp.bG.name=gp.ui.nameListBg[gp.ui.commandNumY*gp.ui.MaxX+gp.ui.commandNumX];
				//System.out.println(gp.ui.nameListBg[gp.ui.commandNumY*gp.ui.MaxX+gp.ui.commandNumX]);
				gp.gameState=gp.playState;
				gp.ui.commandNumX=0;
				gp.ui.commandNumY=0;
			}
			
			break;
			
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();
		
		if(gp.gameState==gp.titleState) {
			if(gp.ui.titleScreenState==0) {
				titleScreen0(code);
			}
			else {
				otherTitleScreen(code);
			}
		}
		
		
		if(gp.gameState==gp.playState) {
			playState(code);
		}
		
		if(gp.gameState==gp.optionState) {
			//optionState(code);
			
		}
		
		if(gp.gameState==gp.pauseState) {
			if(code==KeyEvent.VK_P) {
				gp.gameState=gp.playState;
			}
		}
		
		
	}
	
	/*public void optionState(int code) {
		switch(code) {
		case KeyEvent.VK_ESCAPE:
			gp.gameState=gp.playState;
		case KeyEvent.VK_UP:
			if(gp.ui.commandNumY>0) {
				gp.ui.commandNumY--;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(gp.ui.commandNumY<3) {
				gp.ui.commandNumY++;
			}
			break;
		case KeyEvent.VK_ENTER:
			if(gp.ui.commandNumY==2) {
				System.exit(0);
			}
			
			break;
			
		}
	}*/
	
	public void playState(int code) {
		switch(code) {
		case KeyEvent.VK_A:
			left1Pressed=true;
			break;
		case KeyEvent.VK_D:
			right1Pressed=true;
			break;
		case KeyEvent.VK_W:
			jump1Pressed=true;
			break;
		case KeyEvent.VK_S:
			crouch1Pressed=true;
			break;
		case KeyEvent.VK_J:
			left2Pressed=true;
			break;
		case KeyEvent.VK_L:
			right2Pressed=true;
			break;
		case KeyEvent.VK_I:
			jump2Pressed=true;
			break;
		case KeyEvent.VK_K:
			crouch2Pressed=true;
			break;
		case KeyEvent.VK_SPACE:
			lightAttack1=true;
			break;
		case KeyEvent.VK_C:
			heavyAttack1=true;
			break;
		case KeyEvent.VK_B:
			block1=true;
			break;
		case KeyEvent.VK_ENTER:
			lightAttack2=true;
			break;
		case KeyEvent.VK_SHIFT:
			heavyAttack2=true;
			break;
		case KeyEvent.VK_DOWN:
			block1=true;
			break;
			
		case KeyEvent.VK_E:
			if(gp.player1.relesaseRangeAttack==false) {
				shot1KeyPressed=true;
				gp.player1.relesaseRangeAttack=true;
			}
			break;
		case KeyEvent.VK_O:
			if(gp.player2.relesaseRangeAttack==false) {
				shot2KeyPressed=true;
				gp.player2.relesaseRangeAttack=true;
			}
			break;
		case KeyEvent.VK_ESCAPE:
			gp.gameState=gp.optionState;
			break;
		case KeyEvent.VK_P:
			gp.gameState=gp.pauseState;
			break;
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
			jump1Pressed=false;
			break;
		case KeyEvent.VK_S:
			crouch1Pressed=false;
			break;
		case KeyEvent.VK_J:
			left2Pressed=false;
			break;
		case KeyEvent.VK_L:
			right2Pressed=false;
			break;
		case KeyEvent.VK_I:
			jump2Pressed=false;
			break;
		case KeyEvent.VK_K:
			crouch2Pressed=false;
			break;
		case KeyEvent.VK_SPACE:
			lightAttack1=false;
			break;
		case KeyEvent.VK_C:
			heavyAttack1=false;
			break;
		case KeyEvent.VK_B:
			block1=false;
			break;
		case KeyEvent.VK_ENTER:
			lightAttack2=false;
			break;
		case KeyEvent.VK_SHIFT:
			heavyAttack2=false;
			break;
		case KeyEvent.VK_DOWN:
			block1=false;
			break;
		case KeyEvent.VK_E:
			shot1KeyPressed=false;
			gp.player1.relesaseRangeAttack=false;
			break;
		case KeyEvent.VK_O:
			shot2KeyPressed=false;
			gp.player2.relesaseRangeAttack=false;
			break;
			
		}
		
	}

}
