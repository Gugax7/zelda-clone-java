package com.ggx7studios.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.ggx7studios.main.Game;
import com.ggx7studios.world.Camera;
import com.ggx7studios.world.World;

public class Enemy extends Entity {

	
	private double speed = 1.0;
	private int frames = 0, maxFrames = 20, index = 0, maxIndex = 1;
	private BufferedImage[] enemySprites;
	private double dano = 10;
	private int maskx = 3,
			masky = 3,
			maskw = 10,
			maskh = 10;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		
		enemySprites = new  BufferedImage[2];
		enemySprites[0] = Game.spritesheet.getSprite(80, 0, 16, 16);
		enemySprites[1] = Game.spritesheet.getSprite(80, 16, 16, 16);
		
	}
	public void tick()
	{
		if(this.isCollidingWithPlayer() == false)
		{
		if(Game.rand.nextInt(100) < 70) {
		
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y + speed)) && !isColliding(this.getX(), (int)(y + speed)))
		{
			y+=speed;
		}
		if((int)x < Game.player.getX() && World.isFree((int)(x + speed), this.getY()) && !isColliding((int)(x + speed), this.getY()))
		{
			x+=speed;
		}
		if((int)x > Game.player.getX() && World.isFree((int)(x - speed), this.getY())&& !isColliding((int)(x - speed), this.getY()))
		{
			x-=speed;
		}

		if((int)y > Game.player.getY() && World.isFree(this.getX(), (int)(y - speed))&& !isColliding(this.getX(), (int)(y - speed)))
		{
			y-=speed;
		
		
		}
		}
		}
		else
		{
			if(Game.rand.nextInt(100) < 10)
			{
			Game.player.life-= dano;
			//System.out.println("Vida = "+ Game.player.life);
			}
			if(Game.player.life == 0)
			{
				System.out.println("Você perdeu! ;-;");
				//System.exit(1);
				
			}
			
		}
		

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
		public boolean  isCollidingWithPlayer()
		{
			Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky, maskw, maskh);
			Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(),16,16);
			
			return enemyCurrent.intersects(player);
		}
		public boolean isColliding(int xnext, int ynext)
		{
			Rectangle enemyCurrent = new Rectangle(xnext + maskx,ynext + masky, maskw, maskh);
			for(int i = 0; i < Game.enemies.size(); i++)
			{
				Enemy e = Game.enemies.get(i);
				if(e == this)
				{
					continue;
				}
				Rectangle targetEnemy = new Rectangle(e.getX() + maskx,e.getY() + masky, maskw, maskh);
				if(targetEnemy.intersects(enemyCurrent))
				{
					return true;
				}
			}
			
			
			return false;
			
	}
		
		public void render(Graphics g)
		{
			
			g.drawImage(enemySprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			
			/*super.render(g);
			g.setColor(Color.black);
			g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);*/
			
		}
}
