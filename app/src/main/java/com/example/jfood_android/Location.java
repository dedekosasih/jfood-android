package com.example.jfood_android;


public class Location {

    private String province;
    private String description;
    private String city;


    public Location(String city, String description, String province) {
        // initialise instance variables
        this.city = city;
        this.description = description;
        this.province = province;
    }


    public String getProvince() {
        // initialise instance variables
        return province;
    }


    public String getCity() {
        // initialise instance variables
        return city;
    }

    public String getDescription() {
        // initialise instance variables
        return description;
    }


    public void setProvince(String province) {
        // initialise instance variables
        this.province = province;
    }


    public void setCity(String city) {
        // initialise instance variables
        this.city = city;
    }

    public void setDescription(String description) {

        this.description = description;


    }
}
