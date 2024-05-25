package dev.swanhtet.godaung.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration

@RequiredArgsConstructor
public class ApplicationConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public OpenAPI defineOpenApi() {
    Server server = new Server();
    server.setUrl("http://localhost:8080");
    server.setDescription("Development");

    io.swagger.v3.oas.models.info.Contact myContact = new Contact();
    myContact.setName("EduVerse");
    myContact.setEmail("eduverse-contact@gmail.com");

    Info information =
        new Info()
            .title("E-Learning System API")
            .version("1.0")
            .description("This API exposes endpoints to manage E-Learning system.")
            .contact(myContact);
    return new OpenAPI().info(information).servers(List.of(server));
  }

  @Value("${pdf.filepath}")
  public static String pdfFilePath;


}
