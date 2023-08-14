package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class OneWordCommand implements Command {
    private String goal;
    private List<Wort> andList;
    private List<Wort> orList;
    public OneWordCommand(){
    andList=new ArrayList<>();
    orList=new ArrayList<>();
    }

    public void setAndList(List<Wort> andList) {
        this.andList = andList;
    }

    public void setOrList(List<Wort> orList) {
        this.orList = orList;
    }

    public abstract void evalCommand(String [] Paragraphs);
}
