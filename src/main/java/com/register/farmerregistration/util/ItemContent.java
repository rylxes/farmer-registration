package com.register.farmerregistration.util;


/**
 * Created by sherriff on 21/09/2016.
 */
public class ItemContent {
    private long id;
    private int idInt;
    private String idString;
    private String name;

    public ItemContent(int id, String name) {
        this.idInt = id;
        this.name = name;
    }

    public ItemContent(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ItemContent(String idString, String name) {
        this.idString = idString;
        this.name = name;
    }

//    public ItemContent(ArrayList list) {
//        for (Object eachList : list) {
//            this.id = eachList;
//            this.name = name;
//        }
//
//    }



    public String toStringList() {
        return "ItemContent{" +
                "id=" + id +
                ", idString='" + idString + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
