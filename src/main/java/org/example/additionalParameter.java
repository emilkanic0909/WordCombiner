package org.example;

import java.util.Optional;

public enum additionalParameter {
    First (1),
    Last (-1),
    Order(0),
    All(Integer.MAX_VALUE);
    private int number;
    additionalParameter(int number) {
        this.number = number;
    }
    public void setNumber(int number){
        if(this.equals(Order)){
            this.number=number;
        }
    }

    public int getNumber() {
        return number;
    }
}
