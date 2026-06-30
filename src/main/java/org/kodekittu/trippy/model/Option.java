package org.kodekittu.trippy.model;

public class Option {

    private String id;
    private String value;

    public Option(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}