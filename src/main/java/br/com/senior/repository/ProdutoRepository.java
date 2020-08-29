package br.com.senior.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.senior.entity.Produto;


@RepositoryRestResource(collectionResourceRel = "produto", path = "produto")
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>, QuerydslPredicateExecutor<Produto> {
	
	

}
