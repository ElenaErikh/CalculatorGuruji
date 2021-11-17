package com.example.calculatorguruji;

public enum Symbols {

    sum('+'), sub('-'), mul('×'), div('÷'), plusMin('±');

    private final char symbol;

    Symbols(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }


}
