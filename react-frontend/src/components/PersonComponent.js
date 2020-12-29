import React from 'react';
import PersonService from '../services/PersonService';
import Pagination from 'react-paginate';
import Search from '../components/DataTable/Search';

class PersonComponent extends React.Component {

    constructor(props){
        super(props)
        
        this.state = {
            persons:[],
            offset:0,
            orgtableData:[],
            perPage: 10,
            currentPage:0
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

    render () {
        return(
            <div>
                <h1 className="text-center"> Contact List</h1>
                <div className="row w-100">
                    <div className="col mb-3 col-12 text-center">
                        <div className="row">
                            <div className="col-md-6">
                                <Pagination 
                                   previousLabel={"prev"}
                                   nextLabel={"next"}
                                   breakLabel={"..."}
                                   breakClassName={"break-me"}
                                   pageCount={this.state.pageCount}
                                   marginPagesDisplayed={2}
                                   pageRangeDisplayed={5}
                                   onPageChange={this.handlePageClick}
                                   containerClassName={"pagination"}
                                   subContainerClassName={"pages pagination"}
                                   activeClassName={"active"}
                                />
                            </div>
                            <div className="col-md-6 d-flex flex-row-reverse">
                                <Search />
                            </div>
                        </div>
                        
                        <table className="table table-striped">
                            <thead>
                                <tr>
                                    <td>Person id</td>
                                    <td>Person name</td>
                                    <td>Person image</td>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.persons.map(
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
                </div>  
            </div>
        )
    }
}

export default PersonComponent

