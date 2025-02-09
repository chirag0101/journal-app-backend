package net.engineeringdigest.journalApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{
    protected void configure(HttpSecurity httpSecurity){
        httpSecurity.authorizeRequests()
                .antMatchers("/hello").permitAll()  //every user is allowed to access /hello
                .anyRequest().authenticated()   //if any request is made they need to be authenticated
                .and()              //used to add something extra if needed
                .formLogin();       //if the users aren't authenticated than they will be redirected to formlogin
    }
}
