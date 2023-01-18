package br.com.hospital.das.cruzes.job;

import br.com.hospital.das.cruzes.dao.PacienteDao;
import br.com.hospital.das.cruzes.model.Paciente;

import java.util.*;

public class OutputFirstReportJob {

	public static final String FILE_NAME = "BASE_HOSP_CRUZ_2023_REPORT1.csv";

	public static void main(String[] args) {

		PacienteDao pacienteDao = new PacienteDao();
		List<Paciente> pacientes = pacienteDao.consultarTodos();

		Map<String, List<Paciente>> pacientesAgrupadosPorProcedimento = agrupaPacientesPorProcedimento(pacientes);
		printaNumeroDePacientesPorProcedimento(pacientesAgrupadosPorProcedimento);
	}

	private static Map<String, List<Paciente>> agrupaPacientesPorProcedimento(List<Paciente> pacientes) {
		Map<String, List<Paciente>> pacientesAgrupadosPorProcedimento = new HashMap<>();
		for (Paciente paciente : pacientes) {
			String procedimento = paciente.getProcedimento();
			List<Paciente> pacientesEncontrados = pacientesAgrupadosPorProcedimento.get(procedimento);
			if (pacientesEncontrados == null) {
				pacientesEncontrados =  new ArrayList<>();
				pacientesEncontrados.add(paciente);
				pacientesAgrupadosPorProcedimento.put(procedimento, pacientesEncontrados);
			} else {
				pacientesEncontrados.add(paciente);
			}
		}
		return pacientesAgrupadosPorProcedimento;
	}

	private static void printaNumeroDePacientesPorProcedimento(Map<String, List<Paciente>> pacientesAgrupadosPorProcedimento) {
		for (String procedimento : pacientesAgrupadosPorProcedimento.keySet()) {
			System.out.println(procedimento + " = " + pacientesAgrupadosPorProcedimento.get(procedimento).size());
		}
	}
}
