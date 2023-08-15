package org.example;

public class Wort {
    private String word="";
    private boolean included=true;
    private boolean caseSensitive=false;
    public Wort(String word,boolean included,boolean caseSensitive){
        this.word=word;
        this.included=included;
        this.caseSensitive=caseSensitive;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public boolean isIncluded() {
        return included;
    }

    public String getWord() {
        return word;
    }
}
