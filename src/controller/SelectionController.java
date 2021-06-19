package controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.Board;
import model.Eagle;
import model.Game;
import model.Piece;
import model.Shark;
import view.GameClient;
import view.SquareView;

public class SelectionController implements EventHandler<MouseEvent> {

	private Game game;
	private GameClient gameClient;

	public SelectionController(Game game, GameClient gameClient) {
		this.game = game;
		this.gameClient = gameClient;
	}

	@Override
	public void handle(MouseEvent e) {
		
		// Gets the row & column of selected square
		SquareView vSelectedSquare = (SquareView) e.getSource();
		int newRow = vSelectedSquare.getRow();
		int newColumn = vSelectedSquare.getColumn();
		
		// Gets the current game
		Board board = game.getBoard();
		
		if (vSelectedSquare.getIsSelected()) {
			/* If clicking on already selected square, clear all selections */
			gameClient.clearSelection();
			
		}
		else if (vSelectedSquare.getIsValidMove()) {
			/* If selected destination is a valid move, move and update UI */

			// Gets the piece from the destination, if available
			Piece removedPiece = game.getBoard().getSquare(newRow, newColumn).getPiece();
			
			// Gets the original position of the piece
			int oldRow = board.getSelectedSquare().getRowValue();
			int oldColumn = board.getSelectedSquare().getColumnValue();
			Piece movingPiece = board.getSelectedSquare().getPiece();

			game.movePiece(newRow, newColumn);
			
			// Update view corresponding to the move
			SquareView oldSqr = (SquareView) gameClient.getNodeById("#SQ_" + oldRow + "_" + oldColumn);
			oldSqr.setPiece("");
			SquareView newSqr = (SquareView) gameClient.getNodeById("#SQ_" + newRow + "_" + newColumn);
			newSqr.setPiece(movingPiece);
			// If a piece is removed, add to the left panel removed list
			if(removedPiece != null) {
				gameClient.addToRemoved(removedPiece);
			}
			// Clears all selection on board
			gameClient.clearSelection();
			// Toggle the player turn
			game.togglePlayer();
			
			if (game.getIsGameEnded()) {
				gameClient.showWinMessage(game.getWinnerName(), game.getWinnerTeam());
			} else {
				gameClient.toggleUndoButtons();
				// Show new turn message in the client
				gameClient.showTurnMessage();
			}
		}
		// Check if a piece is selected for first time
		else if (board.getSquare(newRow, newColumn).getPiece() != null 
				&& !board.getSquare(newRow, newColumn).getPiece().isParalyzed()
				&& !game.getIsGameEnded()) {			
			// Gets the selected square and assign it
			Piece selectedPiece = board.getSquare(newRow, newColumn).getPiece();
			
			if (selectedPiece.getPlayer().getTeam().equalsIgnoreCase(game.getActivePlayer().getTeam())) {
				/* If the selected piece belongs to active player */
				
				// Sets the current square 
				board.setSelectedSquare(board.getSquare(newRow, newColumn));
				// Sets the valid moves map for the piece
				selectedPiece.setValidMoves(board);
				// Gets the valid moves map
				boolean[][] arrValidMoves = selectedPiece.getValidMoves();
				
				gameClient.updateHighlights(arrValidMoves, vSelectedSquare);				
				gameClient.showMessage("SELECTED PIECE : " + selectedPiece.getClass().getSimpleName());
				
				Button btnSuperPower = (Button)gameClient.getNodeById("#SUPERPOWERBTN");
				
				if(!selectedPiece.isHasUsedSuperPower())
					btnSuperPower.setDisable(false);
			}
		}
		else if (board.getSquare(newRow, newColumn).getPiece() != null 
				&& board.getSquare(newRow, newColumn).getPiece().isParalyzed()
				&& !game.getIsGameEnded()) {
			if(board.getSquare(newRow, newColumn).getPiece() instanceof Eagle) {
				gameClient.showMessage("This eagle is frozen by an Ice Shark");
			}
			else if(board.getSquare(newRow, newColumn).getPiece() instanceof Shark) {
				gameClient.showMessage("This shark is paralyzed by a Thunder Eagle");
			}
		}
	}
	
}
