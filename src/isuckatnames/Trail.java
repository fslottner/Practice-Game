package isuckatnames;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {
	
	private float alpha = 1;
	private float alphaStep;
	
	private Handler handler;
	private Color color;

	private int width, height;
	public Trail(int x, int y, Color color, int width, int height,float alpha, float alphaStep, Handler handler) {
		super(x, y);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.alpha = alpha;
		this.alphaStep = alphaStep;
		id = ID.Trail;
	}

	public void tick() {
		if (alpha > alphaStep) {
			alpha -= (alphaStep);
		} else handler.removeObject(this);
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
		return(AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		return null;
	}

}
