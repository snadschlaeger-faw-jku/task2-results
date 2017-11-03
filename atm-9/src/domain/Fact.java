package domain;

import java.util.List;

public class Fact {
    private String name;
    private List<Property> propertyList;

    public Fact(String name, List<Property> propertyList) {

        this.name = name;
        this.propertyList = propertyList;
    }

    public Property getPropertyAt(int i){
        return propertyList.get(i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public int getPropertyListSize(){
        return propertyList.size();
    }

    @Override
    public String toString() {
        return name;

    }
}
