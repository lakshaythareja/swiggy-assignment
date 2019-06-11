package Swiggy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "album")
public class Album {

    @Id
    @Getter
    @GeneratedValue
    private long albumId;

    @Getter
    @Setter
    @Column(name = "name",length = 4096)
    private String name;

    @Getter
    @Setter
    @Column(name = "tracks")
    private int tracks;

    public Album() {
    }

    public Album(String name, int tracks) {
        this.name = name;
        this.tracks = tracks;
    }
}
