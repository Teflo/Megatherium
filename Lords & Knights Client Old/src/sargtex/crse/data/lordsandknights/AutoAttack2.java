/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.crse.data.lordsandknights;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import sargtex.config.lordsandknights.Config;
import sargtex.config.lordsandknights.OnTheWayConfig;
import sargtex.util.MathUtil;
import sargtex.util.ReportUtil;

/**
 *
 * @author Pathos
 */
public class AutoAttack2 extends Thread {

	private boolean pause = true;
	private boolean proceed;

	public AutoAttack2() {
	}

	public void pause() {
		pause = true;
	}

	public void proceed() {
		pause = false;
		notify();
	}

	@Override
	public void run() {
		while (true) {
			// pause thread
			synchronized (this) {
				while (pause) {
					try {
						wait();
					} catch (InterruptedException ex) {
						ReportUtil.add(ex);
					}
				}
			}

			// get source habitat list in correct order
			ArrayList<Habitat> sourceHabitatList = OnTheWayConfig.getInstance().getSourceHabitatList();

			// loop through those habitats
			for (int i = 0; i < sourceHabitatList.size(); ++i) {
				// get current source habitat
				final Habitat source = sourceHabitatList.get(i);

				// fetch attack targets for this source habitat in correct order
				ArrayList<Habitat> targetHabitatList = Config.getInstance().getTargets(source);
				Collections.sort(targetHabitatList, new Comparator<Habitat>() {
					@Override
					public int compare(Habitat o1, Habitat o2) {
						// fetch positions
						MapPosition p1 = o1.getPosition();
						MapPosition p2 = o2.getPosition();
						MapPosition ps = source.getPosition();
						
						// compare if at row 0
						if (MathUtil.getDifference(p1.getY(), ps.getY()) == 0 && MathUtil.getDifference(p2.getY(), ps.getY()) == 0) {
							return (p1.getX() > p2.getX()) ? -1 : 1;
						}

						// compare
						if (MathUtil.getDifference(p1.getY(), ps.getY()) == MathUtil.getDifference(p2.getY(), ps.getY())) {
							// diff(p1.y|source.y) == diff(p2.y|source.y)
							if (p1.getY() == p2.getY()) {
								if ((p1.getY() - ps.getY()) > 0) return (p1.getX() < p2.getX()) ? -1 : 1;
								else if ((p1.getY() - ps.getY()) < 0) return (p1.getX() < p2.getX()) ? 1 : -1;
							}else return (p1.getY() < p2.getY()) ? -1 : 1;
						}else if(MathUtil.getDifference(p1.getY(), ps.getY()) < MathUtil.getDifference(p2.getY(), ps.getY())) {
							return -1;
						}else return 1;

						// return sth
						return 0;
					}
				});

				// loop through habitat target list
				for (int j = 0; j < targetHabitatList.size(); ++j) {
					// get current target
					Habitat target = targetHabitatList.get(j);

					// check whether this target was already attacked in the current period and if so, stop attacking it
					if (OnTheWayConfig.getInstance().wasAttackedSince(target, System.currentTimeMillis() / 1000 - Config.getInstance().getAttackFrequency())) {
						System.out.println("Wurde attackiert: " + target.getName());
						continue;
					}

					// check for enough units
					if (!source.hasAttackUnits()) {
						Game.getInstance().addNotification(source.getName() + " besitzt nicht genügend Einheiten für den Angriff.");
						OnTheWayConfig.getInstance().getSourceHabitatList().remove(source);
						continue;
					}

					// update attacking time
					OnTheWayConfig.getInstance().updateAttackTime(target);

					// skip the attack with a possibility of 10%
					if (MathUtil.getRandom(0, 100) > 10) {
						System.out.println("attack sth!");
						// start attack
						Game.getInstance().getCommunicator().startTransit(source.getID(), target.getID());

						// substract attacking units for next round
						source.substractAttackUnits();

						// make notice that a habitat was attacked
						Game.getInstance().notifyAttack(source, target);
					} else {
						Game.getInstance().notifyAttack(source, target, true);
					}

					// wait random time
					synchronized (this) {
						try {
							double time = MathUtil.getRandom(500, 1500);
							double exp = MathUtil.getRandom(1, 1.2);
							time = Math.pow(time, exp);
							wait((long) time);
						} catch (InterruptedException ex) {
							ReportUtil.add(ex);
						}
					}
				}
			}
		}
	}
}
