package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.AngelEagle;
import model.AssassinShark;
import model.DevilShark;
import model.IceShark;
import model.Piece;
import model.SwiftEagle;
import model.ThunderEagle;
import util.Constants;

public class SquareView extends StackPane{
	
	private int rowValue;
	private int columnValue;
	private boolean isSelected;
	private boolean isValidMove;
	private String defaultColor;

	public SquareView(int row, int column, String color) {
		this.rowValue = row;
		this.columnValue = column;
		this.setColor(color);
		this.setId("SQ_" + row + "_" + column);
		this.isSelected = false;
		this.defaultColor = color;
	}

	// Getters and setters
	public int getRow() {
		return rowValue;
	}

	public int getColumn() {
		return columnValue;
	}

	public boolean getIsSelected() {
		return isSelected;
	}

	public boolean getIsValidMove() {
		return isValidMove;
	}
	
	public String getDefaultColor() {
		return this.defaultColor;
	}

	public void setIsSelected(boolean b) {
		isSelected = b;
	}

	public void setIsValidMove(boolean b) {
		isValidMove = b;
	}

	// Set the piece based on the piece type
	public void setPiece(Piece piece) {
		String imagePath = null;
		
		if(piece instanceof SwiftEagle)
			imagePath = Constants.IMG_EAGLE_SWIFTEAGLE;
		else if(piece instanceof ThunderEagle)
			imagePath = Constants.IMG_EAGLE_THUNDEREAGLE;
		else if(piece instanceof AngelEagle)
			imagePath = Constants.IMG_EAGLE_ANGELEAGLE;
		else if(piece instanceof AssassinShark)
			imagePath = Constants.IMG_SHARK_ASSASSINSHARK;
		else if(piece instanceof IceShark)
			imagePath = Constants.IMG_SHARK_ICESHARK;
		else if(piece instanceof DevilShark)
			imagePath = Constants.IMG_SHARK_DEVILSHARK;		
		
		setPiece(imagePath);
	}
	
	// Sets the piece image based on the input path
	public void setPiece(String imagePath) {
		if (imagePath != null && imagePath != "") {
			ImageView img = new ImageView(imagePath);
			img.setPreserveRatio(true);
			img.setFitHeight(31);
			img.setFitWidth(31);
			img.setRotate(360 - Constants.BOARD_TILT_ANGLE); 

			this.getChildren().clear();
			this.getChildren().add(img);
		}
		else
			this.getChildren().clear();
	}

	// Highlight square
	public void highlight() {
		setColor(Constants.COLOR_HIGHLIGHT_CLICK);
	}

	// Clears the highlights
	public void removeHighlight() {
		setColor(this.defaultColor);
	}

	// Set the square color
	public void setColor(String color) {
		this.setStyle("-fx-background-color: " + color + ";-fx-border-color: black;");
	}

}
