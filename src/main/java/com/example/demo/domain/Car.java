package com.example.demo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Car {

    private Long id;

    @NotEmpty(message = "Mark cannot be empty")
    @Size(min = 1, max = 100, message = "Mark mast be between 1 and 100 characters")
    private String mark;

    @NotEmpty(message = "Model cannot be empty")
    @Size(min = 1, max = 100, message = "Model mast be between 1 and 100 characters")
    private String model;

    @PastOrPresent(message = "Production year cannot be future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate production;
    private Color color;

    public Car() {
    }

    public Car(String mark, String model, LocalDate production, Color color) {
        this.mark = mark;
        this.model = model;
        this.production = production;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getProduction() {
        return production;
    }

    public void setProduction(LocalDate production) {
        this.production = production;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
