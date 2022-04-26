package com.employee.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.employee.project.config.ApplicationUserRole.ADMIN;
import static com.employee.project.config.ApplicationUserRole.EMPLOYEE;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class MySecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                 .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeHttpRequests()
//                .antMatchers(HttpMethod.DELETE,"/api2/v1/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/api2/v1/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/api2/v1/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"api2/v1/**").hasAnyRole(ADMIN.name(),EMPLOYEE.name())
//                .antMatchers("/", "/employees")
//                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //.httpBasic();
                .formLogin()
               // .defaultSuccessUrl("/api2/v1/employees",false)
                .and()
                .rememberMe().userDetailsService(userDetailsServiceBean());
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("akash").password("abcd").roles("NORMAL");
//        auth.inMemoryAuthentication().withUser("amit").password("abc").roles("ADMIN");
//    }


    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails employee = User.builder()
                .username("akash")
                .password(this.passwordEncoder().encode("abc"))
                //  .roles(EMPLOYEE.name())
                .authorities(EMPLOYEE.getGrantedAuthorities())
                .build();
        UserDetails admin = User.builder()
                .username("amit")
                .password(this.passwordEncoder().encode("abc"))
                //  .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(employee, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
