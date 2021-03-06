package pipecode.kardex.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import pipecode.kardex.app.model.Category;
import pipecode.kardex.app.model.Product;
import pipecode.kardex.app.services.ProductService;

/**
 * 
 * @author diegogustavomarquez
 *
 */
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	private static final String API_PRODUCT = "/api/product";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService service;

	@Test
	public void save() throws Exception {

		Category category = new Category();
		category.setId(1L);
		category.setName("Deportes");
		category.setCreateAt(new Date());

		Product product = new Product();
		product.setName("Remera");
		product.setCode("REM MASCULINA L");
		product.setCount(10L);
		product.setCategory(category);

		Product productMock = new Product();
		productMock.setId(1L);
		productMock.setName("Remera");
		productMock.setCode("REM MASCULINA L");
		productMock.setCount(10L);
		productMock.setCategory(category);
		productMock.setCreateAt(new Date());

		when(service.save(any(Product.class))).thenReturn(productMock);

		mockMvc.perform(MockMvcRequestBuilders.post(API_PRODUCT).content(new ObjectMapper().writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createAt").exists());
	}

	@Test
	public void update() throws Exception {

		Category category = new Category();
		category.setId(1L);
		category.setName("Deportes");
		category.setCreateAt(new Date());

		Product product = new Product();
		product.setId(1L);
		product.setName("Remera");
		product.setCode("REM MASCULINA L");
		product.setCount(10L);
		product.setCategory(category);
		product.setCreateAt(new Date());

		when(service.findById(any(Long.class))).thenReturn(Optional.of(product));

		when(service.save(any(Product.class))).thenReturn(product);

		mockMvc.perform(
				MockMvcRequestBuilders.put(API_PRODUCT + "/1").content(new ObjectMapper().writeValueAsString(product))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createAt").exists());
	}

	@Test
	public void delete() throws Exception {

		doNothing().when(service).deleteById(1L);

		mockMvc.perform(MockMvcRequestBuilders.delete(API_PRODUCT + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void view() throws Exception {

		Category category = new Category();
		category.setId(1L);
		category.setName("Deportes");
		category.setCreateAt(new Date());

		Product productMock = new Product();
		productMock.setId(1L);
		productMock.setName("Remera");
		productMock.setCode("REM MASCULINA L");
		productMock.setCount(10L);
		productMock.setCategory(category);
		productMock.setCreateAt(new Date());

		when(service.findById(any(Long.class))).thenReturn(Optional.of(productMock));

		mockMvc.perform(MockMvcRequestBuilders.get(API_PRODUCT + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createAt").exists());
	}

	@Test
	public void list() throws Exception {

		Category category = new Category();
		category.setId(1L);
		category.setName("Deportes");
		category.setCreateAt(new Date());

		Product product1Mock = new Product();
		product1Mock.setId(1L);
		product1Mock.setName("Remera");
		product1Mock.setCode("REM MASCULINA L");
		product1Mock.setCount(10L);
		product1Mock.setCategory(category);
		product1Mock.setCreateAt(new Date());

		Product product2Mock = new Product();
		product2Mock.setId(2L);
		product2Mock.setName("Pantalon");
		product2Mock.setCode("PANTALON CORTO");
		product2Mock.setCount(5L);
		product2Mock.setCategory(category);
		product2Mock.setCreateAt(new Date());

		List<Product> list = Arrays.asList(product1Mock, product2Mock);

		when(service.findAll()).thenReturn(list);

		mockMvc.perform(MockMvcRequestBuilders.get(API_PRODUCT + "/list").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].createAt").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].createAt").exists());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void listPageable() throws Exception {

		Category category = new Category();
		category.setId(1L);
		category.setName("Deportes");
		category.setCreateAt(new Date());

		Product product1Mock = new Product();
		product1Mock.setId(1L);
		product1Mock.setName("Remera");
		product1Mock.setCode("REM MASCULINA L");
		product1Mock.setCount(10L);
		product1Mock.setCategory(category);
		product1Mock.setCreateAt(new Date());

		Product product2Mock = new Product();
		product2Mock.setId(2L);
		product2Mock.setName("Pantalon");
		product2Mock.setCode("PANTALON CORTO");
		product2Mock.setCount(5L);
		product2Mock.setCategory(category);
		product2Mock.setCreateAt(new Date());

		List<Product> list = Arrays.asList(product1Mock, product2Mock);
		Page<Product> paged = new PageImpl(list);

		when(service.findAll(any(Pageable.class))).thenReturn(paged);

		Pageable pageable = PageRequest.of(0, 10);

		mockMvc.perform(MockMvcRequestBuilders.get(API_PRODUCT + "/list/page")
				.content(new ObjectMapper().writeValueAsString(pageable)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.numberOfElements").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.first").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.empty").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").exists());
	}

	@Test
	public void increase() throws Exception {
		Category category = new Category();
		category.setId(1L);
		category.setName("Deportes");
		category.setCreateAt(new Date());

		Product productMock = new Product();
		productMock.setId(1L);
		productMock.setName("Remera");
		productMock.setCode("REM MASCULINA L");
		productMock.setCount(10L);
		productMock.setCategory(category);
		productMock.setCreateAt(new Date());

		when(service.increase(any(Long.class), any(Long.class))).thenReturn(productMock);

		mockMvc.perform(MockMvcRequestBuilders.post(API_PRODUCT + "/increase-by-id/" + 1 + "/count/" + 1)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createAt").exists());
	}

	@Test
	public void decrease() throws Exception {
		Category category = new Category();
		category.setId(1L);
		category.setName("Deportes");
		category.setCreateAt(new Date());

		Product productMock = new Product();
		productMock.setId(1L);
		productMock.setName("Remera");
		productMock.setCode("REM MASCULINA L");
		productMock.setCount(10L);
		productMock.setCategory(category);
		productMock.setCreateAt(new Date());

		when(service.decrease(any(Long.class), any(Long.class))).thenReturn(productMock);

		mockMvc.perform(MockMvcRequestBuilders.post(API_PRODUCT + "/decrease-by-id/" + 1 + "/count/" + 1)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createAt").exists());
	}
}
