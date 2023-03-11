package fr.raph.musicstream.storage;

import fr.raph.musicstream.storage.entities.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Integer> {

    SongEntity findByName(String name);
    void deleteByName(String name);
}
