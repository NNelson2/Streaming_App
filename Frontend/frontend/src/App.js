import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import './App.css';
import {useState} from "react";
import LoginPage from "./components/LoginPage";
import User from "./components/User";
import MainPage from "./components/MainPage";
import PlayerPage from "./components/PlayerPage";

function App() {
  const [userIDValue, setUserIDValue] = useState(0);
  const [currentMovie, setMovie] = useState("");
  const valueID = (person) => {
    setUserIDValue(person)
  };
  const movie = (title) => {
    setMovie(title)
  };

  return (
      <Router>
        <div className="App">
          <div className="auth-wrapper">
            <div className="auth-inner">
              <Routes>
                <Route exact path="/" element={<LoginPage value = {valueID} />} />
                <Route path="/sign-in" element={<LoginPage value = {valueID} data-testid = 'login'/>} />
                <Route path="/sign-up" element={<User />} />
                <Route path ="/movie-page" element={<MainPage setMovie = {movie}/>}/>
                <Route path = "/player-page" element ={<PlayerPage title = {currentMovie} />}/>
              </Routes>
            </div>
          </div>
        </div>
      </Router>
  );
}

export default App;
