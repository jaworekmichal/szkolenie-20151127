package com.example.audit;

import org.springframework.context.ApplicationEvent;

public class TranslationLogEvent extends ApplicationEvent {

    public TranslationLogEvent(TranslationLog source) {
        super(source);
    }
}
