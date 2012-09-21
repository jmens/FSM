package de.vonmusil.sa.api.entities;

public class ChangeEvent<S, E> {
	private final S from;
	private final S to;
	private final E event;

	public ChangeEvent(S from, S to, E event) {
		super();
		this.from = from;
		this.to = to;
		this.event = event;
	}

	public S getFrom() {
		return from;
	}

	public S getTo() {
		return to;
	}

	public E getEvent() {
		return event;
	}
}
