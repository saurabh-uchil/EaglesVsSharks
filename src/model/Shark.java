package model;

public abstract class Shark extends Piece {
	
	private static final long serialVersionUID = 1L;
	
	public Shark() {
		super();
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
		
		// Check is super power active for devil sharks
		if(isDevilPowerActive) {
			for(int i=rowPos-moveDistance; i <= rowPos+moveDistance; i++) {
				for(int j=colPos-moveDistance; j <= colPos+moveDistance; j++) {
					if(i<0 || j<0 || i>boardSize-1 || j>boardSize-1)
						continue;
					else if(b.getSquare(i, j).hasObstacle() || b.getSquare(i, j).isEagleSpawnPoint())
						continue;
					else if(b.getSquare(i, j).getPiece()!= null) { 
						if(b.getSquare(i, j).getPiece().getPlayer() != this.player)
							this.validMoves[i][j] = true;
						continue;
					}
					else 
						this.validMoves[i][j] = true;
				}
			}
		}
		else {
			// Set valid moves Vertically down
			for(int r=rowPos+1; r<=rowPos+moveDistance; r++) {
				if(r > boardSize-1)
					break;
				else if(b.getSquare(r, colPos).hasObstacle() || b.getSquare(r, colPos).isEagleSpawnPoint())
					break;
				else if(b.getSquare(r, colPos).getPiece()!= null) { 
					if(b.getSquare(r, colPos).getPiece().getPlayer() != this.player)
						this.validMoves[r][colPos] = true;
					break;
				}
				else 
					this.validMoves[r][colPos] = true;
			}
			
			// Set valid moves Vertically up
			for(int r=rowPos-1; r>=rowPos-moveDistance; r--) {
				if(r < 0)
					break;
				else if(b.getSquare(r, colPos).hasObstacle() || b.getSquare(r, colPos).isEagleSpawnPoint())
					break;
				else if(b.getSquare(r, colPos).getPiece() !=null) { 
					if(b.getSquare(r, colPos).getPiece().getPlayer() != this.player)
						this.validMoves[r][colPos] = true;
					break;
				}
				else
					this.validMoves[r][colPos] = true;
			}
			
			// Set valid moves Horizontally right
			for(int c=colPos+1; c<=colPos+moveDistance; c++) {
				if(c > boardSize-1)
					break;
				else if(b.getSquare(rowPos, c).hasObstacle() || b.getSquare(rowPos, c).isEagleSpawnPoint())
					break;
				else if(b.getSquare(rowPos, c).getPiece() !=null) {
					if(b.getSquare(rowPos, c).getPiece().getPlayer() != this.player)
						this.validMoves[rowPos][c] = true;
					break;
				}
				else
					this.validMoves[rowPos][c] = true;
			}
			
			// Set valid moves Horizontally left
			for(int c=colPos-1; c>=colPos-moveDistance; c--) {
				if(c < 0)
					break;
				else if(b.getSquare(rowPos, c).hasObstacle() || b.getSquare(rowPos, c).isEagleSpawnPoint())
					break;
				else if(b.getSquare(rowPos, c).getPiece() != null) {
					if(b.getSquare(rowPos, c).getPiece().getPlayer() != this.player)
						this.validMoves[rowPos][c] = true;
					break;
				}
				else
					this.validMoves[rowPos][c] = true;
			}
		}
		
		// Revoke shark from move into island
		this.validMoves[(boardSize-1)/2][(boardSize-1)/2] = false;
	}

}
