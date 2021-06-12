package com.example.checklistapp;

public class SectionModel {

    private int sectionID;
    private String sectionName;

    public SectionModel(int sectionID, String sectionName) {
        this.sectionID = sectionID;
        this.sectionName = sectionName;
    }

    public SectionModel() {
    }

    public int getSectionID() {
        return sectionID;
    }

    public String getSectionName() {
        return sectionName;
    }

    @Override
    public String toString() {
        return this.getSectionName();
    }
}
