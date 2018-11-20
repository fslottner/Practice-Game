package isuckatnames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{
	
	private Handler handler;

	public FastEnemy(int x, int y, Handler handler) {
		super(x, y);
		
		this.handler = handler;
		
		velX = 2;
		velY = 12;
		
		id = ID.FastEnemy;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 20, 20);
	}

	public void tick() {
		
		x += velX;
		y += velY;
				
		if (y <= 0 || y >= Game.HEIGHT - 55)
			velY *= -1;
		
		if (x <= 0 || x >= Game.WIDTH - 30)
			velX *= -1;
		
		handler.addObject(new Trail((int) x, (int) y, Color.magenta, 20, 20, 1, 0.05f, handler));

		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.magenta);
		g.fillRect((int) x, (int) y, 20, 20);
		
	}
	
}
