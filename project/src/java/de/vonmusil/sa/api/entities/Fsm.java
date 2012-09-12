package de.vonmusil.sa.api.entities;

import java.util.Set;

import de.vonmusil.sa.Exception.InvalidEventException;

public interface Fsm<S, E> {
	
	void addTransistion(S start, S target, E event);

	void start();

	S changeState(E event) throws InvalidEventException;
	
	Set<E> getValidEvents(S s1);
	
	S getCurrentState();

	void setStartState(S stateDef);
	
	void addFsmListener(FsmListener<S, E> listener);

	void setFsmListener(FsmListener<S, E> listener);

	void removeFsmListener(FsmListener<S, E> listener);
}
