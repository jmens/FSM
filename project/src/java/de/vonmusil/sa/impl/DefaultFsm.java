package de.vonmusil.sa.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.vonmusil.sa.Exception.FsmNotStartedException;
import de.vonmusil.sa.Exception.FsmStartedFsmException;
import de.vonmusil.sa.Exception.InvalidEventException;
import de.vonmusil.sa.Exception.NoStartStateDefinedException;
import de.vonmusil.sa.Exception.TooMuchEventsException;
import de.vonmusil.sa.api.entities.ChangeEvent;
import de.vonmusil.sa.api.entities.Fsm;
import de.vonmusil.sa.api.entities.FsmListener;

public class DefaultFsm<S, E> extends
		FsmListenerSupport<S, E> implements Fsm<S, E> {
	
	private Map<S, Map<E, S>> transitionsPerState = new HashMap<S, Map<E, S>>();

	private S currentState;

	private boolean started;

	@Override
	public void addTransistion(S start, S target, E event) {

		if (started) {
			throw new FsmStartedFsmException(
					"Automata already started. No more transitions addable.");
		}

		initializeState(start);
		initializeState(target);

		Map<E, S> transitions = transitionsPerState.get(start); 
		if (transitions.containsKey(event)) {
			throw new TooMuchEventsException("Cannot assign two events for one state.");
		}
		
		transitionsPerState.get(start).put(event, target);
	}

	private void initializeState(S state) {
		Map<E, S> transitions = transitionsPerState.get(state);  
		if (transitions == null) {
			transitionsPerState.put(state, new HashMap<E, S>());
		}
	}

	@Override
	public void start() {
		if (this.currentState == null) {
			throw new NoStartStateDefinedException("Cannot start FSM without configured start state");
		}
		
		this.started = true;
	}

	@Override
	public S changeState(E event) throws InvalidEventException {
		
		if (!started) {
			throw new FsmNotStartedException("Cannot change state if not started.");
		}

		Set<E>  validEvents = getValidEvents(currentState);

		if (!validEvents.contains(event)) {
			throw new InvalidEventException(event.toString(), validEvents);
		}

		S oldState = currentState; 
		
		this.currentState  = transitionsPerState.get(currentState).get(event);
		
		for (FsmListener<S,E> listener : getListeners()) {
			listener.stateChanged(new ChangeEvent<S, E>(oldState, currentState, event));
		}
		
		
		return currentState;
	}

	@Override
	public void setStartState(S state) {
		if (started) {
			throw new FsmStartedFsmException("Cannot set start state once fsm has been started."); 
		}
		
		initializeState(state);
		
		this.currentState = state; 
	}
	
	@Override
	public S getCurrentState() {
		return currentState;
	}

	@Override
	public Set<E> getValidEvents(S state) {
		return transitionsPerState.get(state).keySet();
	}
}
