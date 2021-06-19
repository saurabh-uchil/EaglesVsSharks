package model;

import java.io.Serializable;

import util.Constants;

public class Square implements Serializable {

	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	private Piece piece = null;
	private boolean hasObstacle;
	private boolean isEagleSpawnPoint;
	private boolean isSharkSpawnPoint;
	
	public Square(int row, int col) {
		this.row = row;
		this.column = col;
		this.hasObstacle = false;
		this.isEagleSpawnPoint = checkIfEagleSpawnPoint(row, col);
		this.isSharkSpawnPoint = checkIfSharkSpawnPoint(row, col);
	}
	
	//Getters and setters
	public int getRowValue() {
		return row;
	}
	
	public int getColumnValue() {
		return column;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public boolean hasObstacle() {
		return hasObstacle;
	}
	
	public void addObstacle() {
		this.hasObstacle = true;
	}
	
	public boolean isEagleSpawnPoint() {
		return this.isEagleSpawnPoint;
	}
	
	public boolean isSharkSpawnPoint() {
		return this.isSharkSpawnPoint;
	}
	
	// Check if square is the island point
	public boolean isIslandPoint() {
		if(row == ((Game.getBoardSize()-1)/2) && column == ((Game.getBoardSize()-1)/2))
			return true;
		else
			return false;
		
	}
	
	// Adds the piece to the square
	public boolean occupySquare(Piece newPiece) {
		if(this.piece != null) {
			this.piece.setStatus(Constants.STATUS_REMOVED);
		}
		this.piece = newPiece;
		return true;
	}
	
	// Removes the piece from the square
	public boolean releaseSquare() {
		this.piece = null;
		return true;
	}
	
	private boolean checkIfEagleSpawnPoint(int row, int col) {
		boolean isSpawnPoint = false;
		int boardSize = Game.getBoardSize();
		if(row == 0 && col == 0)
			isSpawnPoint = true;
		else if(row == 0 && col == boardSize-1)
			isSpawnPoint = true;
		else if(row == boardSize-1 && col == 0)
			isSpawnPoint = true;
		else if(row == boardSize-1 && col == boardSize-1)
			isSpawnPoint = true;
		return isSpawnPoint;
	}
	
	private boolean checkIfSharkSpawnPoint(int row, int col) {
		boolean isSpawnPoint = false;
		int boardSize = Game.getBoardSize();
		if(row == 0 && col == (boardSize-1)/2)
			isSpawnPoint = true;
		else if(row == (boardSize-1)/2 && col == 0)
			isSpawnPoint = true;
		else if(row == (boardSize-1)/2 && col == boardSize-1)
			isSpawnPoint = true;
		else if(row == boardSize-1 && col == (boardSize-1)/2)
			isSpawnPoint = true;
		return isSpawnPoint;
	}
	
	
}
