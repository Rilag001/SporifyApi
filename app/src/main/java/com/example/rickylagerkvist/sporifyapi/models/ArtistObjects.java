package com.example.rickylagerkvist.sporifyapi.models;

/**
 * Created by Ricky on 2016-11-20.
 */

public class ArtistObjects {

    public ExternalURLobject external_urls;
    public String href;
    public String id;
    public String name;
    public String type;
    public String uri;

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
