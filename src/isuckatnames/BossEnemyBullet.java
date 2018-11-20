package isuckatnames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject {
	
	private Handler handler;
	private Random r = new Random();

	public BossEnemyBullet(int x, int y, Handler handler) {
		super(x, y);
		
		this.handler = handler;
		
		velX = r.nextInt(5 - -5) + -5;
		velY = 5;
		
		id = ID.BossEnemyBullet;
	}

	public void tick() {
		
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 55) handler.removeObject(this);
		
		handler.addObject(new Trail((int) x, (int) y, Color.red, 20, 20, 1, 0.05f, handler));

		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int) y, 20, 20);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 20, 20);
	}
	
}
