package imr.supermarket.pos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbMSSQL {

    public static Connection dbMSSQL() throws ClassNotFoundException, SQLException {
        // Load MSSQL JDBC Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Connection URL (Update with your server details)
        String url = "jdbc:sqlserver://DESKTOP-TTRL64D\\SQLEXPRESS:1433;databaseName=pos;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String password = "admin";

        // Establish and return the connection
        return DriverManager.getConnection(url, user, password);
    }
}
