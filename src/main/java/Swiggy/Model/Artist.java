package Swiggy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "artist")
public class Artist {

    @Id
    @Getter
    @GeneratedValue
    private long artistId;

    @Getter
    @Setter
    @Column(name = "name",length = 4096)
    private String name;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }
}
