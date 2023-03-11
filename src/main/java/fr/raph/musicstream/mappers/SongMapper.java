package fr.raph.musicstream.mappers;

import fr.raph.musicstream.models.Song;
import fr.raph.musicstream.storage.entities.SongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SongMapper {

    SongMapper SONG_MAPPER = Mappers.getMapper(SongMapper.class);

    Song toSong(SongEntity songEntity);
    List<Song> toSongs(List<SongEntity> songEntities);

    SongEntity toSongEntity(Song song);
    List<SongEntity> toSongEntities(List<Song> songs);
}
