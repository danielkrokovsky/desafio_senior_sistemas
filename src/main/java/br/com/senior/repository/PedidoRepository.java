package br.com.senior.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.senior.entity.Pedido;

@RepositoryRestResource(collectionResourceRel = "pedido", path = "pedido")
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long>, QuerydslPredicateExecutor<Pedido> {
	
	
	     

}
