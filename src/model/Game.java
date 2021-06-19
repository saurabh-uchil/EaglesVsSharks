package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import util.Constants;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Game uniqueGameInstance;
	private Board board;
	private Player[] players;
	private int activeIndex;
	private int saveBoardSize;
	private static int boardSize;
	private CareTaker caretaker;
	private boolean isHumanPickedUp;
	private boolean isGameEnded;
	private int winnerIndex;
	private PieceMovement pieceMovement;

	public Game(int boardSize, int[] pieceConfig) {
		Game.boardSize = boardSize;
		saveBoardSize = boardSize;
		this.board = new Board(boardSize);
		this.players = new Player[2];
		this.activeIndex = 0;
		caretaker = new CareTaker();
		initializeGame(pieceConfig);
	}

	// Singleton pattern implementation
	public static Game getGameInstance(int boardSize, int[] pieceConfig) {
		if (uniqueGameInstance == null) {
			uniqueGameInstance = new Game(boardSize, pieceConfig);
		}
		return uniqueGameInstance;
	}
	
	// Getters and Setters
	public Board getBoard() {
		return this.board;
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public int getActiveIndex() {
		return this.activeIndex;
	}

	// Returns the active player
	public Player getActivePlayer() {
		return this.players[activeIndex];
	}
	
	// Returns the inActive player
	public Player getInActivePlayer() {
		return this.players[1 - activeIndex];
	}

	public static int getBoardSize() {
		return Game.boardSize;
	}
	
	public static void setBoardSize(int boardSize) {
		Game.boardSize = boardSize;
	}
	
	public int getSaveBoardSize() {
		return this.saveBoardSize;
	}
	
	public boolean getIsGameEnded() {
		return this.isGameEnded;
	}
	
	public void setIsGameEnded(boolean isEnded) {
		this.isGameEnded = isEnded;
	}
	
	public void setWinnerIndex(int index) {
		this.winnerIndex = index;
	}
	
	public String getWinnerName() {
		return this.players[winnerIndex].getUserName();
	}
	
	public String getWinnerTeam() {
		return this.players[winnerIndex].getTeam();
	}
	
	public void setIsHumanPickedUp(boolean isPickedUp) {
		this.isHumanPickedUp = isPickedUp;
	}
	
	public boolean getIsHumanPickedUp() {
		return this.isHumanPickedUp;
	}

	// Toggles play turn and adds memento
	public void togglePlayer() {
		this.activeIndex = 1 - this.activeIndex;

		// Deactivate previously activated super powers
		boolean isAnyPieceAvailable = false;
		ArrayList<Piece> playerPieces = board.getPiecesOfPlayer(getActivePlayer());
		for (Piece p : playerPieces) {
			if (p.isSuperPowerActive()) {
				p.deActivateSuperPower(board);
				p.setIsSuperPowerActive(false);
			}
			if (p.getIsAvailable()) {
				isAnyPieceAvailable = true;
			}
		}

		// Check for game end
		if (!isAnyPieceAvailable) {
			this.isGameEnded = true;
			this.winnerIndex = 1 - getActiveIndex();
		}
		caretaker.addMemento(board);
	}
	
	// Initialize the game pieces and players using AbstractFactory
	private void initializeGame(int[] pieceConfig) {
		// Create players and assign team
		players[0] = new Player(Constants.TEAM_EAGLES);
		players[1] = new Player(Constants.TEAM_SHARKS);
		
		ArrayList<Piece> piecesArr = new ArrayList<Piece>();
		boolean isEagle = true;
		Player player = players[0];
		AbstractFactory pieceFactory;
		String[] pieceTypes= {Constants.PIECE_SWIFTEAGLE,Constants.PIECE_THUNDEREAGLE,Constants.PIECE_ANGELEAGLE,
				Constants.PIECE_ASSASSINSHARK,Constants.PIECE_ICESHARK,Constants.PIECE_DEVILSHARK};
		int[] rows = new int[] {0,0,boardSize-1,boardSize-1};
		int[] cols = new int[] {0,boardSize-1,0,boardSize-1};
		int posIndex = 0;
		Piece piece;
		
		// Create objects using Abstract factory pattern
		for(int i=0; i < pieceConfig.length; i++) {
			if (i == 3) {
				isEagle = false;
				player = players[1];
				posIndex = 0;
				rows = new int[] {0,(boardSize-1)/2,(boardSize-1)/2,boardSize-1};
				cols = new int[] {(boardSize-1)/2,0,boardSize-1,(boardSize-1)/2};
			}
			pieceFactory = FactoryProducer.getFactory(isEagle);
			for(int j=0; j < pieceConfig[i]; j++) {
				piece = pieceFactory.getPiece(pieceTypes[i], player);
				board.getSquare(rows[posIndex],cols[posIndex]).occupySquare(piece);
				piecesArr.add(piece);
				posIndex++;
			}
		}
		
		// place obstacles in the board
		placeObstacles(boardSize);
		
		// Set the valid moves for the created pieces
		for(int index=0; index < piecesArr.size(); index++) {
			piecesArr.get(index).setValidMoves(board);
		}
		
		// Add memento for initial state
		caretaker.addMemento(board);
	}

	// Add obstacles to the board at random places
	private void placeObstacles(int obstacleCount) {
		for (int i = 0; i < obstacleCount; i++) {
			board.getRandomVacantSquare().addObstacle();
		}
	}

	// Execute undo moves using Memento pattern
	public boolean undoMoves(int moveCount) {
		boolean isRestored = false;
		try {
			// Double input to get actual memento count in stack
			int actualMoveCount = moveCount * 2;
			if(actualMoveCount < caretaker.getStackSize()) {
				for(int i=1; i<=actualMoveCount; i++) {
					caretaker.getMemento();
				}
				board.restoreFromMemento(caretaker.getMemento());
				isRestored = true;
			}
		}
		catch(NoSuchElementException e) {
			System.out.println("Reached the end of undo move stack");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return isRestored;
	}
	
	// Uses the Facade class "PieceMovement" for executing the piece movement and capture
	public void movePiece(int destRow, int destCol) {
		Square originSqr = board.getSelectedSquare();
		pieceMovement = new PieceMovement(originSqr, destRow, destCol);
		pieceMovement.executeMovement(board, this);

	}

}
