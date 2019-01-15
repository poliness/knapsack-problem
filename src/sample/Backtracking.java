package sample;

import java.util.ArrayList;

public class Backtracking {

    private Integer solutionValue = 0;
    private Integer solutionWeight = 0;

    private ArrayList<Integer> solutionArray = new ArrayList<>();
    private ArrayList<Integer> tempArray = new ArrayList<>(); //pomocniczna tablica przechowująca przedmioty, których waga jest mniejsza lub równa niż pojemność plecaka
    private boolean isSolution;



    public Backtracking(){}


    public void Knapsack(int i, Integer maxCapacity, Integer totalValue, Integer totalWeight, ArrayList<Item> items)
    {
        //System.out.print(i + "\n");
        for(int k = i; k < items.size(); ++k)
        {
            //System.out.println("k = " + k + " przy i = " + i);

            if(maxCapacity > 0)
            {
                if((items.get(k)).getWeight() <= maxCapacity)
                {
                    tempArray.add(k);

                    if(totalValue + items.get(k).getValue() >= solutionValue)
                    {
                        System.out.println("k = " + k + " solutionValue = " + solutionValue + " dodaje Item value: " + items.get(k).getValue());

                        solutionValue = totalValue + items.get(k).getValue();
                        isSolution = true;

                        solutionWeight = totalWeight + items.get(k).getWeight();



                        for(int a = 0; a < solutionArray.size(); a++){
                            System.out.println(items.get(solutionArray.get(a)).getValue() + " ");
                        }

                    }


                }

                if((k + 1) < items.size())
                {
                    Knapsack(k + 1, maxCapacity - items.get(k).getWeight(), totalValue + items.get(k).getValue(), totalWeight + items.get(k).getWeight(), items);
                }
                else
                {
                    if(isSolution)
                    {
                        if (!solutionArray.isEmpty())
                            solutionArray.clear();

                        for (int j = tempArray.size() - 1; j >= 0; j--){
                            solutionArray.add(tempArray.get(j));
                            //System.out.print(items.get(tempArray.get(j)).getWeight() + " ");

                        }
                        System.out.println();

                        tempArray.clear();
                        isSolution = false;



                    }
                    else
                    {
                        tempArray.clear();
                    }



                    return;
                }
            }


        }





    }












    public Integer getSolutionValue(){return solutionValue;}

    public Integer getSolutionWeight() {
        return solutionWeight;
    }
}
