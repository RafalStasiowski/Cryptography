import java.sql.Statement;

/**
 * Class uses User SQL code to insert him into database.
 */
public class InsertUserIntoDatabase {
    /**
     * @param connector DatabaseConnector type object that contain connection to database.
     * @param user User object with data.
     */
    public void Insert(DatabaseConnector connector, User user) {
        Statement statement = null;
        try {
            statement = connector.getConnection().createStatement();
            statement.executeUpdate(user.getInsertQuery());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
