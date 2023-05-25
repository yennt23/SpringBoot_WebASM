package Ph28001.ASM.config;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    @Bean
    // authentication
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("hoang")
                .password(encoder.encode("08102003"))
//                .roles("ADMIN")
                .authorities(new SimpleGrantedAuthority("ADMIN"))
                .build();
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("pwd1"))
//                . roles("USER")
                .authorities(new SimpleGrantedAuthority("USER"))
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login", "/").permitAll() // với endpoint /hello thì sẽ được cho qua

                .and()
                .authorizeHttpRequests()
                .requestMatchers("/product", "/category", "/user").hasAnyAuthority("USER", "ADMIN") // với endpoint /customer/** sẽ yêu cầu authenticate
                .requestMatchers("/product/**","/newProduct").hasAuthority("ADMIN")
                .requestMatchers("/admin").hasAuthority("ADMIN")
                .and().formLogin() // trả về page login nếu chưa authenticate
                .and().build();
    }
}