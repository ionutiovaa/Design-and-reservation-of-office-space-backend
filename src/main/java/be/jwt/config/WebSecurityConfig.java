package be.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private UserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/createUser",
                        "/users/addUserToEchipa",
                        "/authenticate",
                        "/users/groups",
                        "/users/createUser",
                        "/users/changePassword",
                        "/users/deleteUser",
                        "/users/getUsers",
                        "/users/getUserType",
                        "/users/getUserType/{username}",
                        "/clients/getClients",
                        "/clients/createClient",
                        "/clients/deleteClient/{nume}",
                        "/clients/updateClient",
                        "/departaments/getDepartaments",
                        "/departaments/createDepartament",
                        "/departaments/deleteDepartament/{nume}",
                        "/departaments/updateDepartament",
                        "/departaments/updateResponsabil",
                        "/proiects/getProiects",
                        "/proiects/createProiect",
                        "/proiects/deleteProiect/{nume}",
                        "/proiects/updateProiect",
                        "/proiects/updateClientProiect",
                        "/proiects/updateDepartamentProiect",
                        "/proiects/addProiectToEchipa",
                        "/echipe/getEchipe",
                        "/echipe/createEchipa",
                        "/echipe/deleteEchipa/{nume}",
                        "/echipe/updateEchipa",
                        "/qrcodes/getQRCodes",
                        "/qrcodes/createQRCode",
                        "/qrcodes/deleteQRCode",
                        "/locuri/getLocuri",
                        "/locuri/createLoc",
                        "/locuri/deleteLoc",
                        "/utilizari/createUtilizare"

                )
                .permitAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
