package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Event Ticketing System\n", version = "1.0", description = "Efficiently manage events with our streamlined ticketing system."
        +"\n"))
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
