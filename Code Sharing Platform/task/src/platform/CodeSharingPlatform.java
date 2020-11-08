package platform;

import freemarker.template.Configuration;

import freemarker.template.TemplateExceptionHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@RestController
public class CodeSharingPlatform {

    public static void main(String[] args) {

        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    @Bean
    public CommandLineRunner runApplication(CodeSnippetRepository codeSnippetRepository) {
        return (args -> {
            // call methods you want to use
        });
    }

}
