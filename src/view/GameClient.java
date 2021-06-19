package view;

import controller.GameController;
import controller.LoadGameController;
import controller.SaveGameController;
import controller.StartNewGameController;
import controller.SuperPowerController;
import controller.UndoController;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.Piece;
import util.Constants;

public class GameClient extends Application {
	
	private Stage primaryStage;
	private GameController gameController;
	private BorderPane root;
	private BoardView boardView;
	private ComboBox<Integer> cbSwiftEagle,cbThunderEagle,cbAngelEagle,cbAssassinShark,cbIceShark,cbDevilShark;
	private TextField txtPlayer1,txtPlayer2;
	private ComboBox<Integer> cbBoardSize,cbUndoOptions;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.gameController = GameController.getInstance();
		
		root = new BorderPane();
	    root.setTop(getTopNode());
	    root.setBottom(getBottomNode());
	    root.setLeft(getLeftNode());
	    root.setCenter(getLoginNode());
	     
		primaryStage.setTitle("Eagles vs Sharks Board Game");
		primaryStage.setScene(new Scene(root, 850, 650));
		primaryStage.show();
	}

	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
	public BorderPane getRoot() {
		return this.root;
	}
	
	public GameController getGameController() {
		return this.gameController;
	}
	
	public int getBoardSize() {
		return this.cbBoardSize.getValue();
	}
	
	public String getPlayer1Name() {
		return this.txtPlayer1.getText().trim();
	}
	
	public String getPlayer2Name() {
		return this.txtPlayer2.getText().trim();
	}
	
	public int getUndoCount() {
		return this.cbUndoOptions.getValue();
	}
	
	// Returns the Login View
	private Node getLoginNode() {

		// Initialize view components
		GridPane gridPane = new GridPane();
		
		Label lblNewGame = new Label("New Game");
		lblNewGame.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		lblNewGame.setTextFill(Color.BLUE);
		Label lblPieceConfig = new Label("Piece Configurations");
		lblPieceConfig.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		Tooltip tooltip = new Tooltip();
		tooltip.setText("Select a maximum of 4 pieces for each category");
		lblPieceConfig.setTooltip(tooltip);
		Label lblEagles = new Label("Eagles");
		lblEagles.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		Label lblSharks = new Label("Sharks");
		lblSharks.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		
		
		Label lblEagle = new Label("Player 1 (Team Eagle) : ");       
	    txtPlayer1 = new TextField();
	    Label lblShark = new Label("Player 2 (Team Shark) : ");       
	    txtPlayer2 = new TextField();
	    
	    // Create the Dropdown for board sizes
	    ObservableList<Integer> boardSizeList = FXCollections.<Integer>observableArrayList(5, 7, 9, 11);
	    Label lblBoardSize = new Label("Select Board Size : ");
	    cbBoardSize = new ComboBox<Integer>(boardSizeList);
	    cbBoardSize.setValue(9);
	    cbBoardSize.setPrefWidth(50);
	    
	    // Create the Dropdown for piece counts
	    ObservableList<Integer> pieceCountList = FXCollections.<Integer>observableArrayList(0, 1, 2, 3, 4);
	    cbSwiftEagle = new ComboBox<Integer>(pieceCountList);
	    cbThunderEagle = new ComboBox<Integer>(pieceCountList);
	    cbAngelEagle = new ComboBox<Integer>(pieceCountList);
	    cbAssassinShark = new ComboBox<Integer>(pieceCountList);
	    cbIceShark = new ComboBox<Integer>(pieceCountList);
	    cbDevilShark = new ComboBox<Integer>(pieceCountList);
	    cbSwiftEagle.setValue(1);
	    cbThunderEagle.setValue(1);
	    cbAngelEagle.setValue(2);
	    cbAssassinShark.setValue(1);
	    cbIceShark.setValue(1);
	    cbDevilShark.setValue(2);
	    
	    Label lblSwiftEagle = new Label("Swift Eagle	:");       
	    Label lblThunderEagle = new Label("Thunder Eagle	:");       
	    Label lblAngelEagle = new Label("Angel Eagle	:");       
	    Label lblAssassinShark = new Label("Assassin Shark	:");       
	    Label lblIceShark = new Label("Ice Shark		:");       
	    Label lblDevilShark = new Label("Devil Shark	:");       
	    
	    Button btnStart = new Button("Start Game"); 
	    btnStart.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
	    btnStart.setTextFill(Color.BLUE);
	    // handle 'Start Game' button click
	    btnStart.setOnAction(new StartNewGameController(this));
	     
	    gridPane.setPadding(new Insets(10, 10, 10, 10)); 	      
	    gridPane.setStyle("-fx-background-color: #D6EAF8 ; -fx-border-width: 1 1 1 0; -fx-border-style: solid");
	    gridPane.setAlignment(Pos.CENTER); 
	    
	    // Setting the vertical and horizontal gaps between the columns 
	    gridPane.setVgap(5); 
	    gridPane.setHgap(5);       	    
	      
	    gridPane.add(lblNewGame, 0, 0, 4, 1);
	    gridPane.add(new Text(" "), 0, 1, 4, 1);
	    gridPane.add(lblEagle, 0, 2, 2, 1); 
	    gridPane.add(txtPlayer1, 2, 2, 2, 1);
	    gridPane.add(lblShark, 0, 3, 2, 1); 
	    gridPane.add(txtPlayer2, 2, 3, 2, 1);
	    gridPane.add(lblBoardSize, 0, 4, 2, 1); 
	    gridPane.add(cbBoardSize, 2, 4, 2, 1);
	    
	    gridPane.add(new Text(" "), 0, 5, 4, 1);
	    gridPane.add(lblPieceConfig, 0, 6, 4, 1);
	    gridPane.add(new Text(" "), 0, 7, 4, 1);
	    gridPane.add(lblEagles, 0, 8, 2, 1);
	    gridPane.add(lblSharks, 2, 8, 2, 1);
	    
	    gridPane.add(lblSwiftEagle, 0, 9, 1, 1);
	    gridPane.add(cbSwiftEagle, 1, 9, 1, 1);
	    gridPane.add(lblAssassinShark, 2, 9, 1, 1);
	    gridPane.add(cbAssassinShark, 3, 9, 1, 1);
	    
	    gridPane.add(lblThunderEagle, 0, 10, 1, 1);
	    gridPane.add(cbThunderEagle, 1, 10, 1, 1);
	    gridPane.add(lblIceShark, 2, 10, 1, 1);
	    gridPane.add(cbIceShark, 3, 10, 1, 1);
	    
	    gridPane.add(lblAngelEagle, 0, 11, 1, 1);
	    gridPane.add(cbAngelEagle, 1, 11, 1, 1);
	    gridPane.add(lblDevilShark, 2, 11, 1, 1);
	    gridPane.add(cbDevilShark, 3, 11, 1, 1);
	    
	    gridPane.add(new Text(" "), 0, 12, 4, 1);
	    gridPane.add(btnStart, 0, 13, 4, 1);
	    
	    GridPane.setHalignment(lblNewGame, HPos.CENTER);
	    GridPane.setHalignment(lblPieceConfig, HPos.CENTER);
	    GridPane.setHalignment(btnStart, HPos.CENTER);
	    GridPane.setHalignment(lblEagles, HPos.CENTER);
	    GridPane.setHalignment(lblSharks, HPos.CENTER);
	    
	    gridPane.setGridLinesVisible(false);
		return gridPane;
	
	}
	
	// Returns the top node of client
	private Node getTopNode() {
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699; -fx-border-width: 1 0 0 0; -fx-border-style: solid");

	    Button btnNewGame = new Button("New Game");
	    btnNewGame.setPrefSize(100, 20);
	    // Handle 'New Game' button click
	    btnNewGame.setOnAction(new EventHandler<ActionEvent>() { 
	    	public void handle(ActionEvent event) {
	    		// Clear the left pane
	    		FlowPane fpEagle = (FlowPane)getPrimaryStage().getScene().lookup("#" + Constants.VIEW_ID_REMOVED_EAGLES);
	    		fpEagle.getChildren().clear();
	    		FlowPane fpShark = (FlowPane)getPrimaryStage().getScene().lookup("#" + Constants.VIEW_ID_REMOVED_SHARKS);
	    		fpShark.getChildren().clear();
	    		// Show the login view
	    		root.setCenter(getLoginNode());
	    		clearMessage();
	    	}
	    });
	    
	    Button btnSaveGame = new Button("Save Game");
	    btnSaveGame.setPrefSize(100, 20);
	    btnSaveGame.setOnAction(new SaveGameController(this));
	    btnSaveGame.setId("SAVEGAMEBTN");
	    btnSaveGame.setDisable(true);
	    
	    Button btnLoadGame = new Button("Load Game");
	    btnLoadGame.setPrefSize(100, 20);
	    btnLoadGame.setOnAction(new LoadGameController(this));
	    
	    Button btnEagleUndo = new Button("Eagle Undo");
	    btnEagleUndo.setPrefSize(100, 20);
	    btnEagleUndo.setOnAction(new UndoController(this));
	    btnEagleUndo.setId("EAGLEUNDOBTN");
	    btnEagleUndo.setDisable(true);
	    
	    Button btnSharkUndo = new Button("Shark Undo");
	    btnSharkUndo.setPrefSize(100, 20);
	    btnSharkUndo.setOnAction(new UndoController(this));
	    btnSharkUndo.setId("SHARKUNDOBTN");
	    btnSharkUndo.setDisable(true);
	    
	    // Create the Dropdown for board sizes
	    ObservableList<Integer> undoOptions = FXCollections.<Integer>observableArrayList(1,2,3);
	    Label lblUndoOptions = new Label("Undo count :");
	    lblUndoOptions.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
	    lblUndoOptions.setTextFill(Color.WHITE);
	    cbUndoOptions = new ComboBox<Integer>(undoOptions);
	    cbUndoOptions.setValue(1);
	    cbUndoOptions.setPrefWidth(50);

	    Button btnUseSuperPower = new Button("Super Power");
	    btnUseSuperPower.setPrefSize(100, 20);
	    btnUseSuperPower.setOnAction(new SuperPowerController(this));
	    btnUseSuperPower.setId("SUPERPOWERBTN");
	    btnUseSuperPower.setDisable(true);
	    
	    hbox.getChildren().addAll(btnNewGame, btnUseSuperPower, btnSaveGame, btnLoadGame, btnEagleUndo,btnSharkUndo,lblUndoOptions,cbUndoOptions);
	    hbox.setAlignment(Pos.CENTER);
	    return hbox;
	}
	
	// Returns the bottom node of client 
	private Node getBottomNode() {
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699; -fx-border-width: 1 0 0 0; -fx-border-style: solid");
	    
	    Label message = new Label();
	    message.setId(Constants.VIEW_ID_MESSAGES);
	    message.setStyle("-fx-background-color:WHITE; -fx-border-color:black; -fx-padding:10px;");
	    message.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
	    message.setMinHeight(65);
	    message.prefWidthProperty().bind(hbox.widthProperty());
	    
	    hbox.getChildren().addAll(message);
	    return hbox;
	}
	
	// Returns the left node of client
	private Node getLeftNode() {
		GridPane leftPane = new GridPane();
		leftPane.setStyle("-fx-background-color: #D6EAF8;");

		leftPane.setAlignment(Pos.CENTER);
		leftPane.gridLinesVisibleProperty().set(true);
		leftPane.setMaxHeight(700);
		
		VBox lBox = new VBox();
		HBox title = new HBox();
		HBox eagleBox = new HBox();
		HBox sharkBox = new HBox();
		HBox reagleBox = new HBox();
		HBox rsharkBox = new HBox();
		
		// Removed Eagles view component
		FlowPane eaglePane = new FlowPane();
		eaglePane.setId(Constants.VIEW_ID_REMOVED_EAGLES);
		eaglePane.setPadding(new Insets(5, 0, 5, 0));
		eaglePane.setVgap(1);
		eaglePane.setHgap(1);
		eaglePane.setPrefWrapLength(150);
		eaglePane.setMaxWidth(250);
		eaglePane.setMaxHeight(125);
		
		// Removed sharks view component
		FlowPane sharkPane = new FlowPane();
		sharkPane.setId(Constants.VIEW_ID_REMOVED_SHARKS);
		sharkPane.setPadding(new Insets(5, 0, 5, 0));
		sharkPane.setVgap(1);
		sharkPane.setHgap(1);
		sharkPane.setPrefWrapLength(150);
		sharkPane.setMaxWidth(250);
		sharkPane.setMaxHeight(125);
	
		// Pane Headers
		Text txtHeader = new Text("Eagles vs Sharks");
		txtHeader.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		txtHeader.setFill(Color.BLUE);
		Text eagleTitle = new Text("Removed Eagle Pieces");
		eagleTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		Text sharkTitle = new Text("Removed Shark Pieces");
		sharkTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		
		// Format pane
		eagleBox.setMinHeight(50);
		reagleBox.setMinHeight(175);
		sharkBox.setMinHeight(50);
		rsharkBox.setMinHeight(175);
		eagleBox.setPadding(new Insets(25, 75, 25, 75));
		eagleBox.setAlignment(Pos.CENTER);
		sharkBox.setPadding(new Insets(25, 75, 25, 75));
		sharkBox.setAlignment(Pos.CENTER);
		
		title.setMinHeight(50);
		title.setPadding(new Insets(25, 30, 25, 30));
		title.setAlignment(Pos.CENTER);
		title.getChildren().add(txtHeader);
		eagleBox.getChildren().add(eagleTitle);
		sharkBox.getChildren().add(sharkTitle);
		
		reagleBox.getChildren().add(eaglePane);
		rsharkBox.getChildren().add(sharkPane);
		
		title.setStyle("-fx-border-color: black;" );
		eagleBox.setStyle("-fx-border-color: black;");
		sharkBox.setStyle("-fx-border-color: black; ");
		rsharkBox.setStyle("-fx-border-color: black; ");
		reagleBox.setStyle("-fx-border-color: black;");
		
		lBox.getChildren().addAll(title,eagleBox,reagleBox,sharkBox,rsharkBox);
		lBox.setAlignment(Pos.BASELINE_LEFT);
		lBox.setStyle("-fx-border-color: black;");
		lBox.setFillWidth(true);
		
		leftPane.add(lBox, 0,0);
		GridPane.setVgrow(lBox, Priority.ALWAYS);
		
	return leftPane;
	
	}
	
	// Updates the message in the client
	public void showMessage(String message) {
		Label lblMessages = (Label) this.primaryStage.getScene().lookup("#" + Constants.VIEW_ID_MESSAGES);
		lblMessages.setText(message);
	}
	
	// Clears the message in the client
	public void clearMessage() {
		Label lblMessages = (Label) this.primaryStage.getScene().lookup("#" + Constants.VIEW_ID_MESSAGES);
		lblMessages.setText("");
	}
	
	// Clear all selections and highlights
	public void clearSelection() {
		SquareView sqr;
		gameController.getGame().getBoard().setSelectedSquare(null);
		clearMessage();
		for (int i = 0; i < gameController.getGame().getSaveBoardSize(); i++) {
			for (int j = 0; j < gameController.getGame().getSaveBoardSize(); j++) {
				sqr = (SquareView) getPrimaryStage().getScene().lookup("#SQ_" + i + "_" + j);
				sqr.removeHighlight();
				sqr.setIsValidMove(false);
				sqr.setIsSelected(false);
			}
		}
		Button btnSuperPower = (Button)getNodeById("#SUPERPOWERBTN");
		btnSuperPower.setDisable(true);
		showTurnMessage();
	}
	
	// Returns the node by its ID
	public Node getNodeById(String nodeId) {
		return getPrimaryStage().getScene().lookup(nodeId);
	}
	
	// Add removed pieces to removed panel
	public void addToRemoved(Piece piece) {
		String id = "";
		if (piece.getClass().getSimpleName().toUpperCase().contains("SHARK"))
			id = Constants.VIEW_ID_REMOVED_SHARKS;
		else if (piece.getClass().getSimpleName().toUpperCase().contains("EAGLE"))
			id = Constants.VIEW_ID_REMOVED_EAGLES;

		ImageView imgView = new ImageView(new Image(Constants.getImagePath(piece)));
		imgView.setFitWidth(50);
		imgView.setFitHeight(50);
		FlowPane fp = (FlowPane) getNodeById("#" + id);
		if(fp != null) {
			fp.getChildren().add(imgView);
		}
	}
	
	// Validates the game configurations set
	public boolean validateGameConfig() {
		boolean isValid = false;
		int eagleCount = cbSwiftEagle.getValue() + cbThunderEagle.getValue() + cbAngelEagle.getValue();
		int sharkCount = cbAssassinShark.getValue() + cbIceShark.getValue() + cbDevilShark.getValue();
		
		// Checks if any player fields are empty
		if(txtPlayer1.getText().trim().isEmpty() || txtPlayer2.getText().trim().isEmpty()) {
			showMessage("Enter the player names!");
		}
		else if(txtPlayer1.getText().trim().equalsIgnoreCase(txtPlayer2.getText().trim())){
			showMessage("Player names cannot be same!");
		}
		else if(eagleCount > 4) {
			showMessage("You can only select 4 eagles!");
		}
		else if(sharkCount > 4) {
			showMessage("You can only select 4 sharks!");
		}
		else if(eagleCount < 1) {
			showMessage("Please select atleast 1 Eagle!");
		}
		else if(sharkCount < 1) {
			showMessage("Please select atleast 1 Shark!");
		}
		else {
			isValid = true;
		}
		return isValid;
	}
	
	// Gets the piece configurations set
	public int[] getPieceConfigArray() {
		return new int[] {
				cbSwiftEagle.getValue(),
				cbThunderEagle.getValue(),
				cbAngelEagle.getValue(),
				cbAssassinShark.getValue(),
				cbIceShark.getValue(),
				cbDevilShark.getValue()
			};
	}
	
	// Displays the game board
	public void showGameBoard(int boardSize, Game newGame) {
		boardView = new BoardView(boardSize);
		GridPane board = boardView.drawBoard(newGame,this);
		
		// Rotates the board by an angle
		RotateTransition rt = new RotateTransition(Duration.millis(500), board);
		rt.setByAngle(Constants.BOARD_TILT_ANGLE);
		rt.setCycleCount(1);
		rt.setAutoReverse(false);
		rt.play();
		
		// Show the game board
		root.setCenter(board);
		showTurnMessage();
	}
	
	// Updates the game board view
	public void updateBoard() {
		boardView = new BoardView(gameController.getGame().getSaveBoardSize());
		GridPane board = boardView.drawBoard(gameController.getGame(),this);
		// Rotates the board by an angle
		RotateTransition rt = new RotateTransition(Duration.millis(1), board);
		rt.setByAngle(Constants.BOARD_TILT_ANGLE);
		rt.setCycleCount(1);
		rt.setAutoReverse(false);
		rt.play();
		
		root.setCenter(board);
		showTurnMessage();
	}
	
	// Show the current turn message
	public void showTurnMessage() {
		showMessage("TURN : " + gameController.getGame().getActivePlayer().getUserName() 
				+ " - TEAM " + gameController.getGame().getActivePlayer().getTeam());
	}
	
	// Show the game won message and disable undo buttons
	public void showWinMessage(String winnerName, String winnerTeam) {
		showMessage(winnerTeam + " team wins the game. Winner player : " + winnerName);
		Button btnEagle = (Button)getNodeById("#EAGLEUNDOBTN");
		Button btnShark = (Button)getNodeById("#SHARKUNDOBTN");
		btnEagle.setDisable(true);
		btnShark.setDisable(true);
	}
	
	// Toggle undo button disabling
	public void toggleUndoButtons() {
		Button btnEagle = (Button)getNodeById("#EAGLEUNDOBTN");
		Button btnShark = (Button)getNodeById("#SHARKUNDOBTN");
		
		if(gameController.getGame().getActivePlayer().getTeam().equalsIgnoreCase(Constants.TEAM_EAGLES)) {
			if(!gameController.getGame().getActivePlayer().getIsUndoDone()) {
				btnEagle.setDisable(false);
			}
			btnShark.setDisable(true);
		}
		else {
			btnEagle.setDisable(true);
			if(!gameController.getGame().getActivePlayer().getIsUndoDone()) {
				btnShark.setDisable(false);
			}
		}
	}
	
	// Update view with select highlights
	public void updateHighlights(boolean[][] arrValidMoves, SquareView vSelectedSqr) {
		// Update View using valid moves map
		SquareView sqr;
		for (int i = 0; i < arrValidMoves.length; i++) {
			for (int j = 0; j < arrValidMoves[0].length; j++) {
				sqr = (SquareView) getPrimaryStage().getScene().lookup("#SQ_" + i + "_" + j);
				if (arrValidMoves[i][j] == true) {
					// Highlight the valid moves for the selected piece
					sqr.setIsValidMove(true);
					sqr.highlight();
				} else {
					// Remove all other highlights
					sqr.setIsValidMove(false);
					sqr.removeHighlight();
				}
				// Deselect all squares
				sqr.setIsSelected(false);
			}
		}
		// Select & highlight only current selection
		vSelectedSqr.setIsSelected(true);
		vSelectedSqr.highlight();
	}
	
}
