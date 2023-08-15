package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KonfigInitializer {
    private BufferedReader reader;
    private File konfig=null;
    private List<Command> commandListKass;
    private List<Command> commandListApell;
    private List<String> stringsToParse;
    public KonfigInitializer(File konfig) throws IOException {
        this.konfig=konfig;
        this.commandListKass=new ArrayList<>();
        this.commandListApell=new ArrayList<>();
        reader=new BufferedReader(new FileReader(konfig, StandardCharsets.UTF_8));
        stringsToParse=reader.lines().toList();
    }
    public KonfigInitializer(List<String> stringsToParse) throws IOException {//FOR TESTS
        this.commandListKass=new ArrayList<>();
        this.commandListApell=new ArrayList<>();
        this.stringsToParse=stringsToParse;
    }
    public List<Command> evaluateKfgToRightFormKass() throws IOException {
        int endElem=0;
        for(int i=0;i<stringsToParse.size();i++){
            endElem++;
            if(stringsToParse.get(i).equals("apellopred:")){
                break;
            }
        }
        for(int i=0;i<endElem;i++){
            additionalParameter additionalParameter;
            String curString=stringsToParse.get(i);
            String goal=curString.substring(0,curString.indexOf(" "));
            curString=curString.substring(curString.indexOf(" ")+1);
            if(curString.charAt(0)=='!'||curString.charAt(0)=='#'){ //evaluate a OneWordCommand
                boolean sentence=true;
                if(curString.charAt(0)=='!')sentence=true;
                else sentence=false;//Paragraph
                //OneWord Instruction
                int lastindex=-1;
                if(curString.lastIndexOf('!')!=-1)lastindex=curString.lastIndexOf('!');
                else lastindex=curString.lastIndexOf('#');
                if(lastindex!=curString.length()-1){
                    String thirdParameter=curString.substring(lastindex+2,curString.length());
                    switch (thirdParameter){
                        case "ПЕРВЫЙ":
                            additionalParameter= org.example.additionalParameter.First;
                            break;
                        case "ПОСЛЕДНИЙ":
                            additionalParameter= org.example.additionalParameter.Last;
                            break;
                        case "ВСЕ":
                            additionalParameter= org.example.additionalParameter.All;
                            break;
                        default:
                            int thirdParameterInt=Integer.parseInt(thirdParameter);
                            additionalParameter= org.example.additionalParameter.Order;
                            additionalParameter.setNumber(thirdParameterInt);
                            break;
                    }
                } else additionalParameter= org.example.additionalParameter.First;

                //standard in andList
                List<Wort> orList=new ArrayList<>();
                List<Wort> andList=new ArrayList<>();
                boolean or=false;
                String secondParameter=curString.substring(1,lastindex);
                String arguments[];
                if(secondParameter.contains("*ИЛИ*")){
                    or=true;
                arguments=secondParameter.split("[*]ИЛИ[*]");
                }
                else if(secondParameter.contains("*И*")){
                    or=false;
                    arguments=secondParameter.split("[*]И[*]");
                } else {
                    arguments=new String[1];
                    arguments[0]=secondParameter;
                }
                for(int j=0;j<arguments.length;j++){
                    Wort a;
                    if(arguments[j].contains("(")) {
                        arguments[j]=arguments[j].substring(1,arguments[j].length()-1);
                        a=new Wort(arguments[j],true,false);
                    } else {
                        a=new Wort(arguments[j],true,true);
                    }
                    if(or)orList.add(a);
                    else andList.add(a);
                }
                if(sentence)commandListKass.add(new OneWordSentence(goal,andList,orList,additionalParameter));
                else commandListKass.add(new OneWordParagraph(goal,andList,orList,additionalParameter));
            }
        }
   return commandListKass;
    }
    public List<Command> evaluateKfgToRightFormApell() throws IOException {

        return commandListKass;
    }
    public List<Command> getCommandListKass() {

        return commandListKass;
    }

    public List<Command> getCommandListApell() {

        return commandListApell;
    }
    public void closeReader() throws IOException {
        reader.close();
    }
}
