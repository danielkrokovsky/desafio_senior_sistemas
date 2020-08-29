package br.com.senior.service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.querydsl.core.types.Predicate;

import br.com.senior.entity.ItensPedido;
import br.com.senior.repository.ItensPedidoRepository;

@Service
public class ItensPedidoService {

	@Autowired
	private ItensPedidoRepository itensPedidoRepository;

	public Iterable<ItensPedido> findAllByWebQuerydsl(
			@QuerydslPredicate(root = ItensPedido.class) Predicate predicate) {

		Iterable<ItensPedido> iterable = itensPedidoRepository.findAll(predicate);
		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}

	public void update(ItensPedido iensPedido) {

		this.itensPedidoRepository.save(iensPedido);
	}

	public void create(ItensPedido iensPedido) {

		this.itensPedidoRepository.save(iensPedido);
	}

	public void removeById(@PathVariable Long id) {

		itensPedidoRepository.deleteById(id);

	}

}
