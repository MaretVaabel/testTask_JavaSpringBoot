import React from 'react';
import PersonService from '../services/PersonService';

class PersonComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            persons:[]
        }
    }
    componentDidMount(){
        PersonService.getPersons().then((response) => {
            this.setState({ persons: response.data })
        });
    }
    render () {
        return(
            <div>
                <h1 className="text-center"> Persons List</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td>Person id</td>
                            <td>Person name</td>
                            <td>Person image</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.persons.map(
                                person => 
                                <tr key = { person.id}>
                                    <td> { person.id } </td>
                                    <td> { person.name } </td>
                                    <td> <span><img src={person.url} alt="personImage" /></span></td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default PersonComponent

