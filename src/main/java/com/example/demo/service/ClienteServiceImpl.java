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

import com.example.demo.domain.Cliente;
import com.example.demo.domain.Factura;
import com.example.demo.exception.ZMessManager;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.utility.Utilities;

@Scope("singleton")
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Cliente cliente) throws ConstraintViolationException {

		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return clienteRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente save(Cliente entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Cliente");
		}

		validate(entity);

		return clienteRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Cliente entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Cliente");
		}

		if (entity.getIdCliente() == null) {
			throw new ZMessManager().new EmptyFieldException("idCliente");
		}

		if (clienteRepository.existsById(entity.getIdCliente()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getIdCliente()).ifPresent(entidad -> {
			List<Factura> facturas = entidad.getFacturas();
			if (Utilities.validationsList(facturas) == true) {
				throw new ZMessManager().new DeletingException("facturas");
			}
		});

		clienteRepository.deleteById(entity.getIdCliente());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("idCliente");
		}
		if (clienteRepository.existsById(id)) {
			delete(clienteRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente update(Cliente entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Cliente");
		}

		validate(entity);

		if (clienteRepository.existsById(entity.getIdCliente()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return clienteRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Integer idCliente) {
		return clienteRepository.findById(idCliente);
	}

}
