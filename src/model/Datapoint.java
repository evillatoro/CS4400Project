package model;

/**
 * represents a Datapoint object
 */
public class Datapoint {

    private String locationName;
    private String date;
    private String time;
    private int dataValue;
    private DataType dataType;
    private Boolean accepted;

    /**
     * makes a Datapoint with a location name, date, time, data value, data type, and approved status
     * used when loading from database
     * @param locationName  Datapoint location name
     * @param date          Datapoint date
     * @param time          Datapoint time
     * @param dataValue     Datapoint data value
     * @param dataType      Datapoint data type
     * @param accepted      Datapoint accepted
     */
    public Datapoint(String locationName, String date, String time, int dataValue, DataType dataType, Boolean accepted) {
        this.locationName = locationName;
        this.date = date;
        this.time = time;
        this.dataValue = dataValue;
        this.dataType = dataType;
        this.accepted = accepted;
    }

    /**
     * makes a Datapoint with a location name, date, time, data value, data type, and default approved false
     * used to store into database
     * @param locationName  Datapoint location name
     * @param date          Datapoint date
     * @param time          Datapoint time
     * @param dataValue     Datapoint data value
     * @param dataType      Datapoint data type
     */
    public Datapoint(String locationName, String date, String time, int dataValue, DataType dataType) {
        this(locationName, date, time, dataValue, dataType, false);
    }

    public String getLocationName() {
        return locationName;
    }

    private void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    private void setTime(String time) {
        this.time = time;
    }

    public int getDataValue() {
        return dataValue;
    }

    private void setDataValue(int dataValue) {
        this.dataValue = dataValue;
    }

    public DataType getDataType() {
        return dataType;
    }

    private void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    private void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
