package com.example.hw_5.core.security;

public class SecurityConfig {

   /* @Configuration
    public class SecurityConfig {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean // Sadece var olmayan bean eklenmez, var olan override edilebilir.
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth ->
                            auth
                                    .requestMatchers("/swagger-ui/**").permitAll()
                                    .requestMatchers("v3/api-docs/**").permitAll()
                                    .requestMatchers("/login/**").permitAll()
                                    .anyRequest().authenticated()
                    )
                    .csrf(AbstractHttpConfigurer::disable);

            return http.build();
        }
    }*/
}
