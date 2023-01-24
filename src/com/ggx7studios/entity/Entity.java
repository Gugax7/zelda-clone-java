package com.ggx7studios.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ggx7studios.main.Game;
import com.ggx7studios.world.Camera;

public class Entity {
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(32, 0, 16, 16);
	public static BufferedImage GUN_EN = Game.spritesheet.getSprite(48, 0, 16, 16);
	public static BufferedImage GUNAMMO_EN = Game.spritesheet.getSprite(64, 0, 16, 16);
	public static BufferedImage ENEMY1_EN = Game.spritesheet.getSprite(80, 0, 16, 16);
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
	}
	public void setX(int newX)
	{
		this.x = newX;
	}
	public void setY(int newY)
	{
		this.y = newY;
	}
	
	public int getX()
	{
		return (int)this.x;
	}
	public int getY()
	{
		return (int)this.y;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	public void tick()
	{

		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		
		
	}
}
