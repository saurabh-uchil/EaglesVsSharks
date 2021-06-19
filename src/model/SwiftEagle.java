package model;

import util.Constants;

public class SwiftEagle extends Eagle {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void activateSuperPower(Board b) {
		this.moveRange = 2 * Constants.DEFAULT_MOVE_DISTANCE;
	}
	
	@Override
	public void deActivateSuperPower(Board b) {
		this.moveRange = Constants.DEFAULT_MOVE_DISTANCE;
		
	}

}
