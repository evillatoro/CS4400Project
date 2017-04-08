package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String query = "SELECT 'username' FROM user WHERE username = ?";
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
            CityOfficial cityOfficial = (CityOfficial) user;
            addCityOfficialToDatabase(cityOfficial);
        }
    }

    private void addCityOfficialToDatabase(CityOfficial cityOfficial) {


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
                    return new User(username, password, email, "city official");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
     * loads the pending city officials
     */
    public void loadPendingCityOfficialsFromDatabase() {
        System.out.println("City Officials refreshed");
        CityOfficial.getCityOfficials().clear();
        try {
            String query = "SELECT * FROM city_official INNER JOIN user ON city_official.username = user.username WHERE NOT city_official.approval";
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()) {
                CityState cityState = new CityState(rs.getString("city"), rs.getString("state"));

                CityOfficial cityOfficial = new CityOfficial(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("title"), cityState
                        );
//                CityOfficial cityOfficial = new CityOfficial(
//                        "hi",
//                        "lol",
//                        "lollollol",
//                        rs.getString("title"), cityState
//                );
                CityOfficial.getCityOfficials().add(cityOfficial);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
