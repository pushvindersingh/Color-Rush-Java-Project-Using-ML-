package step1;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler 
{
	//We have to create the list of all Game Objects so we use Linked List as we dont know how many objects we will make
	
	LinkedList<GameObject> obj = new LinkedList<GameObject>();
	
	public void tick()
	{
		for(int i = 0 ; i < obj.size(); i++)
		{
			GameObject tempObj = obj.get(i);
			tempObj.tick();
		}
	}
	public void render(Graphics g)
	{
		for(int i = 0 ; i < obj.size(); i++)
		{
			GameObject tempObj = obj.get(i);
			tempObj.render(g);
		}
	}
	
	public void clearEnemy() 
	{
		for(int i = 0 ; i < obj.size(); i++)
		{
			GameObject tempObj = obj.get(i);
			if(tempObj.getID() == ID.Player)
			{
				obj.clear();
				if(Game.gameState != Game.State.End)
				addObject(new Player((int)tempObj.getX(), (int)tempObj.getY(), ID.Player, this));
			}
		}		
	}
	
	public void addObject(GameObject obj)
	{
		this.obj.add(obj);
	}
	public void removeObject(GameObject obj)
	{
		this.obj.remove(obj);
	}

}
