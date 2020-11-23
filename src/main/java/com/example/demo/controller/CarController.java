package com.example.demo.controller;

import com.example.demo.dao.CarDao;
import com.example.demo.domain.Car;
import com.example.demo.util.NumberParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    private CarDao carDao;
    private NumberParser numberParser;

    @Autowired
    public CarController(CarDao carDao, NumberParser numberParser) {
        this.carDao = carDao;
        this.numberParser = numberParser;
    }

    @GetMapping("/")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carDao.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carDao.deleteCar(id);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("car", new Car());
        return "form";
    }

    @PostMapping("/add")
    public String addCar(@Validated Car car, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        carDao.saveCar(car);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String displayEditForm(@PathVariable Long id, Model model) {
        Car foundCar = carDao.findById(id);
        model.addAttribute("car", foundCar);
        return "edit";
    }

    @PostMapping("/edit")
    public String updateCar(@Validated Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("car", car);
            return "edit";
        }
        carDao.updateCar(car);
        return "redirect:/";
    }

    @GetMapping("/find")
    public String displayFilters() {
        return "find";
    }

    @PostMapping("/find/production")
    public String findByProduction(@RequestParam(value = "from") String from,
                                   @RequestParam(value = "to") String to,
                                   Model model) {

        Integer fromYear = numberParser.parsYear(from);
        Integer toYear = numberParser.parsYear(to);

        if (fromYear < 0 || toYear < 0) {
            model.addAttribute("notFoundInRange", "");
            return "find";
        }

        List<Car> foundCars = carDao.findByYear(fromYear, toYear);

        if (foundCars.size() > 0) {
            model.addAttribute("foundCars", foundCars);
            return "find";
        }

        model.addAttribute("notFoundInRange", "");
        return "find";
    }

    @PostMapping("/find/mark")
    public String findByMark(@RequestParam(value = "mark") String mark, Model model) {
        List<Car> foundCars = carDao.findByMark(mark);

        if (foundCars.size() > 0) {
            model.addAttribute("foundCars", foundCars);
            return "find";
        }

        model.addAttribute("notFound", mark);
        return "find";
    }

    @PostMapping("/find/color")
    public String findByColor(@RequestParam(value = "color") String color, Model model) {
        model.addAttribute("foundCars", carDao.findByColor(color));
        return "find";
    }
}
