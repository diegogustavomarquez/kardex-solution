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

import pipecode.kardex.app.constant.ResponseCode;
import pipecode.kardex.app.exception.CountCannotLessThanZeroException;
import pipecode.kardex.app.exception.EntityNotExistException;
import pipecode.kardex.app.model.Product;
import pipecode.kardex.app.services.ProductService;

/**
 * @author diegogustavomarquez
 *
 */
@RestController
@RequestMapping("/api/product")
public class ProductController extends CommonController<Product, ProductService> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Product entity, BindingResult result) {
		LOGGER.info("init POST {}", getApiName());
		if (result.hasErrors()) {
			return this.validate(result);
		}
		try {
			Product entityDb = service.save(entity);
			return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
		} catch (Exception e) {
			LOGGER.error("Error in save() service : {} exception: {}", service.getClass(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			LOGGER.info("finish POST {}", getApiName());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
		LOGGER.info("init PUT {}/{}", getApiName(), id);
		if (result.hasErrors()) {
			return this.validate(result);
		}
		try {
			Optional<Product> optionalProductDB = service.findById(id);
			if (optionalProductDB.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			Product productDB = optionalProductDB.get();
			productDB.setCode(product.getCode());
			productDB.setName(product.getName());
			productDB.setCategory(product.getCategory());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productDB));
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
			LOGGER.info("finish success DELETE {}/{}", getApiName(), id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			LOGGER.error("Error in delete() : {} exception: {}", service.getClass(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/increase-by-id/{id}/count/{count}")
	public ResponseEntity<?> increase(@PathVariable Long id, @PathVariable Long count) {
		LOGGER.info("init POST {}/increase-by-id/{}/count/{}", getApiName(), id, count);
		if (id < 1L || count < 1L) {
			return ResponseEntity.badRequest().build();
		}
		try {
			return ResponseEntity.ok().body(service.increase(id, count));
		} catch (EntityNotExistException e) {
			LOGGER.info("Error POST {}/increase-by-id/{}/count/{} EntityNotExistException", getApiName(), id, count);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			LOGGER.info("Error POST {}/increase-by-id/{}/count/{} Exception{}", getApiName(), id, count, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			LOGGER.info("finish POST {}/increase-by-id/{}/count/{}", getApiName(), id, count);
		}
	}

	@PostMapping("/decrease-by-id/{id}/count/{count}")
	public ResponseEntity<?> decrease(@PathVariable Long id, @PathVariable Long count) {
		LOGGER.info("init POST {}/decrease-by-id/{}/count/{}", getApiName(), id, count);
		if (id < 1L || count < 1L) {
			return ResponseEntity.badRequest().build();
		}
		try {
			return ResponseEntity.ok().body(service.decrease(id, count));
		} catch (EntityNotExistException e) {
			LOGGER.info("Error POST {}/decrease-by-id/{}/count/{} EntityNotExistException", getApiName(), id, count);
			return ResponseEntity.notFound().build();
		} catch (CountCannotLessThanZeroException e) {			
			LOGGER.info("Error POST {}/decrease-by-id/{}/count/{} CountCannotLessThanZeroException", getApiName(), id, count);
			return ResponseEntity.status(ResponseCode.COUNT_CANNOT_LESS_ZERO).build();
		} catch (Exception e) {
			LOGGER.info("Error POST {}/decrease-by-id/{}/count/{} Exception{}", getApiName(), id, count, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			LOGGER.info("finish POST {}/increase-by-id/{}/count/{}", getApiName(), id, count);
		}
	}

	@Override
	public String getApiName() {
		return "/api/product";
	}

}