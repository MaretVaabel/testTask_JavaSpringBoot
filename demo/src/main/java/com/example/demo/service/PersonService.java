package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }
    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }
    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }
    public int deleteperson(UUID id){
        return personDao.deletePersonById(id);
    }
    public int updatePerson(UUID id, Person newperson){
        return personDao.updatePersonById(id, newperson);
    }
   /* String line = "";
    public void savePersonData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/people.csv"));
            while ((line = br.readLine()) != null) {
                UUID id = UUID.randomUUID();
                String[] data = line.split(",");
                Person c = new Person(id, data[0], data[1]);
                addPerson(c);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
