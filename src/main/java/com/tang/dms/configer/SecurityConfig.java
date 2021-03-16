package com.tang.dms.configer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsService;
    @Autowired
    VerifyCodeFilter verifyCodeFilter;

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("temp1").password("123456").authorities("t1").roles("vip1","vip2").build());
//        manager.createUser(User.withUsername("temp2").password("123456").authorities("t2").roles("vip1","vip2","vip3").build());
//        return manager;
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    //链式编程
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        //首页所有人可以访问，功能页只能对应有权限的人才可以访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

//        没有权限默认会跳到登录页面,需要开启到登录页面
        http
            .formLogin()
            .loginPage("/toLogin")
            .loginProcessingUrl("/login")
            .successForwardUrl("/")
            .failureUrl("/toLogin?error=true")
            .permitAll();// 异常处理

        http.csrf().disable();//关闭csrf功能，登出失败的可能的原因
        http.logout().logoutSuccessUrl("/");//注销 注销后跳到首页

        //记住我的功能开启 cookie,默认保存两周 自定义接受前端参数
        http.rememberMe().rememberMeParameter("remember");

//        http.addFilterAt(adminAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);//.passwordEncoder(new BCryptPasswordEncoder())
    }

}
