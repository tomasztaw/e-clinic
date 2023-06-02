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
                .group("default")
                .pathsToMatch("/**")
                .packagesToScan(EClinicApplication.class.getPackageName())
                .build();
    }

    @Bean
    public OpenAPI springDocOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("e-clinic application")
                        .contact(contact())
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
