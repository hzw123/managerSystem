package com.managerSystem.oasys.config;

import com.managerSystem.oasys.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(customAuthenticationProvider);
    }

//    public void Configure(HttpSecurity http) throws Exception{
//        http.headers().frameOptions().sameOrigin();
//        http
//                .csrf().disable()
//                .authorizeRequests()
////                .antMatchers("/", "/**.html", "/css/**", "/bootstrap/**", "/font-awesome/**", "/img/**","/js/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .formLogin()
//                .successHandler(successHandler)
//                .failureHandler(failureHandler)
////            	.loginPage("/login")			//此处如果打开，，则之前的定制的handler都不生效，，使用的是默认的行为
////            	.defaultSuccessUrl("/", true)
////              .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessHandler(logoutSuccessHandler);
//
//    }

    public void Configure(WebSecurity web){
        String[] antMatchers={
                "login.html","/css/**","/js/**"
        };
        web.ignoring().antMatchers(antMatchers);
    }

}
