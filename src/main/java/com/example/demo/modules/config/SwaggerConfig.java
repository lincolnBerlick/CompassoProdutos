package com.example.demo.modules.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                    List.of(new ResponseMessageBuilder().code(400).message("status_code: 400 \n message: error message").build()))
                .globalResponseMessage(RequestMethod.PUT,
                    List.of(new ResponseMessageBuilder().code(400).message("status_code: 400 \n message: error message").build()))
                .globalResponseMessage(RequestMethod.DELETE,
                    List.of(new ResponseMessageBuilder().code(400).message("status_code: 400 \n message: error message").build()))
                .globalResponseMessage(RequestMethod.POST,
                    List.of(new ResponseMessageBuilder().code(400).message("status_code: 400 \n message: error message").build()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//<6>, regex must be in double quotes.
                .build();
        }

}
