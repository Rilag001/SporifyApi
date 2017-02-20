package com.example.rickylagerkvist.sporifyapi.models;

import java.util.List;

/**
 * Created by Ricky on 2016-11-20.
 */

public class AlbumObject {

    public String album_type;
    public List<ArtistObjects> artists;
    public List<String> available_markets;
    public List<CopyrightObjects> copyrights;
    public List<ExternalIDobject> external_ids;
    public List<ExternalURLobject> external_urls;
    public List<String> genres;
    public String href;
    public String id;
    public List<ImageObjects> images;
    public String label;
    public String name;
    public int popularity;
    public String release_date;
    public String release_date_precision;
    public Object tracks; //
    public String type;
    public String uri;

    public String getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(String album_type) {
        this.album_type = album_type;
    }

    public List<ArtistObjects> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistObjects> artists) {
        this.artists = artists;
    }

    public List<String> getAvailable_markets() {
        return available_markets;
    }

    public void setAvailable_markets(List<String> available_markets) {
        this.available_markets = available_markets;
    }

    public List<CopyrightObjects> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<CopyrightObjects> copyrights) {
        this.copyrights = copyrights;
    }

    public List<ExternalIDobject> getExternal_ids() {
        return external_ids;
    }

    public void setExternal_ids(List<ExternalIDobject> external_ids) {
        this.external_ids = external_ids;
    }

    public List<ExternalURLobject> getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(List<ExternalURLobject> external_urls) {
        this.external_urls = external_urls;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImageObjects> getImages() {
        return images;
    }

    public void setImages(List<ImageObjects> images) {
        this.images = images;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRelease_date_precision() {
        return release_date_precision;
    }

    public void setRelease_date_precision(String release_date_precision) {
        this.release_date_precision = release_date_precision;
    }

    public Object getTracks() {
        return tracks;
    }

    public void setTracks(Object tracks) {
        this.tracks = tracks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
