package step1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 8733312266983759625L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	
	private boolean running = false;
	
	public static boolean paused = false ;
	
	public int diff = 0;  // 0 refers to Normal and 1 refers to Hard.
	
	private Random r;
	
	private Handler handler;
	
	private HUD hud;
	
	private Spawn spawner;
	
	private Menu menu;
	
	public enum State
	{
		Menu,
		Help,
		Select,
		Game,
		End
		
	};
	public static State gameState = State.Menu;
	public Game()
	{
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
	    this.addKeyListener(new KeyInput(handler, this));
	    this.addMouseListener(menu);
	    new Window(WIDTH , HEIGHT , "This is a Game!!" , this);
	    			
		spawner = new Spawn(handler , hud, this);
		r = new Random();
		
		if(gameState == State.Game)
		{
			handler.addObject(new Player(WIDTH/2-32 , HEIGHT/2-32 , ID.Player , handler));
			
			handler.addObject(new BEnemy(r.nextInt(Game.WIDTH - 50) , r.nextInt(Game.HEIGHT - 50) , ID.BEnemy, handler));
			
		}
		else
		{
			for(int i = 0; i < 20; i++)   // To print various waves on the Menu Screen
			{
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
	
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amtofTicks = 60.0;
		double n = 1000000000 / amtofTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running)
		{
			
			long now = System.nanoTime();
			delta += (now - lastTime) / n ;
			lastTime = now ;
			
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				//System.out.println("FPS : "+frames);
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick()
	{
		if(gameState == State.Game)
		{
			if(!paused)
			{
				 hud.tick();
				 spawner.tick();
				 handler.tick();	
				  
				  if(HUD.HEALTH <= 0)
				  {
					  HUD.HEALTH = 100;
					  gameState = State.End;
					  handler.clearEnemy();
					  for(int i = 0; i < 20; i++)   // To print various waves on the Menu Screen
						{
							handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
						}
				  }	             	
			}
		}
		else if(gameState == State.Menu || gameState == State.End || gameState == State.Select)
		{
			menu.tick();
			handler.tick();	
		}
	}
	
	private void render()
	{
		BufferStrategy b = this.getBufferStrategy();
		if(b == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = b.getDrawGraphics();
		
		g.setColor(Color.black); // Set the Color.
		g.fillRect(0, 0, WIDTH, HEIGHT); // Fit in its apporopriate size.

		handler.render(g);
		
		if(paused)
		{
			g.setColor(Color.MAGENTA);
			g.drawString("PAUSED", 100, 100);
		}
		if(gameState == State.Game)
		{
			hud.render(g);			
		}
		else if(gameState == State.Menu || gameState == State.Help || gameState == State.End || gameState == State.Select)
		{
			menu.render(g);
		}
		g.dispose();
		b.show();
	}
	
	public static float clamp(float var, float min, float max)
	{
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[])
	{
		new Game();
		
	}

}
