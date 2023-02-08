package br.edu.unoesc.backend_com_sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendComSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendComSbApplication.class, args);
	}
	
    // Código padrão para corrgir erro de CORS
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*") //VAI ACEITAR REQUISIÇÃO DE QUALQUER PORTA
						.allowedMethods("GET", "POST", "PUT", "DELETE"); // VAI ACEITAR ESSES MÉTODOS
			}
		};
	}

}
