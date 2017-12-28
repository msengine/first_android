package com.example.byhisson.fragmentex;

/**
 * Created by byhisson on 2017. 12. 21..
 */

public class Group {

    private String id;
    private String name;
    private String organization;

    public Group(String id, String name, String organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public String getOrganization() {
        return organization;
    }
}
