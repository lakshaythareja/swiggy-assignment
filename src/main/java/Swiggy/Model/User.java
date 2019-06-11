package Swiggy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "user")
public class User {
    @Id
    @Getter
    @GeneratedValue
    private long userId;

    @Getter
    @Setter
    @Column(name = "name",length = 1024, unique = true)
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
