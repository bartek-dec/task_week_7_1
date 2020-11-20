package com.example.demo.dao;

import com.example.demo.domain.Car;
import com.example.demo.domain.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(Car car) {
        String sql = "INSERT INTO cars (mark, model, production, color) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getProduction(), car.getColor().toString());
    }

    @Override
    public Car findById(Long id) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        Car car = jdbcTemplate.queryForObject(sql, new CarRowMapper(), id);

        return car;
    }

    @Override
    public List<Car> findAll() {
        String sql = "SELECT * FROM cars";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        return mapResults(maps);
    }

    @Override
    public void updateCar(Car car) {
        String sql = "UPDATE cars SET mark = ?, model = ?, production = ?, color = ? WHERE id = ?";
        jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getProduction(), car.getColor().toString(), car.getId());
    }

    @Override
    public void deleteCar(Long id) {
        String sql = "DELETE FROM cars WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Car> findByYear(Integer from, Integer to) {
        String fromYear = from + "-01-01";
        String toYear = to + "-12-31";
        String sql = "SELECT * FROM cars WHERE production >= ? AND production <= ? ORDER BY production ASC ";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, fromYear, toYear);

        return mapResults(maps);
    }

    @Override
    public List<Car> findByColor(String color) {
        String sql = "SELECT * FROM cars WHERE color = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, color);

        return mapResults(maps);
    }

    @Override
    public List<Car> findByMark(String mark) {
        String sql = "SELECT * FROM cars WHERE mark LIKE ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, mark);

        return mapResults(maps);
    }

    private List<Car> mapResults(List<Map<String, Object>> maps) {
        List<Car> cars = new ArrayList<>();

        maps.stream().forEach(element -> {
            Car car = new Car();

            car.setId(Long.parseLong(String.valueOf(element.get("id"))));
            car.setMark(String.valueOf(element.get("mark")));
            car.setModel(String.valueOf(element.get("model")));
            car.setProduction(Date.valueOf(String.valueOf(element.get("production"))).toLocalDate());
            car.setColor(Color.valueOf(String.valueOf(element.get("color"))));

            cars.add(car);
        });
        return cars;
    }
}
