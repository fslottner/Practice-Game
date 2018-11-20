package isuckatnames;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	public void clearEnemies() {
		int rmPos = 0;
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(rmPos);

			if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy
					|| tempObject.getID() == ID.SlowEnemy || tempObject.getID() == ID.SmartEnemy
					|| tempObject.getID() == ID.BossEnemy || tempObject.getID() == ID.FastDigEnemy
					|| tempObject.getID() == ID.BossEnemy2 || tempObject.getID() == ID.BossEnemy2Collision
					|| tempObject.getID() == ID.StealthEnemy) {
				removeObject(tempObject);
			} else
				rmPos += 1;
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}
