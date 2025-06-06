package com.example.main;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
@OpenAPIDefinition(
    info = @Info(
        title = "NexBank Loan Service API", version = "1.0.0",
        description = "NexBank Loan System REST API Documentation.", contact = @Contact(
        name = "NexBank Support Team", email = "support@nexbank.com", url = "https://www.nexbank.com/support"),
        license = @License(
            name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")),
    externalDocs = @ExternalDocumentation(
        description = "NexBank API Full Documentation", url = "https://docs.nexbank.com/account-service"))

public class LoanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }

}
