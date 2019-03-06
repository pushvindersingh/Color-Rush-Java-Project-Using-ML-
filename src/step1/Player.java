package step1;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
	
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 61);
		
		handler.addObject(new Trial(x, y, ID.Trial, Color.white, 32, 32, 0.05f, handler));
		
		collision();
	}
	
	private void collision()
	{
		for(int i = 0; i < handler.obj.size(); i++)
		{
			GameObject tempObj = handler.obj.get(i); //tempObj is now our Basic Enemy
			
			if(tempObj.getID() == ID.BEnemy || tempObj.getID() == ID.FastEnemy || tempObj.getID() == ID.SmartEnemy)
			{
				if(getBounds().intersects(tempObj.getBounds()))
				{
					//Collision Code
					HUD.HEALTH -= 2 ; // Reduce the Health if they collide
				}
			}
		}
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
	}

}
