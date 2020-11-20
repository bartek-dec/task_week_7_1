package com.example.demo.dao;

import com.example.demo.domain.Car;
import com.example.demo.domain.Color;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet resultSet, int i) throws SQLException {
        Car car = new Car();

        car.setId(resultSet.getLong("id"));
        car.setMark(resultSet.getString("mark"));
        car.setModel(resultSet.getString("model"));
        car.setProduction(Date.valueOf(String.valueOf(resultSet.getObject("production"))).toLocalDate());
        car.setColor(Color.valueOf(resultSet.getString("color")));

        return car;
    }
}
