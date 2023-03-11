package fr.raph.musicstream.controllers;

import fr.raph.musicstream.models.Song;
import fr.raph.musicstream.services.SongService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SongController {

    private SongService songService;

    @PostMapping(value = "/songs", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Song addSong(@RequestBody Song song) {
        return songService.add(song);
    }

    @DeleteMapping(value = "/songs/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeSong(@PathVariable String name) {
        songService.remove(name);
    }

    @PutMapping(value = "/songs/{name}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Song updateSong(@PathVariable String name, @RequestBody Song song) {
        return songService.update(name, song);
    }

    @GetMapping(value = "/songs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Song> getSongs() {
        return songService.list();
    }

    @GetMapping(value = "/songs/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Song getSong(@PathVariable String name) {
        return songService.get(name);
    }
}
