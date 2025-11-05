package br.com.samuel.album_generator; // Lembre-se do seu underscore!

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Diz ao Spring que esta é uma classe de configuração
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica-se a todos os endpoints que começam com /api/
            .allowedOrigins("http://localhost:5173") // <-- Permite pedidos vindos desta origem
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Quais métodos HTTP são permitidos
            .allowedHeaders("*") // Quais cabeçalhos são permitidos
            .allowCredentials(true); // Permite o envio de credenciais (como cookies), se houver
    }
}