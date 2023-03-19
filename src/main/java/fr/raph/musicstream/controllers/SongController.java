package fr.raph.musicstream.controllers;

import fr.raph.musicstream.models.Song;
import fr.raph.musicstream.services.SongService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
@CrossOrigin
public class SongController {

    private SongService songService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Song addSong(@RequestParam String name, @RequestParam String author, @RequestParam MultipartFile file) throws IOException {
        Song song = new Song(name, author, file.getBytes());
        return songService.add(song);
    }

    @DeleteMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeSong(@PathVariable String name) {
        songService.remove(name);
    }

    @PutMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Song updateSong(@PathVariable String name, @RequestParam String newName, @RequestParam String author, @RequestParam MultipartFile file) throws IOException {
        Song song = new Song(newName, author, file.getBytes());
        return songService.update(name, song);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Song> getSongs() {
        return songService.list();
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource getSong(@PathVariable String name) {
        return new ByteArrayResource(songService.get(name));
    }
}
