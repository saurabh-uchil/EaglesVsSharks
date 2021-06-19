package model;

import java.io.Serializable;

public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String team;
	private boolean isUndoDone;
	
	public Player(String team) {
		this.team = team;
		this.isUndoDone = false;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getTeam() {
		return this.team;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	public void setIsUndoDone(boolean isUndoDone) {
		this.isUndoDone = isUndoDone;
	}
	
	public boolean getIsUndoDone() {
		return this.isUndoDone;
	}
	
}
