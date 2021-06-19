package model;

public abstract class AbstractFactory {
	public abstract Piece getPiece(String pieceType, Player player);
}
