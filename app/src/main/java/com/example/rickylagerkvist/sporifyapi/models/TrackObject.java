package com.example.rickylagerkvist.sporifyapi.models;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by Ricky on 2016-11-20.
 */

public class TrackObject {

    public AlbumObject album;
    public ArrayList<ArtistObjects> artists;
    public ArrayList<String> available_markets;
    public int disc_number;
    public int duration_ms;
    public boolean explicit;
    public ExternalIDobject external_ids;
    public ExternalURLobject external_urls;
    public String href;
    public String id;
    public String name;
    public int popularity;
    public String preview_url;
    public int track_number;
    public String type;
    public String uri;

    public AlbumObject getAlbum() {
        return album;
    }

    public void setAlbum(AlbumObject album) {
        this.album = album;
    }

    public ArrayList<ArtistObjects> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<ArtistObjects> artists) {
        this.artists = artists;
    }

    public ArrayList<String> getAvailable_markets() {
        return available_markets;
    }

    public void setAvailable_markets(ArrayList<String> available_markets) {
        this.available_markets = available_markets;
    }

    public int getDisc_number() {
        return disc_number;
    }

    public void setDisc_number(int disc_number) {
        this.disc_number = disc_number;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public ExternalIDobject getExternal_ids() {
        return external_ids;
    }

    public void setExternal_ids(ExternalIDobject external_ids) {
        this.external_ids = external_ids;
    }

    public ExternalURLobject getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(ExternalURLobject external_urls) {
        this.external_urls = external_urls;
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

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public int getTrack_number() {
        return track_number;
    }

    public void setTrack_number(int track_number) {
        this.track_number = track_number;
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


    public String getJoinTrack(){
        ArrayList<ArtistObjects> list = getArtists();
        ArrayList<String> nameList = new ArrayList<>();
        for (ArtistObjects model:list) {
            nameList.add(model.name);
        }
        return TextUtils.join(", ", nameList);
    }

    public String getSmallCoverArt(){
        return album.images.get(album.images.size() - 1).getUrl();
    }
}
