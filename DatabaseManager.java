package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager {
    Connection connection;

    public ArrayList<String> users() {
        ArrayList<String> users = new ArrayList<>();

        try {
            connection = DatabaseConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nickname, password FROM users;");

            while (resultSet.next()) {
                users.add(resultSet.getString("nickname") +
                        " " + resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnector.close(connection);
            System.out.println("Connection has been closed.");
        }

        return users;
    }
}
