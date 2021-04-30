package br.com.lable.solution.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private final ResponseMessage created = customMessage();
	private final ResponseMessage update = simpleMessage(204, "Atualização Ok");
	private final ResponseMessage delete = simpleMessage(204, "Deleção Ok");
	private final ResponseMessage unauthorized = simpleMessage(403, "Não Autorizado");
	private final ResponseMessage notFound = simpleMessage(404, "Não Encontrado");
	private final ResponseMessage serverError = simpleMessage(500, "Erro inesperado");
	
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, Arrays.asList(unauthorized, notFound, serverError))
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(created, unauthorized, serverError))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(update, unauthorized, notFound, serverError))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(delete, unauthorized, notFound, serverError))
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.lable.solution.resources"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("API Controle de Vendas", 
				"Esta API será utilizada para avaliação da empresa Label Solution",
				"Versão 1.0", 
				"https://www.linkedin.com/in/alex-silva-105524129/",
				new Contact(
						"Alex De Souza Silva",
						"https://www.linkedin.com/in/alex-silva-105524129/",
						"alex94tu@gmail.com"),
						"Permitido uso para avaliação",
						null,
						Collections.emptyList());
	}
	
	private ResponseMessage simpleMessage(int code, String message) {
		return new ResponseMessageBuilder().code(code).message(message).build();
	}
	
	private ResponseMessage customMessage() {
		Map<String, Header> map = new HashMap<>();
		map.put("location", new Header("location", "URI do novo recurso", new ModelRef("string")));
		return new ResponseMessageBuilder()
				.code(201)
				.message("Recurso criado")
				.headersWithDescription(map)
				.build();
	}
	
	

}
