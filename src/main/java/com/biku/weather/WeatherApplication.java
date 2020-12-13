package com.biku.weather;

import com.biku.weather.security.User;
import com.biku.weather.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Slf4j
@EnableSwagger2
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
@ConfigurationPropertiesScan
public class WeatherApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {

        SpringApplication.run(WeatherApplication.class, args);
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void generateRaport() {
        log.info("At this point we have " + userRepository.count() + " users");
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().size() == 0) {
            User biku = new User();
            biku.setUsername("biku");
            biku.setPassword(passwordEncoder.encode("biku1"));
            biku.setAuthorities(Collections.singletonList(() -> "ROLE_USER"));
            userRepository.save(biku);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin1"));
            admin.setAuthorities(Collections.singletonList(() -> "ROLE_ADMIN"));
            userRepository.save(admin);
        }
    }
}
