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
				registry.addMapping("/channels/*").allowedOrigins("https://www.lijust.in", "http://localhost:5173/");
				registry.addMapping("/movies/*").allowedOrigins("https://www.lijust.in", "http://localhost:5173/");
				registry.addMapping("/tvshows/*").allowedOrigins("https://www.lijust.in", "http://localhost:5173/");
				registry.addMapping("/users/*").allowedOrigins("https://www.lijust.in", "http://localhost:5173/");
			}
		};
	}
}
