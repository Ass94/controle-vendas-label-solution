package br.com.lable.solution;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.lable.solution.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService service;

	@Bean
	public void inicializar() throws ParseException {
		service.inicializarBancoDados();
	}

}
