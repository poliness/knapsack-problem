package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BranchAndBound {

    private int totalWeight = 0;

    public class Node
    {
        int upperBound; //górna granica
        int level; //poziom drzewa decyzyjnego (indeks tablicy)
        int value; //value z węzłów od korzenia do obecnego węzła
        float weight;
    }

    //działa jak greedy, szuka górnej granicy max value
    private int Bound(Node node, int itemsAmount, int maxCapacity, ArrayList<Item> items)
    {
        //jeżeli waga przekracza pojemność plecaka to zwróć 0 zgodnie z oczekiwaniami - upperBound to granica
        if(node.weight >= maxCapacity)
            return 0;

        //inicjalizacja granicy profitu profitem obecnym
        int valueBound = node.value;

        //zacznij dodawać elementy od elementu z indeksem 1 do bieżacego
        int j = node.level + 1;
        float totalWeight = node.weight;

        //wszystkie elementy kolekcji + kontrola wagi plecaka
        while((j < itemsAmount) && (totalWeight + items.get(j).getWeight() <= maxCapacity))
        {
            totalWeight += items.get(j).getWeight();
            valueBound += items.get(j).getValue();
            j++;
        }

        //jeżeli ostatni element sie nie mieści to dodaj jego część, właściwość zachłanna
        if(j < itemsAmount)
        {
            valueBound += (maxCapacity - totalWeight) * items.get(j).getValue() / items.get(j).getWeight();
        }

        return valueBound;
    }

    public int Knapsack(int maxCapacity, ArrayList<Item> items, int itemsAmount)
    {
        //sortowanie wg ratio wartość/waga (kolejność rosnąca)
        Collections.sort(items);
        //for (Item item: items) {
          //System.out.print(item.getValue() + " ");
        //}
        //odwrócenie żeby była kolejność malejąca
        Collections.reverse(items);

        //lista do przechodzenia przez węzeł
        LinkedList<Node> list = new LinkedList<>();
        Node uNode = new Node();
        Node vNode = new Node();

        //sztuczny węzeł
        uNode.level = -1;
        uNode.weight = 0;
        uNode.value = (int)uNode.weight;
        list.add(uNode);

        int totalValue = 0;


        //pętla jeden po drugim wyodrębnia element z drzewa decyzyjnego i oblicza value wszystkich dzieci
        //wyodrębnionego elementu i wartość tę zapisuje w zmiennej totalValue
        while(!list.isEmpty())
        {
            //peek bierze pierwszy element z glowy listy ale go nie usuwa
            uNode = list.peek();
            //usuwa element z listy (zaczynajac od glowy)
            list.pop();

            //jeżeli jest to węzęł początkowy (sztuczny) to oznacz poziom 0
            if(uNode.level == -1)
                uNode.level = 0;

            //jeżeli nic nie ma na następnym poziomie to pomiń iterację
            if(uNode.level == itemsAmount - 1)
                continue;

            //jeżeli to nie jest ostatni poziom to zwiększ poziom
            vNode.level = uNode.level + 1;
            //oblicz zysk węzłów-dzieci

            vNode.weight = uNode.weight + items.get(vNode.level).getWeight();
            vNode.value = uNode.value + items.get(vNode.level).getValue();


            //jeżeli łączna waga jest mniejsza niż pojemność plecaka, a zysk jest większy niż poprzedni to update zysk
            if(vNode.weight <= maxCapacity && vNode.value > totalValue){
                totalValue = vNode.value;
                totalWeight = (int)vNode.weight;
            }



            //obliczenie górnej granicy zysku w celu podjęcia decyzji czy dodać węzeł v do listy czy też nie
            vNode.upperBound = Bound(vNode,itemsAmount,maxCapacity,items);

            //jeżeli wartość wiązana (suma wartości węzłów) jes większa niż totalValue dodaj węzeł do kolejki, aby
            //kontynuować
            if(vNode.upperBound > totalValue)
                list.push(vNode);

            //wykonaj to samo, ale bez dodawania przedmiotu do plecaka
            vNode.weight = uNode.weight;
            vNode.value = uNode.value;
            vNode.upperBound = Bound(vNode,itemsAmount,maxCapacity,items);

            if(vNode.upperBound > totalValue)
                list.push(vNode);
        }

        return totalValue;
    }


    public int GetTotalWeight(){
        return totalWeight;
    }

}
