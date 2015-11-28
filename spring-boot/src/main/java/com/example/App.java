package com.example;

import com.example.audit.TranslationLog;
import com.example.audit.TranslationRepository;
import com.example.dictionary.Dictionary;
import com.example.dictionary.DictionaryWord;
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

    private Dictionary dictionary;
    private TranslationRepository repo;

    @Autowired
    public App(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Autowired
    public void setRepo(TranslationRepository repo) {
        this.repo = repo;
    }

    @RequestMapping("/test")
    public String foo() {
        return "bar";
    }

    @RequestMapping("/translate/{word}")
    public List<DictionaryWord> getTranslations(@PathVariable("word") String word) throws IOException {
        return dictionary.getTranslations(word);
    }

    @RequestMapping("/logs")
    public List<TranslationLog> logs() {
        return repo.findAll();
    }

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }


}
