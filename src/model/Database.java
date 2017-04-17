package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {

    private Connection con;
    private PreparedStatement st;
    private ResultSet rs;

    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Group_6",
                    "cs4400_Group_6",
                    "tIDTKC7_");
            if(!con.isClosed())
                System.out.println("Successfully connected to " +
                        "MySQL server using TCP/IP...");
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

    }

    /**
     * adds a user to the database, first sees if the user is not already in the database
     * @param user user to insert into the database
     * @return true if user is added, false if user is in the database already
     */
    public boolean addUser(User user) {
        try {
            String query = "SELECT username FROM user WHERE username = ?";
            st = con.prepareStatement(query);
            st.setString(1, user.getUsername());
            rs = st.executeQuery();
            if(!(rs.next())) {
                addUserToDatabase(user);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void addUserToDatabase(User user) {
        try {
            String query = "INSERT INTO user " +
                    "(username, password, email, user_type) values (?, ?, ?, ?)";
            st = con.prepareStatement(query);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.setString(4, user.getUser_type());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user instanceof CityOfficial) {
            System.out.println("user is a city official");
            CityOfficial cityOfficial = (CityOfficial) user;
            addCityOfficialToDatabase(cityOfficial);
        }
    }

    private void addCityOfficialToDatabase(CityOfficial cityOfficial) {
        try {
            String query = "INSERT INTO city_official " +
                    "(username, title, city, state) values (?, ?, ?, ?)";
            st = con.prepareStatement(query);
            st.setString(1, cityOfficial.getUsername());
            st.setString(2, cityOfficial.getTitle());
            st.setString(3, cityOfficial.getCity());
            st.setString(4, cityOfficial.getState());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds a poi to the database
     * @param poi poi to insert into the database
     * @return true if poi is added, false if poi is in the database already
     */
    public boolean addPOIToDatabase(POI poi) {
        try {
            String query = "INSERT INTO poi " +
                    "(location_name, flag, date_flagged, zip_code, city, state) values (?, ?, ?, ?, ?, ?)";
            st = con.prepareStatement(query);
            st.setString(1, poi.getName());
            st.setBoolean(2, poi.getFlagged());
            st.setString(3, poi.getDateFlagged());
            st.setInt(4, poi.getZipCode());
            st.setString(5, poi.getCityState().getCity());
            st.setString(6, poi.getCityState().getState());
            st.execute();
            POI.getPois().add(poi);
            POI.getPoisNames().add(poi.getName());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * adds a data point to the database
     * @param poi data point to insert into the database
     * @return true if data point is added, false if data point is in the database already
     */
    public boolean addDataPointToDatabase(DataPoint poi) {
        try {
            String query = "INSERT INTO data_point " +
                    "(poi_name, date_of_reading, time_of_reading, data_value, data_type) values (?, ?, ?, ?, ?)";
            st = con.prepareStatement(query);
            st.setString(1, poi.getLocationName());
            st.setString(2, poi.getDate());
            st.setString(3, poi.getTime());
            st.setInt(4, poi.getDataValue());
            st.setString(5, poi.getDataType());
            st.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * looks for the requested user with the matching username and password
     * used for login
     * @param username username of user
     * @param password password of user
     * @return true if user is in database, false otherwise
     */
    public User searchForUserInDatabase(String username, String password) {
        try {
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            st = con.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            rs = st.executeQuery();
            if (rs.next()) {
                System.out.println("Found.");
                String email = rs.getString("email");
                String user_type = rs.getString("user_type");
                if (user_type.equals("city scientist")) {
                    CityScientist cityScientist = new CityScientist(username, password, email, user_type);
                    return cityScientist;
                } else if (user_type.equals("admin")) {
                    Admin admin = new Admin(username, password, email);
                    return admin;
                } else if (user_type.equals("city official")) {
                    if (checkApproved(username)) {
                        return new User(username, password, email, "city official");
                    } else {
                        return null;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean checkApproved(String username) {
        boolean approval = false;
        try {
            String query = "SELECT approval FROM city_official WHERE username = ?";
            st = con.prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();
            if (rs.next()) {
                System.out.println("City Official approval found.");
                approval = rs.getBoolean("approval");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return approval;
    }
    /**
     * loads the data types from the database
     */
    public void loadDataTypesFromDatabase() {
        System.out.println("Data Types refreshed");
        DataType.getDataTypes().clear();
        try {
            String query = "SELECT type FROM data_type";
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                DataType dataType = new DataType(rs.getString("type"));
                DataType.getDataTypes().add(dataType.getDataTypeName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the city states from the database
     */
    public void loadCityStatesFromDatabase() {
        System.out.println("CityStates refreshed");
        CityState.getCities().clear();
        CityState.getStates().clear();
        try {
            String query = "SELECT * FROM city_state";
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                CityState cityState = new CityState(rs.getString("city"), rs.getString("state"));
                if (!CityState.getCities().contains(cityState.getCity())) {
                    CityState.getCities().add(cityState.getCity());
                }
                if (!CityState.getStates().contains(cityState.getState())) {
                    CityState.getStates().add(cityState.getState());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the pending city officials
     */
    public void loadPendingCityOfficialsFromDatabase() {
        System.out.println("Pending City Officials refreshed");
        // clear the list to show refresh
        CityOfficial.getPendingCityOfficials().clear();
        try {
            // if approval is NULL city official is pending
            String query = "SELECT * FROM city_official INNER JOIN user ON city_official.username = user.username WHERE city_official.approval IS NULL";
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                CityState cityState = new CityState(rs.getString("city"), rs.getString("state"));

                CityOfficial cityOfficial = new CityOfficial(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("title"), cityState
                        );
                CityOfficial.getPendingCityOfficials().add(cityOfficial);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the pending data points
     */
    public void loadPendingDataPointsFromDatabase() {
        System.out.println("Data Points refreshed");
        // clear the list to show refresh
        DataPoint.getPendingDataPoints().clear();
        try {
            String query = "SELECT * FROM data_point WHERE data_point.accepted IS NULL";
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                DataPoint point = new DataPoint(
                        rs.getString("poi_name"),
                        rs.getString("date_of_reading"),
                        rs.getString("time_of_reading"),
                        rs.getInt("data_value"),
                        rs.getString("data_type")
                );
                DataPoint.getPendingDataPoints().add(point);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads POI Locations
     */
    public void loadPOILocationsFromDatabase() {
        System.out.println("POI Locations refreshed");
        POI.getPois().clear();
        POI.getPoisNames().clear();
        try {
            String query = "SELECT location_name as ln, city, state, zip_code, date_flagged, flag,\n" +
                    "(SELECT MIN(dp.data_value) FROM data_point as dp WHERE ln = dp.poi_name AND data_type = \"Mold\" AND dp.accepted = 1) mold_min,\n" +
                    "(SELECT AVG(dp.data_value) FROM data_point as dp WHERE ln = dp.poi_name AND data_type = \"Mold\" AND dp.accepted = 1) mold_avg,\n" +
                    "(SELECT MAX(dp.data_value) FROM data_point as dp WHERE ln = dp.poi_name AND data_type = \"Mold\" AND dp.accepted = 1) mold_max,\n" +
                    "(SELECT MIN(dp.data_value) FROM data_point as dp WHERE ln = dp.poi_name AND data_type = \"Air Quality\" AND dp.accepted = 1) aq_min,\n" +
                    "(SELECT AVG(dp.data_value) FROM data_point as dp WHERE ln = dp.poi_name AND data_type = \"Air Quality\" AND dp.accepted = 1) aq_avg,\n" +
                    "(SELECT MAX(dp.data_value) FROM data_point as dp WHERE ln = dp.poi_name AND data_type = \"Air Quality\" AND dp.accepted = 1) aq_max,\n" +
                    "(SELECT COUNT(*) FROM data_point as dp WHERE ln = dp.poi_name AND dp.accepted = 1) count FROM poi";
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                CityState cityState = new CityState(rs.getString("city"),rs.getString("state"));
                POI poi = new POI(
                        rs.getString("ln"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getInt("zip_code"),
                        rs.getString("date_flagged"),
                        rs.getBoolean("flag"),
                        rs.getDouble("mold_min"),
                        rs.getDouble("mold_avg"),
                        rs.getDouble("mold_max"),
                        rs.getDouble("aq_min"),
                        rs.getDouble("aq_avg"),
                        rs.getDouble("aq_max"),
                        rs.getInt("count")
                        );

                POI.getPois().add(poi);
                POI.getPoisNames().add(poi.getName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * changes status of city officials to approved
     * @param cityOfficials list of city officials that will be approved
     */
    public void acceptCityOfficialAccountsIntoDatabase(ArrayList<CityOfficial> cityOfficials) {
        System.out.println("City Officials Accepted");
        for (int i = 0; i < cityOfficials.size(); i++) {
            try {
                String query = "UPDATE city_official SET approval = ? where username = ?";
                st = con.prepareStatement(query);
                st.setBoolean(1, true);
                st.setString(2, cityOfficials.get(i).getUsername());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * changes status of city officials to rejected
     * @param cityOfficials list of city officials that will be rejected
     */
    public void rejectCityOfficialAccountsIntoDatabase(ArrayList<CityOfficial> cityOfficials) {
        System.out.println("City Officials Rejected");
        for (int i = 0; i < cityOfficials.size(); i++) {
            try {
                String query = "UPDATE city_official SET approval = ? where username = ?";
                st = con.prepareStatement(query);
                st.setBoolean(1, false);
                st.setString(2, cityOfficials.get(i).getUsername());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * sets accepted status of data points to true
     * @param dataPoints list of data points to update to true
     */
    public void acceptDataPointIntoDatabase(ArrayList<DataPoint> dataPoints) {
        System.out.println("Data Point Accepted into Database");
        for (int i = 0; i < dataPoints.size(); i++) {
            try {
                String query = "UPDATE data_point SET accepted = ? where poi_name = ? AND date_of_reading = ? " +
                        "AND time_of_reading = ?";
                st = con.prepareStatement(query);
                st.setBoolean(1, true);
                st.setString(2, dataPoints.get(i).getLocationName());
                st.setString(3, dataPoints.get(i).getDate());
                st.setString(4, dataPoints.get(i).getTime());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * sets accepted status of data points to false
     * @param dataPoints list of data points to update to false
     */
    public void rejectDataPointIntoDatabase(ArrayList<DataPoint> dataPoints) {
        System.out.println("Data Point Rejected into Database");
        for (int i = 0; i < dataPoints.size(); i++) {
            try {
                String query = "UPDATE data_point SET accepted = ? where poi_name = ? AND date_of_reading = ? " +
                        "AND time_of_reading = ?";
                st = con.prepareStatement(query);
                st.setBoolean(1, false);
                st.setString(2, dataPoints.get(i).getLocationName());
                st.setString(3, dataPoints.get(i).getDate());
                st.setString(4, dataPoints.get(i).getTime());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkCityStatesExist(String city, String state) {
        try {
            String query = "SELECT * FROM city_state WHERE city = ? AND state = ?";
            st = con.prepareStatement(query);
            st.setString(1, city);
            st.setString(2, state);
            rs = st.executeQuery();
            if (rs.next()) {
                System.out.println("City State combination Found.");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("City state does not exit");
        }
        return false;
    }
}
