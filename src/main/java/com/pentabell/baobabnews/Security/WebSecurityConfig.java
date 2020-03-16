package com.pentabell.baobabnews.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
    @Autowired
    UserDetailServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

//    @Bean
//    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
//        return new JwtAuthTokenFilter();
//    }

//    @Bean
//    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowUrlEncodedSlash(true);
//        firewall.setAllowSemicolon(true);
//        return firewall;
//    }

    //@Override
    //, WebSecurity web
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
//       / web.httpFirewall(allowUrlEncodedSlashHttpFirewall());

    }
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth

                .inMemoryAuthentication()
                .withUser("admin").password("admin").authorities("ROLE_ADMIN").and()
                .withUser("moderator").password("moderator").authorities("ROLE_Moderateur").and()
                .withUser("journalist").password("journalist").authorities("ROLE_Journalist").and()
                .withUser("user").password("user").authorities("ROLE_USER");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    authorizeRequests()
//    .antMatchers("/api/auth/**",
//    		"/api/file/**",
//    		"/api/dataParts/**",
//    		"/patchzd/**").permitAll()
//    .anyRequest().authenticated()
//    .and()
//    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();
//        http.csrf().disable().authorizeRequests().antMatchers("/api/**","/api/journalist/**")
//                .authenticated().and()
        http.cors().and().csrf().disable().
                authorizeRequests()
<<<<<<< HEAD
                .antMatchers("/journalist/**","/api/admin/**","/api/auth","/api/Moderator/**").permitAll()

                .antMatchers("/journalist/auth/**").access("hasRole('ROLE_Journalist')")
                .antMatchers("/api/Moderator/**").access("hasRole('ROLE_Moderateur')")
                .antMatchers("/api/admin/auth/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/auth/**").access("hasRole('ROLE_USER')")
               .and()
                .formLogin()
                    .loginPage("/journalist/auth/signin").permitAll()
                    .loginPage("/api/Moderator/signin").permitAll()

                .loginProcessingUrl("/journalist/auth/signin") // login proccesing url where authentication happens

                //  .antMatchers("/api/Moderator/PendingJournalist").hasRole("Moderateur")

//
//                .antMatchers("/journalist/auth/signin").hasRole("Journalist")
//               .antMatchers("/api/Moderator/signin").hasRole("Moderateur")
                //.antMatchers("/api/Moderator/**","/api/Admin/**","/journalist/auth/**").permitAll()
                //.hasRole("ADMIN")
//                .antMatchers("/journalist/**").hasRole("Journalist")
//              .antMatchers("/api/Moderator/**").hasRole("Moderateur")
//                .antMatchers("/journalist/auth/**").hasRole("Journalist")
//                .antMatchers("/api/admin/auth/**").hasRole("ADMIN")
                //.anyRequest().authenticated().
              .and().httpBasic().and()

                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

//                .and()
=======
                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/api/journalist/**").permitAll()
//                .antMatchers("/api/Moderator/**").authenticated()
//                .antMatchers("/api/admin/**").authenticated()
//                .antMatchers("/api/user/**").access("hasRole('ROLE_USER')")

                .antMatchers("/api/**").permitAll()

//                .antMatchers("/journalist/auth/signin/**").permitAll()
//                .antMatchers("/api/Moderator/signin/**").permitAll()
                //.antMatchers("/api/**","/journalist/**").permitAll()
                //.hasRole("ADMIN")
//                .antMatchers("/journalist/**").hasRole("Journalist")
//                .antMatchers("/api/Moderator/**").hasRole("Moderateur")
//                .anyRequest().authenticated()
                .and()
//                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//                .and()
                .httpBasic();
//                .and()
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        source.registerCorsConfiguration("/**", config);
        //http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }



}

