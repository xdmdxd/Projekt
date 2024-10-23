package com.example.projekt.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Driver {
    private int personalId;


    @NotEmpty(message="name is required")
    @Size(min=5, message="At least 5 characters")
    private String name;


    @Min(value=18,message="Must be 18")
    @Max(value=98, message="Cannot be over 100")
    private int age;


    @Min(value=10000,message="Must be 10000")
    @Max(value=98000, message="Cannot be over 98000")
    private int salary;


    public int getId() {
        return personalId;
    }

    public @NotEmpty(message = "name is required") @Size(min = 5, message = "At least 5 characters") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "name is required") @Size(min = 5, message = "At least 5 characters") String name) {
        this.name = name;
    }

    @Min(value = 18, message = "Must be 18")
    @Max(value = 98, message = "Cannot be over 100")
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 18, message = "Must be 18") @Max(value = 98, message = "Cannot be over 100") int age) {
        this.age = age;
    }

    @Min(value = 10000, message = "Must be 10000")
    @Max(value = 98000, message = "Cannot be over 98000")
    public int getSalary() {
        return salary;
    }

    public void setSalary(@Min(value = 10000, message = "Must be 10000") @Max(value = 98000, message = "Cannot be over 98000") int salary) {
        this.salary = salary;
    }
}
