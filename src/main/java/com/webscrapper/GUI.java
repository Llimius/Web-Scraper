package com.webscrapper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;


public class GUI extends JFrame {

    static JButton runButton;
    static JButton logButton;
    static JButton clearButton;

    static JCheckBox[] options;
    static JTextArea siteTextArea;
    static JTextArea logWindow;



    public static void runScrapper() {
        System.out.println("Running...");

        String raw = siteTextArea.getText();
        String[] Sites = Writer.filterSites(raw);
        
        for (int i=0; i<Sites.length; i++) {
            Site s = new Site(Sites[i]);
            if (options[0].isSelected()) {
                Writer.WriteTextToFile(s.getText(), s.url, "Output.txt");
            }
            if (options[1].isSelected()) {
                Writer.savePicturesToFolder(s.getImages());
            }
            if (options[2].isSelected()) {
                Writer.saveAudiosToFolder(s.getAudios());
            }
        }
        System.out.println("Finished...");
    }

    public static void extractLog() {
        Writer.WriteTextToFile(logWindow.getText(), "", "Log.txt");
    }

    public static void clearLog() {
        logWindow.setText("");
    }

    public static void displayWindow() {
        JFrame frame = new JFrame("Web Scraper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        JMenu m1 = new JMenu("Help");
        JMenuItem info = new JMenuItem("About");

        menuBar.add(m1);
        m1.add(info);

        JPanel checkBoxes = new JPanel();
        options = new JCheckBox[3];
        options[0] = new JCheckBox("Get Text", true);
        options[1] = new JCheckBox("Get Images", true);
        options[2] = new JCheckBox("Get Audio", false);   


        checkBoxes.setLayout(new BoxLayout(checkBoxes, 3));
        checkBoxes.add(Box.createVerticalStrut(20));
        checkBoxes.add(options[0]);
        checkBoxes.add(options[1]);
        checkBoxes.add(options[2]);

        JPanel p = new JPanel();
        JLabel label = new JLabel("Sites (Seperate by commas)");

        siteTextArea = new JTextArea(6, 40);
        siteTextArea.setEditable(true);
        siteTextArea.setLineWrap(true);
        siteTextArea.setWrapStyleWord(true);
        siteTextArea.setDragEnabled(true);

        JScrollPane tasp = new JScrollPane(siteTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tasp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        tasp.setMinimumSize(new Dimension(200,500));

        runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runScrapper();
            }
        } );

        logButton = new JButton("Extract Log");
        logButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                extractLog();
            }
        } );

        clearButton = new JButton(" Clear Log  ");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearLog();
            }
        } );


        p.add(label);
        p.add(tasp);
        p.add(runButton);

        logWindow = new JTextArea(20, 10);
        logWindow.append("Log Starts Here.\n");
        logWindow.setEditable(false);
        logWindow.setLineWrap(true);
        logWindow.setWrapStyleWord(true);
        logWindow.setDragEnabled(true);

        JScrollPane sp = new JScrollPane(logWindow, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, 3));
        buttonsPanel.add(Box.createHorizontalStrut(0));
        buttonsPanel.add(logButton);
        buttonsPanel.add(clearButton);

        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.WEST, checkBoxes);
        frame.getContentPane().add(BorderLayout.CENTER, p);
        frame.getContentPane().add(BorderLayout.SOUTH, sp);
        frame.getContentPane().add(BorderLayout.EAST, buttonsPanel);

        frame.setVisible(true);
    }
}
