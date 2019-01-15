package sample;

import java.util.ArrayList;

public class DynamicProgramming {

    private int finalWeight = 0;

    public int Knapsack(int maxCapacity, ArrayList<Item> items, int itemsAmount)
    {
        int [][] helpArray = new int[itemsAmount+1][maxCapacity+1];

        for(int i = 0; i <= itemsAmount; i++)
        {
            for(int j = 0; j <= maxCapacity; j++)
            {
                //jeżeli waga całkowita plecka wynosi 0 to wartość w plecaku również zawsze będzie 0
                if(i == 0 || j == 0)
                    helpArray[i][j] = 0;
                else if(items.get(i-1).getWeight() <= j)
                {
                    int temp = items.get(i - 1).getWeight();



                    helpArray[i][j] = max(items.get(i-1).getValue() + helpArray[i-1][j-(temp)], helpArray[i-1][j]);
                }
                else
                    helpArray[i][j] = helpArray[i-1][j];
            }
        }


        for(int i = 0; i < itemsAmount + 1; i++){
            for(int j = 0; j < maxCapacity + 1; j++){
                System.out.print(helpArray[i][j] + " ");
            }
            System.out.println();
        }


        return helpArray[itemsAmount][maxCapacity];
    }

    private int max(int x, int y)
    {
        return (x > y) ? x : y;
    }

    public int GetFinalWeight(){
        return finalWeight;
    }
}
