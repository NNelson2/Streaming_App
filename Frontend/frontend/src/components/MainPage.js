import {useEffect, useState} from "react";
import {Card} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import {useNavigate} from "react-router-dom";

export default function MainPage({setMovie}) {

    let [movies, setMovies] = useState([]);
    const navigate = useNavigate();
    const fetchMovies = () => {
        fetch(`http://localhost:8080/movie/all`)
            .then((response) => response.json())
            .then((actualData) => {
                console.log(actualData)
                setMovies(actualData)
                // console.log(data)
            })
            .catch((err) => {
                console.log(err.message)
            });
    };
    var image = (data) => {
        var image = new Image();
        image.src = 'data:image/jpg;base64, ' + data.thumbnail;
        return image;
    }

    const navigateToPlayer = (title) => {
        setMovie(title)
        navigate('/player-page')
    };

    useEffect(() => {
        fetchMovies();
    }, []);

    return(
        <div>
            <h1>Movies</h1>
            {movies.map((movie,index) => (
                <Card style={{ width: '18rem' }}>
                    <Card.Img style = {{width: 250, height: 250}} variant="top" src= {`data:image/jpg;base64, ${movie.thumbnail}`}  />
                    <Card.Body>
                        <Card.Title>{movie.name}</Card.Title>
                        <Card.Text>
                        </Card.Text>
                        <Button variant="primary" onClick={() => navigateToPlayer(movie.name)}>Watch Movie</Button>
                    </Card.Body>
                </Card>
            ))}

        </div>
    )
}