package com.ggx7studios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ggx7studios.entity.Ammo;
import com.ggx7studios.entity.Enemy;
import com.ggx7studios.entity.Entity;
import com.ggx7studios.entity.LifePack;
import com.ggx7studios.entity.Weapon;
import com.ggx7studios.main.Game;

public class World {
	
	public static int width,height;
	public static Tile[] tiles;
	public static final int tile_size = 16;
	
	public World(String path)
	{
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			width = map.getWidth();
			height = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			for(int xx = 0; xx < map.getWidth(); xx++)
			{
				for(int yy = 0; yy < map.getHeight(); yy++)
				{
					int pixelAtual =pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * width)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					if(pixelAtual == 0xFF007F0E)
					{
						//Floor
						tiles[xx + (yy * width)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					}
					else if(pixelAtual == 0xFF606060)
					{
						//Wall
						tiles[xx + (yy * width)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
					}
					else if(pixelAtual == 0xFF0026FF)
					{
						//Player
						Game.player.setX(xx * 16);
						Game.player.setY(yy * 16);
					}
					else if(pixelAtual == 0xFF5E0000)
					{
						//enemy
						Enemy en = new Enemy(xx * 16, yy * 16, 16, 16, Entity.ENEMY1_EN);
						Game.entities.add(en);
						Game.enemies.add(en);
	
					}
					else if(pixelAtual == 0xFFFFFA00)
					{
						//Ammo
						Game.entities.add(new Ammo(xx*16, yy * 16, 16, 16, Entity.GUNAMMO_EN));
					}
					else if(pixelAtual == 0xFFFF8300)
					{
						//gun
						Game.entities.add(new Weapon(xx * 16, yy * 16, 16, 16, Entity.GUN_EN));
					}
					else if(pixelAtual == 0xFF4CFF00)
					{
						//Life
						Game.entities.add(new LifePack(xx * 16, yy * 16, 16, 16, Entity.LIFEPACK_EN));
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static boolean isFree(int xnext, int ynext)
	{
		int x1 = xnext / tile_size;
		int y1 = ynext / tile_size;
		
		int x2 = (xnext + tile_size - 1) / tile_size;
		int y2 = ynext / tile_size;
		
		int x3 = xnext / tile_size;
		int y3 = (ynext + tile_size - 1)/ tile_size;
		
		int x4 = (xnext + tile_size - 1)/ tile_size;
		int y4 = (ynext + tile_size - 1)/ tile_size;
		
		return !((tiles[x1 + (y1* World.width)] instanceof WallTile) ||
				(tiles[x2 + (y2* World.width)] instanceof WallTile) ||
				(tiles[x3 + (y3* World.width)] instanceof WallTile) ||
				(tiles[x4 + (y4* World.width)] instanceof WallTile));
	}
		public void render(Graphics g)
		{
			int xstart = Camera.x >> 4;
			int ystart = Camera.y >> 4;
			int xfinal = xstart + (Game.width >> 4);
			int yfinal = ystart + (Game.height >> 4);
			for(int xx = xstart; xx <= xfinal; xx++)
			{
				for(int yy = ystart; yy <= yfinal; yy++)
				{
					if(xx < 0 || yy < 0 || xx >= width || yy >= height)
					{
						continue;
					}
					Tile tile = tiles[xx + (yy * width)];
					tile.render(g);
				}
			}
			
		}
}
