package com.example.turboboost.cliente.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.turboboost.cliente.models.Cliente;

@Repository
@Transactional
public class ClienteDao implements Dao<Cliente> {
	
	private EntityManager entityManager;

	@Override
	public Optional<Cliente> findPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Cliente t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
		
	}

	@Override
	public void alterar(Cliente t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(Cliente t) {
		// TODO Auto-generated method stub
		
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			action.accept(entityManager);
			tx.commit();
		}catch(RuntimeException e){
			tx.rollback();
			throw e;
		}
	}

}
