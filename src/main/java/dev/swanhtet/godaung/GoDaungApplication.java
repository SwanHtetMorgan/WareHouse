package dev.swanhtet.godaung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GoDaungApplication {

  public static void main(String[] args) {

    SpringApplication.run(GoDaungApplication.class, args);
  }
}
