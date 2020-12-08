/**
 * 
 */
package pipecode.kardex.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pipecode.kardex.app.model.Category;
import pipecode.kardex.app.services.CategoryService;

/**
 * @author diegogustavomarquez
 *
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController extends CommonController<Category, CategoryService> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Category entity, BindingResult result) {
		LOGGER.info("init POST {}", getApiName());
		if (result.hasErrors()) {
			return this.validate(result);
		}
		try {
			Category entityDb = service.save(entity);
			return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
		} catch (Exception e) {
			LOGGER.error("Error in save() service : {} exception: {}", service.getClass(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			LOGGER.info("finish POST {}", getApiName());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Category category, BindingResult result,
			@PathVariable Long id) {
		LOGGER.info("init PUT {}/{}", getApiName(), id);

		if (result.hasErrors()) {
			return this.validate(result);
		}
		try {
			Optional<Category> optionalCategoryDB = service.findById(id);
			if (optionalCategoryDB.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			Category categoryDB = optionalCategoryDB.get();
			categoryDB.setName(category.getName());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(categoryDB));
		} catch (Exception e) {
			LOGGER.error("Error in update() : {} exception: {}", service.getClass(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			LOGGER.info("finish PUT {}/{}", getApiName(), id);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		LOGGER.info("init DELETE {}/{}", getApiName(), id);
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			LOGGER.error("Error in delete() : {} exception: {}", service.getClass(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			LOGGER.info("finish DELETE {}/{}", getApiName(), id);
		}
	}

	@Override
	public String getApiName() {
		return "/api/category";
	}

}
