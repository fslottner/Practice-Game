package isuckatnames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

	private Handler handler;
	public GameObject player;

	public SmartEnemy(int x, int y, Handler handler) {
		super(x, y);
		
		this.handler = handler;
		
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID() == ID.Player) {
				player = handler.object.get(i);
			}
		}
		
		velX = 5;
		velY = 5;
		
		id = ID.SmartEnemy;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 20, 20);
	}

	public void tick() {
		
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt(
				(x - player.getX()) * (x - player.getX()) +
				(y - player.getY()) * (y - player.getY()));
		
		velX = (float) ((-1.0 / distance) * diffX);
		velY = (float) ((-1.0 / distance) * diffY);

				
		if (y <= 0 || y >= Game.HEIGHT - 55)
			velY *= -1;
		
		if (x <= 0 || x >= Game.WIDTH - 30)
			velX *= -1;
		
		handler.addObject(new Trail((int) x, (int) y, Color.lightGray, 20, 20, 1, 0.05f, handler));

		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.lightGray);
		g.fillRect((int)x, (int) y, 20, 20);
		
	}

}
