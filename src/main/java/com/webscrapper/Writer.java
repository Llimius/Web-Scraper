package com.webscrapper;

import java.io.FileWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.select.Elements;

public class Writer {
        //TODO add Log of downloaded images and their sources
        //TODO Get JSON file from website.

    public static void WriteTextToFile(String text, String URL) {
        File file = new File("Output.txt");
        
        //Following Code Checks if There Exists a File with the Name.
        if (file.exists() == false) {
            System.out.println("Creating text File...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: Cannot Create File");
                e.printStackTrace();
            }  
        }

        //Following Code Starts Writing to a File.
        try {
            FileWriter wr = new FileWriter(file, true);
            wr.append(getDate() + " Entry from ( " + URL + " ): \n" + text + "\n\n");
            System.out.println("Finished Writing Text.");
            wr.close();
        } catch (IOException e) {
            System.out.println("Error: Cannot Write to file. File may be Opened by some Application");
            e.printStackTrace();
        }
    }



    public static void savePicturesToFolder(Elements images) {
        File folder = new File("images");

        //Creates Empty Folder
        if (folder.exists() == false) {
            System.out.println("Creating images Folder...");
            folder.mkdir();
        }

        int next = getNextInteger("image", ".jpg");
        //Following Code Starts Downloading Images.
        for (int i=0; i<images.size(); i++, next++) {
            File imageFile = new File(folder, "image" + next + ".jpg");

            String link = images.get(i).attr("abs:src");
            //Attempt at Downloading Image
            try {
                Response resultImage = Jsoup.connect(link).ignoreContentType(true).timeout(60000).execute();
                FileOutputStream out = new FileOutputStream(imageFile);
                out.write(resultImage.bodyAsBytes());
                out.close();

            //Exception Handlers
            } catch (HttpStatusException h) {
                System.out.println("Cannot access: " + link);
                System.out.println(h.getMessage());
            } catch (MalformedURLException u) {
                System.out.println("Incorrect URL: " + link);
                System.out.println(u.getMessage());
            } catch (IOException e) {
                System.out.println("Error: Cannot Write to file.");
                e.printStackTrace();
            }
        }
        System.out.println("Finished Writing Images");
    }



    public static void saveAudiosToFolder(Elements Audios) {
        File folder = new File("audios");

        //Creates Empty Folder
        if (folder.exists() == false) {
            System.out.println("Creating audios Folder...");
            folder.mkdir();
        }

        int next = getNextInteger("audio", ".ogg");
        //Following Code Starts Downloading Audio Files.
        for (int i=0; i<Audios.size(); i++, next++) {
            File audioFile = new File(folder, "audio" + next + ".ogg");

            String link = Audios.get(i).absUrl("src");
            //Attempt at Downloading Image
            try {
                Response resultAudio = Jsoup.connect(link).ignoreContentType(true).timeout(60000).execute();
                FileOutputStream out = new FileOutputStream(audioFile);
                out.write(resultAudio.bodyAsBytes());
                out.close();

            //Exception Handlers
            } catch (HttpStatusException h) {
                System.out.println("Cannot access: " + link);
                System.out.println(h.getMessage());
            } catch (MalformedURLException u) {
                System.out.println("Incorrect URL: " + link);
                System.out.println(u.getMessage());
            } catch (IOException e) {
                System.out.println("Error: Cannot Write to file.");
                e.printStackTrace();
            }
        }
        System.out.println("Finished Writing Audio Files");
    }


    public static void saveVideosToFolder(Elements Videos) {
        File folder = new File("videos");

        //Creates Empty Folder
        if (folder.exists() == false) {
            System.out.println("Creating videos Folder...");
            folder.mkdir();
        }

        int next = getNextInteger("video", ".webm");

        for (int i=0; i<Videos.size(); i++, next++) {
            File videoFile = new File(folder, "video" + next + ".webm");

            String link = Videos.get(i).absUrl("src");
            //Attempt at Downloading Image
            try {
                Response resultVideo = Jsoup.connect(link).ignoreContentType(true).timeout(60000).execute();
                FileOutputStream out = new FileOutputStream(videoFile);
                out.write(resultVideo.bodyAsBytes());
                out.close();

            //Exception Handlers
            } catch (HttpStatusException h) {
                System.out.println("Cannot access: " + link);
                System.out.println(h.getMessage());
            } catch (MalformedURLException u) {
                System.out.println("Incorrect URL: " + link);
                System.out.println(u.getMessage());
            } catch (IOException e) {
                System.out.println("Error: Cannot Write to file.");
                e.printStackTrace();
            }
        }
        System.out.println("Finished Writing Videos");
    }



    public static String getDate() {
        String date;
        Calendar c = Calendar.getInstance();
        date = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH);
        return date;
    }

    public static int getNextInteger(String type, String fileType) {
        int result = -1;

        for (int i=0; true; i++) {
            File f = new File(type + "s/" + type + + i + fileType);
            if (f.isFile() == true) {
                continue;
            } else {
                result = i;
                break;
            }
        }

        if (result == 0) {
            System.out.println("Starting from scratch...");
        } else {System.out.println("Starting from Last number = " + (result-1));}

        return result;
    }
}
