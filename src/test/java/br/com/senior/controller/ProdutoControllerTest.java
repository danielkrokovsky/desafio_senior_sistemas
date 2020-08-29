package br.com.senior.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import br.com.senior.exception.ProdutoNaoIdentificadoException;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class ProdutoControllerTest {
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
    
    
	@Test
	public void findByIdTest() throws Exception {

		MvcResult result = this.mvc.perform(get("/produto/1542")).andReturn();

		mvc.perform(get("/produto/{id}", 565165l)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isConflict());

	}
}
