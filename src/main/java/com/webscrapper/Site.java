package com.webscrapper;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.UnknownHostException;
import org.jsoup.Jsoup;

public class Site {

    public String url;
    private Document doc;

    public Site (String URL) {
        try {
            url = URL;
            doc = Jsoup.connect(url).followRedirects(false).timeout(60000).get();
            Writer.logEntry("Connected to Site: " + url + "\n");
        }
        catch (UnknownHostException uh) {
            System.out.println("Error, Site could not be reached.");
            uh.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Error Connecting to Site.");
            e.printStackTrace();
        }
        
    }

    public String getText() {
        return doc.body().text();
    }

    public String getHTML() {
        return doc.html();
    }

    public Elements getImages() {
        Elements images = new Elements();
        images = doc.body().select("img[src~=(?i)\\.(png|jpe?g|gif|svg)]");
        return images;
    }

    public Elements getAudios() {
        Elements audios = new Elements();
        audios = doc.body().select("audio");
        return audios;
    }
}
