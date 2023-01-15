package br.com.hospital.das.cruzes.dao;

import br.com.hospital.das.cruzes.exception.PacienteNotFoundException;

import java.util.List;

public interface AbstractDao<T> {

	void cadastrar(T objeto);

	List<T> consultarTodos();

	void atualizar(T objeto) throws Exception;

	void apagar(T objeto);

	default T buscarPorCodigo(int id) throws Exception {
		return null;
	}

}
