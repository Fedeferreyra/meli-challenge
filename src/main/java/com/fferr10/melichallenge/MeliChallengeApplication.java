package com.fferr10.melichallenge;

import com.fferr10.melichallenge.solar.system.Satellite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MeliChallengeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MeliChallengeApplication.class, args);

        context.getBean(Satellite.class).predictWeather();

    }

}

