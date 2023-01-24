package com.ggx7studios.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ggx7studios.entity.Player;
import com.ggx7studios.main.Game;
import com.ggx7studios.world.Camera;

public class UI {
	
	public void render(Graphics g)
	{
		g.setColor(Color.white);
		g.fillOval(1, 3, 13,13);
		g.setColor(Color.red);
		g.fillRect(15, 5, 50,7);
		g.setColor(Color.green);
		g.fillRect(15, 5, (int)((Game.player.life/Game.player.maxLife)*50),7);
		g.setColor(Color.blue);
		g.fillRect(15, 13, 50,3);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 8));
		g.drawString((int)Player.life + "/" + (int)Player.maxLife, 15, 11);
	}

}
