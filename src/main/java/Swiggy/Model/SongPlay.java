package Swiggy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "songLike")
public class SongPlay{

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

    @Getter
    @Column(name = "addedOn",nullable = false,insertable = false,columnDefinition = "DateTime default current_timestamp()")
    private Date dateTime;

    public SongPlay() {
    }

    public SongPlay(Song song, User user) {
        this.song = song;
        this.user = user;
    }
}
