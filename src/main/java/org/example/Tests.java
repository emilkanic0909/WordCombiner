package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
    @Test
    public void TestKonfigInitializerSimple1() throws IOException {
        String command="ААА !БББ! ПЕРВЫЙ";
        List<String> commands=new ArrayList<>();
        commands.add(command);
        KonfigInitializer konfigInitializer=new KonfigInitializer(commands);
        List<Command> commandList =konfigInitializer.evaluateKfgToRightFormKass();
        Object commandObject=commandList.get(0);
        assertTrue(commandObject instanceof OneWordSentence);
        OneWordSentence commandoAfterAssertion= (OneWordSentence) commandList.get(0);
        Wort a=commandoAfterAssertion.getAndList().get(0);
        assertEquals("ААА",commandoAfterAssertion.getGoal());
        assertEquals("БББ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());
        assertEquals(1,commandoAfterAssertion.getAndList().size());
        assertEquals(0,commandoAfterAssertion.getOrList().size());
        assertEquals(additionalParameter.First,commandoAfterAssertion.getParameter());


    }
}
