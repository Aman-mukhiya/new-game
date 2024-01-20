package main;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
	
	// screen settings 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int originalTileSize = 16;// 16x16 tile size
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;// 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;// 768 pixels 
	public final int screenHeight = tileSize * maxScreenRow;// 576 pixels
	// FPS 
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	
	Thread gameThread;
	
	Player player = new Player(this,keyH);
	
	// set palyer's default position
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);// simply improves the game rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true);//
		
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);// thread is created.
		gameThread.start();
	}

	@Override
	public void run() {
		/*
		 // game loop is created 
		
		// simply delaying the game update time 
		double drawInterval = 1000000000/FPS; // 0.016666 seconds is delay for 60fps
		double nextDrawTime = System.nanoTime()+ drawInterval;// adding the delay in system
		
		while (gameThread != null) {
			
			long currentTime = System.nanoTime(); //show exactly how much time does the system take
			// to do this process. we are goona slow it down to show it to uor nacked eyes.
			
			System.out.println("current Time :"+ currentTime);
			
			// game will run till this thread exists 
			//  1 UPDATE : update the information such as character position
			update();
			
			
			//  2 DRAW : draw the  screen with updated information 
			repaint();// paint component is being called because its a built in class
			// feature its kind of confusing but this is how you call it.
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;// chaing into mili second
				if (remainingTime < 0) {
					remainingTime = 0;
					// if remaining time is less than 0 means there is no time to sleep be execute 
					// to make sure that doesn't happen we check and update the time to 0 and add remaing time to it
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	 
		 */
		
		// new method for delaying the loop
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime ;
		long timer = 0;
		int drawCount = 0;
		
		
		while (gameThread != null ) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime)/ drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS:"+ drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
		
		
		
	}
	
	
	public void update() {
		
	
	player.update();
		
		
	}
	
	public void paintComponent (Graphics g) {
		
		// built in method class in java 
		
		super.paintComponent(g);// this super means parent class of JPanel because this
		// gamepanel is a subclass of JPanel. its a practice set by java to make this work
	
		Graphics2D g2 = (Graphics2D)g; // Graphics2D class extends the garphics class
		// for more features .
		
		tileM.draw(g2);//drawing tiles before the player will allow to overdraw the player over tile 
		//making player visible or else the player will hide under the tile. 
		
		player.draw(g2);
	}
	
	
	
	

}
