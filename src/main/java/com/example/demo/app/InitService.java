package com.example.demo.app;

import com.example.demo.domain.Car;
import com.example.demo.domain.Color;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    private List<Car> cars;

    public InitService() {
        cars = new ArrayList<>();
        initializeCars();
    }

    private void initializeCars() {
        cars.add(new Car("Alfa Romeo", "159", LocalDate.of(2010, 1, 1), Color.BLACK));
        cars.add(new Car("Audi", "A4", LocalDate.of(2005, 2, 2), Color.BLUE));
        cars.add(new Car("Bantley", "Continental", LocalDate.of(2014, 3, 3), Color.WHITE));
        cars.add(new Car("BMW", "3", LocalDate.of(2016, 4, 4), Color.RED));
        cars.add(new Car("Citroen", "C4 Picasso", LocalDate.of(2018, 5, 5), Color.GREEN));
        cars.add(new Car("Daewoo", "Matiz", LocalDate.of(1999, 6, 6), Color.YELLOW));
        cars.add(new Car("Fiat", "Bravo", LocalDate.of(2008, 7, 7), Color.PURPLE));
        cars.add(new Car("Ford", "Focus", LocalDate.of(2011, 8, 8), Color.GREY));
        cars.add(new Car("Honda", "Civic", LocalDate.of(2004, 9, 9), Color.BROWN));
        cars.add(new Car("Hyunday", "Tucson", LocalDate.of(2015, 10, 10), Color.BLACK));
        cars.add(new Car("Jaguar", "XJ", LocalDate.of(1973, 11, 11), Color.BLUE));
        cars.add(new Car("Jeep", "Cherokee", LocalDate.of(1998, 12, 12), Color.WHITE));
        cars.add(new Car("Lancia", "Voyager", LocalDate.of(2013, 1, 14), Color.RED));
        cars.add(new Car("Mazda", "CX-3", LocalDate.of(2016, 2, 15), Color.GREEN));
        cars.add(new Car("Mercedes", "GLS", LocalDate.of(2019, 3, 16), Color.YELLOW));
        cars.add(new Car("Opel", "Astra", LocalDate.of(2006, 4, 17), Color.PURPLE));
        cars.add(new Car("Toyota", "Corolla", LocalDate.of(2004, 5, 18), Color.GREY));
        cars.add(new Car("Volvo", "XC90", LocalDate.of(2002, 6, 19), Color.BROWN));
    }

    public List<Car> getCars() {
        return cars;
    }
}
