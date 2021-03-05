package com.tang.dms.service.impl;

import com.tang.dms.entity.Student;
import com.tang.dms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String sno) throws UsernameNotFoundException {
        Student studentUser = userMapper.getStudentUser(sno);
        if(studentUser == null){
            return null;
        }else{
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            User user = new User(studentUser.getSno(),passwordEncoder.encode(studentUser.getPassword()),authorities);
            System.out.println("管理员信息："+user.getUsername()+"   "+passwordEncoder.encode(studentUser.getPassword())+"  "+user.getAuthorities());
            return user;
        }
    }
}