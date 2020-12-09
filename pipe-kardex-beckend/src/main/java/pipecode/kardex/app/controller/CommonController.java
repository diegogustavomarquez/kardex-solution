/**
 * 
 */
package pipecode.kardex.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pipecode.kardex.app.services.CommonService;

/**
 * Generic functionality of APIs
 * 
 * @author diegogustavomarquez
 *
 */
@CrossOrigin({ "http://localhost:4200" })
public abstract class CommonController<E, S extends CommonService<E>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	protected S service;

	@GetMapping(value = "/ping")
	public ResponseEntity<?> ping() {
		return ResponseEntity.ok().body("OK");
	}

	@GetMapping("/list")
	public ResponseEntity<?> list() {
		LOGGER.info("init GET {}", getApiName());
		try {
			return ResponseEntity.ok().body(service.findAll());
		} catch (Exception e) {
			LOGGER.error("Error in findAll() service : {} exception: {}", service.getClass(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			LOGGER.info("finish GET {}", getApiName());
		}

	}

	@GetMapping("/list/page")
	public ResponseEntity<?> listPageable(Pageable pageable) {
		LOGGER.info("init GET {}/page", getApiName());
		try {
			return ResponseEntity.ok().body(service.findAll(pageable));
		} catch (Exception e) {
			LOGGER.error("Error in findAll() pageable: {} service : {} exception: {}", pageable, service.getClass(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			LOGGER.info("finish GET {}/page", getApiName());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable Long id) {
		LOGGER.info("init GET {}/id", getApiName());
		try {
			if (id < 1) {
				LOGGER.error("the id {} cannot <= 0 in {}", id, service.getClass());
				return ResponseEntity.badRequest().build();
			}

			Optional<E> o = service.findById(id);
			// validate id exist
			if (o.isEmpty()) {
				LOGGER.error("the id {} no exist in {}", id, service.getClass());
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(o.get());
		} catch (Exception e) {
			LOGGER.error("Error in findById() service : {} exception: {}", service.getClass(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			LOGGER.info("finish GET {}/id", getApiName());
		}
	}

	/**
	 * list of error from validate from entity
	 * 
	 * @param result
	 * @return
	 */
	protected ResponseEntity<?> validate(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}

	/**
	 * 
	 * @return name of api
	 */
	public abstract String getApiName();

}
