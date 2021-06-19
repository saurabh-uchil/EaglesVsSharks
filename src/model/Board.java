package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Board implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Square[][] squares;
	private Square selectedSquare;
	
	public Board(int boardSize) {
		squares = new Square[boardSize][boardSize];
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares.length; j++) {
				this.squares[i][j] = new Square(i, j);
			}
		}
	}
	
	// Getters and setters
	public Square[][] getBoard(){
		return this.squares;
	}
	
	public Square getSelectedSquare() {
		return this.selectedSquare;
	}
	
	public void setSelectedSquare(Square square) {
		this.selectedSquare = square;
	}
	
	// Returns the square at the input position
	public Square getSquare(int x, int y) {
		return squares[x][y];
	}
	
	// Creates the memento
	public Memento createMemento() {
		Square[][] state = new Square[squares.length][squares.length];
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares.length; j++) {
				state[i][j] = (Square) deepCopy(this.squares[i][j]);
			}
		}
		return new Memento(state);
	}
	
	// Restore state from memento
	public void restoreFromMemento(Memento memento)
	{
		this.squares = memento.getSavedState();
	}
	
	// Gets random vacant squares from board
	public Square getRandomVacantSquare() {
		Random rand = new Random();
		int randomRow,randomCol;
	    
		while(true) {
			randomRow = rand.nextInt(squares.length);
			randomCol = rand.nextInt(squares.length);
			if(squares[randomRow][randomCol].getPiece() == null && !squares[randomRow][randomCol].hasObstacle() 
					&& !squares[randomRow][randomCol].isEagleSpawnPoint() 
					&& !squares[randomRow][randomCol].isSharkSpawnPoint()) {
				int centerIndex = (squares.length-1)/2;
				if(randomRow == centerIndex && randomCol == centerIndex)
					continue;
				else if(randomRow == 1 && randomCol == 1)
					continue;
				else if(randomRow == 1 && randomCol == squares.length-2)
					continue;
				else if(randomRow == squares.length-2 && randomCol == 1)
					continue;
				else if(randomRow == squares.length-2 && randomCol == squares.length-2)
					continue;
				else
					break;
			}
		}
	    return squares[randomRow][randomCol];
	}
	
	// Returns the Square where the piece is located
	public Square getSquareOfPiece(Piece piece) {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares.length; j++) {
				if(piece == this.squares[i][j].getPiece()) {
					return this.squares[i][j];
				}
			}
		}
		return null;
	}
	
	// Get a list of pieces matching the player
	public ArrayList<Piece> getPiecesOfPlayer(Player player) {
		ArrayList<Piece> playerPieces = new ArrayList<Piece>();
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares.length; j++) {
				if(this.squares[i][j].getPiece() != null) {
					if (player.getTeam().equalsIgnoreCase(this.squares[i][j].getPiece().getPlayer().getTeam())) {
						playerPieces.add(this.squares[i][j].getPiece());
					}
				}
			}
		}
		return playerPieces;
	}
	
	// Deep copy the array object
	private static Object deepCopy(Object object) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
			outputStrm.writeObject(object);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
			return objInputStream.readObject();
		} catch (Exception e) {
			System.out.println("Deep copy error");
			return null;
		}
	}
	
}
