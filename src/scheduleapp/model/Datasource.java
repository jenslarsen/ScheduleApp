/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jens Larsen
 */
public class Datasource {

    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_NAME = "U04H9n";
    public static final String DB_URL = "jdbc:mysql://52.206.157.109/" + DB_NAME;
    public static final String DB_USERNAME = "U04H9n";
    public static final String DB_PASSWORD = "53688238693";

    Connection connection = null;

    public boolean open() throws ClassNotFoundException {

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

    public void close() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
