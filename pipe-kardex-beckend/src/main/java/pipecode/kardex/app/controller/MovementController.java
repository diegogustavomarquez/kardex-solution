/**
 * 
 */
package pipecode.kardex.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pipecode.kardex.app.model.Movement;
import pipecode.kardex.app.services.MovementService;

/**
 * @author diegogustavomarquez
 *
 */
@RestController
@RequestMapping("/api/movement")
public class MovementController extends CommonController<Movement, MovementService> {

	@Override
	public String getApiName() {
		return "/api/movement";
	}

}