package com.example;

import com.example.App;
import com.example.dictionary.Dictionary;
import com.example.dictionary.DictionaryWord;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AppTest {

    @Test
    public void tst() throws IOException {
        App app = new App();
        app.dictionary = new Dictionary();

        List<DictionaryWord> translations = app.getTranslations("dom");
    }

}
