package isuckatnames;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class StealthEnemy extends GameObject {
	
	private Handler handler;

	private int width = 20, height = 20;
	private float alpha = 0.015f;
	private float alphaStep = 0.003f;
	private float maxAlpha = 0.3f; 
	
	private Color color = Color.RED;
	public StealthEnemy(float x, float y, Handler handler) {
		super(x, y);
		
		this.handler = handler;
		
		velX = 4;
		velY = 4;
		
		id = ID.StealthEnemy;
	}

	public void tick() {
		x += velX;
		y += velY;
				
		if (y <= 0 || y >= Game.HEIGHT - 55)
			velY *= -1;
		
		if (x <= 0 || x >= Game.WIDTH - 30)
			velX *= -1;
		
		if (alpha >= (maxAlpha - alphaStep) || alpha <= (0 - alphaStep)) {
			alphaStep *= -1;
		} 
		alpha += alphaStep;
		
		handler.addObject(new Trail((int) x, (int) y, Color.red, 20, 20, alpha, alpha / 20, handler));
		
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);
		
		g2d.setComposite(makeTransparent(1));
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y, width, height);
	}

}
