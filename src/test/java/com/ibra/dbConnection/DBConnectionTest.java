package com.ibra.dbConnection;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest {

    @Test
    public void testGetConnection() throws SQLException {
        Connection connection = DBConnection.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testConnectionIsValid() throws SQLException {
        Connection connection = DBConnection.getConnection();
        assertTrue(connection.isValid(1));
    }

    @Test
    public void testCloseConnection() throws SQLException {
        // Get connection
        Connection connection = DBConnection.getConnection();
        assertFalse(connection.isClosed());

        // Close connection
        DBConnection.closeConnection();
        assertTrue(connection.isClosed());
    }

    @Test
    public void testMultipleConnectionCalls() throws SQLException {
        Connection connection1 = DBConnection.getConnection();
        Connection connection2 = DBConnection.getConnection();
        assertSame(connection1, connection2);
    }
}

