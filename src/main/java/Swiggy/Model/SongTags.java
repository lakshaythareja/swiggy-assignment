package Swiggy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "songTags")
public class SongTags {

    @Id
    @Getter
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "songId")
    private Song song;


    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "tagId")
    private Tags tags;


    public SongTags(Song song, Tags tags) {
        this.song = song;
        this.tags = tags;
    }

    public SongTags() {
    }
}
