package model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class CareTaker implements Serializable {

	private static final long serialVersionUID = 1L;

	// Deque (used as a stack) for LIFO undo
	private Deque<Memento> savedStates = new ArrayDeque<>();

	public void addMemento(Board originator) {
		// Create a memento via originator and store it
		savedStates.push(originator.createMemento());
	}

	// Gets the top memento
	public Memento getMemento() {
		return savedStates.pop();
	}

	// Gets the size of the stack
	public int getStackSize() {
		return savedStates.size();
	}

}