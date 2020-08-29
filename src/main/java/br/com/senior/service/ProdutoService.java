package br.com.senior.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import br.com.senior.entity.Produto;
import br.com.senior.exception.ProdutoEmUsoException;
import br.com.senior.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public Iterable<Produto> findAllByWebQuerydsl(Predicate predicate) {

		Iterable<Produto> iterable = produtoRepository.findAll(predicate);
		List<Produto> employees = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		return employees;
	}


	public void create(Produto produto) {

		this.produtoRepository.save(produto);
	}

	public void removeById(Long id) {
		
		Produto prod = new Produto();

		try {
			
			Optional<Produto> p = this.produtoRepository.findById(id);
			prod = p.get();
			
			produtoRepository.deleteById(prod.getId());
			
		} catch (DataIntegrityViolationException e) {
			throw new ProdutoEmUsoException("Não é possível excluir o produto "+prod.getNome()) ;
		}

	}

}
