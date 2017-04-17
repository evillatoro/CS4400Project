package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * represents a DataPoint object
 */
public class DataPoint {

    private final SimpleStringProperty locationName;
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;

    private final SimpleIntegerProperty dataValue;
    private SimpleStringProperty dataType;
    private Boolean accepted;

    /** a list of all the datapoints*/
    private static ObservableList<DataPoint> pendingDataPoints = FXCollections.observableArrayList();

    /**
     * makes a DataPoint with a location name, date, time, data value, data type, and approved status
     * used when loading from database
     * @param locationName  DataPoint location name
     * @param date          DataPoint date
     * @param time          DataPoint time
     * @param dataValue     DataPoint data value
     * @param dataType      DataPoint data type
     * @param accepted      DataPoint accepted
     */
    public DataPoint(String locationName, String date, String time, int dataValue, String dataType, Boolean accepted) {
        this.locationName = new SimpleStringProperty(locationName);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.dataValue = new SimpleIntegerProperty(dataValue);
        this.dataType = new SimpleStringProperty(dataType);
        this.accepted = accepted;
    }

    /**
     * makes a DataPoint with a location name, date, time, data value, data type, and default approved false
     * used to store into database
     * @param locationName  DataPoint location name
     * @param date          DataPoint date
     * @param time          DataPoint time
     * @param dataValue     DataPoint data value
     * @param dataType      DataPoint data type
     */
    public DataPoint(String locationName, String date, String time, int dataValue, String dataType) {
        this(locationName, date, time, dataValue, dataType, null);
    }

    public String getLocationName() {
        return locationName.get();
    }

    private void setLocationName(String locationName) {
        this.locationName.set(locationName);
    }

    public String getDate() {
        return date.get();
    }

    private void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    private void setTime(String time) {
        this.time.set(time);
    }

    public int getDataValue() {
        return dataValue.get();
    }

    private void setDataValue(int dataValue) {
        this.dataValue.set(dataValue);
    }

    public String getDataType() {
        return dataType.get();
    }

    private void setDataType(String dataType) {
        this.dataType.set(dataType);
    }

    public Boolean getAccepted() {
        return accepted;
    }

    private void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public static ObservableList<DataPoint> getPendingDataPoints() {
        return pendingDataPoints;
    }

    public static void setPendingDataPoints(ObservableList<DataPoint> pendingDataPoints) {
        DataPoint.pendingDataPoints = pendingDataPoints;
    }

    @Override
    public String toString() {
        return getLocationName() + " " + getDate() + " " + getTime();
    }
}
