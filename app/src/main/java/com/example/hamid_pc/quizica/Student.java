package com.example.hamid_pc.quizica;

/**
 * Created by Hamid-PC on 3/2/2017.
 */

public class Student {
    private String name;
    private String id;
    private String uuid;

    public Student(String uuid, String name, String id) {
        this.name = name;
        this.id = id;
        this.uuid = uuid;
    }

    public Student() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
