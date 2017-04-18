package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * represents a DataType Object
 */
public class DataType {

    private String dataTypeName;

    /** a list of all the data types*/
    private static ObservableList<String> dataTypes = FXCollections.observableArrayList();

    /** a list of all the data types*/
    private static ObservableList<String> dataTypesForFilter = FXCollections.observableArrayList();

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

    public static ObservableList<String> getDataTypesForFilter() {
        return dataTypesForFilter;
    }

    public static void setDataTypesForFilter(ObservableList<String> dataTypesForFilter) {
        DataType.dataTypesForFilter = dataTypesForFilter;
    }

    public static ObservableList<String> getDataTypes() {
        return dataTypes;
    }
}
