package model;

public class AngelEagle extends Eagle {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void activateSuperPower(Board b) {
		this.isAngelPowerActive = true;
	}

	@Override
	public void deActivateSuperPower(Board b) {
		this.isAngelPowerActive = false;
	}

}
