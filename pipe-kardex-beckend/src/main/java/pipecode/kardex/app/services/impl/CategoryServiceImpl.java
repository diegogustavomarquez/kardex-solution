/**
 * 
 */
package pipecode.kardex.app.services.impl;

import org.springframework.stereotype.Service;

import pipecode.kardex.app.model.Category;
import pipecode.kardex.app.repository.CategoryRepository;
import pipecode.kardex.app.services.CategoryService;

/**
 * @author diegogustavomarquez
 *
 */
@Service
public class CategoryServiceImpl extends CommonServiceImpl<Category, CategoryRepository> implements CategoryService {

}
