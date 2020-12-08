/**
 * 
 */
package pipecode.kardex.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pipecode.kardex.app.model.Movement;

/**
 * @author diegogustavomarquez
 *
 */
public interface MovementRepository extends PagingAndSortingRepository<Movement, Long>{

}
