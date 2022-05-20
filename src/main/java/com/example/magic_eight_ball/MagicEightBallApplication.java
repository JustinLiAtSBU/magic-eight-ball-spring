package com.example.magic_eight_ball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MagicEightBallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagicEightBallApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/channels/all").allowedOrigins("http://localhost:3000");
				registry.addMapping("/movies/random").allowedOrigins("http://localhost:3000");
				registry.addMapping("/movies/all").allowedOrigins("http://localhost:3000");
				registry.addMapping("/tvshows/random").allowedOrigins("http://localhost:3000");
				registry.addMapping("/tvshows/all").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
