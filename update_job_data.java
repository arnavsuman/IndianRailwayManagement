package com.example.demojavafx;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class update_job_data {
    // Assuming you have your database connection details defined somewhere
    static final String URL = "jdbc:mysql://localhost:3306/trains";
    static final String USERNAME = "root";
    static final String PASSWORD = "Arnavcool1";

    public static String[] getJobIds() {
        List<String> jobIdList = new ArrayList<>();

        // Assuming URL, USERNAME, and PASSWORD are defined elsewhere
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT JOB_ID FROM JOBSHEET ORDER BY PRIORITY ASC;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String jobId = resultSet.getString("JOB_ID");
                    jobIdList.add(jobId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert List to int array
        String[] jobIds = new String[jobIdList.size()];
        for (int i = 0; i < jobIdList.size(); i++) {
            jobIds[i] = jobIdList.get(i);
        }
        return jobIds;
    }
    public static String getStopsForRoute(String routeId) {
        String stops = "";
        String sql = "SELECT STOPS FROM ROUTE WHERE ROUTE_ID = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, routeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    stops = resultSet.getString("stops");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stops;
    }
    public static void main(String[] args){
    }
}
