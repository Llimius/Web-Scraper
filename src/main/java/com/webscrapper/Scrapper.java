package com.webscrapper;

//import java.util.Scanner;

public class Scrapper {

    //TODO Add GUI

    public static void main(String[] args) {
        //Site s = new Site("https://en.wikipedia.org/wiki/Rainforest");
        //Site s = new Site("https://www.google.com");
        Site s = new Site("https://minecraft.fandom.com/wiki/Skeleton");
        
        Writer.WriteTextToFile(s.getText(), s.url);
        Writer.savePicturesToFolder(s.getImages());
        Writer.saveAudiosToFolder(s.getAudios());
    }
    

    //GUI
    public static void displayOptions() {
        /*Scanner keys = new Scanner(System.in);
        System.out.println("----------------------------------------------------");
        System.out.println("| Welcome, this is a Web Scraper. What would you like to do?");
        System.out.println("| 1- Get Text from Site(s)");
        System.out.println("| 2- Get Pictures from Site(s)");
        System.out.println("| 3- Get Media from Site(s)");
        System.out.println("| 4- Get All of the Above from Site(s)");
        System.out.println("----------------------------------------------------");
        System.out.print("Enter your decision here: ");
        int decision = keys.nextInt();
        //Insert Deciding Method (with switch)

        System.out.print("Do you have only one Site? [Y/n]: ");
        String yesOrNo = keys.next();
        //Insert method to turn string to boolean

        keys.close();*/
    }
}