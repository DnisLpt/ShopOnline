package shoponline.configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import shoponline.security.SecurityUserDetaillService;


@Configuration
@EnableWebSecurity
public class WebConfig {

    private SecurityUserDetaillService uzerAuthService;

    public WebConfig(SecurityUserDetaillService uzerAuthService) {
        this.uzerAuthService = uzerAuthService;
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home","/").permitAll()
                .antMatchers("/admin","/category/add","/productType/add/*").hasAuthority("ADMIN")
                .antMatchers("/request/*","/product/addToBasket/*").hasAuthority("USER")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/home");

        return http.build();
    }

}