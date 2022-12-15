import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import './App.css';
import {useState} from "react";
import LoginPage from "./components/LoginPage";
import User from "./components/User";

function App() {
  const [userIDValue, setUserIDValue] = useState(0);
  const value = (person) => {
    setUserIDValue(person)
  }

  return (
      <Router>
        <div className="App">
          <div className="auth-wrapper">
            <div className="auth-inner">
              <Routes>
                <Route exact path="/" element={<LoginPage value = {value} />} />
                <Route path="/sign-in" element={<LoginPage value = {value} data-testid = 'login'/>} />
                <Route path="/sign-up" element={<User />} />
              </Routes>
            </div>
          </div>
        </div>
      </Router>
  );
}

export default App;
