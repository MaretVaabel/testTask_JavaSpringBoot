package com.example.TestTaskbackend;

import com.example.TestTaskbackend.model.Person;
import com.example.TestTaskbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class TestTaskBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TestTaskBackendApplication.class, args);
	}
	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/people.csv"));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if(data[0] != "name"){
					System.out.println(data[0] + ' ' + data[1]);
					this.personRepository.save(new Person(data[0], data[1]));
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
