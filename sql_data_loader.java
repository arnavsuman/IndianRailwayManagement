package com.example.demojavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Track{
    int trackID;
    double length;
    String signal_value;
    Boolean trackEmpty;
    int nextTrackID;
    int prevTrackID;
    int permissibleSpeed;

    Track(){
        this.length = 3.5;
        this.signal_value = "RED";
        this.trackEmpty = Boolean.FALSE;
        this.permissibleSpeed = 120;
    }
}

class TRAIN {
    int TRAIN_NO;
    String JOB_ID;
    String SOURCE_STATION;
    String DESTINATION_STATION;
    int COST_TO_OPERATE;
    String ENG_NAME;
    String RAKE_TYPE;
    String ROUTE_ID;
    String RUNNING_STATUS;
    String CURRENT_SIGNAL;
    String CURRENT_TRACK;
    String CURRENT_SPEED;
    int MAX_SPEED;
    int PROFIT;
    String TRAIN_NAME;
    int PRIORITY;

    int get_train_train_no(){
        return this.TRAIN_NO;
    }
    String get_train_train_name(){
        return this.TRAIN_NAME;
    }
    String get_train_source(){
        return this.SOURCE_STATION;
    }
    String get_train_destination(){
        return this.DESTINATION_STATION;
    }
    String get_train_running_status(){
        return this.RUNNING_STATUS;
    }
    String get_train_current_signal(){
        return this.CURRENT_SIGNAL;
    }
    String get_train_current_track(){
        return this.CURRENT_TRACK;
    }
    String get_train_current_speed(){
        return this.CURRENT_SPEED;
    }
    String get_train_route_id(){
        return this.ROUTE_ID;
    }
    int get_train_priority(){
        return this.PRIORITY;
    }
    int get_train_profit(){
        return this.PROFIT;
    }
    int get_train_max_speed(){
        return this.MAX_SPEED;
    }
    String get_train_rake_name(){
        return this.RAKE_TYPE;
    }
    String get_train_eng_name(){
        return this.ENG_NAME;
    }
}

class JOBSHEET{
    String JOB_ID;
    String JOB_TYPE;
    int TRAIN_NO;
    String TRAIN_NAME;
    String SOURCE;
    String DESTINATION;
    int NET_LOAD;
    int PRIORITY;
    int INCOME;
    String ROUTE_ID;
    Double TIME_TAKEN;
}

class RAKE{
    String RAKE_NO;
    String RAKE_TYPE;
    String RAKE_NAME;
    int NET_WEIGHT;
    int CAPACITY;
    int COST_PER_HOUR;
    int MAX_SPEED;
}

public class sql_data_loader {

    private static final String URL = "jdbc:mysql://localhost:3306/trains";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Arnavcool1";

    public static void updateSpeed(int train_no, String givenSpeed) {
        String query = "UPDATE TRAIN SET CURRENT_SPEED = ? WHERE TRAIN_NO = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameters
            preparedStatement.setString(1, givenSpeed);
            preparedStatement.setInt(2, train_no);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Speed updated successfully.");
            } else {
                System.out.println("No train found with the given train number.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating speed: " + e.getMessage());
        }
    }

    public static void changeSignal(int train_no, String signal) {
        String query1 = "UPDATE TRAIN SET CURRENT_SIGNAL = ? WHERE TRAIN_NO = ?";
        String query2 = "UPDATE TRAIN SET RUNNING_STATUS = 'RUNNING' WHERE TRAIN_NO = ?";
        String query3 = "UPDATE TRAIN SET RUNNING_STATUS = 'STOPPED' WHERE TRAIN_NO = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
             PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
             PreparedStatement preparedStatement3 = connection.prepareStatement(query3)) {

            // Execute the first query to update CURRENT_SIGNAL
            preparedStatement1.setString(1, signal);
            preparedStatement1.setInt(2, train_no);
            int rowsAffected1 = preparedStatement1.executeUpdate();

            if (rowsAffected1 > 0) {
                System.out.println(" CURRENT_SIGNAL updated successfully.");
            } else {
                System.out.println("No train found with the given train number.");
            }

            if(Objects.equals(signal, "GREEN"))
            {
                preparedStatement2.setInt(1, train_no);
                int rowsAffected2 = preparedStatement2.executeUpdate();

                if (rowsAffected2 > 0) {
                    System.out.println("RUNNING_STATUS set to 'RUNNING' successfully.");
                } else {
                    System.out.println("No train found with the given train number.");
                }
            }
            else {
                preparedStatement3.setInt(1, train_no);
                int rowsAffected3 = preparedStatement3.executeUpdate();

                if (rowsAffected3 > 0 ) {
                    System.out.println(" RUNNING_STATUS set to 'STOPPED' successfully.");
                } else {
                    System.out.println(" No train found with the given train number.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error executing queries: " + e.getMessage());
        }
    }

    public static void stopAll() {
        String[] queries = {
                "UPDATE TRAIN SET CURRENT_SIGNAL = 'RED'",
                "UPDATE TRAIN SET RUNNING_STATUS = 'STOPPED'",
                "UPDATE TRAIN SET CURRENT_SPEED = '0'"
        };

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            for (String query : queries) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error executing queries: " + e.getMessage());
        }
    }


    public static TRAIN getTrainByno(int train_no) {
        TRAIN train = null;
        // Construct the SQL query string
        String query = "SELECT * FROM TRAIN WHERE TRAIN_NO = " + train_no;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                train = new TRAIN();

                train.TRAIN_NO = resultSet.getInt("TRAIN_NO");
                train.JOB_ID = resultSet.getString("JOB_ID");
                train.SOURCE_STATION = resultSet.getString("SOURCE_STATION");
                train.DESTINATION_STATION = resultSet.getString("DESTINATION_STATION");
                train.COST_TO_OPERATE = resultSet.getInt("COST_TO_OPERATE");
                train.ENG_NAME = resultSet.getString("ENG_NAME");
                train.RAKE_TYPE = resultSet.getString("RAKE_TYPE");
                train.ROUTE_ID = resultSet.getString("ROUTE_ID");
                train.RUNNING_STATUS = resultSet.getString("RUNNING_STATUS");
                train.CURRENT_SIGNAL = resultSet.getString("CURRENT_SIGNAL");
                train.CURRENT_TRACK = resultSet.getString("CURRENT_TRACK");
                train.CURRENT_SPEED = resultSet.getString("CURRENT_SPEED");
                train.MAX_SPEED = resultSet.getInt("MAX_SPEED");
                train.PROFIT = resultSet.getInt("PROFIT");
                train.TRAIN_NAME = resultSet.getString("TRAIN_NAME");
                train.PRIORITY = resultSet.getInt("PRIORITY");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return train;
    }

    public static JOBSHEET getJobsheetByID (String jobID){
        JOBSHEET job = null;
        String query = "SELECT * FROM JOBSHEET WHERE JOB_ID = '" + jobID+"';";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                job = new JOBSHEET();

                job.JOB_ID = resultSet.getString("JOB_ID");
                job.JOB_TYPE = resultSet.getString("JOB_TYPE");
                job.TRAIN_NO = resultSet.getInt("TRAIN_NO");
                job.TRAIN_NAME = resultSet.getString("TRAIN_NAME");
                job.SOURCE = resultSet.getString("SOURCE");
                job.DESTINATION = resultSet.getString("DESTINATION");
                job.PRIORITY = resultSet.getInt("PRIORITY");
                job.NET_LOAD = resultSet.getInt("NET_LOAD");
                job.INCOME = resultSet.getInt("INCOME");
                job.ROUTE_ID = resultSet.getString("ROUTE_ID");
                job.TIME_TAKEN = resultSet.getDouble("TIME_TAKEN");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return job;
    }

    public static String str_return(String input) {
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String extractedString = matcher.group(1);
            return extractedString;
        }
        return null;
    }
    public static int return_int(String a) {

        Pattern pattern = Pattern.compile("\\b(\\d+)\\b");
        Matcher matcher = pattern.matcher(a);

        if (matcher.find()) {
            String extractedNumber = matcher.group(1);
            return Integer.parseInt(extractedNumber);
        }
        return 0;
    }

    public int getFreightChargefromJobsheet(String jobid) {
        int hours = 0;

        String query = "SELECT INCOME FROM JOBSHEET WHERE JOB_ID = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, jobid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    hours = resultSet.getInt("INCOME");
                } else {
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching running cost: " + e.getMessage());
        }

        return hours;
    }
    public int getMaxSpeedLoco(String locoName) {
        int maxSpeed = 0;

        String query = "SELECT MAX_SPEED FROM LOCOMOTIVE WHERE LOCO_NAME = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, locoName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    maxSpeed = resultSet.getInt("MAX_SPEED");
                } else {
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching running cost: " + e.getMessage());
        }

        return maxSpeed;
    }

    public int getMaxSpeedRake(String rakeName) {
        int maxSpeed = 0;

        String query = "SELECT MAX_SPEED FROM RAKE WHERE RAKE_NAME = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, rakeName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    maxSpeed = resultSet.getInt("MAX_SPEED");
                } else {
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching running cost: " + e.getMessage());
        }

        return maxSpeed;
    }

    public Double getHoursfromJobsheet(String jobid) {
        Double hours = 0.0;

        String query = "SELECT TIME_TAKEN FROM JOBSHEET WHERE JOB_ID = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, jobid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    hours = resultSet.getDouble("TIME_TAKEN");
                } else {
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching running cost: " + e.getMessage());
        }

        return hours;
    }

    public int getLOCOcostfromLOCOMOTIVE(String loco_name) {
        int runningCost = 0;

        String query = "SELECT COST_PER_HOUR FROM LOCOMOTIVE WHERE LOCO_NAME = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, loco_name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    runningCost = resultSet.getInt("COST_PER_HOUR");
                } else {
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching running cost: " + e.getMessage());
        }

        return runningCost;
    }

    public int getRAKEcostfromRAKE(String rake_name) {
        int runningCost = 0;

        String query = "SELECT COST_PER_HOUR FROM RAKE WHERE RAKE_NAME = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, rake_name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    runningCost = resultSet.getInt("COST_PER_HOUR");
                } else {
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching running cost: " + e.getMessage());
        }

        return runningCost;
    }
    public static RAKE getRakebyName (String rakeName){
        RAKE rake = null;
        String query = "SELECT * FROM RAKE WHERE RAKE_NAME = '" + rakeName +"';";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                rake = new RAKE();

                rake.RAKE_NO = resultSet.getString("RAKE_NO");
                rake.RAKE_NAME = resultSet.getString("RAKE_NAME");
                rake.RAKE_TYPE = resultSet.getString("RAKE_TYPE");
                rake.NET_WEIGHT = resultSet.getInt("NET_WEIGHT");
                rake.CAPACITY = resultSet.getInt("CAPACITY");
                rake.COST_PER_HOUR = resultSet.getInt("COST_PER_HOUR");
                rake.MAX_SPEED = resultSet.getInt("MAX_SPEED");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rake;
    }



    public static int uploadTraindata(TRAIN train) {
        String sql = "INSERT INTO TRAIN (TRAIN_NO, JOB_ID, SOURCE_STATION, DESTINATION_STATION, COST_TO_OPERATE, " +
                "ENG_NAME, RAKE_TYPE, ROUTE_ID, RUNNING_STATUS, CURRENT_SIGNAL, CURRENT_TRACK, CURRENT_SPEED, " +
                "MAX_SPEED, PROFIT, TRAIN_NAME, PRIORITY) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set values for the prepared statement
            preparedStatement.setInt(1, train.TRAIN_NO);
            preparedStatement.setString(2, train.JOB_ID);
            preparedStatement.setString(3, train.SOURCE_STATION);
            preparedStatement.setString(4, train.DESTINATION_STATION);
            preparedStatement.setInt(5, train.COST_TO_OPERATE);
            preparedStatement.setString(6, train.ENG_NAME);
            preparedStatement.setString(7, train.RAKE_TYPE);
            preparedStatement.setString(8, train.ROUTE_ID);
            preparedStatement.setString(9, train.RUNNING_STATUS);
            preparedStatement.setString(10, train.CURRENT_SIGNAL);
            preparedStatement.setString(11, train.CURRENT_TRACK);
            preparedStatement.setString(12, train.CURRENT_SPEED);
            preparedStatement.setInt(13, train.MAX_SPEED);
            preparedStatement.setInt(14, train.PROFIT);
            preparedStatement.setString(15, train.TRAIN_NAME);
            preparedStatement.setInt(16, train.PRIORITY);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return 1;
            } else {
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        JOBSHEET j1 = new JOBSHEET();
        j1 = getJobsheetByID("J1589");
        System.out.println(j1.DESTINATION);
    }
}
