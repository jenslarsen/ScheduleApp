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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jens Larsen
 */
public class Datasource {

    // data base info
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_NAME = "U04H9n";
    public static final String DB_URL = "jdbc:mysql://52.206.157.109/" + DB_NAME;
    public static final String DB_USERNAME = "U04H9n";
    public static final String DB_PASSWORD = "53688238693";

    // table names
    private static final String TABLE_USER = "user";
    private static final String TABLE_CUSTOMER = "customer";
    private static final String TABLE_ADDRESS = "address";
    private static final String TABLE_CITY = "city";
    private static final String TABLE_COUNTRY = "country";
    private static final String TABLE_APPOINTMENT = "appointment";
    private static final String TABLE_REMINDER = "reminder";

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
    private static final String COLUMN_REMINDER_REMINDERID = "reminderId";
    private static final String COLUMN_REMINDER_REMINDERDATE = "reminderDate";
    private static final String COLUMN_REMINDER_SNOOZEINCREMENT = "snoozeIncrement";
    private static final String COLUMN_REMINDER_SNOOZEINCREMENTTYPE = "snoozeIncrementType";
    private static final String COLUMN_REMINDER_APPOINTMENTID = "appointmentId";
    private static final String COLUMN_REMINDER_CREATEDBY = "createdBy";
    private static final String COLUMN_REMINDER_CREATEDDATE = "createdDate";
    private static final String COLUMN_REMINDER_REMINDERCOL = "remindercol";

    // static queries
    /* Query Customers with Addresses
    SELECT customer.customerName,customer.active,customer.customerId,
	address.address,address.addressId,address.address2,
    city.city,address.postalCode,address.phone, country.country
    FROM (((customer INNER JOIN address on customer.addressId = address.addressId)
    INNER JOIN city on address.cityId = city.cityId)
    INNER JOIN country on city.countryId = country.countryId)
    WHERE customer.active = 1;
     */
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

    // Query appointments
    private static final String QUERY_APPOINTMENTS
            = "SELECT * FROM " + TABLE_APPOINTMENT;

    // Query appointments with Contacts
    /*
    SELECT appointment.appointmentId, appointment.customerId, customer.customerName,
           appointment.title, appointment.description, appointment.location,
           appointment.url, appointment.start, appointment.end
    FROM appointment INNER JOIN customer ON appointment.customerId = customer.customerId
     */
    private static final String QUERY_APPOINTMENTSWITHCONTACTS_STRING
            = "SELECT " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_APPOINTMENTID + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CUSTOMERID + ", "
            + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERID + ", "
            + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERNAME + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_TITLE + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_DESCRIPTION + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_LOCATION + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_URL + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_START + ", "
            + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_END
            + " FROM " + TABLE_APPOINTMENT + " INNER JOIN " + TABLE_CUSTOMER
            + " ON " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CUSTOMERID
            + " = " + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERID
            + " WHERE " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_CONTACT
            + " = " + "?;";

    private static PreparedStatement queryAppointments = null;

    // Add customer
    /*
    INSERT INTO customer (customerName, addressId, active,
    createDate, createdBy, lastUpdate, lastUpdateBy)
     */
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

    private static PreparedStatement customerQuery = null;

    // Add Address
    /*
    INSERT INTO address (address, address2, cityId, postalCode, phone, createDate,
    createdBy, lastUpdate, lastUpdateBy)
     */
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

    // Add City
    /*
    INSERT INTO city (city,countryId,createDate,createdBy, lastUpdate, lastUpdateBy)
     */
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
            + "';";

    private static PreparedStatement cityQuery = null;

    // Add Country
    /*
    INSERT INTO country (country,createDate,createdBy, lastUpdate, lastUpdateBy)
     */
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

    // Add Appointment
    /*
    INSERT INTO appointment (customerId, title, description, location, contact,
                            url, start, end, createdDate, createdBy, lastUpdate, lastUpdatedBy)
     */
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
            + "';";

    private static PreparedStatement appointmentQuery = null;

    // globals
    private static Connection connection = null;
    public static String loggedInUser = null;
    public static CustomerWithAddress customerBeingEdited = new CustomerWithAddress();

    // public methods
    public static boolean open() throws ClassNotFoundException {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected to database : " + DB_NAME);
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to find class " + e.getMessage());
            return false;
        }
    }

    public static void close() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException, LoginException {
        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Unable to open database connection when trying login!");
            return false;
        }

        Statement statement = connection.createStatement();

        String passwordQuery = "SELECT * "
                + "FROM " + TABLE_USER + " "
                + "WHERE " + COLUMN_USER_USERNAME + "='" + username + "' AND "
                + COLUMN_USER_PASSWORD + "='" + password + "'";

        ResultSet result = null;
        try {
            result = statement.executeQuery(passwordQuery);
        } catch (SQLException sQLException) {
            throw new LoginException("SQL Error checking password");
        }

        // if username and password is found in the database, ie a result was returned
        if (result.next()) {
            loggedInUser = username;
            // close connection
            statement.close();
            Datasource.close();
            return true;
        } else {
            // close connection
            statement.close();
            Datasource.close();
            return false;
        }
    }

    public static List<CustomerWithAddress> getCustomersWithAddresses() throws ClassNotFoundException, SQLException {

        List<CustomerWithAddress> customers = new ArrayList<>();

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Unable to open database connection when trying get customers with addresses!");
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

    public static int countryExists(String country) throws ClassNotFoundException, SQLException {
        int countryId = -1;

        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
            return countryId;
        }

        countryQuery = connection.prepareStatement(QUERY_COUNTRY_STRING);
        countryQuery.setString(1, country);

        try {
            result = countryQuery.executeQuery();

            if (result.next()) {
                countryId = result.getInt(COLUMN_COUNTRY_COUNTRYID);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error querying country: " + e.getMessage());
        }

        return countryId;
    }

    public static int addressExists(String address, String address2, String city) throws ClassNotFoundException, SQLException {
        int addressId = -1;

        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
            return addressId;
        }

        addressQuery = connection.prepareStatement(QUERY_ADDRESS_STRING);

        addressQuery.setString(1, address);
        addressQuery.setString(2, address2);
        addressQuery.setString(3, city);

        try {
            System.out.println("Checking for address: " + addressQuery);

            result = addressQuery.executeQuery();

            if (result.next()) {
                addressId = result.getInt(COLUMN_ADDRESS_ADDRESSID);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error querying address: " + e.getMessage());
        }

        return addressId;
    }

    public static int cityExists(String city, String country) throws ClassNotFoundException {
        int cityId = -1;

        String cityQuery
                = "SELECT * FROM " + TABLE_CITY + " "
                + "INNER JOIN " + TABLE_COUNTRY + " "
                + "WHERE " + TABLE_CITY + "." + COLUMN_CITY_CITY
                + " = " + "'" + city + "'"
                + " AND " + TABLE_COUNTRY + "." + COLUMN_COUNTRY_COUNTRY
                + " = " + "'" + country + "';";

        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
            return cityId;
        }

        try (Statement statement = connection.createStatement()) {

            System.out.println("Checking for city: " + cityQuery);

            result = statement.executeQuery(cityQuery);

            if (result.next()) {
                cityId = result.getInt(COLUMN_CITY_CITYID);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error querying city: " + e.getMessage());
        }

        return cityId;
    }

    public static int customerExists(String name, String address) throws ClassNotFoundException {
        int customerId = -1;

        String customerQuery
                = "SELECT * FROM " + TABLE_CUSTOMER + " "
                + "INNER JOIN " + TABLE_ADDRESS + " "
                + "WHERE " + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_CUSTOMERNAME
                + " = " + "'" + name + "'"
                + " AND " + TABLE_ADDRESS + "." + COLUMN_ADDRESS_ADDRESS
                + " = " + "'" + address + "';";

        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
            return customerId;
        }

        try (Statement statement = connection.createStatement()) {

            result = statement.executeQuery(customerQuery);

            if (result.next()) {
                customerId = result.getInt(COLUMN_CUSTOMER_CUSTOMERID);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error querying customer: " + e.getMessage());
        }

        return customerId;
    }

    public static int addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Date todaysDate = new Date();
        Timestamp createDate = new Timestamp(todaysDate.getTime());
        Timestamp lastUpdate = createDate;
        int customerId = -1;
        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
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
            System.out.println("SQL Error adding customer: " + e.getMessage());
        }

        Datasource.close();
        return customerId;
    }

    public static int addAddress(Address address) throws ClassNotFoundException, SQLException {
        Date todaysDate = new Date();
        Timestamp createDate = new Timestamp(todaysDate.getTime());
        Timestamp lastUpdate = createDate;
        int addressId = -1;
        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
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
            System.out.println("SQL Error adding address: " + e.getMessage());
        }

        Datasource.close();
        return addressId;
    }

    public static boolean updateAddress(Address address) throws ClassNotFoundException, SQLException {

        int addressId = address.getAddressId();

        if (addressId < 1) {
            System.out.println("Error with addressId! Unable to update address.");
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
            System.out.println("Error opening datasource!");
            return false;
        }

        try (Statement statement = connection.createStatement()) {
            System.out.println("Updating Address: " + updateAddress);
            // connection.prepareStatement(updateAddress);
            int updateCount = statement.executeUpdate(updateAddress);
            if (updateCount > 0) {
                return true;
            } else {
                System.out.println("Something went wrong updating address " + addressId);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("SQL Error updating address: " + e.getMessage());
        }

        Datasource.close();
        return true;
    }

    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {

        int customerId = customer.getCustomerID();

        if (customerId < 1) {
            System.out.println("Error with customerId! Unable to update customer.");
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
            System.out.println("Error opening datasource!");
            return false;
        }

        try (Statement statement = connection.createStatement()) {
            System.out.println("Updating Customer: " + updateCustomer);
            connection.prepareStatement(updateCustomer);
            int updateCount = statement.executeUpdate(updateCustomer);
            if (updateCount > 0) {
                return true;
            } else {
                System.out.println("Something went wrong updating customer " + customerId);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("SQL Error updating customer: " + e.getMessage());
        }

        Datasource.close();
        return true;
    }

    public static int addCity(City city) throws ClassNotFoundException, SQLException {
        Date todaysDate = new Date();
        Timestamp createDate = new Timestamp(todaysDate.getTime());
        Timestamp lastUpdate = createDate;
        int cityId = -1;
        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
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

        try {
            cityInsert.execute();
            result = cityQuery.executeQuery();
            if (result.next()) {
                cityId = result.getInt(COLUMN_CITY_CITYID);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error adding city: " + e.getMessage());
        }

        Datasource.close();
        return cityId;
    }

    public static int addCountry(Country country) throws ClassNotFoundException, SQLException {
        Date todaysDate = new Date();
        Timestamp createDate = new Timestamp(todaysDate.getTime());
        Timestamp lastUpdate = createDate;
        int countryId = -1;
        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
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
            System.out.println("SQL Error adding country: " + e.getMessage());
        }

        Datasource.close();
        return countryId;
    }

    public static boolean inactivateCustomer(int customerId) throws SQLException, ClassNotFoundException {

        final String inactivateCustomer
                = "UPDATE " + TABLE_CUSTOMER + " "
                + "SET " + COLUMN_CUSTOMER_ACTIVE + " = 0 "
                + "WHERE " + COLUMN_CUSTOMER_CUSTOMERID + " = " + customerId + ";";

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
            return false;
        }

        try (Statement statement = connection.createStatement()) {
            System.out.println("Attempting to inactivate customer " + customerId);
            System.out.println(inactivateCustomer);
            connection.prepareStatement(inactivateCustomer);
            int updateCount = statement.executeUpdate(inactivateCustomer);
            if (updateCount > 0) {
                return true;
            } else {
                System.out.println("Something went wrong inactivating customer " + customerId);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception trying to inactivate customer " + customerId);
            return false;
        }
    }

    public static int addAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
        Date todaysDate = new Date();
        Timestamp createDate = new Timestamp(todaysDate.getTime());
        Timestamp lastUpdate = createDate;
        int appointmentId = -1;
        ResultSet result;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
            return appointmentId;
        }

        appointmentInsert = connection.prepareStatement(ADD_APPOINTMENT_STRING);

        appointmentInsert.setInt(1, appointment.getCustomerID());
        appointmentInsert.setString(2, appointment.getTitle());
        appointmentInsert.setString(3, appointment.getDescription());
        appointmentInsert.setString(4, appointment.getLocation());
        appointmentInsert.setString(5, appointment.getContact());
        appointmentInsert.setString(6, appointment.getUrl());
        appointmentInsert.setTimestamp(7, (Timestamp) appointment.getStart());
        appointmentInsert.setTimestamp(8, (Timestamp) appointment.getEnd());

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
            System.out.println("SQL Error adding appointment: " + e.getMessage());
        }

        Datasource.close();
        return appointmentId;
    }

    public static boolean updateAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {

        int appointmentId = appointment.getAppointmentID();

        if (appointmentId < 1) {
            System.out.println("Error with appointmentId! Unable to update appointment.");
            return false;
        }

        String lastUpdate = LocalDateTime.now().toString();
        String lastUpdateBy = loggedInUser;

        String updateAppointment = "UPDATE " + TABLE_APPOINTMENT
                + " SET "
                + COLUMN_APPOINTMENT_TITLE + " = " + "'" + appointment.getTitle() + "'" + ", "
                + COLUMN_APPOINTMENT_CUSTOMERID + " = " + appointment.getCustomerID() + ", "
                + COLUMN_APPOINTMENT_DESCRIPTION + " = " + "'" + appointment.getDescription() + "'" + ", "
                + COLUMN_APPOINTMENT_LOCATION + " = " + "'" + appointment.getLocation() + "'" + ", "
                + COLUMN_APPOINTMENT_CONTACT + " = " + "'" + appointment.getContact() + "'" + ", "
                + COLUMN_APPOINTMENT_URL + " = " + "'" + appointment.getUrl() + "'" + ", "
                + COLUMN_APPOINTMENT_START + " = " + "'" + appointment.getStart() + "'" + ", "
                + COLUMN_APPOINTMENT_END + " = " + "'" + appointment.getEnd() + "'" + ", "
                + COLUMN_APPOINTMENT_LASTUPDATE + " = " + "'" + lastUpdate + "'" + ", "
                + COLUMN_APPOINTMENT_LASTUPDATEBY + " = " + "'" + lastUpdateBy + "'"
                + " WHERE " + COLUMN_APPOINTMENT_APPOINTMENTID + " = " + appointmentId;

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Error opening datasource!");
            return false;
        }

        try (Statement statement = connection.createStatement()) {
            System.out.println("Updating Appointment: " + updateAppointment);
            connection.prepareStatement(updateAppointment);
            int updateCount = statement.executeUpdate(updateAppointment);
            if (updateCount > 0) {
                return true;
            } else {
                System.out.println("Something went wrong updating appointment " + appointmentId);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("SQL Error updating appointment: " + e.getMessage());
        }

        Datasource.close();
        return true;
    }

    public static List<Appointment> getAppointments() throws ClassNotFoundException, SQLException {
        List<Appointment> appointments = new ArrayList<>();

        boolean open = Datasource.open();

        if (!open) {
            System.out.println("Unable to open database connection when trying get appointments!");
            return null;
        }

        try (Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(QUERY_APPOINTMENTS)) {

            while (result.next()) {

                Appointment tempAppointment = new Appointment();

                tempAppointment.setAppointmentID(result.getInt(COLUMN_APPOINTMENT_APPOINTMENTID));
                tempAppointment.setCustomerID(result.getInt(COLUMN_APPOINTMENT_CUSTOMERID));
                tempAppointment.setTitle(result.getString(COLUMN_APPOINTMENT_TITLE));
                tempAppointment.setDescription(result.getString(COLUMN_APPOINTMENT_DESCRIPTION));
                tempAppointment.setLocation(result.getString(COLUMN_APPOINTMENT_LOCATION));
                tempAppointment.setContact(result.getString(COLUMN_APPOINTMENT_CONTACT));
                tempAppointment.setUrl(result.getString(COLUMN_APPOINTMENT_URL));
                tempAppointment.setStart(result.getTimestamp(COLUMN_APPOINTMENT_START));
                tempAppointment.setEnd(result.getTimestamp(COLUMN_APPOINTMENT_END));

                appointments.add(tempAppointment);
            }
        }

        return appointments;
    }

    public static List<AppointmentWithContact> getAppointmentsWithContacts() throws ClassNotFoundException, SQLException {
        List<AppointmentWithContact> appointments = new ArrayList<>();

        boolean open = Datasource.open();

        queryAppointments = connection.prepareStatement(QUERY_APPOINTMENTSWITHCONTACTS_STRING);
        queryAppointments.setString(1, Datasource.loggedInUser);

        System.out.println(queryAppointments);

        if (!open) {
            System.out.println("Unable to open database connection when trying get appointments with contacts!");
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
                tempAppointment.setStart(result.getTimestamp(COLUMN_APPOINTMENT_START));
                tempAppointment.setEnd(result.getTimestamp(COLUMN_APPOINTMENT_END));

                appointments.add(tempAppointment);
            }
        }

        return appointments;
    }
}
