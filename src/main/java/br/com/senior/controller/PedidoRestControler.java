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

import br.com.senior.entity.Pedido;
import br.com.senior.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoRestControler {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public Iterable<Pedido> findAllByWebQuerydsl(@QuerydslPredicate(root = Pedido.class) Predicate predicate) {

		return pedidoService.findAllByWebQuerydsl(predicate);
	}

	@PutMapping
	public void update(Pedido pedido) {

		this.pedidoService.create(pedido);
	}

	@PostMapping
	public void create(Pedido pedido) {

		this.pedidoService.create(pedido);
	}

	@DeleteMapping(value = "/{id}")
	public void removeById(@PathVariable Long id) {

		this.pedidoService.removeById(id);

	}

}
