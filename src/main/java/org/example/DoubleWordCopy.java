package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.List;

public class DoubleWordCopy extends DoubleWordCommand{

    public DoubleWordCopy(String goal, List<Wort> firstOrList, List<Wort> secondOrList,additionalParameter parameter){
    super();
    setFirstOrList(firstOrList);
    setSecondOrList(secondOrList);
    setGoal(goal);
    setParameter(parameter);
    }

    @Override
    public void evalCommand(String [] Paragraphs, XWPFDocument template) {
//TODO
    }
}
