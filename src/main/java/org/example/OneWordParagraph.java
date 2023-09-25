package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void evalCommand(String [] Paragraphs, XWPFDocument template) {
        List<Integer> indizes=new ArrayList<>();
        for(int i=0;i<getOrList().size();i++){
            Map<Integer,List<Integer>> map= findWort(getOrList().get(i),Paragraphs);
            indizes.addAll(map.keySet());
        }
        indizes=indizes.stream().distinct().collect(Collectors.toList());
        String result="";
        switch (getParameter()){
            case First:
                result=Paragraphs[indizes.get(0)];
                break;
            case Last:
                result=Paragraphs[indizes.get(indizes.size()-1)];
                break;
            case Order:
                result=Paragraphs[indizes.get(getParameter().getNumber())];
                break;
            case All:
                for(int i=0;i<indizes.size();i++){
                    result=result+Paragraphs[indizes.get(i)];
                }
                break;
            default:
                break;
        }
        for(int i=0;i<template.getParagraphs().size();i++){
            for(int j=0;j<template.getParagraphs().get(i).getRuns().size();j++){
                if(template.getParagraphs().get(i).getRuns().get(j).getText(0).contains(getGoal())){
                    template.getParagraphs().get(i).getRuns().get(j).getText(0).replace(getGoal(),result);
                    try {
                        break;
                    } finally {
                        break;
                    }
                }
            }
        }
        indizes=new ArrayList<>();
        //TODO ANDLIST
    }
}
