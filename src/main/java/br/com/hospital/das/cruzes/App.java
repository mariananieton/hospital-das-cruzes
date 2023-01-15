package br.com.hospital.das.cruzes;

import br.com.hospital.das.cruzes.dao.PacienteDao;
import br.com.hospital.das.cruzes.model.Paciente;
import com.google.common.base.Stopwatch;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) throws IOException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		/*Files.lines(Paths.get("BASE_HOSP_CRUZ_2023.csv"))
				.skip(1)
				.map(line -> line.split(";"))
				.map(cols-> new Paciente(cols[0], LocalDate.parse(cols[1], formatter), cols[2].charAt(0), cols[3]))
				.forEach(System.out::println);*/

		InputStream fileInputStream = new FileInputStream("BASE_HOSP_CRUZ_2023.csv");
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
		System.out.println("processo finalizado em "+ swTotal.elapsed(TimeUnit.MILLISECONDS)+"ms");
		pacienteDao.consultarTodos();
		System.out.println(pacientes);

		bufferedReader.close();
	}
}

