package model;

public class ThunderEagle extends Eagle {
	
	private static final long serialVersionUID = 1L;
	int prevRow,prevCol;
	
	@Override
	public void activateSuperPower(Board board) {		
		Square sqr = board.getSquareOfPiece(this);
		prevRow = sqr.getRowValue();
		prevCol = sqr.getColumnValue();
		applySuperPower(board, prevRow, prevCol, true);
	}

	@Override
	public void deActivateSuperPower(Board board) {
		applySuperPower(board, prevRow, prevCol, false);
	}
	
	// Calculates the positions and apply super power
	private void applySuperPower(Board board, int rowPos, int colPos, boolean isParalyzed) {
		int newRow, newCol;
		
		for(int i = 1; i <= Game.getBoardSize(); i++) {
			newRow = rowPos + i;
			newCol = colPos + i;
			if(newRow >= Game.getBoardSize() || newCol >= Game.getBoardSize() || newRow < 0 || newCol < 0)
				break;
			Piece p = board.getSquare(newRow, newCol).getPiece();
			if(p != null && !p.getPlayer().getTeam().equalsIgnoreCase(this.player.getTeam())) {
				p.isParalyzed = isParalyzed;
			}
			
		}
		
		for(int i = 1; i <= Game.getBoardSize(); i++) {
			newRow = rowPos - i;
			newCol = colPos + i;
			if(newRow >= Game.getBoardSize() || newCol >= Game.getBoardSize() || newRow < 0 || newCol < 0)
				break;
			Piece p = board.getSquare(newRow, newCol).getPiece();
			if(p != null && !p.getPlayer().getTeam().equalsIgnoreCase(this.player.getTeam())) {
				p.isParalyzed = isParalyzed;
			}
			
		}
		
		for(int i = 1; i <= Game.getBoardSize(); i++) {
			newRow = rowPos + i;
			newCol = colPos - i;
			if(newRow >= Game.getBoardSize() || newCol >= Game.getBoardSize() || newRow < 0 || newCol < 0)
				break;
			Piece p = board.getSquare(newRow, newCol).getPiece();
			if(p != null && !p.getPlayer().getTeam().equalsIgnoreCase(this.player.getTeam())) {
				p.isParalyzed = isParalyzed;
			}
			
		}
		
		for(int i = 1; i <= Game.getBoardSize(); i++) {
			newRow = rowPos - i;
			newCol = colPos - i;
			if(newRow >= Game.getBoardSize() || newCol >= Game.getBoardSize() || newRow < 0 || newCol < 0)
				break;
			Piece p = board.getSquare(newRow, newCol).getPiece();
			if(p != null && !p.getPlayer().getTeam().equalsIgnoreCase(this.player.getTeam())) {
				p.isParalyzed = isParalyzed;
			}
			
		}
	}

}
