package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FrontEnd extends JFrame {
    private File kassa=null;
    private File apell=null;
    private File konf=null;
    private File template=null;
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
    Container container;
    public FrontEnd(){
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
        container=this.getContentPane();
        this.setBounds(100,100,600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ContainerSet();
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

}
