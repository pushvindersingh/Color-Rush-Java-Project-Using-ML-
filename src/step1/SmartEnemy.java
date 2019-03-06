package step1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;

	public SmartEnemy(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.obj.size(); i++)
		{
			if(handler.obj.get(i).getID() == ID.Player)
			{
				player = handler.obj.get(i);
			}
		}
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float dist = (float)Math.sqrt( (x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()) );
		
		velX = (float) ((-1.0 / dist) * diffX);
		velY = (float) ((-1.0 / dist) * diffY);
		
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1 ;
		
		//if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1 ;
		
		handler.addObject(new Trial(x, y, ID.Trial, Color.orange, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
