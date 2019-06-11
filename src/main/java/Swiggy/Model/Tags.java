package Swiggy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "tags")
public class Tags {

    @Id
    @Getter
    @GeneratedValue
    private long tagId;

    @Getter
    @Setter
    @Column(name = "name",length = 4096)
    private String name;

    public Tags() {
    }

    public Tags(String name) {
        this.name = name;
    }
}
