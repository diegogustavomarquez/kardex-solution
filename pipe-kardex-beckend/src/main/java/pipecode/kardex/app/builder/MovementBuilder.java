package pipecode.kardex.app.builder;

import pipecode.kardex.app.model.Movement;
import pipecode.kardex.app.model.Product;

/**
 * 
 * 
 * @author diegogustavomarquez
 *
 */
public class MovementBuilder {

	/**
	 * Create a movement from the give product, count an type
	 * 
	 * @param product
	 * @param count
	 * @param type
	 * @return
	 */
	public static Movement create(Product product, Long count, String type) {
		Movement movement = new Movement();
		movement.setCategory(product.getCategory().getName());
		movement.setCount(null == product.getCount() ? 0L : product.getCount());
		movement.setDifferenceAmount(null == count ? 0L : count);
		movement.setProductCode(product.getCode());
		movement.setProductName(product.getName());
		movement.setType(type);
		return movement;
	}

}
