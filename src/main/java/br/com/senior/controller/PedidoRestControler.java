package br.com.senior.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import br.com.senior.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoRestControler {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping
	public Iterable<Pedido> findAllByWebQuerydsl(@QuerydslPredicate(root = Pedido.class) Predicate predicate) {

		Iterable<Pedido> iterable = pedidoRepository.findAll(predicate);
		List<Pedido> employees = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		return employees;
	}

	@PutMapping
	public void update(Pedido pedido) {

		this.pedidoRepository.save(pedido);
	}

	@PostMapping
	public void create(Pedido pedido) {

		this.pedidoRepository.save(pedido);
	}

	@DeleteMapping(value = "/{id}")
	public void removeById(@PathVariable Long id) {

		pedidoRepository.deleteById(id);

	}

}
