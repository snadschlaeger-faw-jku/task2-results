package net.nadschlaeger.model;

import java.util.ArrayList;
import java.util.List;

public class Fact {

	private final String name;
	private List<Slot> slots = new ArrayList<>();

	public Fact(String name, List<Slot> slots) {
		super();
		this.name = name;
		this.slots = slots;
	}

	public String getName() {
		return name;
	}

	public int getSlotCount() {
		return slots.size();
	}

	public List<Slot> getSlots() {
		return slots;
	}

	@Override
	public String toString() {
		return "Fact [name=" + name + ", slots=" + slots + "]";
	}

}
