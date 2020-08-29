package br.com.senior.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;
import br.com.senior.entity.Produto;
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

		produtoRepository.deleteById(id);

	}

}
