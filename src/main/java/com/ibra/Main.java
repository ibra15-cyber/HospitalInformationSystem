package com.ibra;

import com.ibra.dbConnection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee");
            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String surname = rs.getString("surname");
                String firstname = rs.getString("first_name");
                System.out.println(id + " "+ surname + " " + firstname );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }
    }
}