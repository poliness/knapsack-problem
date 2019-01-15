package sample;

import java.util.ArrayList;
import java.util.Collections;

public class GreedyAlgorithm {

    private int currentWeight = 0;
    private double finalValue = 0.0;

    public void Knapsack(int maxCapacity, ArrayList<Item> items, int itemsAmount)
    {
        Item[] temp = new Item[itemsAmount];

        Collections.sort(items);

        //wypisanie

        for(Item i: items){
            System.out.println(i.getWeight());
        }

        //zapisanie w odwrotnej kolejności
        for(int j = items.size() - 1, k = 0; j >= 0; j--, k++)
            temp[k] = items.get(j);

        for(int i = 0; i < itemsAmount; i++)
        {
            if(currentWeight + temp[i].getWeight() <= maxCapacity)
            {
                currentWeight += temp[i].getWeight();
                finalValue += temp[i].getValue();
                System.out.println("Wybieram indeks gdzie waga: " + temp[i].getWeight());
            }
            else
            {
                //int capacityRemain = maxCapacity - currentWeight;
                //finalValue += temp[i].getValue() * ((double)capacityRemain / temp[i].getWeight());
                break;
            }
        }
    }

    public double getFinalValue(){return finalValue;}

    public int getCurrentWeight(){
        return currentWeight;
    }
}
