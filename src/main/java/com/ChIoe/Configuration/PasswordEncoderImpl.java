package com.ChIoe.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        // 密码加密方法：这里直接返回明文（实际项目中应该加密）
        // rawPassword: 用户输入的原始密码
        // 返回值: "加密后"的密码（这里实际没加密）
        return rawPassword.toString();  //  不安全：不加密，直接返回明文
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 密码验证方法：比较输入的密码和存储的密码
        // rawPassword: 用户输入的原始密码
        // encodedPassword: 数据库中存储的"加密"密码
        // 返回值: true-密码匹配，false-密码不匹配
        return encodedPassword.equals(rawPassword.toString());  // 安全：明文比较
    }
}
