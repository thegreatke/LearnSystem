package com.online.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Liuyan {
    private int id;
    private String liuyan;
    private String name;

    private String reply = "";


    public Liuyan() {
    }


}
