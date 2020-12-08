/**
 * 
 */
package pipecode.kardex.app.services;

import pipecode.kardex.app.exception.CountCannotLessThanZeroException;
import pipecode.kardex.app.exception.EntityNotExistException;
import pipecode.kardex.app.model.Product;

/**
 * @author diegogustavomarquez
 *
 */
public interface ProductService extends CommonService<Product> {

	/**
	 * increase product count
	 * create a movement of type ADD
	 * 
	 * @param productId
	 * @param count
	 * @return
	 * @throws EntityNotExistException
	 */
	public Product increase(Long productId, Long count) throws EntityNotExistException;

	/**
	 * decrease product count
	 * create a movement of type GET
	 * 
	 * @param productId
	 * @param count
	 * @return
	 * @throws EntityNotExistException
	 * @throws CountCannotLessThanZeroException
	 */
	public Product decrease(Long productId, Long count) throws EntityNotExistException, CountCannotLessThanZeroException;

}