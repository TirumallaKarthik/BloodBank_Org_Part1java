import java.sql.*;
public class Connect {
    public Connection setconnection() throws SQLException {
        String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
        java.sql.Connection conn = DriverManager.getConnection(dbURL,"sys as sysdba","harish");
        if (conn != null) {
            System.out.println("Connected");
            return conn;
        }
        return null;

    }
}
