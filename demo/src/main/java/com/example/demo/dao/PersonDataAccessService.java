package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private  final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        savePersonData();
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String photoURL = resultSet.getString("photoURL");
            return new Person(id, name, photoURL);
        });
        //return List.of(new Person(UUID.randomUUID(), "FROM POSTGRES DB"));
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT * FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(
                sql, new Object[]{id}, (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String photoURL = resultSet.getString("photoURL");
                    return new Person(personId, name, photoURL);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }
    public void savePersonData() {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/people.csv"));
            while ((line = br.readLine()) != null) {
                UUID id = UUID.randomUUID();
                String[] data = line.split(",");
                System.out.println("Olen siin");
                Person c = new Person(id, data[0], data[1]);
                jdbcTemplate.update("INSERT INTO person VALUES ( ?, ?, ?)","id","name","photoURL");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
