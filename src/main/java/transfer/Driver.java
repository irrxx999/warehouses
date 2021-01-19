package main.java.transfer;

import main.java.warehouse.Box;
import main.java.warehouse.PieceLuck;
import main.java.warehouse.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("driver")
public class Driver {
    private Warehouse warehouseFrom;
    private Warehouse warehouseTo;
    private Truck truck;
    private PieceLuck pieceLuck;
    private Box box;
    private int transferCount=0;

    @Autowired
    public Driver(@Qualifier("warehouseA") Warehouse warehouseFrom,@Qualifier("warehouseB") Warehouse warehouseTo, Truck truck) {
        this.warehouseFrom = warehouseFrom;
        this.warehouseTo = warehouseTo;
        this.truck = truck;
    }
    //перевозим
    public void processWarehouse(){
        //основной метод - перевезти все со склада From на склад To
        while(! warehouseFrom.isEmpty()){
            collectTruck();
            transfer();
        }
        //если грузовик не пустой - довезем то что погрузили в последний заход
        if(truck.size()>0){
            transfer();
        }
    }
    //заполняем коробки
    public Box collectBox(){
        //берем новую коробку
        Box box = new Box();
        //берем со склада куски и кладем в коробку
        while(!box.isFull()){
            //если пусто - выходим
            if(warehouseFrom.size()>0) {
                PieceLuck pieceLuck = warehouseFrom.get(warehouseFrom.size() - 1);
                warehouseFrom.remove(pieceLuck);
                box.add(pieceLuck);
            }else{
                break;
            }
        }
        return box;
    }
    //заполняем грузовик
    public void collectTruck(){
        while(!truck.isFull()){
            Box box = collectBox();
            truck.add(box);
            System.out.println("Зaгружено "+truck.size()+"/"+truck.getMAX_AMOUNT()+" коробок");
            if(warehouseFrom.size()==0){
                break;
            }
        }
    }

    public void transfer(){
        for(Box box:truck){
            warehouseTo.addAll(box);
        }
        truck.clear();
        transferCount++;
        System.out.println("Поездка "+transferCount);
    }


    public Warehouse getWarehouseFrom() {
        return warehouseFrom;
    }

    public Warehouse getWarehouseTo() {
        return warehouseTo;
    }

    public Truck getTruck() {
        return truck;
    }

    public PieceLuck getPieceLuck() {
        return pieceLuck;
    }

    public void setPieceLuck(PieceLuck pieceLuck) {
        this.pieceLuck = pieceLuck;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }


}
