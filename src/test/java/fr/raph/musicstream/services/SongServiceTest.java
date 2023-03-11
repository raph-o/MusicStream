package fr.raph.musicstream.services;

import fr.raph.musicstream.mappers.SongMapper;
import fr.raph.musicstream.models.Song;
import fr.raph.musicstream.storage.SongRepository;
import fr.raph.musicstream.storage.entities.SongEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class SongServiceTest {

    private final SongRepository songRepository;
    private final SongService songService;
    private final String name = "test song";
    private final String description = "test description";

    SongServiceTest() {
        this.songRepository = mock(SongRepository.class);
        this.songService = new SongService(songRepository);
    }

    @Test
    void should_add_song() {
        SongEntity songToReturn = new SongEntity(name, description);
        given(songRepository.save(any())).willReturn(songToReturn);

        Song songToCreate = new Song(name, description);
        Song createdSong = songService.add(songToCreate);

        Assertions.assertThat(createdSong).extracting(Song::getName, Song::getDescription).contains(songToReturn.getName(), songToReturn.getDescription());
        then(songRepository).should(times(1)).save(any());
    }

    @Test
    void should_remove_song() {
        doNothing().when(songRepository).deleteByName(any());
        songService.remove(name);
        then(songRepository).should(times(1)).deleteByName(any());
    }

    @Test
    void should_update_song() {
        String updated = "updated ";
        SongEntity songToReturn = new SongEntity(updated + name, updated + description);
        doNothing().when(songRepository).deleteByName(any());
        given(songRepository.save(any())).willReturn(songToReturn);

        Song songUpdated = new Song(updated + name, updated + description);
        Song updatedSong = songService.update(name, songUpdated);

        Assertions.assertThat(updatedSong).extracting(Song::getName, Song::getDescription).contains(songToReturn.getName(), songToReturn.getDescription());
        then(songRepository).should(times(1)).deleteByName(any());
        then(songRepository).should(times(1)).save(any());
    }

    @Test
    void should_get_song() {
        SongEntity songToReturn = new SongEntity(name, description);
        given(songRepository.findByName(any())).willReturn(songToReturn);

        Song song = songService.get(name);

        Assertions.assertThat(song).extracting(Song::getName, Song::getDescription).contains(songToReturn.getName(), songToReturn.getDescription());
        then(songRepository).should(times(1)).findByName(any());
    }

    @Test
    void should_get_songs() {
        SongEntity songEntity1 = new SongEntity(name + " 1", description + " 1");
        SongEntity songEntity2 = new SongEntity(name + " 2", description + " 2");
        List<SongEntity> songsToReturn = List.of(songEntity1, songEntity2);
        given(songRepository.findAll()).willReturn(songsToReturn);

        List<Song> songs = songService.list();

        Assertions.assertThat(songs).hasSize(2).isEqualTo(SongMapper.SONG_MAPPER.toSongs(songsToReturn));
        then(songRepository).should(times(1)).findAll();
    }
}
