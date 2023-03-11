package fr.raph.musicstream.services;

import fr.raph.musicstream.exceptions.ObjectAlreadyExistException;
import fr.raph.musicstream.exceptions.ObjectNotFoundException;
import fr.raph.musicstream.mappers.SongMapper;
import fr.raph.musicstream.models.Song;
import fr.raph.musicstream.storage.SongRepository;
import fr.raph.musicstream.storage.entities.SongEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.List;
import java.util.Optional;

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
        BDDMockito.given(songRepository.save(any())).willReturn(songToReturn);

        Song songToCreate = new Song(name, description);
        Song createdSong = songService.add(songToCreate);

        Assertions.assertThat(createdSong)
                .extracting(Song::getName, Song::getDescription)
                .contains(songToReturn.getName(), songToReturn.getDescription());
        BDDMockito.then(songRepository).should(times(1)).save(any());
    }

    @Test
    void should_throw_ObjectAlreadyExistException_when_adding_existent_song() {
        SongEntity existingSong = new SongEntity(name, description);
        BDDMockito.given(songRepository.findByName(any())).willReturn(Optional.of(existingSong));

        Song songToAdd = new Song(name, description);

        Assertions.assertThatThrownBy(() -> songService.add(songToAdd))
                .isInstanceOf(ObjectAlreadyExistException.class)
                .hasMessage("Object of type Song with value " + name + " already exist");
        BDDMockito.then(songRepository).should(times(1)).findByName(any());
    }

    @Test
    void should_remove_song() {
        SongEntity songToReturn = new SongEntity(name, description);
        BDDMockito.given(songRepository.findByName(any())).willReturn(Optional.of(songToReturn));

        songService.remove(name);

        BDDMockito.then(songRepository).should(times(1)).findByName(any());
        BDDMockito.then(songRepository).should(times(1)).deleteByName(any());
    }

    @Test
    void should_throw_ObjectNotFoundException_when_removing_non_existent_song() {
        Assertions.assertThatThrownBy(() -> songService.remove(name))
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Object of type Song with value " + name + " not found");
    }

    @Test
    void should_update_song() {
        String updated = "updated ";
        SongEntity songToReturn = new SongEntity(updated + name, updated + description);
        doNothing().when(songRepository).deleteByName(any());
        BDDMockito.given(songRepository.save(any())).willReturn(songToReturn);

        Song songUpdated = new Song(updated + name, updated + description);
        Song updatedSong = songService.update(name, songUpdated);

        Assertions.assertThat(updatedSong)
                .extracting(Song::getName, Song::getDescription)
                .contains(songToReturn.getName(), songToReturn.getDescription());
        BDDMockito.then(songRepository).should(times(1)).deleteByName(any());
        BDDMockito.then(songRepository).should(times(1)).save(any());
    }

    @Test
    void should_get_songs() {
        SongEntity songEntity1 = new SongEntity(name + " 1", description + " 1");
        SongEntity songEntity2 = new SongEntity(name + " 2", description + " 2");
        List<SongEntity> songsToReturn = List.of(songEntity1, songEntity2);
        BDDMockito.given(songRepository.findAll()).willReturn(songsToReturn);

        List<Song> songs = songService.list();

        Assertions.assertThat(songs)
                .hasSize(2)
                .isEqualTo(SongMapper.SONG_MAPPER.toSongs(songsToReturn));
        BDDMockito.then(songRepository).should(times(1)).findAll();
    }

    @Test
    void should_get_song() {
        SongEntity songToReturn = new SongEntity(name, description);
        BDDMockito.given(songRepository.findByName(any())).willReturn(Optional.of(songToReturn));

        Song song = songService.get(name);

        Assertions.assertThat(song)
                .extracting(Song::getName, Song::getDescription)
                .contains(songToReturn.getName(), songToReturn.getDescription());
        BDDMockito.then(songRepository).should(times(1)).findByName(any());
    }

    @Test
    void should_throw_ObjectNotFoundException_when_getting_non_existent_song() {
        Assertions.assertThatThrownBy(() -> songService.get(name))
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Object of type Song with value " + name + " not found");
    }
}
