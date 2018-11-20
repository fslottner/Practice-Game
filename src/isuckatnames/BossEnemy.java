package isuckatnames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {
	
	private Handler handler;
	private Random r = new Random();
	
	private int timer = 120;
	private int timer2 = 100;
	private int timer3 = 700;
	
	private int width = 90, height = 90;

	public BossEnemy(int x, int y, Handler handler) {
		super(x, y);
		
		this.handler = handler;
		
		velX = 0;
		velY = 1;
		
		id = ID.BossEnemy;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, width, height);
	}

	public void tick() {
		
		x += velX;
		y += velY;
		
		if (timer <= 0) velY = 0;
		else timer --;
		
		if (timer <= 0) timer2--;
		if (timer2 <= 0) {
			if (velX == 0) velX = 4;
			int spawn = r.nextInt(10);
			if (spawn == 0) handler.addObject(new BossEnemyBullet((int) x + (width / 2), (int) y + (width / 2), handler));
		}
		
		if (x <= 0 || x >= Game.WIDTH - 90)
			velX *= -1;
		
		// accelerate
		if (timer3 <= 0) {
			timer3 = 400;
			velX += Math.signum(velX);
		} else {
			timer3 --;
		}
						
		//handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.red, 90, 90, 0.05f, handler));

		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int) y, width, height);
		
	}

	
	
}
