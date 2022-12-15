package backend.video;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Video was not found")
public class VideoNotFoundException extends RuntimeException {
}
