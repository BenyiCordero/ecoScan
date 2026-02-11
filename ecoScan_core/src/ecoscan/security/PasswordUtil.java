package ecoscan.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Benyi Uriel
 */
public class PasswordUtil {
    
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public static boolean verify(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
    
}
