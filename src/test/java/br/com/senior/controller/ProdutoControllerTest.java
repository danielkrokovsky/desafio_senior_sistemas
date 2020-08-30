package br.com.senior.controller;

import static org.junit.Assert.assertEquals;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.querydsl.core.types.Predicate;

import br.com.senior.MainTest;
import br.com.senior.entity.Produto;
import br.com.senior.entity.QProduto;
import br.com.senior.service.ProdutoService;
import br.com.senior.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutoControllerTest extends MainTest{

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Autowired
	private ProdutoService service;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void findByIdTest() throws Exception {

		mvc.perform(get("/produto/{id}", 565165l).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void findByIdOkTest() throws Exception {

		Predicate p = QProduto.produto.ativo.eq(true);

		List<Produto> produtos = this.service.findAllByWebQuerydsl(p);
		Produto prod = new Produto();

		if (produtos != null && produtos.size() > 0) {
			prod = produtos.get(0);
		}

		mvc.perform(get("/produto/{id}", prod.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void findByStatus() throws Exception {

		mvc.perform(get("/produto?ativo=true").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void saveTest() throws Exception {

		Produto p = new Produto();

		p.setAtivo(true);
		p.setNome("teste");
		p.setServico(false);
		p.setValor(155.10);
		p.setId(1L);

		mvc.perform(MockMvcRequestBuilders.post("/produto").content(Util.asJsonString(p))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
/*
	@Test
	@Order(1)
	public void removeByIdTest() throws Exception {

		Predicate p = QProduto.produto.ativo.eq(true);
		Produto prod = new Produto();

		List<Produto> produtos = this.service.findAllByWebQuerydsl(p);

		if (produtos != null && produtos.size() > 0) {
			prod = produtos.get(1);
			produtos.clear();
			produtos.add(prod);
		}

		//exception.expect(ProdutoEmUsoException.class);
		mvc.perform(delete("/produto/{id}", prod.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}
*/
	
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

}
