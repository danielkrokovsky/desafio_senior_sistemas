package br.com.senior.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.senior.entity.Produto;
import br.com.senior.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoRestControler {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public Iterable<Produto> findAllByWebQuerydsl(@QuerydslPredicate(root = Produto.class) Predicate predicate) {
		
		return produtoService.findAllByWebQuerydsl(predicate);
	}

	@PutMapping
	public void update(@Valid @RequestBody Produto produto) {

		this.produtoService.create(produto);
	}

	@PostMapping
	public void create(@Valid @RequestBody Produto produto) {

		this.produtoService.create(produto);
	}

	@DeleteMapping(value = "/{id}")
	public void removeById(@NotNull @PathVariable Long id) {

		this.produtoService.removeById(id);

	}

}
