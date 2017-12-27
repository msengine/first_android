package com.example.byhisson.fragmentex;

/**
 * Created by byhisson on 2017. 12. 21..
 */

public class Group {

    private String groupId;
    private String groupName;
    private String groupOrganisation;

    public Group(String groupId, String groupName, String groupOrganisation) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupOrganisation = groupOrganisation;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupOrganisation(String groupOrganisation) {
        this.groupOrganisation = groupOrganisation;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupOrganisation() {
        return groupOrganisation;
    }
}
