package Driver;

import JavaLogger.JLogger;
import JavaLogger.Logger;

import java.sql.*;

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

    public static int getVersion(Connection connection, int kontoNr) throws SQLException {
        PreparedStatement ps = null;
        try {
            String query = "SELECT ver FROM konto WHERE knr = " + kontoNr;
            ps = connection.prepareStatement(query);

            ResultSet result = ps.executeQuery(query);
            if (result.next())
                return result.getInt("ver");
            else
                throw new SQLException("No results returned!");
        } catch (SQLException ex) {
            _logger.Log(ex);
            throw ex;
        } finally {
            if (ps != null)
                ps.close();
        }
    }

    public static boolean kontoExists(Connection connection, int kontoNr) throws SQLException {
        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM konto WHERE knr = " + kontoNr;
            ps = connection.prepareStatement(query);

            ResultSet result = ps.executeQuery(query);
            return result.next();
        } catch (SQLException ex) {
            _logger.Log(ex);
            throw ex;
        } finally {
            if (ps != null)
                ps.close();
        }
    }

    public static void incrementVersion(Connection connection, int kontoNr) throws SQLException {
        int version = getVersion(connection, kontoNr);
        incrementVersion(connection, kontoNr, version + 1);
    }

    public static void incrementVersion(Connection connection, int kontoNr, int version) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE konto SET ver = ? WHERE knr = ?");
            ps.setInt(1, version);
            ps.setInt(2, kontoNr);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            _logger.Log(ex);
            throw ex;
        }
    }

    public static void updateKontoStand(Connection connection, int kontoNr, int kstand) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE konto SET kstand = ? WHERE knr = ?");
            ps.setInt(1, kstand);
            ps.setInt(2, kontoNr);

            ps.executeUpdate();
            ps.close();
            incrementVersion(connection, kontoNr);
        } catch (SQLException ex) {
            _logger.Log(ex);
            throw ex;
        }
    }

    public static void deleteKonto(Connection connection, int kontoNr) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM konto WHERE knr = ?");
            ps.setInt(1, kontoNr);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            _logger.Log(ex);
            throw ex;
        }
    }

    public static int getKontoStand(Connection connection, int kontoNr) throws SQLException {
        PreparedStatement ps = null;
        try {
            String query = "SELECT kstand FROM konto WHERE knr = " + kontoNr;
            ps = connection.prepareStatement(query);

            ResultSet result = ps.executeQuery(query);
            if (result.next())
                return result.getInt("kstand");
            else
                throw new SQLException("No results returned!");
        } catch (SQLException ex) {
            _logger.Log(ex);
            throw ex;
        } finally {
            if (ps != null)
                ps.close();
        }
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
