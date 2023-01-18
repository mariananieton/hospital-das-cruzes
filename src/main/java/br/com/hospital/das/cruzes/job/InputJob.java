package br.com.hospital.das.cruzes.job;

import br.com.hospital.das.cruzes.dao.PacienteDao;
import br.com.hospital.das.cruzes.model.Paciente;
import com.google.common.base.Stopwatch;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InputJob {

	public static final String FILE_NAME = "BASE_HOSP_CRUZ_2023.csv";
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args) throws IOException {
		InputStream fileInputStream = Files.newInputStream(Paths.get(FILE_NAME));
		Reader reader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);

		bufferedReader.readLine();
		String linha = bufferedReader.readLine();
		linha.split(";");

		PacienteDao pacienteDao = new PacienteDao();
		Stopwatch swTotal = Stopwatch.createStarted();
		List<Paciente> pacientes = new ArrayList<>();

		while (linha != null) {
			Stopwatch swPaciente = Stopwatch.createStarted();
			String[] colunas = linha.split(";");
			Paciente paciente = new Paciente(colunas[0], LocalDate.parse(colunas[1], formatter), colunas[2].charAt(0), colunas[3]);
			pacientes.add(paciente);
			linha = bufferedReader.readLine();
			System.out.println("paciente inserido em " + swPaciente.elapsed(TimeUnit.MILLISECONDS) + "ms");
		}
		pacienteDao.cadastrarLista(pacientes);
		System.out.println("processo finalizado em " + swTotal.elapsed(TimeUnit.MILLISECONDS) + "ms");
		System.out.println(pacientes);

		bufferedReader.close();
	}
}

