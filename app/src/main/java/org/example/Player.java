package org.example;

package model;

public interface Player {
    char getSymbol();
    int getMove(Board board);
    String getName();
}
