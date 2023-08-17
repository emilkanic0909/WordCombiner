package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
    List<String> commands;
    @BeforeEach
    public void initial(){
        commands=new ArrayList<>();
    }
    @Test
    public void TestKonfigInitializerOneWordKassSimple1() throws IOException {
        System.out.println("Test ! Sentence Command");
        String command="ААА !БББ! ПЕРВЫЙ";
        commands.add(command);
        KonfigInitializer konfigInitializer=new KonfigInitializer(commands);
        List<Command> commandList =konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof OneWordSentence);
        OneWordSentence commandoAfterAssertion= (OneWordSentence) commandList.get(0);
        Wort a=commandoAfterAssertion.getOrList().get(0);
        assertEquals("ААА",commandoAfterAssertion.getGoal());
        assertEquals("БББ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());
        assertEquals(0,commandoAfterAssertion.getAndList().size());
        assertEquals(1,commandoAfterAssertion.getOrList().size());
        assertEquals(additionalParameter.First,commandoAfterAssertion.getParameter());
    }
    @Test
    public void TestKonfigInitializerOneWordKassSimple2() throws IOException {
        String command="ААА #БББ# ПЕРВЫЙ";
        System.out.println("Test # Paragraph Command");
        commands.add(command);
        KonfigInitializer konfigInitializer=new KonfigInitializer(commands);
        List<Command> commandList =konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof OneWordParagraph);
        OneWordParagraph commandoAfterAssertion= (OneWordParagraph) commandList.get(0);
        Wort a=commandoAfterAssertion.getOrList().get(0);
        assertEquals("ААА",commandoAfterAssertion.getGoal());
        assertEquals("БББ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());
        assertEquals(0,commandoAfterAssertion.getAndList().size());
        assertEquals(1,commandoAfterAssertion.getOrList().size());
        assertEquals(additionalParameter.First,commandoAfterAssertion.getParameter());
    }
    @Test
    public void TestKonfigInitializerOneWordKassHard1() throws IOException{
        System.out.println("Test multiply OR commands");
        String command="ААА !БББ*ИЛИ*ССС*ИЛИ*ДДД*ИЛИ*ЕЕЕЕЕЕ! ПЕРВЫЙ";
        commands.add(command);
        KonfigInitializer konfigInitializer=new KonfigInitializer(commands);
        List<Command> commandList =konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof OneWordSentence);
        OneWordSentence commandoAfterAssertion= (OneWordSentence) commandList.get(0);
        assertEquals("ААА",commandoAfterAssertion.getGoal());

        Wort a=commandoAfterAssertion.getOrList().get(0);
        assertEquals("БББ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(1);
        assertEquals("ССС",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(2);
        assertEquals("ДДД",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(3);
        assertEquals("ЕЕЕЕЕЕ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        assertEquals(0,commandoAfterAssertion.getAndList().size());
        assertEquals(4,commandoAfterAssertion.getOrList().size());
        assertEquals(additionalParameter.First,commandoAfterAssertion.getParameter());
    }
    @Test
    public void TestKonfigInitializerOneWordKassHard2() throws IOException{
        System.out.println("Test multiply OR with case (in-)sensitive commands");
        String command="ААА !БББ*ИЛИ*(ССС)*ИЛИ*ДДД*ИЛИ*(ЕЕЕЕЕЕ)! ПЕРВЫЙ";
        commands.add(command);
        KonfigInitializer konfigInitializer=new KonfigInitializer(commands);
        List<Command> commandList =konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof OneWordSentence);
        OneWordSentence commandoAfterAssertion= (OneWordSentence) commandList.get(0);
        assertEquals("ААА",commandoAfterAssertion.getGoal());

        Wort a=commandoAfterAssertion.getOrList().get(0);
        assertEquals("БББ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(1);
        assertEquals("ССС",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(2);
        assertEquals("ДДД",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(3);
        assertEquals("ЕЕЕЕЕЕ",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        assertEquals(0,commandoAfterAssertion.getAndList().size());
        assertEquals(4,commandoAfterAssertion.getOrList().size());
        assertEquals(additionalParameter.First,commandoAfterAssertion.getParameter());
    }
    @Test
    public void TestKonfigInitializerOneWordKassHard3() throws IOException{
        System.out.println("Test multiply OR with case (in-)sensitive without additionalParameter commands");
        String command="ААА !БББ*ИЛИ*(ССС)*ИЛИ*ДДД*ИЛИ*(ЕЕЕЕЕЕ)!";
        commands.add(command);
        KonfigInitializer konfigInitializer=new KonfigInitializer(commands);
        List<Command> commandList =konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof OneWordSentence);
        OneWordSentence commandoAfterAssertion= (OneWordSentence) commandList.get(0);
        assertEquals("ААА",commandoAfterAssertion.getGoal());

        Wort a=commandoAfterAssertion.getOrList().get(0);
        assertEquals("БББ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(1);
        assertEquals("ССС",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(2);
        assertEquals("ДДД",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(3);
        assertEquals("ЕЕЕЕЕЕ",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        assertEquals(0,commandoAfterAssertion.getAndList().size());
        assertEquals(4,commandoAfterAssertion.getOrList().size());
        assertEquals(additionalParameter.First,commandoAfterAssertion.getParameter());
    }
    @Test
    public void TestKonfigInitializerOneWordKassHard4() throws IOException{
        System.out.println("Test multiply OR with case (in-)sensitive with Last additionalParameter commands");
        String command="ААА !БББ*ИЛИ*(ССС)*ИЛИ*ДДД*ИЛИ*(ЕЕЕЕЕЕ)! ПОСЛЕДНИЙ";
        commands.add(command);
        KonfigInitializer konfigInitializer=new KonfigInitializer(commands);
        List<Command> commandList =konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof OneWordSentence);
        OneWordSentence commandoAfterAssertion= (OneWordSentence) commandList.get(0);
        assertEquals("ААА",commandoAfterAssertion.getGoal());

        Wort a=commandoAfterAssertion.getOrList().get(0);
        assertEquals("БББ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(1);
        assertEquals("ССС",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(2);
        assertEquals("ДДД",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(3);
        assertEquals("ЕЕЕЕЕЕ",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        assertEquals(0,commandoAfterAssertion.getAndList().size());
        assertEquals(4,commandoAfterAssertion.getOrList().size());
        assertEquals(additionalParameter.Last,commandoAfterAssertion.getParameter());
    }
    @Test
    public void TestKonfigInitializerOneWordKassHard5() throws IOException{
        System.out.println("Test multiply OR with case (in-)sensitive with Number additionalParameter commands");
        String command="ААА !БББ*ИЛИ*(ССС)*ИЛИ*ДДД*ИЛИ*(ЕЕЕЕЕЕ)! 13";
        commands.add(command);
        KonfigInitializer konfigInitializer=new KonfigInitializer(commands);
        List<Command> commandList =konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof OneWordSentence);
        OneWordSentence commandoAfterAssertion= (OneWordSentence) commandList.get(0);
        assertEquals("ААА",commandoAfterAssertion.getGoal());

        Wort a=commandoAfterAssertion.getOrList().get(0);
        assertEquals("БББ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(1);
        assertEquals("ССС",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(2);
        assertEquals("ДДД",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(3);
        assertEquals("ЕЕЕЕЕЕ",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        assertEquals(0,commandoAfterAssertion.getAndList().size());
        assertEquals(4,commandoAfterAssertion.getOrList().size());
        assertEquals(additionalParameter.Order,commandoAfterAssertion.getParameter());
        assertEquals(13,commandoAfterAssertion.getParameter().getNumber());
    }
    @Test
    public void TestKonfigInitializerOneWordApellHard1() throws IOException{
        System.out.println("Test multiply OR with case (in-)sensitive with Number additionalParameter commands Apell");
        String command="ААА !БББ*ИЛИ*(ССС)*ИЛИ*ДДД*ИЛИ*(ЕЕЕЕЕЕ)! 13";
        commands.add("apellopred:");
        commands.add(command);
        KonfigInitializer konfigInitializer=new KonfigInitializer(commands);
        List<Command> commandList =konfigInitializer.evaluateKfgToRightFormApell();
        assertTrue(commandList.get(0) instanceof OneWordSentence);
        OneWordSentence commandoAfterAssertion= (OneWordSentence) commandList.get(0);
        assertEquals("ААА",commandoAfterAssertion.getGoal());

        Wort a=commandoAfterAssertion.getOrList().get(0);
        assertEquals("БББ",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(1);
        assertEquals("ССС",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(2);
        assertEquals("ДДД",a.getWord());
        assertEquals(true,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        a=commandoAfterAssertion.getOrList().get(3);
        assertEquals("ЕЕЕЕЕЕ",a.getWord());
        assertEquals(false,a.isCaseSensitive());
        assertEquals(true,a.isIncluded());

        assertEquals(0,commandoAfterAssertion.getAndList().size());
        assertEquals(4,commandoAfterAssertion.getOrList().size());
        assertEquals(additionalParameter.Order,commandoAfterAssertion.getParameter());
        assertEquals(13,commandoAfterAssertion.getParameter().getNumber());
    }
    @Test
    public void TestKonfigInitializerDoubleWordKassSimple1() throws IOException {
        System.out.println("Test DoubleWord simple");
        String command = "ААА @БББ@ @ССС@";
        commands.add(command);
        KonfigInitializer konfigInitializer = new KonfigInitializer(commands);
        List<Command> commandList = konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof DoubleWordCopy);
        DoubleWordCopy commandoAfterAssertion = (DoubleWordCopy) commandList.get(0);
        assertEquals("ААА", commandoAfterAssertion.getGoal());
        assertEquals(1,commandoAfterAssertion.getFirstOrList().size());
        assertEquals(1,commandoAfterAssertion.getSecondOrList().size());
        assertEquals("БББ",commandoAfterAssertion.getFirstOrList().get(0).getWord());
        assertEquals("ССС",commandoAfterAssertion.getSecondOrList().get(0).getWord());
        assertEquals(true,commandoAfterAssertion.getFirstOrList().get(0).isIncluded());
        assertEquals(true,commandoAfterAssertion.getSecondOrList().get(0).isIncluded());
        assertEquals(true,commandoAfterAssertion.getFirstOrList().get(0).isCaseSensitive());
        assertEquals(true,commandoAfterAssertion.getSecondOrList().get(0).isCaseSensitive());
        assertEquals(additionalParameter.First,commandoAfterAssertion.getParameter());
    }
    @Test
    public void TestKonfigInitializerDoubleWordKassSimple2() throws IOException {
        System.out.println("Test DoubleWord simple with CaseSencivity or included");
        String command = "ААА @(БББ)@ @/ССС/@";
        commands.add(command);
        KonfigInitializer konfigInitializer = new KonfigInitializer(commands);
        List<Command> commandList = konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof DoubleWordCopy);
        DoubleWordCopy commandoAfterAssertion = (DoubleWordCopy) commandList.get(0);
        assertEquals("ААА", commandoAfterAssertion.getGoal());
        assertEquals(1,commandoAfterAssertion.getFirstOrList().size());
        assertEquals(1,commandoAfterAssertion.getSecondOrList().size());
        assertEquals("БББ",commandoAfterAssertion.getFirstOrList().get(0).getWord());
        assertEquals("ССС",commandoAfterAssertion.getSecondOrList().get(0).getWord());
        assertEquals(true,commandoAfterAssertion.getFirstOrList().get(0).isIncluded());
        assertEquals(false,commandoAfterAssertion.getSecondOrList().get(0).isIncluded());
        assertEquals(false,commandoAfterAssertion.getFirstOrList().get(0).isCaseSensitive());
        assertEquals(true,commandoAfterAssertion.getSecondOrList().get(0).isCaseSensitive());
        assertEquals(additionalParameter.First,commandoAfterAssertion.getParameter());
    }
    @Test
    public void TestKonfigInitializerDoubleWordKassHard1() throws IOException {
        System.out.println("Test DoubleWord simple with CaseSencivity or included komlexerer");
        String command = "ААА @/(БББ)/@ @(/ССС/)@";
        commands.add(command);
        KonfigInitializer konfigInitializer = new KonfigInitializer(commands);
        List<Command> commandList = konfigInitializer.evaluateKfgToRightFormKass();
        assertTrue(commandList.get(0) instanceof DoubleWordCopy);
        DoubleWordCopy commandoAfterAssertion = (DoubleWordCopy) commandList.get(0);
        assertEquals("ААА", commandoAfterAssertion.getGoal());
        assertEquals(1,commandoAfterAssertion.getFirstOrList().size());
        assertEquals(1,commandoAfterAssertion.getSecondOrList().size());
        assertEquals("БББ",commandoAfterAssertion.getFirstOrList().get(0).getWord());
        assertEquals("ССС",commandoAfterAssertion.getSecondOrList().get(0).getWord());
        assertEquals(false,commandoAfterAssertion.getFirstOrList().get(0).isIncluded());
        assertEquals(false,commandoAfterAssertion.getSecondOrList().get(0).isIncluded());
        assertEquals(false,commandoAfterAssertion.getFirstOrList().get(0).isCaseSensitive());
        assertEquals(false,commandoAfterAssertion.getSecondOrList().get(0).isCaseSensitive());
        assertEquals(additionalParameter.First,commandoAfterAssertion.getParameter());
    }
    @Test
    public void TestKonfigInitializerDoubleWordKassApellHard2() throws IOException {
        System.out.println("Test DoubleWord simple with CaseSencivity or included komlexerer with Apell ");
        String command = "ААА @/(БББ)/@ @(/ССС/)@ ПОСЛЕДНИЙ";
        commands.add(command);
        command = "ДДД @/е/*ИЛИ*(про)*ИЛИ*(/ЛОХ/)*ИЛИ*/(ВАУ)/@ @/(УУУ/)*ИЛИ*ВВВ@ ВСЕ";
        commands.add(command);
        command="apellopred:";
        commands.add(command);
        command="ППП @/ИИИ/*ИЛИ*(ЫЫЫ)*ИЛИ*(/ЦЦЦ/)@ @/(АУЕ)/*ИЛИ*(/ЖЖЖ/)*ИЛИ*ЭЭЭ@ 123";
        KonfigInitializer konfigInitializer = new KonfigInitializer(commands);
        List<Command> commandList = konfigInitializer.evaluateKfgToRightFormKass();
        commandList.addAll(konfigInitializer.evaluateKfgToRightFormApell());
        assertTrue(commandList.get(0) instanceof DoubleWordCopy);
        assertTrue(commandList.get(1) instanceof DoubleWordCopy);
        assertTrue(commandList.get(2) instanceof DoubleWordCopy);
        DoubleWordCopy commandoAfterAssertion0 = (DoubleWordCopy) commandList.get(0);
        DoubleWordCopy commandoAfterAssertion1 = (DoubleWordCopy) commandList.get(1);
        DoubleWordCopy commandoAfterAssertion2 = (DoubleWordCopy) commandList.get(2);
        String goal0="ААА";
        String goal1="ДДД";
        String goal2="ППП";
        assertEquals(goal0,commandoAfterAssertion0.getGoal());
        assertEquals(goal1,commandoAfterAssertion1.getGoal());
        assertEquals(goal2,commandoAfterAssertion2.getGoal());

        assertEquals(new Wort("БББ",false,false),commandoAfterAssertion0.getFirstOrList().get(0));
    }
}
