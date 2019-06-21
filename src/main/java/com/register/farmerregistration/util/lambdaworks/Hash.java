package com.register.farmerregistration.util.lambdaworks;


import com.register.farmerregistration.util.lambdaworks.crypto.SCryptUtil;
import org.mindrot.jbcrypt.BCrypt;


public class Hash {
    public static String make(String str) {
        String Salt = BCrypt.gensalt();
        //return SCryptUtil.scrypt(str, 16, 8, 8);
        return BCrypt.hashpw(str, Salt);
    }

    public static boolean check(String str, String hashed) {
        return SCryptUtil.check(str, hashed);
    }

    public static boolean bcryptcheck(String str, String hashed) {
        String replacedString = hashed.replace("$2y$", "$2a$");
        return BCrypt.checkpw(str, replacedString);
    }
}
