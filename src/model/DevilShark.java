package model;

public class DevilShark extends Shark {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void activateSuperPower(Board b) {
		this.isDevilPowerActive = true;
	}

	@Override
	public void deActivateSuperPower(Board b) {
		this.isDevilPowerActive = false;
	}

}
