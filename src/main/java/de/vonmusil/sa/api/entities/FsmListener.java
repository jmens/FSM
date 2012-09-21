package de.vonmusil.sa.api.entities;

public interface FsmListener<S,E> {
	void stateChanged(ChangeEvent<S,E> e);
}
