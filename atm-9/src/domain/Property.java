package domain;

public class Property {
    private String name;
    private String type;
    private String value;
    private Boolean isMandatory;

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Property(Property property) {
        this.name = property.name;
        this.type = property.type;
        this.value = property.value;
        this.isMandatory = property.isMandatory;
    }

    public Property(String name, String type, String value, Boolean isMandatory) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.isMandatory = isMandatory;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
