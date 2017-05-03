package itzero;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class Application {

    @RequestMapping(value = "/api/foo")
    public String api() {
        return "api";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
