package util;

import model.AngelEagle;
import model.AssassinShark;
import model.IceShark;
import model.Piece;
import model.SwiftEagle;
import model.ThunderEagle;

public final class Constants {
	
	public static final String TEAM_EAGLES = "EAGLES";
	public static final String TEAM_SHARKS = "SHARKS";
	
	public static final String PIECE_SWIFTEAGLE = "SWIFTEAGLE";
	public static final String PIECE_THUNDEREAGLE = "THUNDEREAGLE";
	public static final String PIECE_ANGELEAGLE = "ANGELEAGLE";
	public static final String PIECE_ASSASSINSHARK = "ASSASSINSHARK";
	public static final String PIECE_ICESHARK = "ICESHARK";
	public static final String PIECE_DEVILSHARK = "DEVILSHARK";
	
	public static final String IMG_SHARK_ASSASSINSHARK = "/images/shark_assassin.png";
	public static final String IMG_SHARK_ICESHARK = "/images/shark_ice.png";
	public static final String IMG_SHARK_DEVILSHARK = "/images/shark_devil.png";
	
	public static final String IMG_EAGLE_SWIFTEAGLE = "/images/eagle_swift.jpg";
	public static final String IMG_EAGLE_THUNDEREAGLE = "/images/eagle_thunder.jpg";
	public static final String IMG_EAGLE_ANGELEAGLE = "/images/eagle_angel.jpg";
	
	public static final String IMG_HUMAN = "/images/human.gif";
	public static final String[] IMG_OBSTACLES= {"/images/obstacle_rock1.png","/images/obstacle_tornado.png","/images/obstacle_whirlpool.gif"};
	
	//public static final int BOARD_SIZE = 7;
	public static final int BOARD_TILT_ANGLE = 45;
	public static final int DEFAULT_MOVE_DISTANCE = 1;
	public static final int OBSTACLE_COUNT = 7;
	
	public static final String STATUS_AVAILABLE = "AVAILABLE";
	public static final String STATUS_REMOVED = "REMOVED";
	
	public static final int VIEW_SQUARE_SIZE = 40;
	
	public static final String VIEW_ID_REMOVED_EAGLES = "REMOVED_EAGLES";
	public static final String VIEW_ID_REMOVED_SHARKS = "REMOVED_SHARKS";
	public static final String VIEW_ID_MESSAGES = "GAME_MSG";
	
	public static final String COLOR_HIGHLIGHT_CLICK = "YELLOW";
	public static final String COLOR_HIGHLIGHT_HOVER = "ORANGE";
	
	public static final String GAME_SAVE_FILEPATH = "savedstate.sav";
	
	public static  final String getImagePath(Piece piece) {
		if(piece instanceof SwiftEagle)
			return Constants.IMG_EAGLE_SWIFTEAGLE;
		else if(piece instanceof ThunderEagle)
			return Constants.IMG_EAGLE_THUNDEREAGLE;
		else if(piece instanceof AngelEagle)
			return Constants.IMG_EAGLE_ANGELEAGLE;
		else if(piece instanceof AssassinShark)
			return Constants.IMG_SHARK_ASSASSINSHARK;
		else if(piece instanceof IceShark)
			return Constants.IMG_SHARK_ICESHARK;
		else
			return Constants.IMG_SHARK_DEVILSHARK;
	}
	
}
