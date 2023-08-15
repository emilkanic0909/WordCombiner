package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class DoubleWordCommand implements Command{
    private String goal;
    private List<Wort> orList;
    private additionalParameter parameter;
    public DoubleWordCommand(){
        goal="";
        orList=new ArrayList<>();
    }

    public abstract void evalCommand(String [] Paragraphs, String [] resultParagraphs) ;
    public void setOrList(List<Wort> orList) {
        this.orList = orList;
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

    public List<Wort> getOrList() {
        return orList;
    }

    public String getGoal() {
        return goal;
    }
}
