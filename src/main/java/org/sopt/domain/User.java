package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.constant.DataBaseConstant;

@Entity
@Table(name = DataBaseConstant.USER)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    public User() {

    }

    public User(String name) {
        this.author = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }
}
