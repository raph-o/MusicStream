package fr.raph.musicstream.storage.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "songs")
@Data
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
}
