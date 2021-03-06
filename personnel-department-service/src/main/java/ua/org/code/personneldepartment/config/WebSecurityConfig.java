package ua.org.code.personneldepartment.config;

import org.springframework.http.HttpMethod;
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
                .antMatchers(HttpMethod.POST, "/personnel-department/**").hasAnyAuthority("SCOPE_ADMIN")
                .antMatchers(HttpMethod.GET, "/personnel-department/cookers/**")
                .hasAnyAuthority("SCOPE_COOKER", "SCOPE_ADMIN")
                .antMatchers(HttpMethod.GET, "/personnel-department/waiters/**")
                .hasAnyAuthority("SCOPE_WAITER", "SCOPE_ADMIN")
                .antMatchers("/personnel-department/waiters/**", "/personnel-department/cookers/**")
                .authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
