/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import sargtex.crse.data.lordsandknights.Habitat;
import sargtex.crse.data.lordsandknights.MapPosition;
import sargtex.util.MathUtil;

/**
 *
 * @author SargTeX
 */
public class CoordinateSortTest {
	
	public static void main(String[] args) {
		ArrayList<MapPosition> positionList = new ArrayList<MapPosition>();
		final MapPosition ps = new MapPosition(10, 10);
		
		// fill position list with random positions
		for (int i = 0; i < 1000; ++i) {
			positionList.add(new MapPosition((int) MathUtil.getRandom(0, 20), (int) MathUtil.getRandom(0, 20)));
		}
		
		Collections.sort(positionList, new Comparator<MapPosition>() {
					@Override
					public int compare(MapPosition p1, MapPosition p2) {
						if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) return 0;
						
						// compare if at row 0
						if (MathUtil.getDifference(p1.getY(), ps.getY()) == 0 && MathUtil.getDifference(p2.getY(), ps.getY()) == 0) {
							if ((ps.getX() - p1.getX()) == 0) return (p1.getX() < p2.getX()) ? 1 : -1;
							else if ((ps.getX() - p2.getX()) == 0) return (p1.getX() < p2.getX()) ? 1 : -1;
							else if ((ps.getX() - p1.getX()) < 0 && ((ps.getX() - p2.getX()) < 0)) return (p1.getX() < p2.getX()) ? -1 : 1;
							else if ((ps.getX() - p1.getX()) > 0 && ((ps.getX() - p2.getX()) > 0)) return (p1.getX() > p2.getX()) ? -1 : 1;
							else if ((ps.getX() - p1.getX()) < 0) return 1;
							else return -1;
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
		
		// list sorted list
		for (int i = 0; i < positionList.size(); ++i) {
			System.out.println(positionList.get(i).getX()+" | "+positionList.get(i).getY());
		}
	}
	
}
