package model;

import java.util.Objects;

public class User {
    private String username;
    private String mother;
    private String father;

    public User(){

    }

    public User(String username, String mother, String father) {
        this.username = username;
        this.mother = mother;
        this.father = father;
    }

    public String getUsername() {
        return username;
    }

    public String getMother() {
        return mother;
    }

    public String getFather() {
        return father;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setFather(String father) {
        this.father = father;
    }

    @Override
    public String toString() {
        // JSON
        return "{" +
                "'username'='" + username + '\'' +
                ", 'mother'='" + mother + '\'' +
                ", 'father'='" + father + '\'' +
                '}';
    }
}