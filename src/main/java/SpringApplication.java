package main.java;

import main.java.spring.config.MySpringConfiguration;
import main.java.transfer.Driver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfiguration.class);
        Driver driver = (Driver)context.getBean("driver");
        driver.processWarehouse();
    }
}
