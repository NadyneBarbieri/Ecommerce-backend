package security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
            .csrf(csrf -> csrf.disable()); // Desabilita o CSRF usando a nova API

        return http.build();
    }
}

//
//package security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            // Configura a autorização das requisições
//            .authorizeHttpRequests(authz -> authz
//                .requestMatchers("/usuarios/logar").permitAll() // Permite acesso a /usuarios/logar
//                .requestMatchers("/usuarios/cadastrar").permitAll() // Permite acesso a /usuarios/cadastrar
//                .requestMatchers("/produtos/**").permitAll() // Permite acesso a todas as rotas de /produtos
//                .anyRequest().authenticated() // Requer autenticação para todas as outras rotas
//            )
//            .csrf(csrf -> csrf.disable()) // Desabilita o CSRF (útil para APIs)
//            .httpBasic(); // Habilita a autenticação básica HTTP (opcional, se desejar autenticação básica)
//
//        return http.build();
//    }
//}
