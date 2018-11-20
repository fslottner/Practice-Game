package isuckatnames;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy2 extends GameObject {

	private Handler handler;
	
	private Random r = new Random();

	// private GameObject target;
	private BossEnemy2Collision collisionBox;

	private int tpTimer = 0;
	private int appearToTp = 200;

	private float alpha = 0f;
	private float alphaStep = 0.015f;

	private Color color = Color.ORANGE;

	private int width = 90, height = 90;

	public BossEnemy2(float x, float y, Handler handler) {
		super(x, y);
		this.handler = handler;
		
		id = ID.BossEnemy2;

	}

	private void fire() {
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), 2, -6, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), 5, -5, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), 6, -2, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), 6, 2, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), 5, 5, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), 2, 6, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), -2, 6, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), -5, 5, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), -6, 2, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), -6, -2, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), -5, -5, handler));
		handler.addObject(new BossEnemy2Bullet(x + ((width - 20) / 2), y + ((height - 20) / 2), -2, -6, handler));
	}

	private void teleport() {
		/*
		x = target.getX() - ((width - 40) / 2);
		y = target.getY() - ((height - 40) / 2);
		*/
		x = r.nextInt(Game.WIDTH - width - 5);
		y = r.nextInt(Game.HEIGHT - height - 35);
		
		alpha = 0f;
		if (collisionBox != null) {
			collisionBox.destroy();
			collisionBox = null;
		}

	}

	public void tick() {
		if (alpha >= (1 - alphaStep)) {
			if (collisionBox == null) {
				alpha = 1;
				fire();
				collisionBox = new BossEnemy2Collision(x, y, handler);
				handler.addObject(collisionBox);
			}

		} else {
			alpha += alphaStep;
		}

		if (tpTimer <= 0) {
			teleport();
			tpTimer = appearToTp;
			appearToTp -= 5;
			alphaStep += 0.001f;
		} else
			tpTimer--;
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));

		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

		g2d.setComposite(makeTransparent(1));

	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 0, 0);
	}

}
