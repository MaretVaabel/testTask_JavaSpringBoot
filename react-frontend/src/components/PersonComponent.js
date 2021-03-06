import React from 'react';
import PersonService from '../services/PersonService';
import Pagination from 'react-paginate';

class PersonComponent extends React.Component {

    constructor(props){
        super(props)
        
        this.state = {
            persons:[],
            offset:0,
            orgtableData:[],
            perPage: 5,
            currentPage:0,
            search: ""
        }
        this.handlePageClick = this.handlePageClick.bind(this);
    }
    handlePageClick = (e) => {
        const selectedPage = e.selected;
        const offset = selectedPage * this.state.perPage;

        this.setState({
            currentPage:selectedPage,
            offset: offset
        }, () => { this.loadMoreData()
        });
    };
    loadMoreData() {
        const data = this.state.orgtableData;
        const slice = data.slice(this.state.offset, this.state.offset + this.state.perPage)
        this.setState({
            pageCount: Math.ceil(data.length / this.state.perPage),
            persons:slice
        })
    }
    componentDidMount(){
        PersonService.getPersons().then((response) => {
            const data = response.data;            
            const slice = data.slice(this.state.offset, this.state.offset + this.state.perPage)
            this.setState({ 
                pageCount: Math.ceil(data.length / this.state.perPage),
                orgtableData: response.data,
                persons: slice })
        });
    }
    onchange = e => { this.setState({ search : e.target.value });
    }

    render () {
        const {search} = this.state;
        const filterdPersons = !search ? this.state.persons : this.state.orgtableData.filter( persons => {
            return persons.name.toLowerCase().indexOf( search.toLowerCase() ) !== -1
        })
        const filterd = !search ? this.state.persons : filterdPersons.slice(this.state.offset, this.state.offset + this.state.perPage)
        return(
            <div>
                <h1 className="text-center m-5"> Contact List</h1>
                <div className="row w-100 pr-2 pl-4">
                    <div className="col mb-3 col-12 text-center">
                        <div className="row mb-2">
                            <div className="col-md-12 d-flex flex-row-reverse">
                                <input 
                                    type="text"
                                    className="form-control"
                                    style={{ width: "240px" }}
                                    placeholder="Search"
                                    onChange={this.onchange}
                                />
                            </div>
                        </div>
                        
                        <table className="table table-striped">
                            <tbody>
                                {filterd.map(
                                        person => 
                                        <tr key = { person.id}>
                                            <td> <span><img src={person.url} alt="personImage" height="100" maxwidth="70"/></span></td>
                                            <td> { person.name } </td>     
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>
                        <div className="row">
                            <div className="col-md-12 d-flex center">
                                <Pagination 
                                   previousLabel={"prev"}
                                   nextLabel={"next"}
                                   breakLabel={"..."}
                                   breakClassName={"break-me"}
                                   pageCount={!search ? this.state.pageCount : Math.ceil(filterdPersons.length / this.state.perPage)}
                                   //pageCount={this.state.pageCount}
                                   marginPagesDisplayed={2}
                                   pageRangeDisplayed={5}
                                   onPageChange={this.handlePageClick}
                                   containerClassName={"pagination"}
                                   subContainerClassName={"pages pagination"}
                                   activeClassName={"active"}
                                />
                            </div>
                        </div>
                    </div>
                </div>  
            </div>
        )
    }
}

export default PersonComponent

