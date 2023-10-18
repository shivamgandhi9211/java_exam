package com.georgiancollege.test1;

import javafx.scene.chart.XYChart;
import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    private static String user = "Shivam200520466"; // Replace with your database username
    private static String pass = "w-qU20mayP"; // Replace with your database password
    private static String dbName = "Shivam200520466"; // Replace with your database name
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/" + dbName;

    public static ArrayList<Employee> getEmployeesFromDB(String clause) {
        ArrayList<Employee> employees = new ArrayList<>();

        String sql = "SELECT employee_id, first_name, last_name, address, city, province, phone " +
                "FROM midTermEmployee WHERE " + clause + ";";

        try (
                Connection conn = DriverManager.getConnection(connectURL, user, pass);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String province = resultSet.getString("province");
                String phone = resultSet.getString("phone");

                Employee employee = new Employee(employeeId, firstName, lastName, address, city, province, phone);
                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

}
