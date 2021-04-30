package br.com.lable.solution.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lable.solution.domain.Produto;
import br.com.lable.solution.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/produtos")
@CrossOrigin("*")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@ApiOperation("Busca todos")
	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@ApiOperation("Insere")
	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		Produto obj = service.insert(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Buscar por id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = service.findById(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@ApiOperation("Atualiza")
	@PutMapping
	public ResponseEntity<Produto> upadate(@RequestBody Produto produto) {
		service.update(produto);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Remove")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Busca por nome")
	@GetMapping(value = "/nome")
	public ResponseEntity<List<Produto>> findByNome(@RequestParam String nome) {
		List<Produto> produtos = service.findByNome(nome);
		return ResponseEntity.ok().body(produtos);
	}
	
	@ApiOperation("Ordena por data")
	@GetMapping(value = "/ordenar")
	public ResponseEntity<List<Produto>> orderByDataVenda() {
		List<Produto> produtos = service.orderByDataVenda();
		return ResponseEntity.ok().body(produtos);
	}
}

