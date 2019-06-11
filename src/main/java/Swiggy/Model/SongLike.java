package Swiggy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "songLike")
public class SongLike {

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
    @JoinColumn(name = "userId")
    private User user;

    public SongLike() {
    }

    public SongLike(Song song, User user) {
        this.song = song;
        this.user = user;
    }
}
