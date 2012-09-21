package de.vonmusil.sa.impl;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import de.vonmusil.sa.FsmFactory;
import de.vonmusil.sa.api.entities.Fsm;

public class DefaultAutomataFactoryTest {

	@Test
	public void testFactoryWithoutStartState() throws Exception {
		Fsm<Integer, Boolean> fsm = FsmFactory.getDefaultFsm(); 
		
		fsm.addTransistion(1, 2, true);
		fsm.addTransistion(1, 2, false); 
		fsm.addTransistion(2, 1, true); 
		fsm.addTransistion(2, 3, false);
		
		fsm.setStartState(1); 
		
		fsm.start(); 		
		
		Set<Boolean> events;
		
		events = fsm.getValidEvents(1);
		Assert.assertEquals(1, fsm.getCurrentState().intValue()); 
		Assert.assertEquals(2, events.size());
		Assert.assertTrue(events.contains(Boolean.TRUE));
		Assert.assertTrue(events.contains(Boolean.FALSE));
		
		Integer state;
		
		state = fsm.changeState(true);
		events = fsm.getValidEvents(state);
		Assert.assertEquals(2, state.intValue());
		Assert.assertEquals(2, events.size());
		Assert.assertTrue(events.contains(Boolean.TRUE));
		Assert.assertTrue(events.contains(Boolean.FALSE));
		
		state = fsm.changeState(true);
		events = fsm.getValidEvents(state);
		Assert.assertEquals(1, state.intValue());
		Assert.assertEquals(2, events.size());
		Assert.assertTrue(events.contains(Boolean.TRUE));
		Assert.assertTrue(events.contains(Boolean.FALSE));
		
		fsm.changeState(true); 
		state = fsm.changeState(false);
		events = fsm.getValidEvents(state);
		Assert.assertEquals(3, state.intValue());
		Assert.assertEquals(0, events.size());
	}
	
	@Test
	public void testFactoryWithStartState() throws Exception {
		Fsm<Integer, Boolean> fsm = FsmFactory.getDefaultFsm(1); 
		
		fsm.addTransistion(1, 2, true);
		fsm.addTransistion(1, 2, false); 
		fsm.addTransistion(2, 1, true); 
		fsm.addTransistion(2, 3, false);
		
		fsm.start(); 		
		
		Set<Boolean> events;
		
		events = fsm.getValidEvents(1);
		Assert.assertEquals(1, fsm.getCurrentState().intValue()); 
		Assert.assertEquals(2, events.size());
		Assert.assertTrue(events.contains(Boolean.TRUE));
		Assert.assertTrue(events.contains(Boolean.FALSE));
		
		Integer state;
		
		state = fsm.changeState(true);
		events = fsm.getValidEvents(state);
		Assert.assertEquals(2, state.intValue());
		Assert.assertEquals(2, events.size());
		Assert.assertTrue(events.contains(Boolean.TRUE));
		Assert.assertTrue(events.contains(Boolean.FALSE));
		
		state = fsm.changeState(true);
		events = fsm.getValidEvents(state);
		Assert.assertEquals(1, state.intValue());
		Assert.assertEquals(2, events.size());
		Assert.assertTrue(events.contains(Boolean.TRUE));
		Assert.assertTrue(events.contains(Boolean.FALSE));
		
		fsm.changeState(true); 
		state = fsm.changeState(false);
		events = fsm.getValidEvents(state);
		Assert.assertEquals(3, state.intValue());
		Assert.assertEquals(0, events.size());
	}

}
