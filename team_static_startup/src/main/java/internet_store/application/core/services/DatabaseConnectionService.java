package internet_store.application.core.services;

import com.mysql.cj.jdbc.Driver;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

@Component
public class DatabaseConnectionService {

    private final String URL = "jdbc:mysql://localhost:3306/internet_store";
    private final String USERNAME = "root";
    private final String PASSWORD = "12345";

    public Connection getConnection() throws SQLException {
        return openConnection();
    }

    public void closeConnection(Connection connection) {
        if (Objects.isNull(connection)) return;
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection openConnection() throws SQLException {
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
