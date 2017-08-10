import React, {Component} from 'react';
import fetch from 'node-fetch';
import NameEntry from './NameEntry.js'


class Actors extends Component {
    constructor (props) {
        super(props);
        this.state = {datum:[]};
    }

    componentDidMount(){
        var self = this;
        fetch('http://localhost:8080/', {mode:"cors"}).then(
            function(response) {
            response.json().then(function(data) {
                self.setState({
                datum: data
                });
            }); 
            }
        )
    }


    getNames() {
        return this.state.datum.map( ({first, last, id}) => <NameEntry key={id} name={`${first} ${last}`}></NameEntry>);
    }

    render() {
        return (<div>{this.getNames()}</div>);
    }
}

export default Actors;