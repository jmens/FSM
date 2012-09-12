FSM
===

Implementation of a simple finite state automata. 
Simple FSM is implemented generic. The user can specify a custom class as type for state-nodes 
and another custom class as type for event-nodes. The choosen classes must only be usable as keys 
in a hashmap - this is: They mus implement equals() and hashCode() meaningful. 
Enums are suggested types for states and events as long as no custom logic is needed. 
The user can register listeners to fire custom actins whenever the automata changes its state. 

The implementation is really easy to use - look at the tests (DefaultAutomataFactoryTest.java) for a "howto". 
