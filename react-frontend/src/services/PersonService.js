import axios from 'axios'

const PERSON_REST_API_URL = 'http://localhost:8080/api/persons';

class PersonService {

    getPersons(){
       return axios.get(PERSON_REST_API_URL);
    }
}

export default new PersonService();

