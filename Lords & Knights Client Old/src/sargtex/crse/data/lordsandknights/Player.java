/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.crse.data.lordsandknights;

import java.util.*;

/**
 *
 * @author Pathos
 */
public class Player {
	private String nick;
	private int points;
	private boolean isOnVacation;
	private Alliance alliance;
	private int alliancePermission;
	private int id;
	private int rank;
	private int gold;
	private int loginId;
	private String sessionID;
	
	
	// new fields
	private String lastLoginDate;
	private String lastReadFormDate;
	// TODO set real datatype to fetch content
	private String[] allianceInvitationArray;
	private int unreadReportCount;
	private String touchDate;
	private int unreadDiscussionCount;
	private String lastLoginIP;
	private int remainingVacationHours;
	private Map<String, Habitat> habitatDictionary;
	
	/**
	 * Returns the habitat dictionary of the user.
	 * 
	 * @return all habitats of the user
	 */
	public Map<String, Habitat> getHabitats() {
		return this.habitatDictionary;
	}
	
	/**
	 * Returns the list of habitats the player owns.
	 * 
	 * @return all habitats of the user
	 */
	public Habitat[] getHabitatList() {
		Habitat[] habitats = new Habitat[habitatDictionary.size()];
		Iterator<String> iterator = habitatDictionary.keySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			habitats[i] = habitatDictionary.get(iterator.next());
			++i;
		}
		
		return habitats;
	}
	
	/**
	 * Returns the player's ID.
	 * 
	 * @return int the id
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Returns the id of the current session.
	 * 
	 * @return String the session id
	 */
	public String getSessionID() {
		return this.sessionID;
	}
	
	
}
