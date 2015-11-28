package com.example.com.example.dictionary;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class DictionaryTest {

    @Test
    public void should_return_traslations_for_dom() throws IOException {
        Dictionary dict = new Dictionary();

        List<DictionaryWord> dom = dict.getTranslations("dom");

        assertThat(dom.size(), is(equalTo(24)));
    }

}
