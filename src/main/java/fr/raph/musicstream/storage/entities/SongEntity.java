package fr.raph.musicstream.storage.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "songs")
@NoArgsConstructor
@Data
public class SongEntity {

    public SongEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
}
