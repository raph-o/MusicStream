package fr.raph.musicstream.storage;

import fr.raph.musicstream.storage.entities.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Integer> {

    Optional<SongEntity> findByName(String name);
    void deleteByName(String name);
}
