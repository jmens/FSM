package de.vonmusil.sa.impl;

import static de.vonmusil.sa.impl.DefaultFsmTest.EventDef.E1;
import static de.vonmusil.sa.impl.DefaultFsmTest.EventDef.E2;
import static de.vonmusil.sa.impl.DefaultFsmTest.EventDef.E3;
import static de.vonmusil.sa.impl.DefaultFsmTest.StateDef.S1;
import static de.vonmusil.sa.impl.DefaultFsmTest.StateDef.S2;
import static de.vonmusil.sa.impl.DefaultFsmTest.StateDef.S3;
import static de.vonmusil.sa.impl.DefaultFsmTest.StateDef.S4;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.vonmusil.sa.Exception.FsmNotStartedException;
import de.vonmusil.sa.Exception.FsmStartedFsmException;
import de.vonmusil.sa.Exception.InvalidEventException;
import de.vonmusil.sa.Exception.NoStartStateDefinedException;
import de.vonmusil.sa.Exception.TooMuchEventsException;
import de.vonmusil.sa.api.entities.ChangeEvent;
import de.vonmusil.sa.api.entities.Fsm;
import de.vonmusil.sa.api.entities.FsmListener;

public class DefaultFsmTest {

	enum StateDef {
		S1, S2, S3, S4
	}

	enum EventDef {
		E1, E2, E3
	}

	private Fsm<StateDef, EventDef> fsm;

	@Before
	public void setUp() {
		fsm = new DefaultFsm<StateDef, EventDef>();
	}
	
	@Test(expected=FsmStartedFsmException.class)
	public void addTransitionShouldFailIfFsmStarted() {
		fsm.setStartState(S1);
		fsm.addTransistion(S1, S2, E1);
		fsm.start();
		fsm.addTransistion(S1, S3, E2);
	}
	
	@Test(expected=NoStartStateDefinedException.class) 
	public void startFsmShouldFailWithoutStartState() {
		fsm.start();
	}
	
	@Test(expected=FsmStartedFsmException.class)
	public void setStartStateShouldFailIfFsmStarted() {
		fsm.setStartState(S1);
		fsm.addTransistion(S1, S2, E1);
		fsm.start();
		fsm.setStartState(S2);
	}
	
	@Test 
	public void startFsmShouldWorkWithStartState() {
		fsm.setStartState(S1);
		fsm.start();
	}
	
	@Test(expected=TooMuchEventsException.class)
	public void addOneEventTwiceShouldFail() {
		fsm.addTransistion(S1, S2, E1);
		fsm.addTransistion(S1, S3, E1);
	}
	
	@Test 
	public void changeStateShouldChangeCorrectly() throws Exception {
		fsm.addTransistion(S1, S2, E1);
		fsm.addTransistion(S2, S3, E2);
		fsm.addTransistion(S1, S4, E3);
		fsm.addTransistion(S3, S1, E1);
		
		fsm.setStartState(S1);
		
		fsm.start();
		
		StateDef newStateDef;
		
		newStateDef = fsm.changeState(E1);
		Assert.assertEquals(S2, newStateDef);
		Assert.assertEquals(S2, fsm.getCurrentState());
		
		newStateDef = fsm.changeState(E2);
		Assert.assertEquals(S3, newStateDef);
		Assert.assertEquals(S3, fsm.getCurrentState());
		
		newStateDef = fsm.changeState(E1);
		Assert.assertEquals(S1, newStateDef);
		Assert.assertEquals(S1, fsm.getCurrentState());
		
		newStateDef = fsm.changeState(E3);
		Assert.assertEquals(S4, newStateDef);
		Assert.assertEquals(S4, fsm.getCurrentState());
	}
	
	@Test 
	public void fsmShouldWorkWithAllStateTypes() throws Exception {
		
		Fsm<String, String> fsm = new DefaultFsm<String, String>();
		
		String S1 = "S1";
		String S2 = "S2";
		String S3 = "S3";
		String S4 = "S4";
		String E1 = "E1";
		String E2 = "E2";
		String E3 = "E3";
		
		fsm.addTransistion(S1, S2, E1);
		fsm.addTransistion(S2, S3, E2);
		fsm.addTransistion(S1, S4, E3);
		fsm.addTransistion(S3, S1, E1);
		
		fsm.setStartState(S1); 
		
		fsm.start(); 
		
		String newStateDef; 
		
		newStateDef = fsm.changeState(E1);
		Assert.assertEquals(S2, newStateDef);
		Assert.assertEquals(S2, fsm.getCurrentState());
		
		newStateDef = fsm.changeState(E2);
		Assert.assertEquals(S3, newStateDef);
		Assert.assertEquals(S3, fsm.getCurrentState());
		
		newStateDef = fsm.changeState(E1);
		Assert.assertEquals(S1, newStateDef);
		Assert.assertEquals(S1, fsm.getCurrentState());
		
		newStateDef = fsm.changeState(E3);
		Assert.assertEquals(S4, newStateDef);
		Assert.assertEquals(S4, fsm.getCurrentState());
	}
	
	@Test(expected=FsmNotStartedException.class)
	public void changeStateShouldFailIfFsmNoStarted() throws Exception {
		fsm.addTransistion(S1, S2, E1);
		fsm.addTransistion(S2, S3, E2);
		fsm.addTransistion(S1, S4, E3);
		fsm.addTransistion(S3, S1, E1);
		
		fsm.setStartState(S1);
		
		fsm.changeState(E1);
	}
	
	@Test(expected=InvalidEventException.class)
	public void changeStateShouldFailIfInvalidEvent() throws Exception {
		fsm.addTransistion(S1, S2, E1);
		fsm.addTransistion(S2, S3, E2);
		fsm.addTransistion(S2, S4, E3);
		fsm.addTransistion(S1, S4, E3);
		fsm.addTransistion(S3, S1, E1);
		
		fsm.setStartState(S1);
		
		fsm.start();
		
		fsm.changeState(E1);
		fsm.changeState(E1);
	}
	
	@Test 
	public void listenerCOnfiguredByFsmShouldBeInvoked() throws Exception {
		fsm.addTransistion(S1, S2, E1);
		fsm.addTransistion(S2, S3, E2);
		fsm.addTransistion(S2, S4, E3);
		fsm.addTransistion(S1, S4, E3);
		fsm.addTransistion(S3, S1, E1);
		
		fsm.setStartState(S1);
		
		class TestAutomataListener<S, E> implements FsmListener<S, E> {
			
			S from; 
			S to; 
			E event; 
			
			@Override
			public void stateChanged(ChangeEvent<S, E> e) {
				from = e.getFrom(); 
				to = e.getTo(); 
				event = e.getEvent(); 
			}

		};
		
		TestAutomataListener<StateDef, EventDef> listener = new TestAutomataListener<StateDef, EventDef>();
		
		fsm.addFsmListener(listener);

		fsm.start();
		
		fsm.changeState(E1);
		Assert.assertEquals(S1, listener.from);
		Assert.assertEquals(S2, listener.to);
		Assert.assertEquals(E1, listener.event);
		
		fsm.changeState(E2);
		Assert.assertEquals(S2, listener.from);
		Assert.assertEquals(S3, listener.to);
		Assert.assertEquals(E2, listener.event);
		
		fsm.changeState(E1);
		Assert.assertEquals(S3, listener.from);
		Assert.assertEquals(S1, listener.to);
		Assert.assertEquals(E1, listener.event);
		
		fsm.changeState(E3);
		Assert.assertEquals(S1, listener.from);
		Assert.assertEquals(S4, listener.to);
		Assert.assertEquals(E3, listener.event);
	}
	
	@Test 
	public void getValidEventsShouldDeliverCorrectValues() {
		fsm.addTransistion(S1, S2, E1);
		fsm.addTransistion(S1, S4, E3);
		fsm.addTransistion(S2, S3, E2);
		fsm.addTransistion(S2, S4, E3);
		fsm.addTransistion(S3, S1, E1);
		
		Set<EventDef> validEvents;
		
		validEvents = fsm.getValidEvents(S1);
		Assert.assertEquals(2, validEvents.size()); 
		Assert.assertTrue(validEvents.contains(E1));
		Assert.assertTrue(validEvents.contains(E3)); 
		
		validEvents = fsm.getValidEvents(S2);
		Assert.assertEquals(2, validEvents.size()); 
		Assert.assertTrue(validEvents.contains(E2));
		Assert.assertTrue(validEvents.contains(E3)); 
		
		validEvents = fsm.getValidEvents(S3);
		Assert.assertEquals(1, validEvents.size()); 
		Assert.assertTrue(validEvents.contains(E1));
		
		validEvents = fsm.getValidEvents(S4);
		Assert.assertTrue(validEvents.isEmpty()); 		
	}
}
