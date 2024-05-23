package com.example.demojavafx;

import java.sql.*;
import java.util.ArrayList;

public class update_train_data {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trains";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Arnavcool1";

    public static String[] train_no_array() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[] outputArray = null;

        try {
            // Establishing a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Creating a statement object
            statement = connection.createStatement();

            // Executing a SELECT SQL query
            String sqlQuery = "SELECT TRAIN_NO FROM TRAIN WHERE TRAIN_NO IN (SELECT TRAIN_NO  FROM TRAIN  WHERE RUNNING_STATUS IN ('RUNNING', 'STOPPED', 'SCHEDULED'));";    //NESTED QUERY
            String sqlQuery1 = "SELECT TRAIN_NO FROM TRAIN;";
            resultSet = statement.executeQuery(sqlQuery);

            // Creating an ArrayList to store track numbers
            ArrayList<String> trackNumbers = new ArrayList<>();

            // Building the output string
            while (resultSet.next()) {
                String trackno = resultSet.getString("TRAIN_NO");
                trackNumbers.add(trackno);
            }

            // Convert ArrayList to array
            outputArray = trackNumbers.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return outputArray;
    }

    public static void main(String[] args){
        // Call the method to get the array
        String[] arr = train_no_array();

        // Print the array elements
        if (arr != null) {
            System.out.print("Output Array: [");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
                if (i < arr.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        } else {
            System.out.println("No data retrieved from the database.");
        }
    }
}
