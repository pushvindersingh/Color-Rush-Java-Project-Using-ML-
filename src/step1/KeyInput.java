package step1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import step1.Game.State;
//The movements of Player is done here !!
public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Game game;
	
	public KeyInput(Handler handler, Game game)
	{
		this.handler = handler;
		
		this.game = game;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.obj.size(); i++)
		{
			GameObject tempObj = handler.obj.get(i);
			
			if(tempObj.getID() == ID.Player)
			{
				//Key Events for Player 1 Only
				
				if(key == KeyEvent.VK_UP) { tempObj.setVelY(-5); }
				if(key == KeyEvent.VK_DOWN) { tempObj.setVelY(5); }
				if(key == KeyEvent.VK_RIGHT) { tempObj.setVelX(5); }
				if(key == KeyEvent.VK_LEFT) { tempObj.setVelX(-5); }
			}
		}
		
		if(key == KeyEvent.VK_SPACE)
		{
			if(game.gameState == State.Game)
			{
				if(Game.paused) Game.paused = false;
				else Game.paused = true;				
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1); // To exit the game using esc game
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.obj.size(); i++)
		{
			GameObject tempObj = handler.obj.get(i);
			
			if(tempObj.getID() == ID.Player)
			{
				//Key Events for Player 1 Only
				
				if(key == KeyEvent.VK_UP) {tempObj.setVelY(0); keyDown[0] = false;}
				if(key == KeyEvent.VK_DOWN) {tempObj.setVelY(0); keyDown[1] = false;}
				if(key == KeyEvent.VK_RIGHT) {tempObj.setVelX(0); keyDown[2] = false;}
				if(key == KeyEvent.VK_LEFT) {tempObj.setVelX(0); keyDown[3] = false;}
				
				//Vertical Movement
				if(keyDown[0] && !keyDown[1]) tempObj.setVelY(0);
				//Horizontal Movement
				if(keyDown[2] && !keyDown[3]) tempObj.setVelX(0);
			}
			
		}
	}

}
