package model;

import util.Constants;

public class EagleFactory extends AbstractFactory {

	@Override
	public Piece getPiece(String pieceType, Player player) {
		Piece piece;
		
		if(pieceType.equalsIgnoreCase(Constants.PIECE_SWIFTEAGLE))
			piece = new SwiftEagle();
		else if(pieceType.equalsIgnoreCase(Constants.PIECE_THUNDEREAGLE))
			piece = new ThunderEagle();
		else if(pieceType.equalsIgnoreCase(Constants.PIECE_ANGELEAGLE))
			piece = new AngelEagle();
		else
			return null;
		
		piece.setPlayer(player);
		return piece;
	}

}
