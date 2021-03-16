package com.tang.dms.service.impl;

import com.tang.dms.entity.Student;
import com.tang.dms.mapper.UserMapper;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
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
            //在这里获取权限，authorUser用户所应有的权限
           /* Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("vip1"));
            authorities.add(new SimpleGrantedAuthority("vip2"));
            authorities.add(new SimpleGrantedAuthority("vip3"));
//            authorities.add(new SwitchUserGrantedAuthority("vip1",new RunAsUserToken("")));
            User user = new User(studentUser.getSno(),studentUser.getPassword(),authorities);//passwordEncoder.encode(studentUser.getPassword())
            System.out.println("管理员信息："+user.getUsername()+"   "+passwordEncoder.encode(studentUser.getPassword())+"  "+user.getAuthorities());
            return user;*/
           UserDetails userDetails = User.withUsername(studentUser.getName()).password(studentUser.getPassword()).authorities("t1 ").roles("vip1").build();
            if (userDetails==null){
                throw new UsernameNotFoundException("用户不存在！");
            }
            System.out.println("管理员信息："+userDetails.getUsername()+"   "+passwordEncoder.encode(studentUser.getPassword())+"  "+userDetails.getAuthorities());
           return userDetails;
        }
    }
}