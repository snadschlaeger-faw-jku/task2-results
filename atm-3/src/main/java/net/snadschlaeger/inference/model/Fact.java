package net.snadschlaeger.inference.model;

import java.util.ArrayList;
import java.util.List;

public class Fact extends Model {

	private String name;

	private List<Slot> slots;

	public static Fact create(String name) {
		return new Fact(name);
	}

	public Fact(String name) {
		this.name = name;
		this.slots = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public Fact addSlot(Slot slot) {
		this.slots.add(slot);
		return this;
	}

}
