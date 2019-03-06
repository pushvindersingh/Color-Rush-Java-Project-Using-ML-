package step1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import step1.Game.State;

public class Menu extends MouseAdapter
{
	private Game game;
	private Handler handler;
	private HUD hud;
	Random r = new Random();
	public Menu(Game game, Handler handler, HUD hud)
	{
		this.game = game ;
		this.hud = hud;
		this.handler = handler ;
	}
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == State.Menu)
		{
			
		    //Play Button
		    if(mouseOver(mx, my, 202, 155, 230, 70))
		     {
			   //game.gameState = State.Game;
               //handler.addObject(new Player(Game.WIDTH/2-32 , Game.HEIGHT/2-32 , ID.Player , handler));
               //handler.clearEnemy();
			   //handler.addObject(new BEnemy(r.nextInt(Game.WIDTH - 50) , r.nextInt(Game.HEIGHT - 50) , ID.BEnemy, handler));
		    	game.gameState = State.Select;
		    	return;
		     }
		    
		    //Help Button
		    if(mouseOver(mx, my, 202, 255, 230, 70))
		     {
			   game.gameState = State.Help;		
		     }
		
		    //Quit Button
		    if(mouseOver(mx, my, 202, 355, 230, 70))
		     {
			   System.exit(1);
		     }
		}	
		
		if(game.gameState == State.Select)
		{
			
		    //Normal Button
		    if(mouseOver(mx, my, 202, 155, 230, 70))
		     {
			   game.gameState = State.Game;
               handler.addObject(new Player(Game.WIDTH/2-32 , Game.HEIGHT/2-32 , ID.Player , handler));
               handler.clearEnemy();
			   handler.addObject(new BEnemy(r.nextInt(Game.WIDTH - 50) , r.nextInt(Game.HEIGHT - 50) , ID.BEnemy, handler));
		       game.diff = 0;
		     }
		    
		    //Hard Button
		    if(mouseOver(mx, my, 202, 255, 230, 70))
		     {
		    	game.gameState = State.Game;
	            handler.addObject(new Player(Game.WIDTH/2-32 , Game.HEIGHT/2-32 , ID.Player , handler));
	            handler.clearEnemy();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50) , r.nextInt(Game.HEIGHT - 50) , ID.BEnemy, handler));
			    game.diff = 1;
		     }
		
		    //Back Button
		    if(mouseOver(mx, my, 202, 355, 230, 70))
		     {
		    	game.gameState = State.Menu;
				return;
		     }
		}	
		
		//Back Button of Help
		if(game.gameState == State.Help)
		{	
			if(mouseOver(mx, my, 202, 355, 230, 70))
			{
				game.gameState = State.Menu;
				return;
			}
		}
		
		if(game.gameState == State.End)
		{	
			if(mouseOver(mx, my, 202, 355, 230, 70))
			{
				game.gameState = State.Menu;
				hud.setLevel(1);
				hud.setScore(0);
			}
		}
	}
	
	public void mouseRelease(MouseEvent e)
	{
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
		{
			if(my > y && my < y + height)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public void tick()
	{
		
	}
	public void render(Graphics g)
	{
		if(game.gameState == State.Menu)
		{
			Font fnt = new Font("arial", 1, 52);
		    Font fnt2 = new Font("arial", 1, 32);
		    g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Colour Rush", 168, 85);
			
			g.setFont(fnt2);
			g.drawRect(202, 155, 230, 70);
			g.drawString("Play", 283, 197);
			
		    g.drawRect(202, 255, 230, 70);
		    g.drawString("Help", 283, 297);
		    
			g.drawRect(202, 355, 230, 70);
			g.drawString("Quit", 283, 397);
		}
		
		else if(game.gameState == State.Help)
		{
			Font fnt = new Font("arial", 1, 52);
			Font fnt2 = new Font("arial", 1, 32);
			Font fnt3 = new Font("arial", 1, 22);
		    g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 255, 85);
			
			g.setFont(fnt3);
			g.drawString("Use Arrow KEYS to Move and Dodge the Enemies", 70, 225);
	
		    g.setFont(fnt2);
		    g.drawRect(202, 355, 230, 70);
			g.drawString("Back", 283, 397);
		}
		
		else if(Game.gameState == State.End)
		{
			Font fnt = new Font("arial", 1, 52);
			Font fnt2 = new Font("arial", 1, 32);
			Font fnt3 = new Font("arial", 1, 22);
		    g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over !!", 169, 85);
			
			g.setFont(fnt3);
			g.drawString("YOU Lost with a Score Of : " + hud.getScore(), 160, 225);
	
		    g.setFont(fnt2);
		    g.drawRect(202, 355, 230, 70);
			g.drawString(" Try Again ", 240, 397);
		}
		
		else if(game.gameState == State.Select)
		{
			Font fnt = new Font("arial", 1, 52);
		    Font fnt2 = new Font("arial", 1, 32);
		    g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", 138, 85);
			
			g.setFont(fnt2);
			g.drawRect(202, 155, 230, 70);
			g.drawString("NORMAL", 250, 202);
			
		    g.drawRect(202, 255, 230, 70);
		    g.drawString("HARD", 270, 298);
		    
			g.drawRect(202, 355, 230, 70);
			g.drawString("BACK", 270, 400);
		}
		
	}

}
