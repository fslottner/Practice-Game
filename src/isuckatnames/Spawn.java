package isuckatnames;

public class Spawn {

	private Handler handler;
	private HUD hud;

	private int scoreKeep = 800;
	private static boolean incrSC = true;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public static void onDie() {
		incrSC = false;
	}

	public void tick() {
		if (incrSC) {
			scoreKeep++;
		}
		
		if (scoreKeep >= 1000) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			int level = hud.getLevel();
			switch (level) {
			case 1:
				handler.addObject(new BasicEnemy(0, 0, handler));
				break;
			case 2:
				handler.addObject(new BasicEnemy(Game.WIDTH - 35, Game.HEIGHT - 65, handler));
				break;
			case 3:
				handler.addObject(new BasicEnemy(200, 0, handler));
				break;
			case 4:
				handler.addObject(new BasicEnemy(500, Game.HEIGHT - 65, handler));
				break;
			case 5:
				handler.addObject(new FastEnemy(0, Game.HEIGHT / 2, handler));
				break;
			case 6:
				handler.addObject(new BasicEnemy(0, 250, handler));
				//handler.addObject(new BasicEnemy(0, 400, ID.BasicEnemy, handler));
				break;
			case 7:
				handler.addObject(new FastEnemy(Game.WIDTH - 45, Game.HEIGHT /2 + 20, handler));
				break;
			case 8:
				handler.clearEnemies();
				handler.addObject(new BossEnemy(Game.WIDTH / 2 - 45, -120, handler));
				break;
			case 10:
				handler.clearEnemies();
				hud.boostLife(50);
				handler.addObject(new SlowEnemy(Game.WIDTH - 35, 0, handler));
				handler.addObject(new SlowEnemy(0, Game.HEIGHT - 65, handler));
				handler.addObject(new SmartEnemy(Game.WIDTH / 2 - 10, 0, handler));
				break;
			case 11:
				handler.addObject(new SlowEnemy(Game.WIDTH - 35, Game.HEIGHT - 65, handler));
				break;
			case 12:
				handler.addObject(new FastDigEnemy(0, 0, handler));
				break;
			case 13:
				handler.addObject(new SlowEnemy(Game.WIDTH - 35, Game.HEIGHT - 65, handler));
				break;
			case 14:
				handler.addObject(new FastDigEnemy(0, Game.HEIGHT / 2, handler));
				break;
			case 15:
				handler.addObject(new SlowEnemy(0, Game.HEIGHT - 65, handler));
				break;
			case 16:
				handler.addObject(new FastDigEnemy(40, Game.HEIGHT - 65, handler));
				break;
			case 17:
				handler.clearEnemies();
				System.out.println("boss");
				handler.addObject(new BossEnemy2(0, 0, handler));
				break;
			case 19:
				handler.clearEnemies();
				handler.addObject(new StealthEnemy(0, Game.HEIGHT / 2, handler));
				handler.addObject(new StealthEnemy(Game.WIDTH - 35, Game.HEIGHT / 2, handler));
				break;
				
			}
		}

	}
}
