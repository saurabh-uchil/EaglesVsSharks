package model;

import java.io.Serializable;

public class PieceMovement implements Serializable {

	private static final long serialVersionUID = 1L;
	private int originRow,originCol,destinationRow,destinationCol;

	public PieceMovement( Square originSqr, int destRow, int destCol) {
		this.originRow = originSqr.getRowValue();
		this.originCol = originSqr.getColumnValue();
		this.destinationRow = destRow;
		this.destinationCol = destCol;

	}

	// Executes the movement for the piece 
	public void executeMovement(Board board, Game game) {

		Piece removedPiece = game.getBoard().getSquare(destinationRow, destinationCol).getPiece();
		// Gets the original position of the piece
		Piece movingPiece = board.getSelectedSquare().getPiece();

		// Moves the piece to the valid location
		if (movingPiece.isValidMove(board.getSquare(destinationRow, destinationCol))) {
			board.getSquare(originRow, originCol).releaseSquare();
			board.getSquare(destinationRow, destinationCol).occupySquare(movingPiece);

			// If moving piece is eagle, do game end validations
			if (movingPiece instanceof Eagle) {
				if (!game.getIsHumanPickedUp() && board.getSquare(destinationRow, destinationCol).isIslandPoint()) {
					((Eagle) movingPiece).pickupHuman();
					game.setIsHumanPickedUp(true);
				} else if (((Eagle) movingPiece).hasHuman() && board.getSquare(destinationRow, destinationCol).isEagleSpawnPoint()) {
					game.setIsGameEnded(true);
					game.setWinnerIndex(game.getActiveIndex());
				}
			}
		}

		// Sets the valid moves for the moved piece
		movingPiece.setValidMoves(board);
		
		// Check if eagle carrying human is killed, then end game
		if(removedPiece != null) {
			if(removedPiece instanceof Eagle) {
				if(((Eagle)removedPiece).hasHuman()) {
					game.setIsGameEnded(true);
					game.setWinnerIndex(game.getActiveIndex());
				}
			}
		}

	}

}
