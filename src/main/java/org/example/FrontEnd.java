package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FrontEnd extends JFrame {
    private File kassa=null;
    private File apell=null;
    private File konf=null;
    private File template=null;
    private File homeDirectory=null;
    private JButton kassaButton;
    private JButton apellButton;
    private JButton konfigButton;
    private JButton templateButton;
    private JButton startButton;
    private JLabel kassaLabel;
    private JLabel apellLabel;
    private JLabel konfigLabel;
    private JLabel templateLabel;
    private JCheckBox paint;
    private JFileChooser fileChooser;
    private FileNameExtensionFilter filterDocX;
    private FileNameExtensionFilter filterTxt;
    Container container;
    public FrontEnd(){
        homeDirectory= FileSystemView.getFileSystemView().getHomeDirectory();
        kassaButton=new JButton("Кассационное");
        apellButton=new JButton("Апеляционное");
        konfigButton=new JButton("Конфиг");
        templateButton=new JButton("Шаблон");
        startButton=new JButton("Старт");
        kassaLabel=new JLabel("Касса Путь: ");
        apellLabel=new JLabel("Апел Путь: ");
        konfigLabel=new JLabel("Конфиг Путь: ");
        templateLabel=new JLabel("Шаблон Путь: ");
        paint=new JCheckBox("Красить",false);
        fileChooser=new JFileChooser();
        fileChooser.setCurrentDirectory(homeDirectory);
        filterDocX=new FileNameExtensionFilter("Word","docx","doc");
        filterTxt=new FileNameExtensionFilter("Text","txt");
        container=this.getContentPane();
        this.setBounds(100,100,600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ContainerSet();
        addActionListener();
    }
    private void ContainerSet(){
        container.setLayout( new GridLayout(10,1,3,3));
        container.add(kassaButton);
        container.add(apellButton);
        container.add(konfigButton);
        container.add(templateButton);
        container.add(kassaLabel);
        container.add(apellLabel);
        container.add(templateLabel);
        container.add(konfigLabel);
        container.add(paint);
        container.add(startButton);
    }
    public void addActionListener(){
        kassaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setFileFilter(filterDocX);
                int responce=fileChooser.showOpenDialog(null);
                if(responce==JFileChooser.APPROVE_OPTION){
                    kassa=new File(fileChooser.getSelectedFile().getAbsoluteFile().toURI());
                    kassaLabel.setText("Касса Путь: "+kassa.getAbsolutePath());
                }
            }
        });
        apellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setFileFilter(filterDocX);
                int responce=fileChooser.showOpenDialog(null);
                if(responce==JFileChooser.APPROVE_OPTION){
                    apell=new File(fileChooser.getSelectedFile().getAbsoluteFile().toURI());
                    apellLabel.setText("Апел Путь: "+apell.getAbsolutePath());
                }
            }
        });
        templateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setFileFilter(filterDocX);
                int responce=fileChooser.showOpenDialog(null);
                if(responce==JFileChooser.APPROVE_OPTION){
                    template=new File(fileChooser.getSelectedFile().getAbsoluteFile().toURI());
                    templateLabel.setText("Шаблон Путь: "+template.getAbsolutePath());
                }
            }
        });
        konfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setFileFilter(filterTxt);
                int responce=fileChooser.showOpenDialog(null);
                if(responce==JFileChooser.APPROVE_OPTION){
                    konf=new File(fileChooser.getSelectedFile().getAbsoluteFile().toURI());
                    konfigLabel.setText("Конфиг Путь: "+konf.getAbsolutePath());
                }
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kassa==null||apell==null||konf==null||template==null){
                    JOptionPane.showMessageDialog(null,"Один из файлов не выбран","output",JOptionPane.PLAIN_MESSAGE);
                } else {
                    fileChooser.setFileFilter(filterDocX);
                    int responce= fileChooser.showSaveDialog(null);
                    if(responce==JFileChooser.APPROVE_OPTION){
                        File output=new File(fileChooser.getSelectedFile().getAbsoluteFile().toURI()+"docx");
                        BackEnd backEnd=null;
                        try {
                            backEnd=new BackEnd(kassa,apell,template,konf,output);
                            backEnd.start();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null,"Один из файлов выбран неверно","output",JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
            }
        });
    }
}
