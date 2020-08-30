package br.com.senior.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.querydsl.core.types.Predicate;

import br.com.senior.MainTest;
import br.com.senior.entity.Pedido;
import br.com.senior.entity.Produto;
import br.com.senior.entity.QPedido;
import br.com.senior.entity.QProduto;
import br.com.senior.service.PedidoService;
import br.com.senior.service.ProdutoService;
import br.com.senior.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoControllerTest extends MainTest{

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PedidoService pedidoService;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	@Order(1)
	public void saveTest() throws Exception {

		Pedido pedido = new Pedido();

		Predicate p = QProduto.produto.ativo.eq(true);
		List<Produto> produtos = produtoService.findAllByWebQuerydsl(p);

		pedido.setAtivo(true);
		pedido.setProdutos(produtos);

		mvc.perform(MockMvcRequestBuilders.post("/pedido").content(Util.asJsonString(pedido))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void removeTest() throws Exception {

		Pedido pedido = new Pedido();

		Predicate p = QPedido.pedido.ativo.eq(true);
		List<Pedido> produtos = pedidoService.findAllByWebQuerydsl(p);

		if (produtos != null && produtos.size() > 0) {
			pedido = produtos.get(0);
		}

		mvc.perform(delete("/pedido/{id}", pedido.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	public void findByStatus() throws Exception {

		mvc.perform(get("/pedido?ativo=true").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	/**
	 * Teste salvar produtos desativados
	 * @throws Exception
	 */
	@Test
	@Order(4)
	public void saveExecptionTest() throws Exception {

		Pedido pedido = new Pedido();

		Predicate p = QProduto.produto.ativo.eq(false);
		List<Produto> produtos = produtoService.findAllByWebQuerydsl(p);

		//pedido.setAtivo(false);
		pedido.setProdutos(produtos);

		mvc.perform(MockMvcRequestBuilders.post("/pedido").content(Util.asJsonString(pedido))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}
}
