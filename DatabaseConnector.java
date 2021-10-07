package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:src/evigram_database.db"
            );

            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
