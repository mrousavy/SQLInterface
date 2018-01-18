import JavaLogger.JLogger;
import JavaLogger.Logger;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloWorld {

    public static void main(String[] args) {
        Logger<String> logger = new JLogger(System.out);

        logger.Log(Logger.Severity.Info, "Starting...\n");
        String credentials = "?user=insy4" + "&password=blabla" + "&useSSL=false";
        String server = "127.0.0.1:3306";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + server + "/restaurant4a2017" + credentials);
            logger.Log(Logger.Severity.Info, "Connected!");

            connection.close();
            logger.Log(Logger.Severity.Info, "Disconnected.\n");
        } catch (SQLException ex) {
            logger.Log(ex);
            logger.Log(Logger.Severity.Info, "SQL Exception.\n");
        }
    }
}
