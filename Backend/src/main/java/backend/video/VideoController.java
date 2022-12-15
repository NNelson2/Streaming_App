package backend.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class VideoController {
    @Autowired
    private VideoRepository videoRepository;

    // Each parameter annotated with @RequestParam corresponds to a form field where the String argument is the name of the field
    @PostMapping()
    public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
        if(videoRepository.existsByName(name)){
            throw new VideoExistsException();
        }
        Video newVid = new Video(name, file.getBytes());
        videoRepository.save(newVid);
        return ResponseEntity.ok("Video saved successfully.");
    }

    // {name} is a path variable in the url. It is extracted as the String parameter annotated with @PathVariable
    @GetMapping("{name}")
    public ResponseEntity<Resource> getVideoByName(@PathVariable("name") String name){
        if(!videoRepository.existsByName(name)){
            throw new VideoExistsException();
        }
        Video video = videoRepository.findByName(name);
        return ResponseEntity
                .ok(new ByteArrayResource(video.getData()));
    }

    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideoNames(){
        return ResponseEntity
                .ok(videoRepository.getAllEntryNames());
    }
}
