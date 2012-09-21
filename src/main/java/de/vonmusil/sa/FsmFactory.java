package de.vonmusil.sa;

import de.vonmusil.sa.api.entities.Fsm;
import de.vonmusil.sa.impl.DefaultFsm;

public final class FsmFactory {
	
	private FsmFactory() {
		super();
	}
	
	public static <S, E> Fsm<S, E> getDefaultFsm(S startState) {
		DefaultFsm<S, E> fsm = new DefaultFsm<S, E>();
		fsm.setStartState(startState);
		return fsm; 
	}
	
	public static <S, E> Fsm<S, E> getDefaultFsm() {
		return new DefaultFsm<S, E>(); 
	}
}
