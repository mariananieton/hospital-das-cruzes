package br.com.hospital.das.cruzes.dao;

import br.com.hospital.das.cruzes.exception.PacienteNotFoundException;
import br.com.hospital.das.cruzes.factory.ConnectionFactory;
import br.com.hospital.das.cruzes.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Paciente
 */
public class PacienteDao implements AbstractDao<Paciente> {

	/**
	 * Método utilizado para inserir um Paciente no banco
	 *
	 * @param paciente a ser inserido
	 */
	@Override
	public void cadastrar(Paciente paciente) {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_HDC_PACIENTE(id_paciente," +
					"nm_paciente, dt_nascimento, fl_sexo_biologico, nm_procedimento) values " +
					"(SQ_HDC_PACIENTE.nextval, ?, ?, ?, ?)");

			stm.setString(1, paciente.getNome());
			stm.setDate(2, java.sql.Date.valueOf(paciente.getDtNascimento()));
			stm.setString(3, String.valueOf(paciente.getSexoBiologico()));
			stm.setString(4, paciente.getProcedimento());

			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				paciente.setId(result.getInt(1));
			}
			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
	}

	/**
	 * Método utilizado para listar todos os registros do banco
	 *
	 * @return lista de pacientes
	 */
	@Override
	public List<Paciente> consultarTodos() {
		List<Paciente> lista = new ArrayList<>();
		try {
			Connection conexao = ConnectionFactory.getConnection();
			Statement stm = conexao.createStatement();
			ResultSet resultSet = stm.executeQuery("select * from T_HDC_PACIENTE");

			while (resultSet.next()) {
				Paciente paciente = new Paciente();
				paciente.setId(resultSet.getInt("id_paciente"));
				paciente.setNome(resultSet.getString("nm_paciente"));
				paciente.setDtNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
				paciente.setSexoBiologico(resultSet.getString("fl_sexo_biologico").charAt(0));
				paciente.setProcedimento(resultSet.getString("nm_procedimento"));

				lista.add(paciente);
			}
			conexao.close();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

	@Override
	public void atualizar(Paciente paciente) throws PacienteNotFoundException {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_HDC_PACIENTE set  " +
					" nm_paciente = ?, dt_nascimento = ?, " +
					" fl_sexo_biologico = ?, nm_procedimento = ?" +
					" where id_usuario = ?");

			stm.setString(1, paciente.getNome());
			stm.setDate(2, java.sql.Date.valueOf(paciente.getDtNascimento()));
			stm.setString(3, String.valueOf(paciente.getSexoBiologico()));
			stm.setString(4, paciente.getProcedimento());
			stm.setInt(5, paciente.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (paciente == null) {
			throw new PacienteNotFoundException("Esse paciente não pode ser encontrado");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 *
	 * @param paciente a ser removido
	 */
	@Override
	public void apagar(Paciente paciente) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_HDC_PACIENTE where id_paciente = ?");
			stm.setInt(1, paciente.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Método utilizado para pesquisar um Paciente pelo Id do banco
	 *
	 * @param id a ser pesquisado
	 * @return paciente referente ao id pesquisado
	 * @throws PacienteNotFoundException lancado quando não encontrado nenhum registro
	 */
	public Paciente pesquisarPorCodigo(int id) throws PacienteNotFoundException {
		Paciente paciente = null;
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("select * from T_HDC_PACIENTE where id_paciente = ?");
			stm.setInt(1, id);
			ResultSet resultSet = stm.executeQuery();

			if (resultSet.next()) {
				paciente = new Paciente();
				paciente.setId(resultSet.getInt("id_paciente"));
				paciente.setNome(resultSet.getString("nm_paciente"));
				paciente.setDtNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
				paciente.setSexoBiologico(resultSet.getString("fl_sexo_biologico").charAt(0));
				paciente.setProcedimento(resultSet.getString("nm_procedimento"));
			}
			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (paciente == null) {
			throw new PacienteNotFoundException("Esse paciente não pode ser encontrado");
		}
		return paciente;
	}
}
