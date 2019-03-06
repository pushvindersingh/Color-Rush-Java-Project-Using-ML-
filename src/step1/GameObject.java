package step1;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject  // They pretain to have a Player object and Enemy Object. 
{
	protected float x , y;  // protected can only be accessed by those who inherit the Game Object
	
	// To differentiate between the Player and Enemy we will provide them with an ID using enum(enumeration).
	protected ID id;
	protected float velX , velY;
	
	public GameObject(float x, float y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds();
	
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
	public void setID(ID id)
	{
		this.id = id;
	}
	public ID getID()
	{
		return id;
	}
	public void setVelX(int velX)
	{
		this.velX = velX;
	}
	public void setVelY(int velY)
	{
		this.velY = velY;
	}
	public float getVelX()
	{
		return velX;
	}
	public float getVelY()
	{
		return velY;
	}
}
