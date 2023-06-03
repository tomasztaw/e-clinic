package pl.taw.infrastructure.configuration;

/**
 * Configuration class for the API generator
 */

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.taw.EClinicApplication;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("default") // nazwa grupy dla dokumentacji
                .pathsToMatch("/**") // wzorzec ścieżki pasujących endpoint-ów, w tym przypadku wszystko
                .packagesToScan(EClinicApplication.class.getPackageName()) // pakiety, które mają być przeskanowane
                .build();
    }

    @Bean
    public OpenAPI springDocOpenApi() {
        return new OpenAPI()
                .components(new Components()) // tworzenie nowego obiektu Components zawierającego różne komponenty, takie jak schematy, definicje parametrów
                .info(new Info()  // metadane aplikacji
                        .title("e-clinic application")
                        .contact(contact()) // zwracany jest kontakt
                        .version("1.0"));
    }

    @Bean
    public Contact contact() {
        return new Contact()
                .name("TomaszTAW")
                .url("https://tomasztaw@github.io")
                .email("tomasz.wegrzyn.wro@gmail.com");
    }
}
