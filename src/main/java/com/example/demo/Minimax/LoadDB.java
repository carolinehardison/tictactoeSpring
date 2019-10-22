package com.example.demo.Minimax;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDB {

    @Autowired
    Computer computer;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            log.info("Preloading database...");
            computer.setDifficulty(3);
            Board b = new Board();
            computer.move(b);
            log.info("Database loaded");
        };
    }


}
