package pipecode.kardex.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pipecode.kardex.app.model.Category;

/**
 * @author diegogustavomarquez
 *
 */
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

}
