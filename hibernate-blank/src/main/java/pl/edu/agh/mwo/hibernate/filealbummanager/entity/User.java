package pl.edu.agh.mwo.hibernate.filealbummanager.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    //        User -> Album
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "UserId")
    private Set<Album> albums = new HashSet<>();

    //        User <-> Photo
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PhotoLikes",
            joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "PhotoId"))
    private Set<Photo> photos = new HashSet<>();


    //     User <-> User
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Friends",
            joinColumns = @JoinColumn(name = "InvitationSentByUserId"),
            inverseJoinColumns = @JoinColumn(name = "InvitationAcceptedByUserId"))
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

    public Set<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        if (album != null) {
            albums.add(album);
            album.setUserId(this.id);
        }
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void addPhoto(Photo photo) {
        if (photo != null) {
            photos.add(photo);

            if (!photo.getUsers().contains(this))
                photo.getUsers().add(this);
        }
    }

    public void removePhoto(Photo photo) {
        if (photo != null) {
            photos.remove(photo);
            photo.getUsers().remove(this);
        }
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (user != null && user != this) {
            users.add(user);
            user.getUsers().add(this);
        }
    }

    public void removeUser(User user) {
        if (user != null) {
            users.remove(user);
            user.getUsers().remove(this);
        }
    }

    @Override
    public String toString() {
        return "user name: " + name;
    }

}
