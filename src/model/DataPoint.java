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
    private final SimpleStringProperty dataType;

    /** a list of all the pending data points*/
    private static ObservableList<DataPoint> pendingDataPoints = FXCollections.observableArrayList();

    /** a list of all the data points that will be displayed according to filter*/
    private static ObservableList<DataPoint> poiDetailDataPoints = FXCollections.observableArrayList();

    /**
     * makes a DataPoint with a location name, date, time, data value, data type, and approved status
     * used when loading and storing into database
     * @param locationName  DataPoint location name
     * @param date          DataPoint date
     * @param time          DataPoint time
     * @param dataValue     DataPoint data value
     * @param dataType      DataPoint data type
     */
    public DataPoint(String locationName, String date, String time, int dataValue, String dataType) {
        this.locationName = new SimpleStringProperty(locationName);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.dataValue = new SimpleIntegerProperty(dataValue);
        this.dataType = new SimpleStringProperty(dataType);
    }

    public String getLocationName() {
        return locationName.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTime() {
        return time.get();
    }

    public int getDataValue() {
        return dataValue.get();
    }

    public String getDataType() {
        return dataType.get();
    }

    public static ObservableList<DataPoint> getPendingDataPoints() {
        return pendingDataPoints;
    }

    public static ObservableList<DataPoint> getPoiDetailDataPoints() {
        return poiDetailDataPoints;
    }

    @Override
    public String toString() {
        return getLocationName() + " " + getDate() + " " + getTime();
    }
}
