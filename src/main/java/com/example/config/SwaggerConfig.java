package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket jokesApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("guru.springframework.controllers"))
      .paths(regex("/api/v1"))
      .build();

  }

  private ApiInfo metaData() {
    ApiInfo apiInfo = new ApiInfo(
      "Spring Boot REST API",
      "Spring Boot REST API for Jokes",
      "1.0",
      "Terms of service",
      new Contact("Andromachi Rozaki", "", "andromachirozakii@gmail.com"),
      "Apache License Version 2.0",
      "https://www.apache.org/licenses/LICENSE-2.0");
    return apiInfo;
  }
}