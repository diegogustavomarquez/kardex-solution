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
import pipecode.kardex.app.services.CategoryService;

/**
 * 
 * @author diegogustavomarquez
 *
 */
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

	private static final String API_CATEGORY = "/api/category";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService service;

	@Test
	public void save() throws Exception {

		Category category = new Category();
		category.setName("Deportes");

		Category categoryMock = new Category();
		categoryMock.setId(1L);
		categoryMock.setName("Deportes");
		categoryMock.setCreateAt(new Date());

		when(service.save(any(Category.class))).thenReturn(categoryMock);

		mockMvc.perform(
				MockMvcRequestBuilders.post(API_CATEGORY).content(new ObjectMapper().writeValueAsString(category))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createAt").exists());
	}

	@Test
	public void update() throws Exception {

		Category category = new Category();
		category.setId(1L);
		category.setName("Deportes");
		category.setCreateAt(new Date());

		when(service.findById(any(Long.class))).thenReturn(Optional.of(category));

		when(service.save(any(Category.class))).thenReturn(category);

		mockMvc.perform(
				MockMvcRequestBuilders.put(API_CATEGORY + "/1").content(new ObjectMapper().writeValueAsString(category))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createAt").exists());
	}

	@Test
	public void delete() throws Exception {

		doNothing().when(service).deleteById(1L);

		mockMvc.perform(MockMvcRequestBuilders.delete(API_CATEGORY + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void view() throws Exception {

		Category categoryMock = new Category();
		categoryMock.setId(1L);
		categoryMock.setName("Deportes");
		categoryMock.setCreateAt(new Date());

		when(service.findById(any(Long.class))).thenReturn(Optional.of(categoryMock));

		mockMvc.perform(MockMvcRequestBuilders.get(API_CATEGORY + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createAt").exists());
	}

	@Test
	public void list() throws Exception {

		Category deportCategoryMock = new Category();
		deportCategoryMock.setId(1L);
		deportCategoryMock.setName("Deportes");
		deportCategoryMock.setCreateAt(new Date());

		Category technologyCategoryMock = new Category();
		technologyCategoryMock.setId(1L);
		technologyCategoryMock.setName("Tecnologia");
		technologyCategoryMock.setCreateAt(new Date());

		List<Category> list = Arrays.asList(deportCategoryMock, technologyCategoryMock);

		when(service.findAll()).thenReturn(list);

		mockMvc.perform(MockMvcRequestBuilders.get(API_CATEGORY + "/list").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].createAt").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].createAt").exists());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void listPageable() throws Exception {

		Category deportCategoryMock = new Category();
		deportCategoryMock.setId(1L);
		deportCategoryMock.setName("Deportes");
		deportCategoryMock.setCreateAt(new Date());

		Category technologyCategoryMock = new Category();
		technologyCategoryMock.setId(1L);
		technologyCategoryMock.setName("Tecnologia");
		technologyCategoryMock.setCreateAt(new Date());

		List<Category> list = Arrays.asList(deportCategoryMock, technologyCategoryMock);
		Page<Category> paged = new PageImpl(list);

		when(service.findAll(any(Pageable.class))).thenReturn(paged);

		Pageable pageable = PageRequest.of(0, 10);

		mockMvc.perform(MockMvcRequestBuilders.get(API_CATEGORY + "/list/page")
				.content(new ObjectMapper().writeValueAsString(pageable)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.numberOfElements").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.first").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.empty").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").exists());
	}

}
