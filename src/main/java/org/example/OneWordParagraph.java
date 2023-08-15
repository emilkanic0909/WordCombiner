package org.example;

import java.util.List;

public class OneWordParagraph extends OneWordCommand{
    //TODO
    public OneWordParagraph(String goal, List<Wort> andlist, List<Wort> orList, additionalParameter parameter){
        super();
        setGoal(goal);
        setAndList(andlist);
        setOrList(orList);
        setParameter(parameter);
    }


    @Override
    public void evalCommand(String [] Paragraphs, String [] resultParagraphs) {

    }
}
