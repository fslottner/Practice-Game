package isuckatnames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossEnemy2Bullet extends GameObject {

	private Handler handler;

	private Color color = Color.ORANGE;

	private int width = 20, height = 20;
	private int bounce = 2;

	public BossEnemy2Bullet(float x, float y, float velX, float velY, Handler handler) {
		super(x, y);
		this.handler = handler;

		this.velX = velX;
		this.velY = velY;
		
		id = ID.BossEnemy2Bullet;

	}

	public void tick() {

		x += velX;
		y += velY;

		// remove when off-screen
		if (x <= -10 || x >= Game.WIDTH || y <= -10 || y >= Game.HEIGHT) {
			handler.removeObject(this);
		}

		handler.addObject(new Trail((int) x, (int) y, color, width, height, 1, 0.05f, handler));

		if ((x <= 0 || x >= Game.WIDTH - 25) && bounce > 0) {
			velX *= -1;
			bounce--;
		}

		if ((y <= 0 || y >= Game.HEIGHT - 50) && bounce > 0) {
			velY *= -1;
			bounce--;	
		}
		

	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

}
