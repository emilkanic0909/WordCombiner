package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Command {
    public default Map<Integer, List<Integer>> findWort(Wort wort, String [] paragraphs){
        //return a indedexes of Wort in each Paragraph OR sentence
        Map<Integer,List<Integer>> mapOfIndexes=new HashMap<>();
        String word;
        String newParagraphs[];
        if(wort.isCaseSensitive()){
         word=wort.getWord();
         newParagraphs=paragraphs;
        } else {
            word= wort.getWord().toLowerCase();
            newParagraphs=new String[paragraphs.length];
            for(int i=0;i< paragraphs.length;i++)newParagraphs[i]=paragraphs[i].toLowerCase();
        }
        for(int i=0;i< newParagraphs.length;i++){
            int startindex=0;
            int curindex=newParagraphs[i].indexOf(word,startindex);
            startindex++;
            if(curindex!=-1)mapOfIndexes.put(i,new ArrayList<>());
            while(curindex!=-1){
               if(wort.isIncluded()) mapOfIndexes.get(i).add(curindex);
               else mapOfIndexes.get(i).get(curindex+word.length()+1);
                curindex=newParagraphs[i].indexOf(word,startindex);
            }
        }
        return mapOfIndexes;
    }
    public void evalCommand(String [] Paragraphs, XWPFDocument template);
}
