package sample;

import java.util.ArrayList;

public class BruteForce {

    private int bestValue;
    private int bestPosition;
    private long permutations;
    private int finalWeight = 0;



    ArrayList<Item> list = new ArrayList<>();

    public void Knapsack(int maxCapacity, ArrayList<Item> items, int itemsAmount)
    {
        bestValue = 0;
        bestPosition = 0;

        permutations = (long)Math.pow(2,itemsAmount);

        for(int i = 0; i < permutations; i++)
        {

            int totalValue = 0;
            int totalWeight = 0;

            for(int j = 0; j < itemsAmount; j++)
            {
                if(((i>>j)&1) != 1)
                    continue;

                totalValue += items.get(j).getValue();
                totalWeight += items.get(j).getWeight();
            }

            if(totalWeight <= maxCapacity && totalValue > bestValue)
            {
                bestPosition = i;
                bestValue = totalValue;
            }


        }



        for(int j = 0; j < itemsAmount; j++)
        {
            if(((bestPosition>>j) & 1) == 1)
            {
                list.add(items.get(j));
                finalWeight += items.get(j).getWeight();
            }
        }
    }


    public int getBestValue(){return bestValue;}

    public int getBestPosition(){return bestPosition;}

    void getList(){

        for (sample.Item item: list) {
            System.out.print(item.getValue() + " ");
        }
    }

    public int getTotalWeight(){
        return finalWeight;
    }

}
