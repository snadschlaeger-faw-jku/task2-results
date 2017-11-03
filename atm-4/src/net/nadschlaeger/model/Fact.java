package net.nadschlaeger.model;
import java.util.List;

public class Fact {
	public String name;
	public List<Slot> slots;
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
	
	
	@Override
	public String toString() {
		return name;
	}

	
}