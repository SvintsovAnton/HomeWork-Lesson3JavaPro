package client;

import app.controller.AutoController;
import app.domain.Auto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Client {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext("app");
        AutoController autoController = context.getBean(AutoController.class);
        System.out.println(autoController.getAuto(2L));

    }
}
