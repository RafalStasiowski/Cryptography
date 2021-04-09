import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Scanner;

/**
 * Class that handle comunication with user through console.
 *
 */
public class TextInterface {

    private DatabaseConnector connector;

    public TextInterface() {
        this.connector = new DatabaseConnector("jdbc:mysql://localhost/desktop_app?user=root&password=&serverTimezone=UTC");
        this.connector.Connect();
    }

    public void showMenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        Scanner scanner = new Scanner(System.in);
        char answer = scanner.nextLine().charAt(0);

        if(answer == '1') {
            CreateUser createUser = new CreateUser();
            User user = createUser.Create();
            InsertUserIntoDatabase insert = new InsertUserIntoDatabase();
            insert.Insert(this.connector,user);
            this.showMenu();

        } else if(answer == '2') {
            System.out.println("Enter username");
            String username = scanner.nextLine();
            System.out.println("Enter password");
            String password = Encrypter.getHash(scanner.nextLine());
            GetUserFromDatabase getUserFromDatabase = new GetUserFromDatabase();
            User user = getUserFromDatabase.getUser(this.connector,username);
            PasswordChecker passwordChecker = new PasswordChecker(user,password);
            if(passwordChecker.check()) {
                System.out.println("Successfully logged in!");
            } else {
                System.out.println("Wrong username or password");
            }
            this.showMenu();
        } else {
            connector.Close();
            System.exit(0);
        }

    }

    public void showRegisterMenu() {
        System.out.println();
    }
}
