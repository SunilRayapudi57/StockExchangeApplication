package com.cg.stockapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StockExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockExchangeApplication.class, args);
	}
	
	@Bean 
	public Docket productApi()
	{
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com"))
					.paths(PathSelectors.any()) 
					.build()
					.apiInfo(metaInfo());
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo metaInfo()
	{
		return new ApiInfo(
							  "Stock Exchange Application", 
							  "Sprint 1",
							  "1.0",
							  "",
							  "",
							  "Licence 2.1.7",
							  "https://www.capgemini.com");
	}

}
