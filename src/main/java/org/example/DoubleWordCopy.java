package org.example;

import java.util.List;

public class DoubleWordCopy extends DoubleWordCommand{
    //TODO
    public DoubleWordCopy(String goal, List<Wort> orList){
    super();
    setOrList(orList);
    setGoal(goal);
    }

    @Override
    public void evalCommand(String [] Paragraphs, String [] resultParagraphs) {

    }
}
