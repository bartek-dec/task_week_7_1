package com.example.demo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private DataSource dataSource;
    private InitService initService;

    @Autowired
    public DbConfig(DataSource dataSource,
                    InitService initService) {
        this.dataSource = dataSource;
        this.initService = initService;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        String dropTable = "DROP TABLE IF EXISTS `cars`";
        getJdbcTemplate().update(dropTable);

        String createTable = "CREATE TABLE cars (id int AUTO_INCREMENT PRIMARY KEY , mark varchar (100), model varchar (100)," +
                " production date, color varchar (20))";
        getJdbcTemplate().update(createTable);

        String save = "INSERT INTO cars (mark, model, production, color) VALUES (?,?,?,?)";
        initService.getCars()
                .forEach(car ->
                        getJdbcTemplate().update(save, car.getMark(), car.getModel(), car.getProduction(), car.getColor().toString()));
    }
}
