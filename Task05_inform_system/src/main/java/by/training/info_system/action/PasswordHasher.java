package by.training.info_system.action;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    private PasswordHasher() {
    }

    public static String hashPass(final String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    public static boolean checkPass(final String pass, final String hashedPass) {
        return BCrypt.checkpw(pass, hashedPass);
    }
}
