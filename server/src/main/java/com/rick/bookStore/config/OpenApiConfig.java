package com.rick.bookStore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
// configuration class for Swagger
//http://localhost:8080/swagger-ui/index.html
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info((new Info()
                        .title("Bookstore")
                        .version("v1")
                        .description("e-commerce for books.")));
    }
}
