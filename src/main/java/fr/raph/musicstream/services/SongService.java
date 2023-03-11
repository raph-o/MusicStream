package fr.raph.musicstream.services;

import fr.raph.musicstream.mappers.SongMapper;
import fr.raph.musicstream.models.Song;
import fr.raph.musicstream.storage.SongRepository;
import fr.raph.musicstream.storage.entities.SongEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {

    private SongRepository songRepository;
    private final SongMapper SONG_MAPPER = SongMapper.SONG_MAPPER;

    public Song add(Song song) {
        SongEntity songEntity = SONG_MAPPER.toSongEntity(song);
        SongEntity savedSongEntity = songRepository.save(songEntity);
        return SONG_MAPPER.toSong(savedSongEntity);
    }

    public void remove(String name) {
        songRepository.deleteByName(name);
    }

    public Song update(String name, Song song) {
        songRepository.deleteByName(name);
        SongEntity songEntity = SONG_MAPPER.toSongEntity(song);
        SongEntity savedSongEntity = songRepository.save(songEntity);
        return SONG_MAPPER.toSong(savedSongEntity);
    }

    public List<Song> list() {
        return SONG_MAPPER.toSongs(songRepository.findAll());
    }

    public Song get(String name) {
        SongEntity songEntity = songRepository.findByName(name);
        return SONG_MAPPER.toSong(songEntity);
    }
}
