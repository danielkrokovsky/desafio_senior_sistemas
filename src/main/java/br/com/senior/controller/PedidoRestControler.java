package br.com.senior.controller;

import java.net.URI;
import java.util.List;

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

import br.com.senior.entity.Pedido;
import br.com.senior.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoRestControler {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> findAllByWebQuerydsl(@QuerydslPredicate(root = Pedido.class) Predicate predicate) {

		return ResponseEntity.ok(pedidoService.findAllByWebQuerydsl(predicate));
	}

	@PutMapping
	public void update(@Valid @RequestBody Pedido pedido) {

		this.pedidoService.update(pedido);
	}

	@PostMapping
	public ResponseEntity<Pedido> finalizarPedido(@Valid @RequestBody Pedido pedido) {

		Pedido p = this.pedidoService.finalizarPedido(pedido);
		return new ResponseEntity<Pedido>(p, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removeById(@PathVariable @NotNull Long id) {

		this.pedidoService.removeById(id);
		
		return ResponseEntity.ok().build();

	}

}
