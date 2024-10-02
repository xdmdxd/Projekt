package com.example.projekt.model;

public class Car {
    private int id=-1;
    private String spz;
    private String color;
    private String tankVolume;
    private String numberOfSeats;


    public Car(String spz, String color, String tankVolume, String numberOfSeats) {
        this.spz = spz;
        this.color = color;
        this.tankVolume = tankVolume;
        this.numberOfSeats = numberOfSeats;
    }

    public Car(){

    }

    public String getSpz() {
        return spz;
    }

    public String getColor() {
        return color;
    }

    public String getTankVolume() {
        return tankVolume;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTankVolume(String tankVolume) {
        this.tankVolume = tankVolume;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
