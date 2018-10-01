package models;


//import javax.persistence.*;

//@Entity
public class User {
    //    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;
    public String name;

    public String tweet;

    public User(String name, String tweet) {
        this.name = name;
        this.tweet = tweet;
    }

    public User(int id, String name, String tweet) {
        this.id = id;
        this.name = name;
        this.tweet = tweet;
    }

    public User() {

    }

    public String toString() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTweet() {
        return tweet;
    }
}