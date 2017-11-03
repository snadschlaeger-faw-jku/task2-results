package net.nadschlaeger.model;

public class Slot {

	private String name;

	private String type;

	private Object value;

	private boolean inputRequired = false;

	private boolean mandatory = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isInputRequired() {
		return inputRequired;
	}

	public void setInputRequired(boolean inputRequired) {
		this.inputRequired = inputRequired;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

}
