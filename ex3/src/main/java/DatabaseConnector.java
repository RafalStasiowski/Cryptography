import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class that allows easier management with connection to database
 */
public class DatabaseConnector {

    private String connectionString;
    private Connection connection;

    public DatabaseConnector(String connectionStrings) {
        this.connectionString = connectionStrings;
        this.connection = null;
    }

    public boolean Connect() {
        boolean connected = false;
        try {
            connection = DriverManager.getConnection(connectionString);
            connected = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connected;
    }


    public void Close()  {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
