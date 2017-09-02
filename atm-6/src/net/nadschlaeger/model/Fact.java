package net.nadschlaeger.model;

import java.util.ArrayList;
import java.util.List;

public class Fact {

	private String name;

	private List<Slot> slots = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

}
