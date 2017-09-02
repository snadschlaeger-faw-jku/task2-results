package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Fact {

	public String name;
	public List<Slot> slots = new ArrayList<>();

	public Fact(String name) {
		super();
		this.name = name;
	}

	public Slot getSlot(Slot property) {
		return slots.stream().filter(s -> s.equals(property)).findAny().get();
	}

	@Override
	public String toString() {
		return "Fact [name=" + name + "]";
	}
	
	

}
