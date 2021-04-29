package br.com.lable.solution.test.resources;

import static java.util.Arrays.asList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.lable.solution.domain.Produto;
import br.com.lable.solution.domain.enums.Status;
import br.com.lable.solution.service.ProdutoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProdutoResourceTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private ProdutoService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	
	@Test
	public void findProdutosWhenUrlIsIncorrectShouldReturnStatusCode404() {
		ResponseEntity<String> response = restTemplate.getForEntity("/produto", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void findProdutosWhenUrlIsCorrectShouldReturnStatusCode200() {
		ResponseEntity<String> response = restTemplate.getForEntity("/produtos", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void findProdutosShouldReturnStatusCode200() throws ParseException {
		List<Produto> produtos = asList(
				new Produto(1, "Cropped Gola Alta", 1, 8.00, 20.00, format.parse("20/02/2021"),
						format.parse("17/03/2021"), Status.Vendido),
				new Produto(2, "Cropped Gola Alta", 1, 8.00, 20.00, format.parse("20/02/2021"),
						format.parse("17/03/2021"), Status.Disponivel));
	
		BDDMockito.when(service.findAll()).thenReturn(produtos);
		
		ResponseEntity<String> response = restTemplate.getForEntity("/produtos", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void findProdutosByIdShouldReturnStatus200() throws ParseException {
		Produto produto = new Produto(1, "Cropped Gola Alta", 1, 8.00, 20.00, format.parse("20/02/2021"),
				format.parse("17/03/2021"), Status.Vendido);
		
		BDDMockito.when(service.findById(produto.getId())).thenReturn(produto);
		
		ResponseEntity<Produto> response = restTemplate.getForEntity("/produtos/{id}", Produto.class, produto.getId());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}
	
	@Test
	public void deleteProdutoExistsShouldReturnStatusCode204() throws Exception {
		BDDMockito.doNothing().when(service).delete(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/{id}", 1))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	

	

	
	
	
	
	
	
	
	
	

}
