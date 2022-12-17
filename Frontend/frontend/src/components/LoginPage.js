import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {Form, InputGroup} from "react-bootstrap";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";

export default function LoginPage({value}) {

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const navigate = useNavigate();

    const navigateToCreateAccount = () => {
        // 👇️ navigate to /contacts
        setUsername("")
        setPassword("")
        navigate('/sign-up');
    };

    const validateLogin = async() => {
        console.log(username);
        const res = await fetch('http://localhost:8080/users/username/'+ username, {
            method: 'GET'
        });
        const data = await res.json();
        console.log(data);
        // for (var i = 0; i < data.length; i++) {
        //     if (data[i].userName == username && data[i].password == password) {
        //         console.log("it worked")
        //         value(data[i].id)
        //         navigate('/movie-page')
        //         return
        //     }
        // }
        if(data.userName == username && data.password == password) {
            navigate('/movie-page');
            return;
        }
        alert("no account found");
    };

    return (
        <div className = "LoginPage">
            <header className = "LoginPage-Header">
                <p>Clicker Game Login</p>
            </header>
            <br></br>
            <Form>
                <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                    <Col xs = "auto">
                        <Form.Label htmlFor= "inlineFormInput" visuallyHidden>
                            Username
                        </Form.Label>
                        <InputGroup className = "mb-2" id = "username-textbox">
                            <Form.Control type = "username"
                                          id = "inlineFormInputGroup"
                                          placeholder= "Username"
                                          value = {username}
                                          onChange={(event) => setUsername(event.target.value)} />
                        </InputGroup>
                    </Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                    <Col xs = "auto">
                        <Form.Label htmlFor= "inlineFormInput" visuallyHidden>
                            Password
                        </Form.Label>
                        <InputGroup className = "mb-2" id = "password-textbox">
                            <Form.Control type = "password"
                                          id = "inlineFormInputGroup"
                                          placeholder= "Password"
                                          value = {password}
                                          onChange={(event) => setPassword(event.target.value)} />
                        </InputGroup>
                    </Col>

                </Form.Group>
                <Button variant="secondary" onClick={validateLogin} data-testid="login-button" id = "login-buttonPress">Login</Button>{' '}
                <Button variant="secondary" onClick={navigateToCreateAccount} data-testid="create-account-button">Create Account</Button>{' '}
            </Form>
            {/*<Foot />*/}
        </div>
    )
}