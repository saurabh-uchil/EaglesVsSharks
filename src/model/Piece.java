package model;

import java.io.Serializable;

import util.Constants;

public abstract class Piece implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String status;
	protected Player player;
	protected boolean[][] validMoves;
	protected int moveRange;
	protected boolean isParalyzed;
	protected boolean isDevilPowerActive;
	protected boolean isAngelPowerActive;
	protected boolean hasUsedSuperPower;
	protected boolean isSuperPowerActive;
	
	public Piece() {
		this.status = Constants.STATUS_AVAILABLE;
		this.moveRange = Constants.DEFAULT_MOVE_DISTANCE;
		this.validMoves = new boolean[Game.getBoardSize()][Game.getBoardSize()];
		this.isParalyzed = false;
		this.isDevilPowerActive = false;
		this.isAngelPowerActive = false;
		this.hasUsedSuperPower = false;
		this.isSuperPowerActive = false;
	}
	
	// Getters and Setters
	public String getStatus() {
		return status;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean[][] getValidMoves(){
		return validMoves;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isParalyzed() {
		return isParalyzed;
	}

	public void setParalyzed(boolean isParalyzed) {
		this.isParalyzed = isParalyzed;
	}

	public boolean isHasUsedSuperPower() {
		return hasUsedSuperPower;
	}

	public void setHasUsedSuperPower(boolean hasUsedSuperPower) {
		this.hasUsedSuperPower = hasUsedSuperPower;
	}
	
	public boolean isSuperPowerActive() {
		return this.isSuperPowerActive;
	}

	public void setIsSuperPowerActive(boolean isActive) {
		this.isSuperPowerActive = isActive;
	} 

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	// Check if the piece is available on board
	public boolean getIsAvailable() {
		if(status.equalsIgnoreCase(Constants.STATUS_AVAILABLE))
			return true;
		else
			return false;
	}
	
	// Sets the valid moves map
	public abstract void setValidMoves(Board b);
	
	// Activates the super power of the piece
	public abstract void activateSuperPower(Board b);
	
	public abstract void deActivateSuperPower(Board b);
	
	// Checks if the square is a valid move for the piece
	public boolean isValidMove(Square newPosition) {			
		return validMoves[newPosition.getRowValue()][newPosition.getColumnValue()];
	}
	
	// Resets the valid moves map
	protected void resetValidMoves() {
		for(int i = 0; i < validMoves.length; i++) {
			for(int j = 0; j < validMoves[0].length; j++) {
				validMoves[i][j] = false;
			}
		}
	}
	
}
