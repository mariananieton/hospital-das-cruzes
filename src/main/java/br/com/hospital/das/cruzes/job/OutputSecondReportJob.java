package br.com.hospital.das.cruzes.job;

import br.com.hospital.das.cruzes.dao.PacienteDao;
import br.com.hospital.das.cruzes.model.Paciente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputSecondReportJob {

	public static void main(String[] args) {
		PacienteDao pacienteDao = new PacienteDao();
		List<Paciente> pacientes = pacienteDao.consultarTodos();

		Map<String, List<Paciente>> pacientesAgrupadosPorSexoBiologico = agrupaPacientesPorSexoBiologico(pacientes);
		printaNumeroDePacientesPorSexoBiologico(pacientesAgrupadosPorSexoBiologico);

	}

	private static Map<String, List<Paciente>> agrupaPacientesPorSexoBiologico(List<Paciente> pacientes) {
		Map<String, List<Paciente>> pacientesAgrupadosPorSexoBiologico = new HashMap<>();
		for (Paciente paciente : pacientes) {
			String sexoBiologico = String.valueOf(paciente.getSexoBiologico());
			List<Paciente> pacientesEncontrados = pacientesAgrupadosPorSexoBiologico.get(sexoBiologico);
			if (pacientesEncontrados == null) {
				pacientesEncontrados = new ArrayList<>();
				pacientesEncontrados.add(paciente);
				pacientesAgrupadosPorSexoBiologico.put(sexoBiologico, pacientesEncontrados);
			} else {
				pacientesEncontrados.add(paciente);
			}
		}
		return pacientesAgrupadosPorSexoBiologico;
	}

	private static void printaNumeroDePacientesPorSexoBiologico(Map<String, List<Paciente>> pacientesAgrupadosPorSexoBiologico) {
		for (String sexoBiologico : pacientesAgrupadosPorSexoBiologico.keySet()) {
			System.out.println(sexoBiologico + " = " + pacientesAgrupadosPorSexoBiologico.get(sexoBiologico).size());
		}
	}
}
