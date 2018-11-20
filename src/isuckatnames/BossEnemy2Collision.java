package isuckatnames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossEnemy2Collision extends GameObject {
	
	private Handler handler;
		
	private int width = 90, height = 90;

	public BossEnemy2Collision(float x, float y, Handler handler) {
		super(x, y);
		this.handler = handler;
		
		id = ID.BossEnemy2Collision;
	}
	
	public void destroy() {
		handler.removeObject(this);
	}

	public void tick() {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {
		// graphics in BossEnemy2§
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, width, height);
	}

}
