package de.vonmusil.sa.impl;

import java.util.ArrayList;
import java.util.List;

import de.vonmusil.sa.api.entities.FsmListener;

public class FsmListenerSupport<S, E> {

	private final List<FsmListener<S, E>> listeners = new ArrayList<FsmListener<S, E>>();

	public FsmListenerSupport() {
		super();
	}

	public void addFsmListener(FsmListener<S, E> listener) {
		listeners.add(listener); 
	}

	public void setFsmListener(FsmListener<S, E> listener) {
		listeners.clear(); 
		listeners.add(listener); 
	}

	public void removeFsmListener(FsmListener<S, E> listener) {
		listeners.remove(listener);
	}

	protected List<FsmListener<S, E>> getListeners() {
		return listeners;
	}
}