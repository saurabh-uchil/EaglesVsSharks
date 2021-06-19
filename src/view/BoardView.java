package view;

import java.util.Random;

import controller.SelectionController;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import model.Game;
import util.Constants;

public class BoardView {
	
	private int boardSize;
	private SelectionController selectController;
	public BoardView(int boardSize) {
		this.boardSize = boardSize;
	}
	
	// Returns the game board
	public GridPane drawBoard(Game game, GameClient gameClient) {
		GridPane gBoard = new GridPane();
		gBoard.setId("gameboard");
		selectController = new SelectionController(game, gameClient);
		
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				SquareView vSquare;
				// Set land squares
				if(row == 0 && col == 0 || row == 0 && col == boardSize-1 || row == boardSize-1 && col == 0 || 
						row == boardSize-1 && col == boardSize-1) {
					vSquare = new SquareView(row, col, "LIGHTGREEN");
				}
				// Set cave squares
				else if(row == 0 && col == (boardSize-1)/2 || row == (boardSize-1)/2 && col == 0 || 
						row == (boardSize-1)/2 && col == boardSize-1 || row == boardSize-1 && col == (boardSize-1)/2) {
					vSquare = new SquareView(row, col, "LIGHTGRAY");
				}
				// Set island square
				else if(row == (boardSize-1)/2 && col == (boardSize-1)/2) {
					vSquare = new SquareView(row, col, "LIGHTGREEN");
				}
				// Set water squares
				else
					vSquare = new SquareView(row, col, "#00bfff");
				
				//ADD PIECES & OBSTACLES TO THE BOARD
				
				// Assign pieces to the squares
				if (game.getBoard().getSquare(row, col).getPiece() != null) {
					vSquare.setPiece(game.getBoard().getSquare(row, col).getPiece());
				}
				else if(game.getBoard().getSquare(row, col).hasObstacle()) {
					vSquare.setPiece(getRandomObstacle());
				}
				// Assign island to the center square
				if(row == (boardSize-1)/2 && col == (boardSize-1)/2 && !game.getIsHumanPickedUp()) {
					vSquare.setPiece(Constants.IMG_HUMAN);
				}
				gBoard.add(vSquare, col, row);
				setHighlights(vSquare);
				
				// Handle mouse click for the square view 
				vSquare.setOnMouseClicked(selectController);
			}
		}
		formatBoard(gBoard);
		return gBoard;
		
	}
	
	// Gets a random Obstacle from the list
	private String getRandomObstacle() {
		Random rand = new Random();
	    int upperbound = Constants.IMG_OBSTACLES.length;
	    int randIndex = rand.nextInt(upperbound);
	    return Constants.IMG_OBSTACLES[randIndex];
	}
	
	// Formats the board
	private void formatBoard(GridPane gBoard) {
		int cellSize = Constants.VIEW_SQUARE_SIZE;
		for (int i = 0; i < boardSize; i++) {
			gBoard.getColumnConstraints().add(new ColumnConstraints(cellSize, Control.USE_COMPUTED_SIZE,
					Double.POSITIVE_INFINITY, Priority.NEVER, HPos.CENTER, true));
			gBoard.getRowConstraints().add(new RowConstraints(cellSize, Control.USE_COMPUTED_SIZE,
					Double.POSITIVE_INFINITY, Priority.NEVER, VPos.CENTER, true));
		}
		gBoard.setGridLinesVisible(true);
		gBoard.setMaxSize(150, 150);
		
	}
	
	// Set the highlights for square view
	private void setHighlights(SquareView square) {
		square.setOnMouseEntered(e -> {
			if (!square.getIsSelected() && !square.getIsValidMove())
				square.setColor(Constants.COLOR_HIGHLIGHT_HOVER);
		});

		square.setOnMouseExited(e -> {
			if (!square.getIsSelected() && !square.getIsValidMove()) {
				square.setColor(square.getDefaultColor());
			}
		});

	}
	
	
}
