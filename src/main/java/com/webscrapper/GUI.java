package com.webscrapper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
    static JCheckBox[] options;
    static JTextField siteTextField;



    public static void runScrapper() {
        System.out.println("Running...");

        String raw = siteTextField.getText();
        String[] Sites = Writer.filterSites(raw);
        
        for (int i=0; i<Sites.length; i++) {
            Site s = new Site(Sites[i]);
            if (options[0].isSelected()) {
                Writer.WriteTextToFile(s.getText(), s.url);
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
        siteTextField = new JTextField(60);
        
        runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runScrapper();
            }
        } );
        
        p.add(label);
        p.add(siteTextField);
        p.add(runButton);

        JTextArea ta = new JTextArea(20, 10);
        ta.append("Log Starts Here.\n");
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setDragEnabled(true);
        ta.setMaximumSize(new Dimension(20, 20));

        JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        

        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.WEST, checkBoxes);
        frame.getContentPane().add(BorderLayout.CENTER, p);
        frame.getContentPane().add(BorderLayout.SOUTH, sp);

        frame.setVisible(true);
    }
}
