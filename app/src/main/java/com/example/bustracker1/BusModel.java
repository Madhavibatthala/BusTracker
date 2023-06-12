package com.example.bustracker1;

public class BusModel {

    public String latitude;
    private String longitude;
//   private String phoneNum;
public String name;


    public BusModel(String latitude, String longitude,String name ) {
        this.latitude = latitude;
        this.longitude = longitude;
        //this.phoneNum = phoneNum1;
//        this.phoneNum = phoneNum;
        this.name = name;
    }

    public BusModel() {

    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

//    public String getPhoneNum() {
//        return phoneNum;
//    }

//    public void setPhoneNum(String phoneNum) {
//        this.phoneNum = phoneNum;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
