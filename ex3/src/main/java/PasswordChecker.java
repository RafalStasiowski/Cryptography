/**
 * Class that ensure that password is correct.
 *
 */
public class PasswordChecker {
    private User user;
    private String password;
    public PasswordChecker(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public boolean check() {

        String tmp1 = (Encrypter.getSaltedPassword(this.user.getPassword(), this.user.getSalt().getBytes()));
        String tmp2 = (Encrypter.getSaltedPassword(this.password,this.user.getSalt().getBytes()));

        if(tmp1.equals(tmp2)) {
                return true;
        }
        else {
            return false;
        }
    }
}
