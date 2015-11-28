package com.example.dictionary;

import com.example.audit.TranslationLog;
import com.example.audit.TranslationLogEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class Dictionary {

    @Autowired
    private ApplicationContext ctx;

    public List<DictionaryWord> getTranslations(String polishWord) throws IOException {
        List<String> words = getWords(polishWord);

        List<DictionaryWord> list = IntStream.range(0, words.size())         //for all words in list
                .filter(i -> i % 2 == 0)                //take every second
                .mapToObj(i -> new DictionaryWord(words.get(i), words.get(i + 1))) //map to domain object
                .collect(Collectors.toList());//end return

        TranslationLog log = new TranslationLog();
        log.setDate(new Date());
        log.setPolishWord(polishWord);
        log.setCnt(list.size());
        ctx.publishEvent(new TranslationLogEvent(log));

        return list;
    }

    private List<String> getWords(String polishWord) throws IOException {
        List<String> words = new ArrayList<>();

        String urlString = "http://www.dict.pl/dict?word=" + polishWord
                + "&words=&lang=PL";

        InputStream in = new URL(urlString).openStream();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
            String line = bufferedReader.readLine();
            Pattern pat = Pattern
                    .compile(".*<a href=\"dict\\?words?=(.*)&lang.*");

            while (line != null) {
                Matcher matcher = pat.matcher(line);
                if (matcher.find()) {
                    String word = matcher.group(matcher.groupCount());
                    words.add(word);
                }
                line = bufferedReader.readLine();
            }
        }

        return words;
    }

}
