package com.example;

import com.example.com.example.dictionary.Dictionary;
import com.example.com.example.dictionary.DictionaryWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@EnableAutoConfiguration
@RestController
@ComponentScan
public class App {

    @Autowired
    Dictionary dictionary;

    @RequestMapping("/test")
    public String foo() {
        return "bar";
    }

    @RequestMapping("/translate/{word}")
    public List<DictionaryWord> getTranslations(@PathVariable("word") String word) throws IOException {
        return dictionary.getTranslations(word);
    }

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }


}
