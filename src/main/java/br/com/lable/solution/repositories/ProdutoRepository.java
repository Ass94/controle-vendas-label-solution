package br.com.lable.solution.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lable.solution.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@Query("SELECT produto FROM Produto produto WHERE produto.nome LIKE %:nome%")
	List<Produto> findByNome(@Param("nome") String nome);
	
	@Query("SELECT produto FROM Produto produto ORDER BY produto.dataVenda ASC")
	List<Produto> orderByDataVenda();

}
