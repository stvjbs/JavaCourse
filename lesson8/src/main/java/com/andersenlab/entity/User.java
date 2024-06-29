package com.andersenlab.entity;

import java.sql.Timestamp;

public class User {
    private int id;
    private String name;
    private Timestamp creationDate;


    public User() {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
