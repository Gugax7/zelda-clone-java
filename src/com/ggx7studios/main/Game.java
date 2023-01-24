package com.ggx7studios.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.Runnable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.ggx7studios.entity.Enemy;
import com.ggx7studios.entity.Entity;
import com.ggx7studios.entity.Player;
import com.ggx7studios.graphics.Spritesheet;
import com.ggx7studios.graphics.UI;
import com.ggx7studios.world.World;

public class Game extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static JFrame jframe;
	private Thread thread;
	private boolean isRunning;
	public static final int width = 240;
	public static final int height = 160;
	private final int scale = 3;
	private BufferedImage image;
	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static Spritesheet spritesheet;
	public static Player player;
	public static World world;
	public static Random rand;
	public UI ui;
	
	public Game()
	{

		rand = new Random();
		addKeyListener(this);
		this.setPreferredSize(new Dimension(width*scale, height*scale));
		jframe = new JFrame("Game #1 entendendo pela primeira vez");
		initFrame();
		ui = new UI();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,16,16,spritesheet.getSprite(32, 16, 16, 16));
		world = new World("/level1.png");
		entities.add(player);
	}
	
	public void initFrame() {
		
		
		jframe.add(this);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		
		
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		isRunning = true;
		thread.start();
		
		
		
	}
	
	public synchronized void stop()
	{
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void main(String [] args)
	{
		Game inicio = new Game();
		inicio.start();
		
	}
	public void tick()
	{
		
		for(int i = 0; i < entities.size();i++)
		{
			Entity e = entities.get(i);
			e.tick();
		}
	
	}
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;

		}
		
		Graphics g = image.getGraphics();

		
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		world.render(g);
		for(int i = 0; i < entities.size();i++)
		{
			Entity e = entities.get(i);
			e.render(g);
		}

		ui.render(g);
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, width*scale, height*scale, null);
		bs.show();
		

	
		
	}
	public void run() {
		long NanoSegundos = System.nanoTime();
		double quantidadeDeTicks = 60.0;
		double ns = 1000000000 / quantidadeDeTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning)
		{
			long now = System.nanoTime();
			delta+= (now - NanoSegundos) / ns;
			NanoSegundos = now;
			if(delta >= 1)
			{
				tick();
				render();
				frames++;
				delta--;
				
			}
			
			if(System.currentTimeMillis() - timer >= 1000)
			{
				System.out.println(frames);
				frames = 0;
				timer+= 1000;
				
				
				
			
	
			}

		}
		stop();
	}

	
	public void keyTyped(KeyEvent e) {

		
	}

	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			player.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			player.down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			player.left = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			player.right = true;
		}

	}

	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			player.down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			player.left = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			player.right = false;
		}
		
		
		
	}
		
}
