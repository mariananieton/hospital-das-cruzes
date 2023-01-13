package br.com.hospital.das.cruzes.model;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Paciente {

	private int id;
	private String nome;
	private LocalDate dtNascimento;
	private char sexoBiologico;
	private String procedimento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public char getSexoBiologico() {
		return sexoBiologico;
	}

	public void setSexoBiologico(char sexoBiologico) {
		this.sexoBiologico = sexoBiologico;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Paciente.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("nome='" + nome + "'")
				.add("dtNascimento=" + dtNascimento)
				.add("sexoBiologico=" + sexoBiologico)
				.add("procedimento='" + procedimento + "'")
				.toString();
	}
}
