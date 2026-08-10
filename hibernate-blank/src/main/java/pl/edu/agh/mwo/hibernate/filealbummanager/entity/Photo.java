package pl.edu.agh.mwo.hibernate.filealbummanager.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String date; // Date date -> sql -> 1740006000000
    @Column
    private int albumId;

//         Photo <-> User
    @ManyToMany(mappedBy = "photos")
    private Set<User> users = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (user != null) {
            users.add(user);
            if (!user.getPhotos().contains(this))
                user.getPhotos().add(this);
        }
    }

    public void removeUser(User user) {
        if (user != null) {
            users.remove(user);
            if (user.getPhotos().contains(this))
                user.getPhotos().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Photo name: " + name;
    }
}
