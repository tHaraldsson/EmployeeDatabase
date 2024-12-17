package se.tommy.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {


    private static final Properties properties = new Properties();

    static {
        try (InputStream input = JDBCUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Could not find application properties file");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load application properties");
        }
    }


    public static Connection getConnection() throws SQLException {
//"jdbc:hsqldb:hsql://localhost/jdbclab";
        Driver hsqlDriver = new org.hsqldb.jdbc.JDBCDriver();
        DriverManager.registerDriver(hsqlDriver);
        String dbURL = properties.getProperty("db.url");
        System.out.println("Url: " + dbURL);
        String userId = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        Connection conn = DriverManager.getConnection(dbURL, userId, password);
        conn.setAutoCommit(false);
        return conn;

    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closePrepareStatement(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getDatabaseProductName(Connection connection) {
        try {
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();
                return metaData.getDatabaseProductName();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
