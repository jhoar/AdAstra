package esa.sci.ops.adastra.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        boolean foundClass = false;

        for (String name : applicationContext.getBeanDefinitionNames()) {
            if (name.equals("wpApiImplementation")) {
                foundClass = true;
            }
        }

        if (!foundClass) {
            System.err.println("Did not find Bean");
        }

    }
}