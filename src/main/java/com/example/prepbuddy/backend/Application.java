package com.example.prepbuddy.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Autowired
	BookmarkRepo bookmarkRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	 // This method will run after the app starts
    @Bean
    public CommandLineRunner runAfterStartup() {
        return args -> {
            bookmarkRepository.getBookmarksForStudent("Talha");
        };
    }

}
