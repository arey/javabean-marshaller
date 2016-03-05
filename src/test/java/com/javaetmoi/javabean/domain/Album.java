package com.javaetmoi.javabean.domain;

import java.time.LocalDate;

public class Album {

    private String name;

    private LocalDate releaseDate;

    private Artist artist;

    public Album() {
    }

    public Album(String name, LocalDate releaseDate, Artist artist) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
