package com.example;

import com.example.audit.TranslationLogEvent;
import com.example.dictionary.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
@ComponentScan
public class App implements ApplicationListener<TranslationLogEvent> {

    public static void main(String... args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);

        Dictionary dictionary = ctx.getBean(Dictionary.class);
        System.out.println(dictionary.getTranslations("dom"));
    }

    @Override
    public void onApplicationEvent(TranslationLogEvent event) {
        System.out.println("event = " + event.getSource());
    }
}
