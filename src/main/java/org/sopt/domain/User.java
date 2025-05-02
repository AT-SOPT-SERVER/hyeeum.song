package org.sopt.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
    }

    @OneToMany(mappedBy = "user")
    private List<Post> diaryEntities = new ArrayList<>();
}
