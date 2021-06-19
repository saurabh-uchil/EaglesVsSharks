package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Game;
import model.GameLoader;
import view.GameClient;

public class LoadGameController implements EventHandler<ActionEvent> {

	private GameClient gameClient;
	
	public LoadGameController(GameClient gameClient) {
		this.gameClient = gameClient;
	}
	
	@Override
	public void handle(ActionEvent event) {
		GameLoader gl = new GameLoader();
		Game loadedGame = gl.loadGame();
		if (loadedGame != null) {
			
			// Set the new board size
			Game.setBoardSize(loadedGame.getSaveBoardSize());
			
			// Load the new game
			gameClient.getGameController().loadGame(loadedGame);
			gameClient.showGameBoard(loadedGame.getSaveBoardSize(), loadedGame);
			
			// Enable Save button
			Button btnSaveGame = (Button) gameClient.getNodeById("#SAVEGAMEBTN");
			btnSaveGame.setDisable(false);
			gameClient.showMessage("Game loaded successfully!");
		}
		else {
			gameClient.showMessage("Game could not be loaded!");
		}
	}

}
