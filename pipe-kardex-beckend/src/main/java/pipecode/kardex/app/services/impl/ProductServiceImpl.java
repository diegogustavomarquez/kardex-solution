/**
 * 
 */
package pipecode.kardex.app.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pipecode.kardex.app.builder.MovementBuilder;
import pipecode.kardex.app.constant.TypeMovement;
import pipecode.kardex.app.exception.CountCannotLessThanZeroException;
import pipecode.kardex.app.exception.EntityNotExistException;
import pipecode.kardex.app.model.Product;
import pipecode.kardex.app.repository.MovementRepository;
import pipecode.kardex.app.repository.ProductRepository;
import pipecode.kardex.app.services.ProductService;

/**
 * @author diegogustavomarquez
 *
 */
@Service
public class ProductServiceImpl extends CommonServiceImpl<Product, ProductRepository> implements ProductService {

	@Autowired
	private MovementRepository movementRepository;

	@Override
	@Transactional
	public Product save(Product product) {
		movementRepository.save(movementRepository.save(MovementBuilder.create(product, product.getCount(), TypeMovement.CREATED.getType())));
		return repository.save(product);
	}

	@Override
	@Transactional
	public Product increase(Long productId, Long count) throws EntityNotExistException {

		Optional<Product> optional = repository.findById(productId);

		// validate product exist
		if (optional.isEmpty()) {
			throw new EntityNotExistException();
		}
		Product product = optional.get();
		// increase count
		product.setCount(product.getCount() + count);
		// update product
		product = repository.save(product);

		// create a movement of type ADD
		movementRepository.save(movementRepository.save(MovementBuilder.create(product, count, TypeMovement.ADD.getType())));

		return product;
	}

	@Override
	@Transactional
	public Product decrease(Long productId, Long count)
			throws EntityNotExistException, CountCannotLessThanZeroException {
		Optional<Product> optional = repository.findById(productId);

		// validate product exist
		if (optional.isEmpty()) {
			throw new EntityNotExistException();
		}
		Product product = optional.get();

		Long updateCount = product.getCount() - count;
		// validate count isn't negative
		if (updateCount < 0L) {
			throw new CountCannotLessThanZeroException();
		}

		// decrease count
		product.setCount(updateCount);
		// update product
		product = repository.save(product);

		// create a movement of type GET
		movementRepository.save(movementRepository.save(MovementBuilder.create(product, count, TypeMovement.GET.getType())));

		return product;
	}

}
