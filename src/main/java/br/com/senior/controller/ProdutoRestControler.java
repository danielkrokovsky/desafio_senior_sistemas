package br.com.senior.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
	public ResponseEntity<List<Produto>> findAllByWebQuerydsl(@QuerydslPredicate(root = Produto.class) Predicate predicate) {
		
		return ResponseEntity.ok(produtoService.findAllByWebQuerydsl(predicate));
	}
	
	@GetMapping(value = "/{id}")
	public Produto findById(@NotNull @PathVariable Long id) {
		
		return produtoService.findById(id);
	}


	@PutMapping
	public void update(@Valid @RequestBody Produto produto) {

		this.produtoService.create(produto);
	}

	@PostMapping
	public ResponseEntity<Produto> create(@Valid @RequestBody Produto produto) {

		 Produto p = this.produtoService.create(produto);
		 
		 return ResponseEntity.created(URI.create("/produto/" + p.getId())).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> removeById(@NotNull @PathVariable Long id) {

		this.produtoService.removeById(id);
		
		return new ResponseEntity<String>(HttpStatus.OK);

	}

}
