package com.example.web;

import com.example.dictionary.Dictionary;
import com.example.dictionary.DictionaryWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    Dictionary dict;

    @RequestMapping(value = "/translate/{word}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DictionaryWord> getTranslations(@PathVariable("word") String word) throws IOException {
        return dict.getTranslations(word);
    }

}
