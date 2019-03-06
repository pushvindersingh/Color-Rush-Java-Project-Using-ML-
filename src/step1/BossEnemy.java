package step1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	private int timer = 150;
	private int timer2 = 50;

	public BossEnemy(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 96, 96);
	}

	public void tick() 
	{		
		x += velX;
		y += velY;
		
		// Timer to stop the boss at a particular spot.
		timer--;
		if(timer <= 0) velY = 0;
		
		else timer--;
		
		if(timer <= 0) timer2--;
		
		if(timer2 <= 0)
		{
			if(velX == 0) velX = 3; // Add velocity to its movements
			
			if(velX > 0) velX += 0.001f;
			else if(velX < 0) velX -= 0.006f;
			
			velX = Game.clamp(velX, -10, 10);
			
			int spawn = r.nextInt(10);
			if(spawn == 0) handler.addObject(new BossBullet((int)x + 50, (int)y + 50, ID.BEnemy, handler));
		}
		
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1 ;
		
		if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1 ;
		
		//handler.addObject(new Trial(x, y, ID.Trial, Color.blue, 96, 96, 0.008f, handler));
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 96, 96);
	}

}
