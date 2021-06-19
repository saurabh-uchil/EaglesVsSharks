package model;

import java.io.Serializable;

public class Memento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Square[][] state;

	public Memento(Square[][] state) {
		this.state = state;
	}

	public Square[][] getSavedState() {
		return state;
	}
}
