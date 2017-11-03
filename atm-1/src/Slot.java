

public class Slot<T> {

    private final String name;
    private T value;
    private final boolean mandatory;
    private final boolean askUser;
    private final String type;

    public Slot(String name, T value, String type)
    {
        this(name, value, type, true);
    }

    public Slot(String name, T value, String type, boolean mandatory) {
        this.name = name;
        this.value = value;
        this.mandatory = mandatory;
        this.type = type;

        this.askUser = value == null;
    }

    public String getName() {
        return name;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String getValueClassName()
    {
        return type;
    }

    public boolean askUser()
    {
        return value == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slot property = (Slot) o;

        return name != null ? name.equals(property.name) : property.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}