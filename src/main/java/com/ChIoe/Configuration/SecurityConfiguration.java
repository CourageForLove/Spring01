package com.ChIoe.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
/*@Component 是 Spring 中最基础的注解，
用于标记一个类为 Spring 组件，
让 Spring 容器能够自动检测和管理它。
*/
@Configuration//配置类
@EnableWebSecurity
public class SecurityConfiguration {
    /**
     * 自定义密码编码器（需要你原有的 PasswordEncoderImpl 类）
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderImpl();  // 你的自定义密码编码器
    }
    //@Autowired
    //private PasswordEncoderImpl passwordEncoder;

    /**
     * 内存用户详情服务
     * 替换原来的 configure(AuthenticationManagerBuilder auth) 方法
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // 创建用户
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("123456"))  // 使用密码编码器
                .roles("USER")
                .build();
        /*
        * 用户名: user
        密码: 123456（会被加密）
        角色: USER
        * */
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("608912"))  // 使用密码编码器
                .roles("ADMIN", "USER")  // 注意：这里用逗号分隔角色
                .build();
        /*用户名: admin
        密码: 608912（会被加密）
        角色: ADMIN 和 USER（双角色）*/
        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * 安全过滤器链配置
     * 替换原来的 configure(HttpSecurity http) 方法
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 授权配置
                .authorizeHttpRequests(authorize -> authorize
                        // 精确匹配路径
                        .requestMatchers("/admin").hasRole("ADMIN")

                        // 访问表达式
                        .requestMatchers("/User").hasAnyRole("ADMIN", "USER")

                        // 其他所有请求需要认证
                        .anyRequest().authenticated()
                )//lambda表达式
                // 表单登录配置
                .formLogin(form -> form
                        .permitAll()  // 允许所有人访问登录页
                )

                // 注销配置
                .logout(logout -> logout
                        .permitAll()  // 允许所有人访问注销
                )

                // 禁用CSRF
                .csrf(csrf -> csrf.disable());
        /*
        * 这段代码配置了 Spring Security 的 HTTP 安全规则：
        * 首先进行授权配置，
        * 要求访问 /admin 路径必须具有 ADMIN 角色，
        * 访问 /User 路径需要 ADMIN 或 USER 角色，
        * 所有其他请求都需要用户认证；
        * 然后配置表单登录和注销功能，允许所有用户访问登录和注销页面；
        * 最后禁用了 CSRF 防护功能。整个配置构建了一个基础的基于角色和路径的权限控制系统。
        * */
        return http.build();
    }
}
