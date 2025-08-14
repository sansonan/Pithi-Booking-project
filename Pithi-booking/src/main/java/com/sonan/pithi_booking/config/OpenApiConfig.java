package com.sonan.pithi_booking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pithiBookingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pithi Booking API")
                        .description("API documentation for Pithi Booking platform")
                        .version("1.0.0"));
    }
}
