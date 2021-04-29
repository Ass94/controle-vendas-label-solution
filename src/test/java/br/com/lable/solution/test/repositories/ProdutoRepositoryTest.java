package br.com.lable.solution.test.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lable.solution.domain.Produto;
import br.com.lable.solution.domain.enums.Status;
import br.com.lable.solution.repositories.ProdutoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepository repo;
	@Rule
	private ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void insertShouldPersistData() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Produto produto = new Produto(null, "Blusa", 1, 29.99, 39.99, format.parse("20/04/2021"), format.parse("28/04/2021"), Status.Disponivel);
		repo.save(produto);
		assertThat(produto.getId()).isNotNull();
		assertThat(produto.getNome()).isEqualTo("Blusa");
		assertThat(produto.getStatus()).isEqualTo(Status.Disponivel);
	}
	
	@Test
	public void deleteShouldPersistData() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Produto produto = new Produto(null, "Blusa", 1, 29.99, 39.99, format.parse("20/04/2021"), format.parse("28/04/2021"), Status.Disponivel);
		Produto p = repo.save(produto);
		Integer id = p.getId();
		repo.deleteById(id);
		assertThat(repo.findById(id)).isEmpty();
	}
	
	@Test
	public void updateShouldChangeAndPersistData() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Produto produto = new Produto(null, "Blusa", 1, 29.99, 39.99, format.parse("20/04/2021"), format.parse("28/04/2021"), Status.Disponivel);
		produto.setNome("Bermuda");
		produto.setStatus(Status.Vendido);
		repo.save(produto);
		assertThat(produto.getNome()).isEqualTo("Bermuda");
		assertThat(produto.getStatus()).isEqualTo(Status.Vendido);
	
	}
	
	@Test
	public void shouldfindByNome() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Produto produto = new Produto(null, "Calça", 1, 29.99, 39.99, format.parse("20/04/2021"), format.parse("28/04/2021"), Status.Disponivel);
		List<Produto> produtos = repo.findByNome("Cal");
		for (Produto p : produtos) {
			assertThat(produto.getNome()).isEqualTo(p.getNome());
		}
		
	}
	
	
	
	
	
	

}
