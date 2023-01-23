package com.webscrapper;

import java.io.IOException;

public class Scrapper {

    
    public static void main(String[] args) throws IOException {
        Site s = new Site("https://www.google.com/");
        s.getText();
        

    }
    
}