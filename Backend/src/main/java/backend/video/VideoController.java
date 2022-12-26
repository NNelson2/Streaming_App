package backend.video;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private StreamingService service;

    // Each parameter annotated with @RequestParam corresponds to a form field where the String argument is the name of the field
    @PostMapping(path = "/movie/add")
    public ResponseEntity<String> saveVideo(@RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("title") String title) throws IOException {
        if (videoRepository.existsByName(title)) {
            throw new VideoExistsException();
        }
        byte[] fileContent = thumbnail.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        Video video = new Video(title, encodedString);
        videoRepository.save(video);
        return ResponseEntity.ok("video added");
    }


    @GetMapping(value = "movie/{name}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String name, @RequestHeader("Range") String range) {
        if(!videoRepository.existsByName(name)){
            throw new VideoExistsException();
        }
        System.out.println("range in bytes() : " + range);
        return service.getVideo(name);
    }

    @GetMapping("/movie/all")
    public List<Video> getAllVideoNames(){
        return videoRepository.findAll();
    }
}
