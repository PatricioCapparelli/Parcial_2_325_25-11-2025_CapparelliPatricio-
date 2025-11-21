package cine.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    public static String hashearPassword(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    public static boolean verificarPassword(String passwordPlano, String hashGuardado) {
        try {
            return BCrypt.checkpw(passwordPlano, hashGuardado);
        } catch (Exception e) {
            return false;
        }
    }
}