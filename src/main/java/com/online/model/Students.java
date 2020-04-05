package com.online.model;

import lombok.Data;

@Data
public class Students {
    private int id;
    private String name;
    private String password;
    private int score;
    private String teacherName;
    private String email;


    public Students() {
    }

    public Students(int id, String name, String teacherName) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
    }
}
