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
            //Finden a Teiler
            if(stringsToParse.get(i).equals("apellopred:")){
                break;
            } else endElem++;
        }
    evaluateCommandList(0,endElem,true);
   return commandListKass;
    }
    public List<Command> evaluateKfgToRightFormApell() throws IOException {
        int endElem=0;
        for(int i=0;i<stringsToParse.size();i++){
//Teiler
            if(stringsToParse.get(i).equals("apellopred:")){
                break;
            } else endElem++;
        }
        evaluateCommandList(endElem+1,stringsToParse.size(),false);
        return commandListApell;
    }
    public void evaluateCommandList(int start,int end,boolean kass){
        for(int i=start;i<end;i++){
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
                            //Erste
                            additionalParameter= org.example.additionalParameter.First;
                            break;
                        case "ПОСЛЕДНИЙ":
                            //Letzte
                            additionalParameter= org.example.additionalParameter.Last;
                            break;
                        case "ВСЕ":
                            //All
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
                boolean or=true;
                String secondParameter=curString.substring(1,lastindex);
                String arguments[];

                if(secondParameter.contains("*ИЛИ*")){ //ODER
                    or=true;
                    arguments=secondParameter.split("[*]ИЛИ[*]");
                }
                else if(secondParameter.contains("*И*")){ //UND
                    or=false;
                    arguments=secondParameter.split("[*]И[*]");
                } else {
                    arguments=new String[1];
                    arguments[0]=secondParameter;
                }
                for(int j=0;j<arguments.length;j++){
                    Wort a;
                    if(arguments[j].contains("(")) { //pruefen ob case in- sensitive
                        arguments[j]=arguments[j].substring(1,arguments[j].length()-1);
                        a=new Wort(arguments[j],true,false);
                    } else {
                        a=new Wort(arguments[j],true,true);
                    }
                    if(or)orList.add(a);
                    else andList.add(a);
                }
                if(kass) {
                    if (sentence) commandListKass.add(new OneWordSentence(goal, andList, orList, additionalParameter));
                    else commandListKass.add(new OneWordParagraph(goal, andList, orList, additionalParameter));
                } else {
                    if (sentence) commandListApell.add(new OneWordSentence(goal, andList, orList, additionalParameter));
                    else commandListApell.add(new OneWordParagraph(goal, andList, orList, additionalParameter));
                }
            }
            else {
                //it is DoubleWordCopy
                String firstWord=curString.substring(1,curString.indexOf('@',1));
                curString=curString.substring(curString.indexOf('@',1)+2);
                String secondWord=curString.substring(1,curString.indexOf('@',1));
                curString=curString.substring(curString.indexOf('@',1)+1);
                if(curString.length()>0&& curString.charAt(0)==' ')curString=curString.substring(1);
                switch (curString){
                    case "":
                        additionalParameter= org.example.additionalParameter.First;
                    break;
                    case "ПЕРВЫЙ":
                        //Erste
                        additionalParameter= org.example.additionalParameter.First;
                        break;
                    case "ПОСЛЕДНИЙ":
                        //Letzte
                        additionalParameter= org.example.additionalParameter.Last;
                        break;
                    case "ВСЕ":
                        //ALL
                        additionalParameter= org.example.additionalParameter.All;
                        break;
                    case " ПЕРВЫЙ":
                        //Erste
                        additionalParameter= org.example.additionalParameter.First;
                        break;
                    case " ПОСЛЕДНИЙ":
                        //Letzte
                        additionalParameter= org.example.additionalParameter.Last;
                        break;
                    case " ВСЕ":
                        //All
                        additionalParameter= org.example.additionalParameter.All;
                        break;
                    default:
                        additionalParameter= org.example.additionalParameter.Order;
                        additionalParameter.setNumber(Integer.parseInt(curString));
                        break;
                }

                List<Wort> orListFirst=new ArrayList<>();
                List<Wort> orListSecond=new ArrayList<>();
                String[]firstArguments;
                String[]secondArguments;
                if(firstWord.contains("*ИЛИ*")){//ODER

                    firstArguments=firstWord.split("[*]ИЛИ[*]");
                } else {
                    firstArguments=new String[1];
                    firstArguments[0]=firstWord;
                }
                //Second
                if(secondWord.contains("*ИЛИ*")){//ODER
                    secondArguments=secondWord.split("[*]ИЛИ[*]");
                } else {
                    secondArguments=new String[1];
                    secondArguments[0]=secondWord;
                }
                //Cycle for First
                for(int j=0;j<firstArguments.length;j++){
                    boolean included=true;
                    boolean caseSensitive=true;
                    if(firstArguments[j].contains("(")){//case in- sensitive
                        caseSensitive=false;
                        firstArguments[j]=firstArguments[j].replace("(","");
                        firstArguments[j]=firstArguments[j].replace(")","");
                    }
                    if(firstArguments[j].contains("/")){//included oder nicht
                        included=false;
                        firstArguments[j]=firstArguments[j].replaceAll("/","");
                    }
                    Wort a=new Wort(firstArguments[j],included,caseSensitive);
                   orListFirst.add(a);

                }

                //Cycle for Second
                for(int j=0;j<secondArguments.length;j++){
                    boolean included=true;
                    boolean caseSensitive=true;
                    if(secondArguments[j].contains("(")){//case in- sensitive
                        caseSensitive=false;
                        secondArguments[j]= secondArguments[j].replace("(","");
                        secondArguments[j]= secondArguments[j].replace(")","");
                    }
                    if( secondArguments[j].contains("/")){// included oder nicht
                        included=false;
                        secondArguments[j]= secondArguments[j].replaceAll("/","");
                    }
                    Wort a=new Wort( secondArguments[j],included,caseSensitive);
                    orListSecond.add(a);

                }

                DoubleWordCopy command=new DoubleWordCopy(goal,orListFirst,orListSecond,additionalParameter);
                if(kass){
                    commandListKass.add(command);
                } else {
                    commandListApell.add(command);
                }
            }
        }
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
