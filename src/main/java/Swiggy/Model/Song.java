package Swiggy.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "song")
public class Song {

    @Id
    @Getter
    @GeneratedValue
    private long songId;

    @Getter
    @Setter
    @Column(name = "name",length = 4096)
    private String name;

    @Getter
    @Setter
    @Column(name = "url",length = 4096)
    private String url;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "albumId")
    private Album album;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "artistId")
    private Artist artist;


    @Getter
    @Setter
    @Column(name = "likes",columnDefinition = "int default 0")
    private int likes;

    @Getter
    @Setter
    @Column(name = "plays",columnDefinition = "int default 0")
    private int plays;


    public Song() {
    }

    public Song(String name, Album album, Artist artist) {
        this.name = name;
        this.album = album;
        this.artist = artist;
    }
}
