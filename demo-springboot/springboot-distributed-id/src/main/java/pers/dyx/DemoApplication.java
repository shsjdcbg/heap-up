package pers.dyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.dyx.demo.MyApplicationPreparedEvent;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.addListeners(new MyApplicationPreparedEvent());
        application.run(args);
    }

}
