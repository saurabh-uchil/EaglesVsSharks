package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Game;
import view.GameClient;

public class StartNewGameController implements EventHandler<ActionEvent> {

	private GameClient gameClient;
		
	public StartNewGameController(GameClient gameClient) {
		this.gameClient = gameClient;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if(gameClient.validateGameConfig()) {
			int boardSize = gameClient.getBoardSize();
			String p1Name = gameClient.getPlayer1Name();
			String p2Name = gameClient.getPlayer2Name();
			// Creates a new game and show it
			Game newGame = gameClient.getGameController().getNewGame(boardSize,gameClient.getPieceConfigArray(),p1Name,p2Name);
			gameClient.showGameBoard(boardSize,newGame);
			gameClient.toggleUndoButtons();
			Button btnSaveGame = (Button) gameClient.getNodeById("#SAVEGAMEBTN");
			btnSaveGame.setDisable(false);
		}
	}

}
