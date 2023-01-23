package com.webscrapper;

import org.jsoup.nodes.Document;
import java.io.IOException;
import org.jsoup.Jsoup;


public class Site {

    public String url;
    public Document doc;
    

    public Site (String u) throws IOException {
        url = u;
        doc = Jsoup.connect(url).followRedirects(false).timeout(60000).get();
    }
    //TODO add other features

    public void getText() {
        System.out.println(doc.body().text());
    }

    public void getPictures() {

    }

    public void getHTML() {
        
    }

    public void getMedia() {

    }
}
