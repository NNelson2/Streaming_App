import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup'; import Row from 'react-bootstrap/Row';
import {useState } from 'react'
import { FaCoins } from 'react-icons/fa'
import {FormLabel} from "react-bootstrap";


export default function CreateAccount({onAdd}) {
    const [email, setEmail] = useState('')
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const onSubmit = (event) => {
        event.preventDefault()
        onAdd({email, username, password})
        setEmail("")
        setUsername("")
        setPassword("")
    }

    return (
        <div>
            <Form style = {{marginTop: 25}} onSubmit={onSubmit}>
                <header>Create Account</header>
                <br></br>
                <Row className = "align-items-center">
                    <Col xs = "auto">
                        <Form.Label htmlFor= "inlineFormInput" visuallyHidden>
                            Username
                        </Form.Label>
                        <InputGroup className = "mb-2">
                            <Form.Control type = "username"
                                          id = "inlineFormInputGroup"
                                          placeholder= "Username"
                                          value = {username}
                                          onChange={(event) => setUsername(event.target.value)} />
                        </InputGroup>
                    </Col>
                    <Col xs = "auto">
                        <Form.Label htmlFor= "inlineFormInput" visuallyHidden>
                            Email
                        </Form.Label>
                        <InputGroup className = "mb-2">
                            <Form.Control type = "email"
                                          id = "inlineFormInputGroup"
                                          placeholder= "email@example.com"
                                          value = {email}
                                          onChange={(event) => setEmail(event.target.value)} />
                        </InputGroup>
                    </Col>

                    <Col xs = "auto">
                        <Form.Label htmlFor= "inlineFormInput" visuallyHidden>
                            Password
                        </Form.Label>
                        <InputGroup className = "mb-2">
                            <Form.Control type = "password"
                                          id = "inlineFormInputGroup"
                                          placeholder= "Password"
                                          value = {password}
                                          onChange={(event) => setPassword(event.target.value)} />
                        </InputGroup>
                    </Col>

                    <Col xs = "auto">
                        <Button type="submit" className = "mb-2">
                            <FaCoins /> Create Account
                        </Button>
                    </Col>
                </Row>
            </Form>
            {/*<Foot />*/}
        </div>

    );
}