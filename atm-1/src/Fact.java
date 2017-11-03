import java.util.*;

public class Fact {
    private final String name;

    private final List<Slot> slots = new ArrayList<>();

    public Fact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Slot> getSlots()
    {
        return new ArrayList<>(slots);
    }

    public int getSlotIndex(String name)
    {
        Slot slot = new Slot(name, null, null);
        return slots.indexOf(slot);
    }

    public Slot getSlot(int index)
    {
        return slots.get(index);
    }

    public <T> Fact addSlot(String name, T value, String type)
    {
        Slot<T> slot = new Slot<>(name, value, type);
        int index = slots.indexOf(slot);

        if(index < 0)
        {
            slots.add(slot);
        }
        else
        {
            slot = slots.get(index);
            slot.setValue(value);
        }
        return this;
    }

    public int getNumProperties() {
        return slots.size();
    }

    @Override
    public String toString() {
        if(slots.isEmpty())
        {
            return name;
        }
        return name + " " + slots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fact fact = (Fact) o;

        return name != null ? name.equals(fact.name) : fact.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
