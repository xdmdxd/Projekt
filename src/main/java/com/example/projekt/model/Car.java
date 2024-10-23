package com.example.projekt.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Car {
    private int id=-1;


    @Min(value=7,message="Must be 7")
    @Max(value=7, message="Cannot be over 7")
    private String spz;

    @NotBlank
    private String color;

    @Min(value=30,message="Must be 30")
    @Max(value=100, message="Cannot be over 100")
    private String tankVolume;


    @Min(value=1,message="Must be 1")
    @Max(value=12, message="Cannot be over 12")
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
