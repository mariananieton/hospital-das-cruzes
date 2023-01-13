package br.com.hospital.das.cruzes.dao;

import java.util.List;

public interface AbstractDao<T> {

	void cadastrar(T objeto);

	List<T> consultarTodos();

	void apagar(T objeto);

	default T buscarPorCodigo(int id) {
		return null;
	}

}
