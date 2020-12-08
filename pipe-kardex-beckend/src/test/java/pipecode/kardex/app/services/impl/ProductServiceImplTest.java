package pipecode.kardex.app.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pipecode.kardex.app.PipecodeKardexApplication;
import pipecode.kardex.app.exception.CountCannotLessThanZeroException;
import pipecode.kardex.app.exception.EntityNotExistException;
import pipecode.kardex.app.model.Category;
import pipecode.kardex.app.model.Product;
import pipecode.kardex.app.repository.MovementRepository;
import pipecode.kardex.app.services.CategoryService;
import pipecode.kardex.app.services.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PipecodeKardexApplication.class)
class ProductServiceImplTest {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private MovementRepository movementRepository;

	@Test
	void testSaveProduct() {

		Category category = new Category();
		category.setName("Deportes");
		category.setCreateAt(new Date());

		Product product = new Product();
		product.setName("Remera");
		product.setCode("REM MASCULINA L");
		product.setCount(10L);
		product.setCategory(categoryService.save(category));

		Product productDB = productService.save(product);

		assertNotNull(productDB.getId());

		assertTrue(productService.findAll().iterator().hasNext());
		assertTrue(movementRepository.findAll().iterator().hasNext());
	}

	@Test
	void testIncreaseOk() {

		Long count = 10L;

		Category category = new Category();
		category.setName("Games");
		category.setCreateAt(new Date());

		Product product = new Product();
		product.setName("Playstation 5");
		product.setCode("PS5");
		product.setCount(30L);
		product.setCategory(categoryService.save(category));

		Product productDB = productService.save(product);

		try {
			productService.increase(productDB.getId(), count);
			assertEquals(productService.findById(productDB.getId()).get().getCount(), product.getCount() + count);
		} catch (EntityNotExistException e) {
			assertTrue("product not exist", false);
		}
	}

	@Test
	void testDecreaseOk() {
		Long count = 10L;

		Category category = new Category();
		category.setName("Games");
		category.setCreateAt(new Date());

		Product product = new Product();
		product.setName("XBOX ONE");
		product.setCode("XBOXO");
		product.setCount(30L);
		product.setCategory(categoryService.save(category));

		Product productDB = productService.save(product);

		try {
			productService.decrease(productDB.getId(), count);
			assertEquals(productService.findById(productDB.getId()).get().getCount(), product.getCount() - count);
		} catch (EntityNotExistException e) {
			assertTrue("product not exist", false);
		} catch (CountCannotLessThanZeroException e) {
			assertTrue("count cannot less than zero", false);
		}

	}

	@Test
	void testIncreaseEntityNotExistException() throws EntityNotExistException {
		try {
			productService.increase(99L, 10L);
			assertTrue(false);
		} catch (EntityNotExistException e) {
			assertTrue("product not exist", true);
		}
	}

	@Test
	void testDecreaseEntityNotExistException() {
		try {
			productService.decrease(99L, 10L);
			assertTrue(false);
		} catch (EntityNotExistException e) {
			assertTrue("product not exist", true);
		} catch (CountCannotLessThanZeroException e) {
			assertTrue("count cannot less than zero", false);
		}

	}

	@Test
	void testDecreaseCountCannotLessThanZeroException() {
		Long count = 50L;

		Category category = new Category();
		category.setName("Game");
		category.setCreateAt(new Date());

		Product product = new Product();
		product.setName("Family Games");
		product.setCode("FG");
		product.setCount(30L);
		product.setCategory(categoryService.save(category));

		Product productDB = productService.save(product);
		
		try {
			productService.decrease(productDB.getId(), count);
			assertTrue(false);
		} catch (EntityNotExistException e) {
			assertTrue(false);
		} catch (CountCannotLessThanZeroException e) {
			assertTrue("count cannot less than zero", true);
		}
		assertEquals(productService.findById(productDB.getId()).get().getCount(), productDB.getCount());
	}

}
