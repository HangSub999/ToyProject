import java.sql.*;

public class JDBCUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            String jdbcUrl = "jdbc:h2:tcp://localhost/~/test";
            conn = DriverManager.getConnection(jdbcUrl, "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement stmt, Connection conn) {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
