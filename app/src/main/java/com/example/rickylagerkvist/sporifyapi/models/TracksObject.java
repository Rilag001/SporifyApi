package com.example.rickylagerkvist.sporifyapi.models;

import java.util.ArrayList;

/**
 * Created by Ricky on 2016-11-20.
 */

public class TracksObject {

    public String href;
    public ArrayList<TrackObject> items;
    public int integer;
    public String next;
    public CursorObject cursors;
    public int total;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ArrayList<TrackObject> getItems() {
        return items;
    }

    public void setItems(ArrayList<TrackObject> items) {
        this.items = items;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public CursorObject getCursors() {
        return cursors;
    }

    public void setCursors(CursorObject cursors) {
        this.cursors = cursors;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
