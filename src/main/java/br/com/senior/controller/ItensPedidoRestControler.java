package br.com.senior.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public void update(ItensPedido iensPedido) {

		this.itensPedidoService.create(iensPedido);
	}

	@PostMapping
	public void create(ItensPedido iensPedido) {

		this.itensPedidoService.create(iensPedido);
	}

	@DeleteMapping(value = "/{id}")
	public void removeById(@PathVariable Long id) {

		this.itensPedidoService.removeById(id);

	}

}
