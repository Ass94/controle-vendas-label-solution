package br.com.lable.solution.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lable.solution.domain.Produto;
import br.com.lable.solution.domain.enums.Status;
import br.com.lable.solution.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private ProdutoRepository repo;

	public void inicializarBancoDados() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Produto produto = new Produto(null, "Cropped Gola Alta", 1, 8.00, 20.00, format.parse("20/02/2021"),
				format.parse("17/03/2021"), Status.Vendido);

		Produto produto1 = new Produto(null, "Cropped Gola Alta", 1, 8.00, 20.00, format.parse("20/02/2021"),
				format.parse("17/03/2021"), Status.Disponivel);

		repo.saveAll(Arrays.asList(produto, produto1));
	}

}
