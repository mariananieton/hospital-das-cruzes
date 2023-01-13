package br.com.hospital.das.cruzes.model;

import java.time.LocalDate;

public class Paciente {

	private int id;
	private String nome;
	private LocalDate dtNascimento;
	private char sexoBiologico;
	private String procedimento;

	public Paciente() {

	}

	public Paciente(String nome, LocalDate dtNascimento, char sexoBiologico, String procedimento) {
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.sexoBiologico = sexoBiologico;
		this.procedimento = procedimento;
	}

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
		return "Paciente{nome='" + nome + "\', dtNascimento=" + dtNascimento + ", sexoBiologico=" + sexoBiologico + ", procedimento=" + procedimento + "}" ;
	}
}
