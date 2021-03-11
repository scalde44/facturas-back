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
import com.example.demo.repository.DetalleRepository;
import com.example.demo.repository.ProductoRepository;

@Scope("singleton")
@Service
public class DetalleServiceImpl implements DetalleService {
	@Autowired
	private DetalleRepository detalleRepository;
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private Validator validator;

	@Override
	public void validate(Detalle detalle) throws ConstraintViolationException {
		Set<ConstraintViolation<Detalle>> constraintViolations = validator.validate(detalle);

		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return detalleRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Detalle> findAll() {

		return detalleRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Detalle save(Detalle entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Detalle");
		}

		validate(entity);
		Optional<Producto> producto=productoRepository.findById(entity.getProducto().getIdProducto());
		Producto producto2=producto.get();
		entity.setPrecio(producto2.getPrecio()*entity.getCantidad());
		return detalleRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Detalle entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Detalle");
		}

		if (entity.getNumDetalle() == null) {
			throw new ZMessManager().new EmptyFieldException("numDetalle");
		}

		if (detalleRepository.existsById(entity.getNumDetalle()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		detalleRepository.deleteById(entity.getNumDetalle());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("numDetalle");
		}

		if (detalleRepository.existsById(id)) {
			delete(detalleRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Detalle update(Detalle entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Detalle");
		}

		validate(entity);

		if (detalleRepository.existsById(entity.getNumDetalle()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return detalleRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Detalle> findById(Integer numDetalle) {

		return detalleRepository.findById(numDetalle);
	}

	@Override
	public List<Detalle> findShcaByNumFactura(Integer nFactura) throws Exception {
		return detalleRepository.findShcaByNumFactura(nFactura);
	}
}
