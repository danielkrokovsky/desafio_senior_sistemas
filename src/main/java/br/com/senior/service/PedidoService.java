package br.com.senior.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import br.com.senior.entity.Pedido;
import br.com.senior.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;


	public Iterable<Pedido> findAllByWebQuerydsl(Predicate predicate) {

		Iterable<Pedido> iterable = pedidoRepository.findAll(predicate);
		List<Pedido> employees = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		return employees;
	}

	public void update(Pedido pedido) {

		this.pedidoRepository.save(pedido);
	}

	public void create(Pedido pedido) {

		this.pedidoRepository.save(pedido);
	}

	public void removeById(Long id) {

		pedidoRepository.deleteById(id);

	}


}
