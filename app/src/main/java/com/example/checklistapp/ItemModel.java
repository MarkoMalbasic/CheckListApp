package com.example.checklistapp;

public class ItemModel {

    private String itemID;
    private String itemName;
    private String sectionsID;

    public ItemModel(String itemID, String itemName, String sectionsID) {

        this.itemID = itemID;
        this.itemName = itemName;
        this.sectionsID = sectionsID;
    }

    public String getItemID() {

        return itemID;
    }

    public String getItemName() {

        return itemName;
    }

    public String getSectionsID() {

        return sectionsID;
    }

    @Override
    public String toString() {
        return this.getItemName();
    }
}
