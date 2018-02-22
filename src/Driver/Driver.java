package Driver;

import JavaLogger.JLogger;
import JavaLogger.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {
    private static Logger<String> _logger = new JLogger(System.out);

    private static String buildConnectionString(String server, String database, String user, String password) {
        String credentials = "?user=" + user + "&password=" + password;
        return "jdbc:mysql://" + server + "/" + database + credentials + "&useSSL=false";
    }

    public static Connection connect(String server, String database, String user, String password) throws SQLException {
        _logger.Log(Logger.Severity.Debug, "Connecting...");
        String connectionString = buildConnectionString(server, database, user, password);
        System.out.println(connectionString);
        return DriverManager.getConnection(connectionString);
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                _logger.Log(ex);
            }
        }
    }
}
