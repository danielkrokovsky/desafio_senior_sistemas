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

import br.com.senior.entity.ItensPedido;
import br.com.senior.repository.ItensPedidoRepository;

@RestController
@RequestMapping("/itenspedido")
public class ItensPedidoRestControler {

	@Autowired
	private ItensPedidoRepository itensPedidoRepository;

	@GetMapping
	public Iterable<ItensPedido> findAllByWebQuerydsl(@QuerydslPredicate(root = ItensPedido.class) Predicate predicate) {

		Iterable<ItensPedido> iterable = itensPedidoRepository.findAll(predicate);
		List<ItensPedido> employees = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		return employees;
	}

	@PutMapping
	public void update(ItensPedido iensPedido) {

		this.itensPedidoRepository.save(iensPedido);
	}

	@PostMapping
	public void create(ItensPedido iensPedido) {

		this.itensPedidoRepository.save(iensPedido);
	}

	@DeleteMapping(value = "/{id}")
	public void removeById(@PathVariable Long id) {

		itensPedidoRepository.deleteById(id);

	}

}
