package main.java.transfer;

import main.java.warehouse.Box;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

//Грузовик наследуется от ArrayList (перевозит коробки удачи, которые так же наследованы от ArrayList)
@Component
public class Truck extends ArrayList<Box> {
    private final int MAX_AMOUNT = 10;
//Проверяем на заполненность
    public boolean isFull(){
        return size()==MAX_AMOUNT;
    }

    public int getMAX_AMOUNT() {
        return MAX_AMOUNT;
    }
}
