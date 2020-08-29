package br.com.senior.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.senior.entity.ItensPedido;

@RepositoryRestResource(collectionResourceRel = "itenspedido", path = "itenspedido")
public interface ItensPedidoRepository extends PagingAndSortingRepository<ItensPedido, Long>, QuerydslPredicateExecutor<ItensPedido> {
	
	
	     

}
