package com.example.turboboost.commons;

public interface ServicoBase<O, K> {

	void salvar(O objeto);
	
	void alterar(O objeto);
	
	void remover(O objeto);
}
