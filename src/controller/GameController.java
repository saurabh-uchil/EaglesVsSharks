package controller;

import model.Game;

public class GameController {
	
	private Game game;
	private static GameController uniqueInstance;

	private GameController() {}
	
	// Singleton pattern implementation
	public static GameController getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new GameController();
		}
		return uniqueInstance;
	}
	
	// Returns current game
	public Game getGame() {
		return this.game;
	}
	
	// Creates and returns a new game
	public Game getNewGame(int boardSize, int[] pieceConfig, String player1Name, String player2Name) {
		this.game = new Game(boardSize, pieceConfig);
		this.game.getPlayers()[0].setUserName(player1Name);
		this.game.getPlayers()[1].setUserName(player2Name);
		return this.game;
	}
	
	// Loads the locally saved game
	public void loadGame(Game savedGame) {
		this.game = savedGame;
	}

}
