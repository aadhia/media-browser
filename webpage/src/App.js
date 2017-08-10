import React from 'react';
import Actors from './Actors.jsx'
import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom';

const App = () => (
  <Router>
    <div className="App">
      <div className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h2>Welcome to React</h2>
      </div>
      <p className="App-intro">
        To get started, edit <code>src/App.js</code> and save to reload.
      </p>

      <Route exact path="/" component={Home}/>
      <Route path="/actors" component={Actors}/>

    </div>
  </Router>
);

const Home = () => (
  <div>
    <h2>Home</h2>
    <Link to="/actors">Actors</Link>
  </div>
)


export default App;
