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
import com.example.demo.domain.Factura;
import com.example.demo.exception.ZMessManager;
import com.example.demo.repository.FacturaRepository;
import com.example.demo.utility.Utilities;

@Scope("singleton")
@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Factura factura) throws ConstraintViolationException {

		Set<ConstraintViolation<Factura>> constraintViolations = validator.validate(factura);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return facturaRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Factura> findAll() {
		return facturaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Factura save(Factura entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Factura");
		}

		validate(entity);

		return facturaRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Factura entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Factura");
		}

		if (entity.getNumFactura() == null) {
			throw new ZMessManager().new EmptyFieldException("numFactura");
		}

		if (facturaRepository.existsById(entity.getNumFactura()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getNumFactura()).ifPresent(entidad -> {
			List<Detalle> detalles = entidad.getDetalles();
			if (Utilities.validationsList(detalles) == true) {
				throw new ZMessManager().new DeletingException("detalles");
			}
		});

		facturaRepository.deleteById(entity.getNumFactura());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("numFactura");
		}
		if (facturaRepository.existsById(id)) {
			delete(facturaRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Factura update(Factura entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Factura");
		}

		validate(entity);

		if (facturaRepository.existsById(entity.getNumFactura()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return facturaRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Factura> findById(Integer numFactura) {
		return facturaRepository.findById(numFactura);
	}

}
