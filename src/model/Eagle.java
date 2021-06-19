package model;

public abstract class Eagle extends Piece {

	private static final long serialVersionUID = 1L;
	private boolean hasHuman;
	
	public Eagle() {
		super();
		hasHuman = false;
	}

	public boolean hasHuman() {
		return hasHuman;
	}

	public void pickupHuman() {
		this.hasHuman = true;
	}
	
	// Maps the valid moves available for the piece 
	public void setValidMoves(Board b){
		
		// Retrieves the square where the piece is located
		Square square = b.getSquareOfPiece(this);
		int rowPos = square.getRowValue();
		int colPos = square.getColumnValue();
		int moveDistance = this.moveRange;
		int boardSize = Game.getBoardSize();
		
		// resets the valid moves map
		resetValidMoves();
		
		int newRow, newCol;
		
		// Set valid moves Bottom right diagonally
		for(int i = 1; i <= moveDistance; i++) {
			newRow = rowPos + i;
			newCol = colPos + i;
			if(newRow > (boardSize-1) || newRow < 0 || newCol > (boardSize-1) || newCol < 0)
				break;
			else if(b.getSquare(newRow, newCol).hasObstacle() || b.getSquare(newRow, newCol).isSharkSpawnPoint())
				break;
			else if(b.getSquare(newRow, newCol).getPiece()!= null) {
				if(b.getSquare(newRow, newCol).getPiece().getPlayer() != this.player)
					this.validMoves[newRow][newCol] = true;
				break;
			}
			else
				this.validMoves[newRow][newCol] = true;
		}
		
		// Set valid moves Top right diagonally
		for(int i = 1; i <= moveDistance; i++) {
			newRow = rowPos - i;
			newCol = colPos + i;
			if(newRow > (boardSize-1) || newRow < 0 || newCol > (boardSize-1) || newCol < 0)
				break;
			else if(b.getSquare(newRow, newCol).hasObstacle() || b.getSquare(newRow, newCol).isSharkSpawnPoint())
				break;
			else if(b.getSquare(newRow, newCol).getPiece()!= null) {
				if(b.getSquare(newRow, newCol).getPiece().getPlayer() != this.player)
					this.validMoves[newRow][newCol] = true;
				break;
			}
			else
				this.validMoves[newRow][newCol] = true;	
		}
		
		// Set valid moves Bottom left diagonally
		for(int i = 1; i <= moveDistance; i++) {
			newRow = rowPos + i;
			newCol = colPos - i;
			if(newRow > (boardSize-1) || newRow < 0 || newCol > (boardSize-1) || newCol < 0)
				break;
			else if(b.getSquare(newRow, newCol).hasObstacle() || b.getSquare(newRow, newCol).isSharkSpawnPoint())
				break;
			else if(b.getSquare(newRow, newCol).getPiece()!= null) {
				if(b.getSquare(newRow, newCol).getPiece().getPlayer() != this.player)
					this.validMoves[newRow][newCol] = true;
				break;
			}
			else
				this.validMoves[newRow][newCol] = true;	
		}
		
		// Set valid moves Top left diagonally
		for(int i = 1; i <= moveDistance; i++) {
			newRow = rowPos - i;
			newCol = colPos - i;
			if(newRow > (boardSize-1) || newRow < 0 || newCol > (boardSize-1) || newCol < 0)
				break;
			else if(b.getSquare(newRow, newCol).hasObstacle() || b.getSquare(newRow, newCol).isSharkSpawnPoint())
				break;
			else if(b.getSquare(newRow, newCol).getPiece()!= null) {
				if(b.getSquare(newRow, newCol).getPiece().getPlayer() != this.player)
					this.validMoves[newRow][newCol] = true;
				break;
			}
			else
				this.validMoves[newRow][newCol] = true;	
		}
		
	}
	
}
