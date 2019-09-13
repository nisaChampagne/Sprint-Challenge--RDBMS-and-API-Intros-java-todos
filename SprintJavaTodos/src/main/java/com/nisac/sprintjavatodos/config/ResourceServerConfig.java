package com.nisac.sprintjavatodos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
        //decides who has access to what based on roles and endpoints
{

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        // http.anonymous().disable();
        http.authorizeRequests()
                .antMatchers("/",                       // h2
                        "/h2-console/**",
                        "/swagger-resources/**",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources/configuration/security",
                        "/swagger-resource/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**").permitAll()
                ///list of endpoints in antMatchers
                ///../** means all links underneath it accessible


//                .antMatchers("/users/**", "/quotes/**").authenticated()
//                ///anyone with valid token can go to anything under users and anything under quotes
//                .antMatchers("/roles").hasAnyRole("ADMIN")
//                ///for any endpoint ending in /roles has to have role  ADMIN to access
//                //a regular user would get access denied when attempting to go to ../roles

                    .antMatchers("/users/**").authenticated()
                    .antMatchers("/roles", "/users/", "/todos/**").hasAnyRole("ADMIN")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
