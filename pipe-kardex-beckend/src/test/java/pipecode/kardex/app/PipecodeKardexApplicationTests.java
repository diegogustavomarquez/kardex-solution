package pipecode.kardex.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pipecode.kardex.app.controller.CategoryController;
import pipecode.kardex.app.controller.MovementController;
import pipecode.kardex.app.controller.ProductController;

@SpringBootTest
class PipecodeKardexApplicationTests {

	@Autowired
	private CategoryController categoryController;

	@Autowired
	private ProductController productController;

	@Autowired
	private MovementController movementController;

	@Test
	void contextLoads() {
		assertThat(categoryController).isNotNull();
		assertThat(productController).isNotNull();
		assertThat(movementController).isNotNull();
	}

}