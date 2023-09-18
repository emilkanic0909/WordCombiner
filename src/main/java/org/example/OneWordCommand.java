package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.ArrayList;
import java.util.List;

public abstract class OneWordCommand implements Command {
    private String goal;
    private List<Wort> andList;
    private List<Wort> orList;
    private additionalParameter parameter;
    public OneWordCommand(){
        goal="";
    andList=new ArrayList<>();
    orList=new ArrayList<>();
    }
    public abstract void evalCommand(String [] Paragraphs, XWPFDocument template);
    public void setAndList(List<Wort> andList) {
        this.andList = andList;
    }

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

    public List<Wort> getAndList() {
        return andList;
    }

    public List<Wort> getOrList() {
        return orList;
    }

    public String getGoal() {
        return goal;
    }
}
