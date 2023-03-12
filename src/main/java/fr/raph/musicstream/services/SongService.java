package fr.raph.musicstream.services;

import fr.raph.musicstream.exceptions.ObjectAlreadyExistException;
import fr.raph.musicstream.exceptions.ObjectNotFoundException;
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
        if (songRepository.findByName(song.getName()).isPresent()) { throw new ObjectAlreadyExistException("Song", song.getName()); }
        SongEntity songEntity = SONG_MAPPER.toSongEntity(song);
        SongEntity savedSongEntity = songRepository.save(songEntity);
        return SONG_MAPPER.toSong(savedSongEntity);
    }

    public void remove(String name) {
        if (songRepository.findByName(name).isEmpty()) { throw new ObjectNotFoundException("Song", name); }
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

    public byte[] get(String name) {
        SongEntity songEntity = songRepository.findByName(name).orElseThrow(() -> new ObjectNotFoundException("Song", name));
        Song song = SONG_MAPPER.toSong(songEntity);
        return song.getData();
    }
}
