package com.example.projekt.model;

import jakarta.validation.constraints.*;

public class Driver {

    private int id = -1;

    @NotBlank
    private String name;

    @Min(value = 18)
    @Max(value = 90)
    private int age;

    @Min(value = 20000)
    private int salary;

    public int getId() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}