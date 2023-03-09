package sml;

import java.util.*;
import java.util.stream.Collectors;

/**
 *This class offers operations to add labels and addresses, get addresses from labels, and clear the collection.

 * @author ...Amran
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label   the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		if (labels.containsKey(label)) {
			throw new IllegalArgumentException("Label already exists in map: " + label);
		}
			labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// If the label parameter supplied to getAddress is null, a NullPointerException may be thrown.
		// The method should have a check for this added at the start, and we should then throw an exception.
		if (labels.get(label) == null) {
			throw new NullPointerException("Label is null");
		}
		return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]"));
	}

	/**
	 * Removes the labels
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Labels other) {
			return Objects.equals(this.labels, other.labels);
		}
		return false;
	}

	@Override
	public int hashCode() { return Objects.hash(labels); }
	public void reset() {
		labels.clear();
	}
}
