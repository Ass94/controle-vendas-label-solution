package br.com.lable.solution.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lable.solution.domain.Produto;
import br.com.lable.solution.repositories.ProdutoRepository;
import br.com.lable.solution.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	
	public Produto findById(Integer id) {
		Optional<Produto> produto = repo.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + " Tipo: " + Produto.class.getName()));
	}
	
	public List<Produto> findAll() {
		return repo.findAll();
	}
	
	public Produto insert(Produto produto) {
		return repo.save(produto);
	}
	
	public Produto update(Produto produto) {
		Produto newProduto = findById(produto.getId());
		updateProduto(produto, newProduto);
		return repo.save(newProduto);
	}
	
	private void updateProduto(Produto oldProduto, Produto newProduto) {
		newProduto.setNome(oldProduto.getNome());
		newProduto.setQuantidade(oldProduto.getQuantidade());
		newProduto.setValorCompra(oldProduto.getValorCompra());
		newProduto.setValorVenda(oldProduto.getValorVenda());
		newProduto.setDataCompra(oldProduto.getDataCompra());
		newProduto.setDataVenda(oldProduto.getDataCompra());
		newProduto.setStatus(oldProduto.getStatus());
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public List<Produto> findByNome(String nome) {
		return repo.findByNome(nome);
	}
	
	public List<Produto> orderByDataVenda() {
		return repo.orderByDataVenda();
	}
	

}

