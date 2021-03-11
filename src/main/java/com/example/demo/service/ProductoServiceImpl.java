package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Detalle;
import com.example.demo.domain.Producto;
import com.example.demo.exception.ZMessManager;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.utility.Utilities;

@Scope("singleton")
@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Producto producto) throws ConstraintViolationException {

		Set<ConstraintViolation<Producto>> constraintViolations = validator.validate(producto);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return productoRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Producto save(Producto entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Producto");
		}

		validate(entity);

		return productoRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Producto entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Producto");
		}

		if (entity.getIdProducto() == null) {
			throw new ZMessManager().new EmptyFieldException("idProducto");
		}

		if (productoRepository.existsById(entity.getIdProducto()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getIdProducto()).ifPresent(entidad -> {
			List<Detalle> detalles = entidad.getDetalles();
			if (Utilities.validationsList(detalles) == true) {
				throw new ZMessManager().new DeletingException("detalles");
			}
		});

		productoRepository.deleteById(entity.getIdProducto());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("idProducto");
		}
		if (productoRepository.existsById(id)) {
			delete(productoRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Producto update(Producto entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Producto");
		}

		validate(entity);

		if (productoRepository.existsById(entity.getIdProducto()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return productoRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Integer idProducto) {
		return productoRepository.findById(idProducto);
	}

}
