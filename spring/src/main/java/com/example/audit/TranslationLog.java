package com.example.audit;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

public class TranslationLog {

    private String polishWord;
    private Integer cnt;
    private Date date;

    public String getPolishWord() {
        return polishWord;
    }

    public void setPolishWord(String polishWord) {
        this.polishWord = polishWord;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TranslationLog{" +
                "polishWord='" + polishWord + '\'' +
                ", cnt=" + cnt +
                ", date=" + date +
                '}';
    }
}
