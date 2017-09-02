package main.java.model;

public class Slot {

	public String name;
	public String type;
	public Object value;
	public boolean mandatory;
	
	public Slot(String name, String type, Object value, boolean mandatory) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
		this.mandatory=mandatory;
	}

	@Override
	public String toString() {
		return "Slot [name=" + name + ", type=" + type + ", value=" + value + "]";
	}
	
	
	
	
}
