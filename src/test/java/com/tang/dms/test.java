package com.tang.dms;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description:
 * @author: Tang
 * @create: 2021-03-05 00:08
 */
public class test {
    public static void main(String[] args) {
        String pass = "123456";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("123456",hashPass);
        System.out.println(f);
    }
}
