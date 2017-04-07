package model;

/**
 * represents a City Official Object
 */
public class CityOfficial extends User {

    private String title;
    private Boolean approved;
    private CityState cityState;

    /**
     * makes a City Official with a username, password, email, title, CityState object, and default approved false
     * used to store into database
     * @param username  City Official username
     * @param password  City Official password
     * @param email     City Official email
     * @param title     City Official title
     * @param cityState City Official city and state
     */
    public CityOfficial(String username, String password, String email, String title, CityState cityState) {
        this (username, password, email, title, cityState, false);
    }

    /**
     * makes a City Official with a username, password, email, title, CityState Object, and approved status
     * used when loading from database
     * @param username  City Official username
     * @param password  City Official password
     * @param email     City Official email
     * @param title     City Official title
     * @param cityState City Official city and state
     */
    public CityOfficial(String username, String password, String email, String title, CityState cityState, boolean approved) {
        super(username, password, email, "city official");
        this.title = title;
        this.cityState = cityState;
        this.approved = approved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CityState getCityState() {
        return cityState;
    }

    public void setCityState(CityState cityState) {
        this.cityState = cityState;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
