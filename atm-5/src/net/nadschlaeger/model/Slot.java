package net.nadschlaeger.model;

public class Slot {

	private final String name;
	private boolean requiresInputFromUser = false;
	private boolean isMandatory = false;
	private Object value;
	private final String type;
	private String text;

	public Slot(String name, boolean requiresInputFromUser, Object value, boolean isMandatory, String type) {
		super();
		this.name = name;
		this.requiresInputFromUser = requiresInputFromUser;
		this.value = value;
		this.isMandatory = isMandatory;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public String getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public boolean isRequiresInputFromUser() {
		return requiresInputFromUser;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Slot [name=" + name + ", requiresInputFromUser=" + requiresInputFromUser + ", isMandatory="
				+ isMandatory + ", value=" + value + "]";
	}

}
