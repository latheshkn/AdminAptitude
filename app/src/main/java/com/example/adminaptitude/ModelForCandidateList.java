package com.example.adminaptitude;

public class ModelForCandidateList {

    String name;
    int mobile;

    public ModelForCandidateList(String name, int mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }
}
