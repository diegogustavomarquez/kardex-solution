package pipecode.kardex.app.controller;

import static org.mockito.ArgumentMatchers.any;
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

import pipecode.kardex.app.constant.TypeMovement;
import pipecode.kardex.app.model.Movement;
import pipecode.kardex.app.services.MovementService;

/**
 * 
 * @author diegogustavomarquez
 *
 */
@WebMvcTest(MovementController.class)
public class MovementControllerTest {

	private static final String API_MOVEMENT = "/api/movement";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovementService service;

	@Test
	public void view() throws Exception {

		Movement movementMock = new Movement();
		movementMock.setId(1L);
		movementMock.setProductCode("NIKEBARTEMP20");
		movementMock.setProductName("Camiseta Barcelona 2020");
		movementMock.setCategory("Deporte");
		movementMock.setCount(20L);
		movementMock.setType(TypeMovement.ADD.getType());

		movementMock.setCreateAt(new Date());

		when(service.findById(any(Long.class))).thenReturn(Optional.of(movementMock));

		mockMvc.perform(MockMvcRequestBuilders.get(API_MOVEMENT + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productName").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.type").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createAt").exists());
	}

	@Test
	public void list() throws Exception {

		Movement deportMovementMock = new Movement();
		deportMovementMock.setId(1L);
		deportMovementMock.setProductCode("NIKEBARTEMP20");
		deportMovementMock.setProductName("Camiseta Barcelona 2020");
		deportMovementMock.setCategory("Deporte");
		deportMovementMock.setCount(20L);
		deportMovementMock.setType(TypeMovement.ADD.getType());
		deportMovementMock.setCreateAt(new Date());

		Movement technologyMovementMock = new Movement();
		technologyMovementMock.setId(1L);
		technologyMovementMock.setProductCode("NIKEBARTEMP20");
		technologyMovementMock.setProductName("Camiseta Barcelona 2020");
		technologyMovementMock.setCategory("Deporte");
		technologyMovementMock.setCount(20L);
		technologyMovementMock.setType(TypeMovement.GET.getType());
		technologyMovementMock.setCreateAt(new Date());

		List<Movement> list = Arrays.asList(deportMovementMock, technologyMovementMock);

		when(service.findAll()).thenReturn(list);

		mockMvc.perform(MockMvcRequestBuilders.get(API_MOVEMENT + "/list").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productName").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].type").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].createAt").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].productCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].productName").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].category").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].type").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].count").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].createAt").exists());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void listPageable() throws Exception {

		Movement deportMovementMock = new Movement();
		deportMovementMock.setId(1L);
		deportMovementMock.setProductCode("NIKEBARTEMP20");
		deportMovementMock.setProductName("Camiseta Barcelona 2020");
		deportMovementMock.setCategory("Deporte");
		deportMovementMock.setCount(20L);
		deportMovementMock.setType(TypeMovement.ADD.getType());
		deportMovementMock.setCreateAt(new Date());

		Movement technologyMovementMock = new Movement();
		technologyMovementMock.setId(1L);
		technologyMovementMock.setProductCode("NIKEBARTEMP20");
		technologyMovementMock.setProductName("Camiseta Barcelona 2020");
		technologyMovementMock.setCategory("Deporte");
		technologyMovementMock.setCount(20L);
		technologyMovementMock.setType(TypeMovement.GET.getType());
		technologyMovementMock.setCreateAt(new Date());

		List<Movement> list = Arrays.asList(deportMovementMock, technologyMovementMock);

		Page<Movement> paged = new PageImpl(list);

		when(service.findAll(any(Pageable.class))).thenReturn(paged);

		Pageable pageable = PageRequest.of(0, 10);

		mockMvc.perform(MockMvcRequestBuilders.get(API_MOVEMENT + "/list/page")
				.content(new ObjectMapper().writeValueAsString(pageable)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.numberOfElements").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.first").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.empty").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").exists());
	}

}
