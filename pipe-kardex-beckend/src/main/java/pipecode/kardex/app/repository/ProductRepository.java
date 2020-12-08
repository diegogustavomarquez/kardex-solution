/**
 * 
 */
package pipecode.kardex.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pipecode.kardex.app.model.Product;

/**
 * @author diegogustavomarquez
 *
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
