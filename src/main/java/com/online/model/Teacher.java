package com.online.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    String id;
    String password;
    String schoolName;
    static String classTime;

    public static void setClassTime(String classTime) {
        Teacher.classTime = classTime;
    }

    public static String getClassTime() {
        return classTime;
    }
}
