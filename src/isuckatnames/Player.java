package isuckatnames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;

	public Player(int x, int y, Handler handler) {
		super(x, y);
		this.handler = handler;
		id = ID.Player;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 40, 40);
	}

	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.WIDTH - 45);
		y = Game.clamp(y, 0, Game.HEIGHT - 75);

		collision();

	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy
					|| tempObject.getID() == ID.SlowEnemy || tempObject.getID() == ID.SmartEnemy
					|| tempObject.getID() == ID.BossEnemy || tempObject.getID() == ID.BossEnemyBullet
					|| tempObject.getID() == ID.FastDigEnemy || tempObject.getID() == ID.BossEnemy2Collision
					|| tempObject.getID() == ID.BossEnemy2Bullet || tempObject.getID() == ID.StealthEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					// collision code

					HUD.HEALTH -= 1;
				}
			}

		}
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 40, 40);
	}

}
