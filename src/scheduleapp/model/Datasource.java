/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds static methods to manipulate the database and was created to
 * isolate the database code from the rest of the package
 *
 * User names and passwords to access the application:
 *
 * User: jens Password: panda
 *
 * User: jamie Password: hippo
 *
 * User: test Password: test
 *
 * Some assumptions about the database and its structure were made:
 *
 * - ID fields were set to auto-increment starting at ID 100.
 *
 * - The user that is logging into the application is the same as the contact
 * listed on the appointment. Therefore, only appointments for the current
 * logged in user are shown in the appointment list.
 *
 * - The reminder table is not used - it doesn't seem necessary for this
 * application.
 *
 * - The address table does not have a state field so none of the address have
 * one.
 *
 * @author Jens Larsen
 */
public class Datasource {

    // data base info
    /**
     * Class name for the JDBC driver
     */
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * Database name
     */
    public static final String DB_NAME = "U04H9n";

    /**
     * Database URL
     */
    public static final String DB_URL = "jdbc:mysql://52.206.157.109/" + DB_NAME;

    /**
     * Database username
     */
    public static final String DB_USERNAME = "U04H9n";

    /**
     * Database password
     */
    public static final String DB_PASSWORD = "53688238693";

    // static final strings to hold table and column names
    // table names
    private static final String TABLE_USER = "user";
    private static final String TABLE_CUSTOMER = "customer";
    private static final String TABLE_ADDRESS = "address";
    private static final String TABLE_CITY = "city";
    private static final String TABLE_COUNTRY = "country";
    private static final String TABLE_APPOINTMENT = "appointment";

    // table columns
    private static final String COLUMN_USER_USERNAME = "userName";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_CUSTOMER_CUSTOMERID = "customerId";
    private static final String COLUMN_CUSTOMER_CUSTOMERNAME = "customerName";
    private static final String COLUMN_CUSTOMER_ADDRESSID = "addressId";
    private static final String COLUMN_CUSTOMER_ACTIVE = "active";
    private static final String COLUMN_CUSTOMER_CREATEDATE = "createDate";
    private static final String COLUMN_CUSTOMER_CREATEDBY = "createdBy";
    private static final String COLUMN_CUSTOMER_LASTUPDATE = "lastUpdate";
    private static final String COLUMN_CUSTOMER_LASTUPDATEBY = "lastUpdateBy";
    private static final String COLUMN_ADDRESS_ADDRESSID = "addressId";
    private static final String COLUMN_ADDRESS_ADDRESS = "address";
    private static final String COLUMN_ADDRESS_ADDRESS2 = "address2";
    private static final String COLUMN_ADDRESS_CITYID = "cityId";
    private static final String COLUMN_ADDRESS_POSTALCODE = "postalCode";
    private static final String COLUMN_ADDRESS_PHONE = "phone";
    private static final String COLUMN_ADDRESS_CREATEDATE = "createDate";
    private static final String COLUMN_ADDRESS_CREATEDBY = "createdBy";
    private static final String COLUMN_ADDRESS_LASTUPDATE = "lastUpdate";
    private static final String COLUMN_ADDRESS_LASTUPDATEBY = "lastUpdateBy";
    private static final String COLUMN_CITY_CITYID = "cityId";
    private static final String COLUMN_CITY_CITY = "city";
    private static final String COLUMN_CITY_COUNTRYID = "countryId";
    private static final String COLUMN_CITY_CREATEDATE = "createDate";
    private static final String COLUMN_CITY_CREATEDBY = "createdBy";
    private static final String COLUMN_CITY_LASTUPDATE = "lastUpdate";
    private static final String COLUMN_CITY_LASTUPDATEBY = "lastUpdateBy";
    private static final String COLUMN_COUNTRY_COUNTRYID = "countryId";
    private static final String COLUMN_COUNTRY_COUNTRY = "country";
    private static final String COLUMN_COUNTRY_CREATEDATE = "createDate";
    private static final String COLUMN_COUNTRY_CREATEDBY = "createdBy";
    private static final String COLUMN_COUNTRY_LASTUPDATE = "lastUpdate";
    private static final String COLUMN_COUNTRY_LASTUPDATEBY = "lastUpdateBy";
    private static final String COLUMN_APPOINTMENT_APPOINTMENTID = "appointmentId";
    private static final String COLUMN_APPOINTMENT_CUSTOMERID = "customerId";
    private static final String COLUMN_APPOINTMENT_TITLE = "title";
    private static final String COLUMN_APPOINTMENT_DESCRIPTION = "description";
    private static final String COLUMN_APPOINTMENT_LOCATION = "location";
    private static final String COLUMN_APPOINTMENT_CONTACT = "contact";
    private static final String COLUMN_APPOINTMENT_URL = "url";
    private static final String COLUMN_APPOINTMENT_START = "start";
    private static final String COLUMN_APPOINTMENT_END = "end";
    private static final String COLUMN_APPOINTMENT_CREATEDATE = "createDate";
    private static final String COLUMN_APPOINTMENT_CREATEDBY = "createdBy";
    private static final String COLUMN_APPOINTMENT_LASTUPDATE = "lastUpdate";
    private static final String COLUMN_APPOINTMENT_LASTUPDATEBY = "lastUpdateBy";

    // static queries
    private static final String QUERY_CUSTOMERS_WITH_ADDRESSES
            = "SELECT " + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERNAME
            + "," + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_ACTIVE + ","
            + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERID + ","
            + TABLE_ADDRESS + "." + COLUMN_ADDRESS_ADDRESS + ","
            + TABLE_ADDRESS + "." + COLUMN_ADDRESS_ADDRESSID + ","
            + TABLE_ADDRESS + "."
            + COLUMN_ADDRESS_ADDRESS2 + ","
            + TABLE_CITY + "." + COLUMN_CITY_CITY + "," + TABLE_ADDRESS + "."
            + COLUMN_ADDRESS_POSTALCODE + "," + TABLE_ADDRESS + "."
            + COLUMN_ADDRESS_PHONE + "," + TABLE_COUNTRY + "." + TABLE_COUNTRY + " "
            + "FROM (((" + TABLE_CUSTOMER + " INNER JOIN " + TABLE_ADDRESS
            + " on " + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_ADDRESSID + " = "
            + TABLE_ADDRESS + "." + COLUMN_ADDRESS_ADDRESSID + ") INNER JOIN "
            + TABLE_CITY + " on " + TABLE_ADDRESS + "." + COLUMN_ADDRESS_CITYID
            + " = " + TABLE_CITY + "." + COLUMN_CITY_CITYID + ") INNER JOIN "
            + TABLE_COUNTRY + " on " + TABLE_CITY + "." + COLUMN_CITY_COUNTRYID
            + " = " + TABLE_COUNTRY + "." + COLUMN_COUNTRY_COUNTRYID
            + ") WHERE " + TABLE_CUSTOMER + "." + "active = 1;";

    private static final String QUERY_MONTHAPPTSWITHCONTACTS_STRING
            = "SELECT " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_APPOINTMENTID + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CUSTOMERID + ", "
            + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERID + ", "
            + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERNAME + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_TITLE + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_DESCRIPTION + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_LOCATION + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_URL + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_START + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_END + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CONTACT
            + " FROM " + TABLE_APPOINTMENT + " INNER JOIN " + TABLE_CUSTOMER
            + " ON " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CUSTOMERID
            + " = " + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERID
            + " WHERE " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CONTACT + " = ?"
            + " AND " + COLUMN_APPOINTMENT_START
            + " BETWEEN STR_TO_DATE(?,'%Y-%m-%d') "
            + " AND DATE_ADD(?, INTERVAL 1 MONTH);";

    private static final String QUERY_WEEKAPPTSWITHCONTACTS_STRING
            = "SELECT " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_APPOINTMENTID + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CUSTOMERID + ", "
            + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERID + ", "
            + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERNAME + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_TITLE + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_DESCRIPTION + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_LOCATION + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_URL + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_START + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_END + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CONTACT
            + " FROM " + TABLE_APPOINTMENT + " INNER JOIN " + TABLE_CUSTOMER
            + " ON " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CUSTOMERID
            + " = " + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERID
            + " WHERE " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CONTACT + " = ?"
            + " AND " + COLUMN_APPOINTMENT_START
            + " BETWEEN STR_TO_DATE(?,'%Y-%m-%d') "
            + " AND DATE_ADD(?, INTERVAL 1 WEEK);";

    private static PreparedStatement queryAppointments = null;

    private static final String ADD_CUSTOMER_STRING
            = "INSERT INTO " + TABLE_CUSTOMER
            + " (" + COLUMN_CUSTOMER_CUSTOMERNAME + "," + COLUMN_CUSTOMER_ADDRESSID
            + "," + COLUMN_CUSTOMER_ACTIVE + "," + COLUMN_CUSTOMER_CREATEDATE
            + "," + COLUMN_CUSTOMER_CREATEDBY + "," + COLUMN_CUSTOMER_LASTUPDATE
            + "," + COLUMN_CUSTOMER_LASTUPDATEBY + ")"
            + "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static PreparedStatement customerInsert = null;

    private static final String QUERY_CUSTOMER_STRING
            = "SELECT * FROM " + TABLE_CUSTOMER + " "
            + " WHERE " + COLUMN_CUSTOMER_CUSTOMERNAME + " = ? "
            + " AND " + COLUMN_CUSTOMER_ADDRESSID + " = ?;";

    private static final String QUERY_CUSTOMER_ADDRESS_STRING
            = "SELECT * FROM " + TABLE_CUSTOMER + " "
            + "INNER JOIN " + TABLE_ADDRESS + " "
            + "WHERE " + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERNAME
            + " = " + "?"
            + " AND " + TABLE_ADDRESS + "." + COLUMN_ADDRESS_ADDRESS
            + " = ?;";

    private static PreparedStatement customerQuery = null;

    private static final String ADD_ADDRESS_STRING
            = "INSERT INTO " + TABLE_ADDRESS
            + "(" + COLUMN_ADDRESS_ADDRESS + "," + COLUMN_ADDRESS_ADDRESS2 + ","
            + COLUMN_ADDRESS_CITYID + "," + COLUMN_ADDRESS_POSTALCODE + ","
            + COLUMN_ADDRESS_PHONE + "," + COLUMN_ADDRESS_CREATEDATE + ","
            + COLUMN_ADDRESS_CREATEDBY + "," + COLUMN_ADDRESS_LASTUPDATE + ","
            + COLUMN_ADDRESS_LASTUPDATEBY + ") "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static PreparedStatement addressInsert = null;

    private static final String QUERY_ADDRESS_STRING
            = "SELECT * FROM " + TABLE_ADDRESS
            + " WHERE " + COLUMN_ADDRESS_ADDRESS + " = ?"
            + " AND " + COLUMN_ADDRESS_ADDRESS2 + " = ?"
            + " AND " + COLUMN_ADDRESS_CITYID + " = ?" + ";";

    private static PreparedStatement addressQuery = null;

    private static final String ADD_CITY_STRING
            = "INSERT INTO " + TABLE_CITY
            + "(" + COLUMN_CITY_CITY + "," + COLUMN_CITY_COUNTRYID + ","
            + COLUMN_CITY_CREATEDATE + ","
            + COLUMN_CITY_CREATEDBY + "," + COLUMN_CITY_LASTUPDATE + ","
            + COLUMN_CITY_LASTUPDATEBY + ") "
            + "VALUES (?, ?, ?, ?, ?, ?);";

    private static PreparedStatement cityInsert = null;

    private static final String QUERY_CITY_STRING
            = "SELECT * FROM " + TABLE_CITY
            + " WHERE " + COLUMN_CITY_CITY + " = ?"
            + " AND " + COLUMN_CITY_COUNTRYID + " = ?"
            + "';";

    private static PreparedStatement cityQuery = null;

    private static final String ADD_COUNTRY_STRING
            = "INSERT INTO " + TABLE_COUNTRY
            + "(" + COLUMN_COUNTRY_COUNTRY + "," + COLUMN_COUNTRY_CREATEDATE + ","
            + COLUMN_COUNTRY_CREATEDBY + "," + COLUMN_COUNTRY_LASTUPDATE + ","
            + COLUMN_COUNTRY_LASTUPDATEBY + ") "
            + "VALUES (?, ?, ?, ?, ?);";

    private static PreparedStatement countryInsert = null;

    private static final String QUERY_COUNTRY_STRING
            = "SELECT * FROM " + TABLE_COUNTRY
            + "  WHERE " + COLUMN_COUNTRY_COUNTRY + " = ?;";

    private static PreparedStatement countryQuery = null;

    private static final String ADD_APPOINTMENT_STRING
            = "INSERT INTO " + TABLE_APPOINTMENT
            + "(" + COLUMN_APPOINTMENT_CUSTOMERID + "," + COLUMN_APPOINTMENT_TITLE + ","
            + COLUMN_APPOINTMENT_DESCRIPTION + "," + COLUMN_APPOINTMENT_LOCATION + ","
            + COLUMN_APPOINTMENT_CONTACT + "," + COLUMN_APPOINTMENT_URL + ","
            + COLUMN_APPOINTMENT_START + "," + COLUMN_APPOINTMENT_END + ","
            + COLUMN_APPOINTMENT_CREATEDATE + "," + COLUMN_APPOINTMENT_CREATEDBY + ","
            + COLUMN_APPOINTMENT_LASTUPDATE + "," + COLUMN_APPOINTMENT_LASTUPDATEBY + ") "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static PreparedStatement appointmentInsert = null;

    private static final String QUERY_APPOINTMENT_STRING
            = "SELECT * FROM " + TABLE_APPOINTMENT
            + "  WHERE " + COLUMN_APPOINTMENT_TITLE + " = ?"
            + " AND " + COLUMN_APPOINTMENT_CUSTOMERID + " = ?;";

    private static PreparedStatement appointmentQuery = null;

    private static final String QUERY_PASSWORD_STRING
            = "SELECT * "
            + "FROM " + TABLE_USER + " "
            + "WHERE " + COLUMN_USER_USERNAME + "= ? AND "
            + COLUMN_USER_PASSWORD + "= ?;";

    private static PreparedStatement passwordQuery = null;

    private static final String INACTIVATE_CUSTOMER_STRING
            = "UPDATE " + TABLE_CUSTOMER + " "
            + "SET " + COLUMN_CUSTOMER_ACTIVE + " = 0 "
            + "WHERE " + COLUMN_CUSTOMER_CUSTOMERID + " = ?;";

    private static PreparedStatement inactivateCustomer = null;

    private static String UPDATE_APPOINTMENT_STRING
            = "UPDATE " + TABLE_APPOINTMENT
            + " SET "
            + COLUMN_APPOINTMENT_TITLE + " = ?" + ", "
            + COLUMN_APPOINTMENT_CUSTOMERID + " = ?" + ", "
            + COLUMN_APPOINTMENT_DESCRIPTION + " = ?" + ", "
            + COLUMN_APPOINTMENT_LOCATION + " = ?" + ", "
            + COLUMN_APPOINTMENT_CONTACT + " = ?" + ", "
            + COLUMN_APPOINTMENT_URL + " = ?" + ", "
            + COLUMN_APPOINTMENT_START + " = ?" + ", "
            + COLUMN_APPOINTMENT_END + " = ?" + ", "
            + COLUMN_APPOINTMENT_LASTUPDATE + " = ?" + ", "
            + COLUMN_APPOINTMENT_LASTUPDATEBY + " = ?"
            + " WHERE " + COLUMN_APPOINTMENT_APPOINTMENTID + " = ?";

    private static PreparedStatement updateAppointment = null;

    // globals
    /**
     * Static variable for the database connection
     */
    private static Connection connection = null;

    /**
     * Static variable used to track the user that is currently logged in
     */
    public static String loggedInUser = null;

    /**
     * Static variable used to track the customer that is currently being edited
     */
    public static CustomerWithAddress customerBeingEdited
            = new CustomerWithAddress();

    /**
     * Static variable used to track the appointment that is currently being
     * edited
     */
    public static AppointmentWithContact appointmentBeingEdited
            = new AppointmentWithContact();

    public static ZoneId timeZone;

    /**
     * Opens the database for access
     *
     * @return true is successful
     * @throws ClassNotFoundException
     */
    public static boolean open() throws ClassNotFoundException {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected to database : " + DB_NAME);
            return true;
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class " + e.getMessage());
            return false;
        }
    }

    /**
     * Closes the database connection
     *
     * @throws SQLException
     */
    public static void close() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Method to check the entered username and password against the database of
     * users
     *
     * @param username
     * @param password
     *
     * @return true if the username and password combination is found in the
     * database
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws LoginException
     */
    public static boolean checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException, LoginException {
        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Unable to open database connection when trying login!");
            return false;
        }

        passwordQuery = connection.prepareStatement(QUERY_PASSWORD_STRING);
        passwordQuery.setString(1, username);
        passwordQuery.setString(2, password);

        ResultSet result = null;
        try {
            result = passwordQuery.executeQuery();
        } catch (SQLException sQLException) {
            throw new LoginException("SQL Error checking password");
        }

        // if username and password is found in the database, ie a result was returned
        if (result.next()) {
            loggedInUser = username;
            // close connection
            passwordQuery.close();
            Datasource.close();
            return true;
        } else {
            // close connection
            passwordQuery.close();
            Datasource.close();
            return false;
        }
    }

    /**
     * Queries the database for a list of all customers and their associated
     * addresses
     *
     * Only retrieves active customers
     *
     * @return ArrayList of CustomerWithAddress
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static List<CustomerWithAddress> getCustomersWithAddresses() throws ClassNotFoundException, SQLException {

        List<CustomerWithAddress> customers = new ArrayList<>();

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Unable to open database connection when trying get customers with addresses!");
            return null;
        }

        try (Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(QUERY_CUSTOMERS_WITH_ADDRESSES)) {

            while (result.next()) {

                CustomerWithAddress tempCustomer = new CustomerWithAddress();

                tempCustomer.setCustomerID(result.getInt(COLUMN_CUSTOMER_CUSTOMERID));
                tempCustomer.setCustomerName(result.getString(COLUMN_CUSTOMER_CUSTOMERNAME));
                tempCustomer.setAddressId(result.getInt(COLUMN_CUSTOMER_ADDRESSID));
                tempCustomer.setAddress(result.getString(COLUMN_ADDRESS_ADDRESS));
                tempCustomer.setAddress2(result.getString(COLUMN_ADDRESS_ADDRESS2));
                tempCustomer.setCity(result.getString(COLUMN_CITY_CITY));
                tempCustomer.setPostalCode(result.getString(COLUMN_ADDRESS_POSTALCODE));
                tempCustomer.setPhone(result.getString(COLUMN_ADDRESS_PHONE));
                tempCustomer.setCountry(result.getString(COLUMN_COUNTRY_COUNTRY));

                // SQL Query only returns active customers
                tempCustomer.setActive(true);

                customers.add(tempCustomer);
            }
        }
        return customers;
    }

    /**
     * Checks to see if a country exists in the database
     *
     * @param country
     * @return countryId if the country is found, -1 if the country is not found
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int countryExists(String country) throws ClassNotFoundException {
        int countryId = -1;

        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return countryId;
        }

        try {
            countryQuery = connection.prepareStatement(QUERY_COUNTRY_STRING);
            countryQuery.setString(1, country);

            result = countryQuery.executeQuery();

            if (result.next()) {
                countryId = result.getInt(COLUMN_COUNTRY_COUNTRYID);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error querying country: " + e.getMessage());
        }

        return countryId;
    }

    /**
     * Checks if and address exists in the database
     *
     * @param address
     * @param address2
     * @param city
     * @return addressId if the address is found, otherwise -1
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int addressExists(String address, String address2, String city) throws ClassNotFoundException {
        int addressId = -1;

        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return addressId;
        }

        try {

            addressQuery = connection.prepareStatement(QUERY_ADDRESS_STRING);

            addressQuery.setString(1, address);
            addressQuery.setString(2, address2);
            addressQuery.setString(3, city);

            result = addressQuery.executeQuery();

            if (result.next()) {
                addressId = result.getInt(COLUMN_ADDRESS_ADDRESSID);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error querying address: " + e.getMessage());
        }

        return addressId;
    }

    /**
     * Checks to see if a city and country combination exists in the database
     *
     * @param city
     * @param country
     * @return cityId if found, otherwise -1
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int cityExists(String city, String country) throws ClassNotFoundException, SQLException {
        int cityId = -1;

        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return cityId;
        }

        int countryId = Datasource.cityExists(city, country);

        if (countryId < 1) {
            return -1;     // unable to add city if country does not exist
        }

        cityQuery = connection.prepareStatement(QUERY_CITY_STRING);

        cityQuery.setString(1, city);
        cityQuery.setInt(2, countryId);

        try {
            result = cityQuery.executeQuery();

            if (result.next()) {
                cityId = result.getInt(COLUMN_CITY_CITYID);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error querying city: " + e.getMessage());
        }

        return cityId;
    }

    /**
     * Checks to see if a customer and address combination exists in the
     * database
     *
     * @param name
     * @param address
     * @return customerId if the customer is found, otherwise -1
     * @throws ClassNotFoundException
     */
    public static int customerExists(String name, String address) throws ClassNotFoundException {
        int customerId = -1;

        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return customerId;
        }

        try {
            customerQuery = connection.prepareStatement(QUERY_CUSTOMER_ADDRESS_STRING);

            customerQuery.setString(1, name);
            customerQuery.setString(2, address);

            result = customerQuery.executeQuery();

            if (result.next()) {
                customerId = result.getInt(COLUMN_CUSTOMER_CUSTOMERID);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error querying customer: " + e.getMessage());
        }

        return customerId;
    }

    /**
     * Adds a customer to the database
     *
     * @param customer
     * @return the customerId of the new customer, otherwise -1
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        LocalDateTime todaysDate = LocalDateTime.now();
        Timestamp createDate = Timestamp.valueOf(todaysDate);
        Timestamp lastUpdate = createDate;

        int customerId = -1;
        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return customerId;
        }

        customerInsert = connection.prepareStatement(ADD_CUSTOMER_STRING);

        customerInsert.setString(1, customer.getCustomerName());
        customerInsert.setInt(2, customer.getAddressID());
        customerInsert.setInt(3, 1); // set active
        customerInsert.setTimestamp(4, createDate);
        customerInsert.setString(5, Datasource.loggedInUser);
        customerInsert.setTimestamp(6, lastUpdate);
        customerInsert.setString(7, Datasource.loggedInUser);

        customerQuery = connection.prepareStatement(QUERY_CUSTOMER_STRING);
        customerQuery.setString(1, customer.getCustomerName());
        customerQuery.setInt(2, customer.getAddressID());

        try {
            customerInsert.execute();
            result = customerQuery.executeQuery();
            if (result.next()) {
                customerId = result.getInt(COLUMN_CUSTOMER_CUSTOMERID);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error adding customer: " + e.getMessage());
        }

        Datasource.close();
        return customerId;
    }

    /**
     * Adds and city to the database
     *
     * @param city
     * @return cityId of the new city, otherwise -1
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int addCity(City city) throws ClassNotFoundException, SQLException {
        LocalDateTime todaysDate = LocalDateTime.now();
        Timestamp createDate = Timestamp.valueOf(todaysDate);
        Timestamp lastUpdate = createDate;

        int cityId = -1;

        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return cityId;
        }

        cityInsert = connection.prepareStatement(ADD_CITY_STRING);

        cityInsert.setString(1, city.getCity());
        cityInsert.setInt(2, city.getCountryid());

        cityInsert.setTimestamp(3, createDate);
        cityInsert.setString(4, Datasource.loggedInUser);
        cityInsert.setTimestamp(5, lastUpdate);
        cityInsert.setString(6, Datasource.loggedInUser);

        cityQuery = connection.prepareStatement(QUERY_CITY_STRING);
        cityQuery.setString(1, city.getCity());
        cityQuery.setInt(2, city.getCountryid());

        try {
            cityInsert.execute();
            result = cityQuery.executeQuery();
            if (result.next()) {
                cityId = result.getInt(COLUMN_CITY_CITYID);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error adding city: " + e.getMessage());
        }

        Datasource.close();
        return cityId;
    }

    /**
     * Adds an address to the database
     *
     * @param address
     * @return addressId of the new address, otherwise -1
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int addAddress(Address address) throws ClassNotFoundException, SQLException {
        LocalDateTime todaysDate = LocalDateTime.now();
        Timestamp createDate = Timestamp.valueOf(todaysDate);
        Timestamp lastUpdate = createDate;

        int addressId = -1;
        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return addressId;
        }

        addressInsert = connection.prepareStatement(ADD_ADDRESS_STRING);

        addressInsert.setString(1, address.getAddress());
        addressInsert.setString(2, address.getAddress2());
        addressInsert.setInt(3, address.getCityId());
        addressInsert.setString(4, address.getPostalCode());
        addressInsert.setString(5, address.getPhone());

        addressInsert.setTimestamp(6, createDate);
        addressInsert.setString(7, Datasource.loggedInUser);
        addressInsert.setTimestamp(8, lastUpdate);
        addressInsert.setString(9, Datasource.loggedInUser);

        addressQuery = connection.prepareStatement(QUERY_ADDRESS_STRING);
        addressQuery.setString(1, address.getAddress());
        addressQuery.setString(2, address.getAddress2());
        addressQuery.setInt(3, address.getCityId());

        try {
            addressInsert.execute();
            result = addressQuery.executeQuery();
            if (result.next()) {
                addressId = result.getInt(COLUMN_ADDRESS_ADDRESSID);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error adding address: " + e.getMessage());
        }

        Datasource.close();
        return addressId;
    }

    /**
     * Adds a country to the database
     *
     * @param country
     * @return countryId if successful, otherwise -1
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int addCountry(Country country) throws ClassNotFoundException, SQLException {
        LocalDateTime todaysDate = LocalDateTime.now();
        Timestamp createDate = Timestamp.valueOf(todaysDate);
        Timestamp lastUpdate = createDate;

        int countryId = -1;
        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return countryId;
        }

        countryInsert = connection.prepareStatement(ADD_COUNTRY_STRING);

        countryInsert.setString(1, country.getCountry());
        countryInsert.setTimestamp(2, createDate);
        countryInsert.setString(3, Datasource.loggedInUser);
        countryInsert.setTimestamp(4, lastUpdate);
        countryInsert.setString(5, Datasource.loggedInUser);

        countryQuery = connection.prepareStatement(QUERY_COUNTRY_STRING);
        countryQuery.setString(1, country.getCountry());

        try {
            countryInsert.execute();
            result = countryQuery.executeQuery();
            if (result.next()) {
                countryId = result.getInt(COLUMN_COUNTRY_COUNTRYID);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error adding country: " + e.getMessage());
        }

        Datasource.close();
        return countryId;
    }

    /**
     * Adds a new appointment
     *
     * @param appointment
     * @return appointmentId of the new appointment, otherwise -1
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int addAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
        LocalDateTime todaysDate = LocalDateTime.now();
        Timestamp createDate = Timestamp.valueOf(todaysDate);
        Timestamp lastUpdate = createDate;

        int appointmentId = -1;
        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return appointmentId;
        }

        appointmentInsert = connection.prepareStatement(ADD_APPOINTMENT_STRING);

        appointmentInsert.setInt(1, appointment.getCustomerID());
        appointmentInsert.setString(2, appointment.getTitle());
        appointmentInsert.setString(3, appointment.getDescription());
        appointmentInsert.setString(4, appointment.getLocation());
        appointmentInsert.setString(5, appointment.getContact());
        appointmentInsert.setString(6, appointment.getUrl());
        appointmentInsert.setTimestamp(7, Timestamp.valueOf(appointment.getStart()));
        appointmentInsert.setTimestamp(8, Timestamp.valueOf(appointment.getEnd()));

        appointmentInsert.setTimestamp(9, createDate);
        appointmentInsert.setString(10, Datasource.loggedInUser);
        appointmentInsert.setTimestamp(11, lastUpdate);
        appointmentInsert.setString(12, Datasource.loggedInUser);

        appointmentQuery = connection.prepareStatement(QUERY_APPOINTMENT_STRING);
        appointmentQuery.setString(1, appointment.getTitle());
        appointmentQuery.setInt(2, appointment.getCustomerID());

        try {
            appointmentInsert.execute();
            result = appointmentQuery.executeQuery();
            if (result.next()) {
                appointmentId = result.getInt(COLUMN_APPOINTMENT_APPOINTMENTID);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error adding appointment: " + e.getMessage());
        }

        Datasource.close();
        return appointmentId;
    }

    /**
     * >>>>>>>>>>>>> NEEDS TO BE FIXED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     *
     * Updates and existing address
     *
     * @param address
     * @return true if the address was updated
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean updateAddress(Address address) throws ClassNotFoundException, SQLException {

        int addressId = address.getAddressId();

        if (addressId < 1) {
            System.err.println("Error with addressId! Unable to update address.");
            return false;
        }

        String address1 = address.getAddress();
        String address2 = address.getAddress2();
        int cityId = address.getCityId();
        String postalCode = address.getPostalCode();
        String phone = address.getPhone();
        String lastUpdate = LocalDateTime.now().toString();
        String lastUpdateBy = loggedInUser;

        String updateAddress = "UPDATE " + TABLE_ADDRESS
                + " SET " + COLUMN_ADDRESS_ADDRESS + " = " + "'" + address1 + "'" + ", "
                + COLUMN_ADDRESS_ADDRESS2 + " = " + "'" + address2 + "'" + ", "
                + COLUMN_ADDRESS_CITYID + " = " + cityId + ", "
                + COLUMN_ADDRESS_POSTALCODE + " = " + "'" + postalCode + "'" + ", "
                + COLUMN_ADDRESS_PHONE + " = " + "'" + phone + "'" + ", "
                + COLUMN_ADDRESS_LASTUPDATE + " = " + "'" + lastUpdate + "'" + ", "
                + COLUMN_ADDRESS_LASTUPDATEBY + " = " + "'" + lastUpdateBy + "'"
                + " WHERE " + COLUMN_ADDRESS_ADDRESSID + " = " + addressId;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return false;
        }

        try (Statement statement = connection.createStatement()) {
            int updateCount = statement.executeUpdate(updateAddress);
            if (updateCount > 0) {
                return true;
            } else {
                System.err.println("Something went wrong updating address " + addressId);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("SQL Error updating address: " + e.getMessage());
        }

        Datasource.close();
        return true;
    }

    /**
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> NEEDS TO BE FIXED >>>>>>>>>>>>>>>>>>>>>>>>
     *
     * Updates and existing customer
     *
     * @param customer
     * @return true if successful
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {

        int customerId = customer.getCustomerID();

        if (customerId < 1) {
            System.err.println("Error with customerId! Unable to update customer.");
            return false;
        }

        String customerName = customer.getCustomerName();
        boolean active = true;
        String lastUpdate = LocalDateTime.now().toString();
        String lastUpdateBy = loggedInUser;

        String updateCustomer = "UPDATE " + TABLE_CUSTOMER
                + " SET " + COLUMN_CUSTOMER_CUSTOMERNAME + " = " + "'" + customerName + "'" + ", "
                + COLUMN_CUSTOMER_LASTUPDATE + " = " + "'" + lastUpdate + "'" + ", "
                + COLUMN_CUSTOMER_LASTUPDATEBY + " = " + "'" + lastUpdateBy + "'"
                + " WHERE " + COLUMN_CUSTOMER_CUSTOMERID + " = " + customerId;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return false;
        }

        try (Statement statement = connection.createStatement()) {
            connection.prepareStatement(updateCustomer);
            int updateCount = statement.executeUpdate(updateCustomer);
            if (updateCount > 0) {
                return true;
            } else {
                System.err.println("Something went wrong updating customer " + customerId);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("SQL Error updating customer: " + e.getMessage());
        }

        Datasource.close();
        return true;
    }

    /**
     * Updates and existing appointment
     *
     * @param appointment
     * @return true if successful
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean updateAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {

        int appointmentId = appointment.getAppointmentID();

        if (appointmentId < 1) {
            System.err.println("Error with appointmentId! Unable to update appointment.");
            return false;
        }

        LocalDateTime todaysDate = LocalDateTime.now();
        Timestamp lastUpdate = Timestamp.valueOf(todaysDate);

        String lastUpdateBy = loggedInUser;

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return false;
        }

        try {
            updateAppointment = connection.prepareStatement(UPDATE_APPOINTMENT_STRING);
            updateAppointment.setString(1, appointment.getTitle());
            updateAppointment.setInt(2, appointment.getCustomerID());
            updateAppointment.setString(3, appointment.getDescription());
            updateAppointment.setString(4, appointment.getLocation());
            updateAppointment.setString(5, appointment.getContact());
            updateAppointment.setString(6, appointment.getUrl());
            updateAppointment.setTimestamp(7, Timestamp.valueOf(appointment.getStart()));
            updateAppointment.setTimestamp(8, Timestamp.valueOf(appointment.getEnd()));
            updateAppointment.setTimestamp(9, lastUpdate);
            updateAppointment.setString(10, lastUpdateBy);
            updateAppointment.setInt(11, appointmentId);

            System.out.println("Update appointment: " + updateAppointment.toString());

            int updateCount = updateAppointment.executeUpdate();
            if (updateCount > 0) {
                return true;
            } else {
                System.err.println("Something went wrong updating appointment " + appointmentId);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("SQL Error updating appointment: " + e.getMessage());
        }

        Datasource.close();
        return true;
    }

    /**
     * Makes a customer inactive in the database. Inactive customers are not
     * loaded in the customer list.
     *
     * @param customerId
     * @return true if successful
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static boolean inactivateCustomer(int customerId) throws SQLException, ClassNotFoundException {

        boolean open = Datasource.open();

        if (!open) {
            System.err.println("Error opening datasource!");
            return false;
        }

        try {
            inactivateCustomer = connection.prepareStatement(INACTIVATE_CUSTOMER_STRING);
            inactivateCustomer.setInt(1, customerId);

            int updateCount = inactivateCustomer.executeUpdate();
            if (updateCount > 0) {
                return true;
            } else {
                System.err.println("Something went wrong inactivating customer " + customerId);
                return false;
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception trying to inactivate customer " + customerId);
            return false;
        }
    }

    /**
     * Gets a list of appointments for the upcoming 7 days with associated
     * contacts
     *
     * @return array list of AppointmentWithContac
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static List<AppointmentWithContact> getWeekApptsWithContacts() throws ClassNotFoundException, SQLException {
        List<AppointmentWithContact> appointments = new ArrayList<>();
        LocalDateTime todaysDate = LocalDateTime.now();
        Timestamp tsTodaysDate = Timestamp.valueOf(todaysDate);
        boolean open = Datasource.open();

        queryAppointments = connection.prepareStatement(QUERY_WEEKAPPTSWITHCONTACTS_STRING);
        queryAppointments.setString(1, Datasource.loggedInUser);
        queryAppointments.setTimestamp(2, tsTodaysDate);
        queryAppointments.setTimestamp(3, tsTodaysDate);

        if (!open) {
            System.err.println("Unable to open database connection when trying get appointments with contacts!");
            return null;
        }

        try (ResultSet result = queryAppointments.executeQuery()) {

            while (result.next()) {

                AppointmentWithContact tempAppointment = new AppointmentWithContact();

                tempAppointment.setAppointmentID(result.getInt(COLUMN_APPOINTMENT_APPOINTMENTID));
                tempAppointment.setCustomerID(result.getInt(COLUMN_APPOINTMENT_CUSTOMERID));
                tempAppointment.setCustomerName(result.getString(COLUMN_CUSTOMER_CUSTOMERNAME));
                tempAppointment.setTitle(result.getString(COLUMN_APPOINTMENT_TITLE));
                tempAppointment.setDescription(result.getString(COLUMN_APPOINTMENT_DESCRIPTION));
                tempAppointment.setLocation(result.getString(COLUMN_APPOINTMENT_LOCATION));
                tempAppointment.setContact(Datasource.loggedInUser);
                tempAppointment.setUrl(result.getString(COLUMN_APPOINTMENT_URL));

                Timestamp tsStart = result.getTimestamp(COLUMN_APPOINTMENT_START);
                tempAppointment.setStart(tsStart.toLocalDateTime());

                Timestamp tsEnd = result.getTimestamp(COLUMN_APPOINTMENT_END);
                tempAppointment.setEnd(tsEnd.toLocalDateTime());

                appointments.add(tempAppointment);
            }
        }

        return appointments;
    }

    /**
     * Gets a list of appointments for the upcoming 30 days with associated
     * contacts
     *
     * @return @throws ClassNotFoundException
     * @throws SQLException
     */
    public static List<AppointmentWithContact> getMonthApptsWithContacts() throws ClassNotFoundException, SQLException {
        List<AppointmentWithContact> appointments = new ArrayList<>();
        LocalDateTime todaysDate = LocalDateTime.now();
        Timestamp tsTodaysDate = Timestamp.valueOf(todaysDate
        );
        boolean open = Datasource.open();

        queryAppointments = connection.prepareStatement(QUERY_MONTHAPPTSWITHCONTACTS_STRING);
        queryAppointments.setString(1, Datasource.loggedInUser);
        queryAppointments.setTimestamp(2, tsTodaysDate);
        queryAppointments.setTimestamp(3, tsTodaysDate);

        System.out.println("Getting appointments....\n" + queryAppointments.toString());

        if (!open) {
            System.err.println("Unable to open database connection when trying get appointments with contacts!");
            return null;
        }

        try (ResultSet result = queryAppointments.executeQuery()) {

            while (result.next()) {

                AppointmentWithContact tempAppointment = new AppointmentWithContact();

                tempAppointment.setAppointmentID(result.getInt(COLUMN_APPOINTMENT_APPOINTMENTID));
                tempAppointment.setCustomerID(result.getInt(COLUMN_APPOINTMENT_CUSTOMERID));
                tempAppointment.setCustomerName(result.getString(COLUMN_CUSTOMER_CUSTOMERNAME));
                tempAppointment.setTitle(result.getString(COLUMN_APPOINTMENT_TITLE));
                tempAppointment.setDescription(result.getString(COLUMN_APPOINTMENT_DESCRIPTION));
                tempAppointment.setLocation(result.getString(COLUMN_APPOINTMENT_LOCATION));
                tempAppointment.setContact(Datasource.loggedInUser);
                tempAppointment.setUrl(result.getString(COLUMN_APPOINTMENT_URL));

                Timestamp tsStart = result.getTimestamp(COLUMN_APPOINTMENT_START);
                tempAppointment.setStart(tsStart.toLocalDateTime());

                Timestamp tsEnd = result.getTimestamp(COLUMN_APPOINTMENT_END);
                tempAppointment.setEnd(tsEnd.toLocalDateTime());

                appointments.add(tempAppointment);
            }
        }

        return appointments;
    }
}
