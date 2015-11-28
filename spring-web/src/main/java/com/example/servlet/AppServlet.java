package com.example.servlet;

import com.example.dictionary.Dictionary;
import com.example.dictionary.DictionaryWord;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AppServlet extends javax.servlet.http.HttpServlet {

    WebApplicationContext webApplicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException,
            IOException {
        Dictionary bean = webApplicationContext.getBean(Dictionary.class);

        String word = request.getParameter("word");
        List<DictionaryWord> dom = bean.getTranslations(word);

        PrintWriter writer = response.getWriter();
        writer.append("<html><body><ul>");

        for(DictionaryWord w: dom) {
            writer.append("<li>" + w.getPolishWord() + " :: " + w.getEnglishWord());
        }

        writer.append("</ul></body></html>");
        writer.flush();
    }

}
