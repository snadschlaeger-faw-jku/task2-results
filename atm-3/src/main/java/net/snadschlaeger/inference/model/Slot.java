package net.snadschlaeger.inference.model;

public class Slot extends Model {

	private String name;

	private Class<?> type;

	private Object value;

	private boolean requiresUserInput;

	private boolean mandatory;

	public static Slot create(String name, Class<?> type, Object value) {
		return new Slot(name, type, value);
	}

	public static Slot create(String name, Class<?> type) {
		return new Slot(name, type);
	}

	public Slot(String name) {
		this(name, String.class, null);
	}

	public Slot(String name, Class<?> type) {
		this(name, type, null);
	}

	public Slot(String name, Class<?> type, Object value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
		this.requiresUserInput = value == null;
		this.mandatory = true;
	}

	public String getName() {
		return name;
	}

	public Slot setName(String name) {
		this.name = name;
		return this;
	}

	public Class<?> getType() {
		return type;
	}

	public Slot setType(Class<?> type) {
		this.type = type;
		return this;
	}

	public Object getValue() {
		return value;
	}

	public Slot setValue(Object value) {
		this.value = value;
		return this;
	}

	public Slot setRequiresUserInput(boolean requiresUserInput) {
		this.requiresUserInput = requiresUserInput;
		return this;
	}

	public boolean isRequiresUserInput() {
		return requiresUserInput;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public Slot setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
		return this;
	}

}
