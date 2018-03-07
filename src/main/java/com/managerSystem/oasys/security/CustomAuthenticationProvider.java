package com.managerSystem.oasys.security;

import com.managerSystem.oasys.domain.Role;
import com.managerSystem.oasys.domain.User;
import com.managerSystem.oasys.enums.AllEnum;
import com.managerSystem.oasys.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    public Authentication authenticate(Authentication auth) throws AuthenticationException{
        String name=auth.getName();
        String passWord=auth.getCredentials().toString();
        logger.debug("username is "+name);
        User user=userRepository.findByName(name);
        if(user==null || name ==null || passWord == null){
            throw new BadCredentialsException("Username not found");
        }

        if(!user.getStatus().equals(AllEnum.USING)){
            throw new BadCredentialsException("user stop");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        if(!bCryptPasswordEncoder.matches(passWord,user.getPassWord())){
            throw new BadCredentialsException("Wrong passWord");
        }

        Set<Role> set=user.getRoles();

        if(set.isEmpty()){
            throw new BadCredentialsException("Role info is missing");
        }

        List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();

        for (Role role:set) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            logger.debug("Role is " + name + ", " + role.getName());
        }

        return new UsernamePasswordAuthenticationToken(name,passWord,grantedAuthorities);
    }

    public boolean supports(Class<?> auth){
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
