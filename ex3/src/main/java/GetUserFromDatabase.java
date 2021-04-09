import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Class that gets user data from database by his username.
 */
public class GetUserFromDatabase {
    public User getUser(DatabaseConnector connector, String username) {
        Statement statement = null;
        User user = null;
        try {
            String sql = "SELECT * FROM desktop_app.users WHERE users.username = \"" + username + "\"";
            //String sql = "SELECT * FROM desktop_app.users WHERE users.username = ?";
            statement = connector.getConnection().createStatement();
            statement.execute(sql);
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                user = new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getString("salt")
                        );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
