/**
 * 
 */
package pipecode.kardex.app.services.impl;

import org.springframework.stereotype.Service;

import pipecode.kardex.app.model.Movement;
import pipecode.kardex.app.repository.MovementRepository;
import pipecode.kardex.app.services.MovementService;

/**
 * @author diegogustavomarquez
 *
 */
@Service
public class MovementServiceImpl extends CommonServiceImpl<Movement, MovementRepository> implements MovementService {

}
