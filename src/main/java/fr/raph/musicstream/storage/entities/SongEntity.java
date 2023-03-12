package fr.raph.musicstream.storage.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "songs")
@NoArgsConstructor
@Data
public class SongEntity {

    public SongEntity(String name, String author, byte[] data) {
        this.name = name;
        this.author = author;
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String author;

    @Lob
    private byte[] data;
}
