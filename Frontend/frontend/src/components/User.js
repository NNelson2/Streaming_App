import AddUser from "./CreateAccount";
import {Routes, Route, useNavigate} from 'react-router-dom'
import CreateAccount from "./CreateAccount";

const {useState} = require("react");



function User() {
    const [users, addUsers] = useState([])
    const navigate = useNavigate();

    const createUser = async(user) => {
        const id = 'code-' + Math.floor(Math.random() * 100000)
        const clicks = 0
        const newUser = {id,...user,clicks}
        const res = await fetch('http://localhost:8080/users', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(newUser)
        })
        console.log(newUser)
        const data = await res.json
        addUsers([data,...users])
        navigate('/sign-in')
    }

    return (
        <CreateAccount onAdd = {createUser} />
    )
}
export default User;