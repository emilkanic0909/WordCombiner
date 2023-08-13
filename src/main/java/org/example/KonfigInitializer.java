package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KonfigInitializer {
    File konfig=null;
    public KonfigInitializer(File konfig){
        this.konfig=konfig;
    }
    public void evaluateKfgToRightForm(){
        List<Command> commandList=new ArrayList<>();
         commandList.add(new OneWordCommand());
         commandList.add(new DoubleWordCommand());
    }
}
