package com.example.databaseschema.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${application.version}")
    private String version;

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Books Api")
                        .description("Book Api that provides a book details")
                        .version(version));
    }

    @Bean
    public GroupedOpenApi authorsEndpoint(){
        return  GroupedOpenApi
                .builder()
                .group("Author")
                .pathsToMatch("/authors/**").build();
    }

    @Bean
    public GroupedOpenApi genresEndpoint(){
        return  GroupedOpenApi
                .builder()
                .group("Genre")
                .pathsToMatch("/genres/**").build();
    }

    @Bean
    public GroupedOpenApi booksEndpoint(){
        return  GroupedOpenApi
                .builder()
                .group("Book")
                .pathsToMatch("/books/**").build();
    }
}
