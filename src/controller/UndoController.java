package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.GameClient;

public class UndoController implements EventHandler<ActionEvent> {

	private GameClient gameClient;
		
	public UndoController(GameClient gameClient) {
		this.gameClient = gameClient;
	}

	@Override
	public void handle(ActionEvent event) {
		int undoCount = gameClient.getUndoCount();
		// If undo operation successfull, update the view
		if(gameClient.getGameController().getGame().undoMoves(undoCount)) {
			gameClient.updateBoard();
			gameClient.getGameController().getGame().getActivePlayer().setIsUndoDone(true);
			gameClient.showMessage("Undo moves completed!");
			Button btnSource = (Button) event.getSource();
			btnSource.setDisable(true);
		}
		else {
			gameClient.showMessage("Undo operation is not possible with the selected parameters!");
		}
		
	}
	
	
}
