package br.com.senior.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import br.com.senior.entity.Pedido;
import br.com.senior.entity.Produto;
import br.com.senior.exception.PedidoFechadoException;
import br.com.senior.exception.ProdutoDesativadoException;
import br.com.senior.repository.PedidoRepository;
import br.com.senior.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

	@Value("${percentual_desconto}")
	private Double desconto;

	public Iterable<Pedido> findAllByWebQuerydsl(Predicate predicate) {

		Iterable<Pedido> iterable = pedidoRepository.findAll(predicate);
		List<Pedido> employees = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		return employees;
	}

	public void update(Pedido pedido) {

		this.pedidoRepository.save(pedido);
	}

	public Pedido finalizarPedido(Pedido pedido) {
		
		pedido.setProdutos(carregarProdutos(pedido));
		validarItensSelecionados(pedido);
		pedido.setValorFinal(aplicarDesconto(pedido));

		return this.pedidoRepository.save(pedido);
	}

	public void removeById(Long id) {

		pedidoRepository.deleteById(id);

	}

	private double aplicarDesconto(Pedido pedido) {

		if (pedido.getAtivo().equals(Boolean.TRUE)) {

			Double valorSemDesconto = pedido.getProdutos().stream()
					.filter(f -> f.getIsServico() == false)
					.mapToDouble(g -> g.getValor()).sum();

			if (valorSemDesconto <= 0) {
				throw new RuntimeException("O valor total não pode ser 0");
			}

			pedido.setValorFinal(valorSemDesconto * desconto);

			return valorSemDesconto * desconto;

		} else {
			throw new PedidoFechadoException("Pedido Fechado, não é possível aplicar desconto");
		}

	}

	private void validarItensSelecionados(Pedido pedido) {

		pedido.getProdutos().forEach(f -> {

			if (!f.isAtivo()) {
				throw new ProdutoDesativadoException(
						"Produto " + f.getNome() + " desativado, não é possível adiciona-lo a lista");
			}
		});
	}
	
	private List<Produto> carregarProdutos(Pedido pedido){
		
		List<Produto> lista = new ArrayList<>();
		
		pedido.getProdutos().forEach(f -> lista.add(this.produtoRepository.findById(f.getId()).get()));
		
		return lista;
		
	}

}
