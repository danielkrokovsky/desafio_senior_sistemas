package br.com.senior.controller;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.querydsl.core.types.Predicate;

import br.com.senior.entity.ItensPedido;
import br.com.senior.entity.Produto;
import br.com.senior.entity.QItensPedido;
import br.com.senior.entity.QProduto;
import br.com.senior.service.ItensPedidoService;
import br.com.senior.service.ProdutoService;
import br.com.senior.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class ItensControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Autowired
	private ItensPedidoService service;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	/*
	@Test
	public void findByIdTest() throws Exception {

		mvc.perform(get("/itenspedido/{id}", 565165l).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}*/

	
	@Test
	public void findByIdOkTest() throws Exception {

		Predicate p = QItensPedido.itensPedido.status.eq(true);

		List<ItensPedido> pedidos = this.service.findAllByWebQuerydsl(p);
		ItensPedido ped = new ItensPedido();

		if (pedidos != null && pedidos.size() > 0) {
			ped = pedidos.get(0);
		}

		mvc.perform(get("/produto/{id}", ped.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	/*
	@Test
	public void findByStatus() throws Exception {

		mvc.perform(get("/produto?ativo=true").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}*/

	@Test
	public void saveTest() throws Exception {

		ItensPedido itens = new ItensPedido();
		
		Predicate p = QProduto.produto.ativo.eq(true);
		List<Produto> produtos = produtoService.findAllByWebQuerydsl(p);
		
		itens.setStatus(true);
		itens.setProdutos(produtos);
		itens.setId(1L);

		mvc.perform(MockMvcRequestBuilders.post("/itenspedido").content(Util.asJsonString(itens))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
/*
	@Test
	public void removeByIdTest() throws Exception {

		Predicate p = QProduto.produto.ativo.eq(true);
		Produto prod = new Produto();

		List<Produto> produtos = this.service.findAllByWebQuerydsl(p);

		if (produtos != null && produtos.size() > 0) {
			prod = produtos.get(0);
		}

		mvc.perform(delete("/produto/{id}", prod.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void updateTest() throws Exception {

		Predicate p = QProduto.produto.ativo.eq(false);
		Produto prod = new Produto();

		List<Produto> produtos = this.service.findAllByWebQuerydsl(p);

		if (produtos != null && produtos.size() > 0) {
			prod = produtos.get(0);
		}

		prod.setAtivo(true);

		mvc.perform(MockMvcRequestBuilders.put("/produto").content(Util.asJsonString(prod))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		Produto prod2 = this.service.findById(prod.getId());

		assertEquals(prod2.isAtivo(), true);
	}
	*/
}
