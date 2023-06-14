package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Config {
}
import java.util.List;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.core.userdetails.User;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.NoOpPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.provisioning.InMemoryUserDetailsManager; import org.springframework.security.provisioning.UserDetailsManager;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
        import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

        import com.spring.security.formlogin.AuthFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    protected UserDetailsManager userDetailsService() {
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("abby")
                .password(passwordEncoder().encode("12345"))
                .authorities("read") .build();
        userDetailsManager.createUser(user);
        return userDetailsManager;

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() .authorizeRequests().anyRequest()
                .authenticated() .and()
                .formLogin()
                .and()
                .rememberMe()
                .and() .logout() .logoutUrl("/logout")
                .logoutSuccessUrl("/login") .deleteCookies("remember-me");
    }
}