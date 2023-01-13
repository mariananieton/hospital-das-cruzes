package br.com.hospital.das.cruzes.main;

import br.com.hospital.das.cruzes.dao.PacienteDao;
import br.com.hospital.das.cruzes.model.Paciente;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) throws IOException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Files.lines(Paths.get("BASE_HOSP_CRUZ_2023.csv"))
				.skip(1)
				.map(line -> line.split(";"))
				.map(col-> new Paciente(col[0], LocalDate.parse(col[1], formatter), col[2].charAt(0), col[3]))
				.forEach(System.out::println);
	}
}

