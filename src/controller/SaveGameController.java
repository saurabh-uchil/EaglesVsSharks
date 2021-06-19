package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.GameSaver;
import view.GameClient;

public class SaveGameController implements EventHandler<ActionEvent> {

	private GameClient gameClient;
	
	public SaveGameController(GameClient gameClient) {
		this.gameClient = gameClient;
	}
	
	@Override
	public void handle(ActionEvent event) {
		GameSaver gs = new GameSaver();
		if(gameClient.getGameController().getGame() != null) {
			if(gs.saveGame(gameClient.getGameController().getGame())) {
				gameClient.showMessage("Saved game successfully!");
			}
			else {
				gameClient.showMessage("Game could not be saved!");
			}
		}
		else {
			gameClient.showMessage("No active game found. Please start a new game!");
		}
	}
	
}
