package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		/*String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/people.csv"));
			while ((line = br.readLine()) != null) {
				UUID id = UUID.randomUUID();
				String[] data = line.split(",");
				System.out.println(data[0] + ' ' + data[1]);
				Person c = new Person(id, data[0], data[1]);
				//addPerson(c);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}*/
		SpringApplication.run(DemoApplication.class, args);
	}

}
