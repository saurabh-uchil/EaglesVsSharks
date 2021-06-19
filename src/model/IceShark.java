package model;

public class IceShark extends Shark {

	private static final long serialVersionUID = 1L;
	private int rowPos,colPos;
	
	@Override
	public void activateSuperPower(Board board) {		
		Square sqr = board.getSquareOfPiece(this);
		rowPos = sqr.getRowValue();
		colPos = sqr.getColumnValue();
		applySuperPower(board, rowPos, colPos, true);
	}

	@Override
	public void deActivateSuperPower(Board board) {
		applySuperPower(board, rowPos, colPos, false);
		
	}
	
	// Calculates the positions and apply super power
	private void applySuperPower(Board board, int row, int col, boolean isParalyzed) {
		for (int i = 0; i < Game.getBoardSize(); i++) {
			for (int j = 0; j < Game.getBoardSize(); j++) {
				if(row == i || col == j) {
					Piece p = board.getSquare(i, j).getPiece();
					if(p != null && !p.getPlayer().getTeam().equalsIgnoreCase(this.player.getTeam()) && !p.isAngelPowerActive) {
						p.isParalyzed = isParalyzed;
					}
				}
			}
		}
	}

}
