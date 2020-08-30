package br.com.senior.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.senior.entity.ItensPedido;
import br.com.senior.service.ItensPedidoService;

@RestController
@RequestMapping("/itenspedido")
public class ItensPedidoRestControler {

	@Autowired
	private ItensPedidoService itensPedidoService;

	@GetMapping
	public Iterable<ItensPedido> findAllByWebQuerydsl(@QuerydslPredicate(root = ItensPedido.class) Predicate predicate) {

		return itensPedidoService.findAllByWebQuerydsl(predicate);
	}

	@PutMapping
	public ResponseEntity<String> update(@Valid @RequestBody  ItensPedido iensPedido) {

		this.itensPedidoService.create(iensPedido);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ItensPedido> create(@Valid @RequestBody ItensPedido iensPedido) {
		
		ItensPedido create = this.itensPedidoService.create(iensPedido);

		return ResponseEntity.created(URI.create("/produto/" + create.getId())).build();
	}

	@DeleteMapping(value = "/{id}")
	public void removeById(@PathVariable @NotNull Long id) {

		this.itensPedidoService.removeById(id);

	}

}
