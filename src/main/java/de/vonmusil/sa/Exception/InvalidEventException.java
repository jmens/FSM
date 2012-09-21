package de.vonmusil.sa.Exception;

import static java.text.MessageFormat.format;

import java.util.HashSet;
import java.util.Set;

public class InvalidEventException extends SimpleFsmException {

	private static final String MSG_PATTERN = "Invalid incoming event {0}. Expected one of: {1}";

	private static final long serialVersionUID = 1L;

	public InvalidEventException(String eventDef, Set<?> validEventsDefs) {
		super(buildErrorMessage(eventDef, toStringSet(validEventsDefs)));
	}

	private static String buildErrorMessage(String event, Set<String> validEvents) {
		StringBuilder builder = new StringBuilder();

		boolean first = true;
		for (String currentEvent : validEvents) {
			if (first) {
				first = false;
			} else {
				builder.append(", ");
			}
			builder.append(currentEvent);
		}

		return format(MSG_PATTERN, event.toString(), builder.toString());
	}
	
	public static Set<String> toStringSet(Set<?> set) {
		Set<String> result = new HashSet<String>();
		for (Object object : set) {
			result.add(object.toString());
		}
		return result;
	}
}
