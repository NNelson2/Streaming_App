import Button from "react-bootstrap/Button";


export default function PlayerPage(title) {
    let name = title.title;
    let link = "localhost:8080/movie/" + name;
    return(
        <div>
            <h2>{name}</h2>
            <video src="localhost:8080/movie/The_Green_Mile" width="720px" height="480px" controls preload="none" >
            </video>
            <Button variant="primary" onClick={() => console.log(link)}>Watch Movie</Button>
        </div>
    )
}