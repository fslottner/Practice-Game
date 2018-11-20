package isuckatnames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH = 100;
	
	private int greenValue = 255;
	
	private int score = 0;
	private int timer = 200;
	private int level = 18;
	private int boostTimer = 0;
	
	private int[] highscores;
	
	private boolean ded = false;
	
	public void tick() {	
		
		if (HEALTH <= 0 && !ded) {
			ded = true;		
			Spawn.onDie();
			onDie();
		}
		
		HEALTH = (int) Game.clamp(HEALTH, 0, 100);
		greenValue = (int) Game.clamp(greenValue, 0, 100);
		
		greenValue = HEALTH * 2;
		
		if (timer <= 0 && !ded) score++;
		if (timer >= 0) timer--;
		
		if (boostTimer > 0) {
			boostTimer -= 1;
			HEALTH += 1;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(10, 10, 200, 20);
		g.setColor(new Color(80, greenValue, 0));
		g.fillRect(10, 10, (int) HEALTH * 2, 20);
		g.setColor(Color.white);
		g.drawRect(10, 10, 200, 20);
		
		g.drawString("Score " + score, 15, 60);
		g.drawString("Level " + level, 15, 80);
		
		if (ded) {
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 190));
			g.drawString("DED", 127, 215);
						
			g.setFont(new Font("TimesRoman", Font.PLAIN, 70));
			int scoreStringWidth = g.getFontMetrics().stringWidth("Score:" + score);
			g.drawString("Score: " + score, (Game.WIDTH / 2) - (scoreStringWidth / 2), 300);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
			g.drawString("Highscores: ", 240, 337);
			for (int i = 0; i < highscores.length; i++) {
			g.drawString(Integer.toString(highscores[i]), 240, 377 + (30 * i));
			}
			
		}
	}
	
	private void onDie() {
		
		highscores = FileManagement.getHighestScores(score);
		
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getScore() {
		return score;
	}
	
	public void boostLife(int incr) {
		boostTimer = incr;
	}

}
