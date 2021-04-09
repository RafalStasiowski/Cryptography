import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class CreateUser {
    private String userame;
    private String firstName;
    private String lastName;
    private String password;
    private String salt;
    private Scanner scanner;

    public CreateUser() {
        this.scanner = new Scanner(System.in);
    }

    public User Create() {
        System.out.println("Enter username");
        this.userame = scanner.nextLine();
        System.out.println("Enter first name");
        this.firstName = scanner.nextLine();
        System.out.println("Enter last name");
        this.lastName = scanner.nextLine();
        do {
            System.out.println("Enter password");
            this.password = Encrypter.getHash(scanner.nextLine());
            System.out.println("Repeat password");
        }
        while(Encrypter.getHash(scanner.nextLine()) == this.password);
        try {
            this.salt = Encrypter.getSalt().toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new User(null,this.userame,this.firstName,this.lastName,this.password, this.salt);
    }
}
