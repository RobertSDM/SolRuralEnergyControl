package com.mai.solar.energyControl.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers(HttpMethod.GET, "/actuator/**", "/favicon.ico").permitAll()
                        .requestMatchers(HttpMethod.GET, "/farm", "/farm/{id}", "/solar-panel", "/solar-panel/{id}",
                                "/energy-history", "/energy-history/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/farm", "/solar-panel").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/farm", "/solar-panel").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/farm/{id}", "/solar-panel/{id}").permitAll()

                        .anyRequest().authenticated())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .build();
    }
}
