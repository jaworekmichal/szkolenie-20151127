package com.example.audit;

import com.example.dictionary.DictionaryWord;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Aspect
@Component
public class TranslationMonitor {

    @Autowired
    TranslationRepository repo;

    @Around("execution(* com.example.App.getTranslations(..))")
    public Object monitor(ProceedingJoinPoint jp) throws Throwable {
        String word = (String) jp.getArgs()[0];

        List<DictionaryWord> translations = (List<DictionaryWord>) jp.proceed();

        TranslationLog log = new TranslationLog();
        log.setPolishWord(word);
        log.setDate(new Date());
        log.setCnt(translations.size());
        repo.save(log);

        return translations;
    }

}
