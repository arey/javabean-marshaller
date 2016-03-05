package com.javaetmoi.javabean.domain;

import java.util.ArrayList;
import java.util.List;

public class Artist {

    private long id;

    private String name;

    private ArtistType type;

    private List<Album> albums = new ArrayList<>();

    public Artist() {
    }

    public Artist(long id, String name, ArtistType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public ArtistType getType() {
        return type;
    }

    public void setType(ArtistType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
