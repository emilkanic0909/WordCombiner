package org.example;

import java.io.File;

public class BackEnd {
    private File kassa;
    private File apell;
    private File template;
    private File konfig;
    private File output;
    public BackEnd(File kassa, File apell, File template, File konfig, File output){
       this.kassa=kassa;
       this.apell=apell;
       this.template=template;
       this.konfig=konfig;
       this.output=output;
    }
    public void start(){

    }
    public void konfigInitialize(File konfig){

    }
}
