package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Square;
import view.GameClient;
import view.SquareView;

public class SuperPowerController implements EventHandler<ActionEvent> {

	private GameClient gameClient;
	
	public SuperPowerController(GameClient gameClient) {
		this.gameClient = gameClient;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		Square selectedSquare = this.gameClient.getGameController().getGame().getBoard().getSelectedSquare();
		// Activate the super power and update view
		selectedSquare.getPiece().activateSuperPower(gameClient.getGameController().getGame().getBoard());
		selectedSquare.getPiece().setHasUsedSuperPower(true);
		selectedSquare.getPiece().setIsSuperPowerActive(true);
		selectedSquare.getPiece().setValidMoves(this.gameClient.getGameController().getGame().getBoard());
		boolean[][] arrValidMoves = selectedSquare.getPiece().getValidMoves();
		SquareView vSelectedSqr = (SquareView)gameClient.getNodeById("#SQ_" + selectedSquare.getRowValue() + "_" + selectedSquare.getColumnValue()); 
		gameClient.updateHighlights(arrValidMoves, vSelectedSqr);
		this.gameClient.showMessage("Super power applied");
	}

}
