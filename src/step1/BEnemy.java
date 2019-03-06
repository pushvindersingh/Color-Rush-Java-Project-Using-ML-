package step1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BEnemy extends GameObject{
	
	private Handler handler;

	public BEnemy(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1 ;
		
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1 ;
		
		handler.addObject(new Trial(x, y, ID.Trial, Color.red, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
