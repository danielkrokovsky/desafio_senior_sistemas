package br.com.senior.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.senior.entity.Produto;
import br.com.senior.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoRestControler {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public Iterable<Produto> findAllByWebQuerydsl(@QuerydslPredicate(root = Produto.class) Predicate predicate) {

		Iterable<Produto> iterable = produtoRepository.findAll(predicate);
		List<Produto> employees = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		return employees;
	}

}
