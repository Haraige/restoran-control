package ua.org.code.hall.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                .antMatchers("/hall/refresh")
                .hasAnyAuthority("SCOPE_ADMIN")
                .antMatchers("/hall/waiters/**", "/hall/finished/**")
                .hasAnyAuthority("SCOPE_WAITER", "SCOPE_ADMIN")
                .antMatchers("/hall/reserve", "/hall/tables/free/**", "/hall/swagger-ui.html")
                .permitAll()
                .antMatchers("/hall/waiters/**")
                .authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hall/reserving", "/hall/swagger-ui.html");
    }
}
