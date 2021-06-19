package model;

import util.Constants;

public class SharkFactory extends AbstractFactory {

	@Override
	public Piece getPiece(String pieceType, Player player) {
		Piece piece;
		
		if(pieceType.equalsIgnoreCase(Constants.PIECE_ASSASSINSHARK))
			piece = new AssassinShark();
		else if(pieceType.equalsIgnoreCase(Constants.PIECE_ICESHARK))
			piece = new IceShark();
		else if(pieceType.equalsIgnoreCase(Constants.PIECE_DEVILSHARK))
			piece = new DevilShark();
		else
			return null;
		
		piece.setPlayer(player);
		return piece;
	}

}
