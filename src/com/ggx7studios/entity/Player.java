package com.ggx7studios.entity;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ggx7studios.main.Game;
import com.ggx7studios.world.Camera;
import com.ggx7studios.world.World;

public class Player extends Entity {
	private BufferedImage[] leftPlayer;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] frontPlayer;
	private BufferedImage[] backPlayer;
	private BufferedImage[] stoppedPlayer;
	private int rightdir = 1, leftdir = 2, frontdir = 3, backdir = 4, stoppeddir = 5;
	private int dir = 3;
	public boolean right, up, left, down, stopped;
	public double speed = 1.5;
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 2;
	private boolean moved = false;
	public static double life = 100, maxLife = 100;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightPlayer = new BufferedImage[3];
		leftPlayer = new BufferedImage[3];
		frontPlayer = new BufferedImage[3];
		backPlayer = new BufferedImage[3];
		stoppedPlayer = new BufferedImage[1];
		for(int i = 0; i < 3; i++)
		{
			rightPlayer[i] = Game.spritesheet.getSprite(32, 16 + (i * 16), 16, 16);
		} 
		for(int i = 0; i < 3; i++)
		{
			leftPlayer[i] = Game.spritesheet.getSprite(48, 16 + (i * 16), 16, 16);
		} 
		for(int i = 0; i < 3; i++)
		{
			frontPlayer[i] = Game.spritesheet.getSprite(0, 16 + (i * 16), 16, 16);
		} 
		for(int i = 0; i < 3; i++)
		{
			backPlayer[i] = Game.spritesheet.getSprite(16, 16 + (i * 16), 16, 16);
		} 
	
		stoppedPlayer[0] = Game.spritesheet.getSprite(0,16,16,16);
		
		
		
		
	}
	
	public void tick()
	{
		
	
		moved = false;
		if(right && World.isFree((int)(x + speed), this.getY()))
		{
			moved = true;
			dir = rightdir;
			x+=speed;
		}
		else if(left && World.isFree((int)(x - speed), this.getY()))
		{
			moved = true;
			dir = leftdir;
			x-=speed;
		}
		if(down && World.isFree(this.getX(), (int)(y + speed)))
		{
			moved = true;
			dir = frontdir;
			y+=speed;
		}
		else if(up && World.isFree(this.getX(),(int)(y - speed)))
		{
			moved = true;
			dir = backdir;
			y-=speed;
		}
		if(up == false && down == false && right == false && left == false)
		{
			dir = stoppeddir;
		}
		
		if(moved)
		{
			frames++;
			if(frames == maxFrames)
			{
				frames = 0;
				index++;
				if(index > maxIndex)
				{
					index = 0;
				}
				
			}
		
		}
		Camera.x = Camera.clamp(this.getX() - (Game.width/2), 0, World.width * 16 - Game.width);
		Camera.y = Camera.clamp(this.getY() - (Game.height/2), 0, World.height * 16 - Game.height);
		
	}
	
	public void render(Graphics g)
	{
		if(dir == rightdir) 
		{
		g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		else if(dir == leftdir)
		{
			
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		else if(dir == frontdir)
		{
			g.drawImage(frontPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
		}
		else if(dir == backdir)
		{
			g.drawImage(backPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
		}
		if(dir == stoppeddir)
		{
			
			g.drawImage(stoppedPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
	}
}

