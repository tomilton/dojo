package co.com.nequi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        System.setProperty("aws.region", "us-west-1");
        SpringApplication.run(MainApplication.class, args);
    }
}
