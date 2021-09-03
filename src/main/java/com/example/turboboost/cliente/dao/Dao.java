package com.example.turboboost.cliente.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

	Optional<T> findPorId(long id);
	
	List<T> getTodos();
	
	void salvar(T t);
	
	void alterar(T t);
	
	void deletar(T t);
}

