package com.example;

import com.example.com.example.audit.TranslationLog;
import com.example.com.example.audit.TranslationRepository;
import com.example.com.example.dictionary.Dictionary;
import com.example.com.example.dictionary.DictionaryWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@EnableAutoConfiguration
@RestController
@ComponentScan
public class App {

    @Autowired
    Dictionary dictionary;

    @Autowired
    TranslationRepository repo;

    @RequestMapping("/test")
    public String foo() {
        return "bar";
    }

    @RequestMapping("/translate/{word}")
    public List<DictionaryWord> getTranslations(@PathVariable("word") String word) throws IOException {
        List<DictionaryWord> translations = dictionary.getTranslations(word);

        TranslationLog log = new TranslationLog();
        log.setPolishWord(word);
        log.setDate(new Date());
        log.setCnt(translations.size());
        repo.save(log);

        return translations;
    }

    @RequestMapping("/logs")
    public List<TranslationLog> logs() {
        return repo.findAll();
    }

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }


}
