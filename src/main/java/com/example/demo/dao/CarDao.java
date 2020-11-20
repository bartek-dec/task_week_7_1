package com.example.demo.dao;

import com.example.demo.domain.Car;

import java.util.List;

public interface CarDao {

    void saveCar(Car car);

    Car findById(Long id);

    List<Car> findAll();

    void updateCar(Car car);

    void deleteCar(Long id);

    List<Car> findByYear(Integer from, Integer to);

    List<Car> findByColor(String color);

    List<Car> findByMark(String mark);
}
