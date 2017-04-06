package model;

/**
 * represents a DataType Object
 */
public class DataType {

    private String dataTypeName;

    /**
     * makes a DataType with a name
     * @param name DataType name
     */
    public DataType(String name) {
        this.dataTypeName = name;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }
}
