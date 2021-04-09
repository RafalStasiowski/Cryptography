/**
 * User model that contain all fields that are in the database.
 * Default constructor takes as parameters all field values and assigns it to them.
 * Class have method getInsertQuery that returns String with SQL code that allow to insert User into database.
 */
public class User {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String salt;

    public User(Long id,String username, String firstName, String lastName, String password, String salt) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.salt = salt;
    }

    public String getInsertQuery() {
        return "INSERT INTO `desktop_app`.`users`\n" +
                "(`id`,\n" +
                "`username`,\n" +
                "`password`,\n" +
                "`salt`,\n" +
                "`first_name`,\n" +
                "`last_name`)\n" +
                "VALUES\n" +
                "("+this.id+",\n" +
                "\""+this.username+"\""+",\n" +
                "\""+this.password+"\""+",\n" +
                "\""+this.salt+"\""+",\n" +
                "\""+this.firstName+"\""+",\n" +
                "\""+this.lastName+"\""+");\n";
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }
}
