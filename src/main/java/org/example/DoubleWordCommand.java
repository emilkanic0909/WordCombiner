package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class DoubleWordCommand implements Command{
    private String goal;
    private List<Wort> orList;
    public DoubleWordCommand(){
        orList=new ArrayList<>();
    }

    public void setOrList(List<Wort> orList) {
        this.orList = orList;
    }

    public abstract void evalCommand(String [] Paragraphs) ;
}
