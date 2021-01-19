package main.java.warehouse;

import java.util.ArrayList;

//Коробки с удачей наследуем от ArrayList
public class Box extends ArrayList<PieceLuck> {
    private final int MAX_AMOUNT = 4;

    public boolean isFull(){
        return size()==MAX_AMOUNT;
    }
}
