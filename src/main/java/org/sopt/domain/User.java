package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.constant.DataBaseConstant;

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

    public User(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = DataBaseConstant.USER)
    private List<Post> diaryEntities = new ArrayList<>();
}
