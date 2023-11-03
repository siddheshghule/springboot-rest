package com.digitalhotelmanagement.digitalhotelmanagement.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	Contact contact = new Contact("Siddhesh Ghule","","siddhesh_ghule@outlook.com");
	   @Bean
	    public Docket apiDocket() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	        		.select()
	                .apis(RequestHandlerSelectors.basePackage("com.digitalhotelmanagement.digitalhotelmanagement"))
	                .paths(PathSelectors.any())
	                .build();
	    }
	   private ApiInfo apiInfo() {
			return new ApiInfoBuilder().title("Hotel Administration")
					.description("Springboot Application for Digital Hotel Management")
					.contact(contact)
					.version("1.0").build();
		}
	
}
