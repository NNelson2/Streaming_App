package backend.video;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "A backend.video with this name already exists")
public class VideoExistsException extends RuntimeException {

}