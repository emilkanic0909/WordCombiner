package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class DoubleWordCommand implements Command{
    private String goal;
    private List<Wort> orListFirstWord;
    private List<Wort> orListSecondWord;
    private additionalParameter parameter;
    public DoubleWordCommand(){
        goal="";
        orListFirstWord=new ArrayList<>();
        orListSecondWord=new ArrayList<>();
        parameter=additionalParameter.First;
    }

    public abstract void evalCommand(String [] Paragraphs, String [] resultParagraphs) ;
    public void setFirstOrList(List<Wort> orListFirstWord) {
        this.orListFirstWord = orListFirstWord;
    }
    public void setSecondOrList(List<Wort> orListSecondWord) {
        this.orListSecondWord = orListSecondWord;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setParameter(additionalParameter parameter) {
        this.parameter = parameter;
    }

    public additionalParameter getParameter() {
        return parameter;
    }

    public List<Wort> getFirstOrList() {
        return orListFirstWord;
    }
    public List<Wort> getSecondOrList() {
        return orListSecondWord;
    }

    public String getGoal() {
        return goal;
    }
}
