package sample;

public class Item implements Comparable {

    private Integer weight; //waga przedmiotu
    private Integer value; //wartość przedmiotu

    //konstruktor, przypisuje wagę oraz wartość przedmiotowi
    public Item(Integer _weight, Integer _value)
    {
        this.weight = _weight;
        this.value = _value;
    }

    //metoda porównuje stosunek wartości do wagi dwóch przedmiotów
    public int compareTo(Object object)
    {

        Item that = (Item)object;

        double r1 = (double)this.value / (double)this.weight; //stosunek wartości do wagi pierwszego przedmiotu
        //System.out.println(r1);
        double r2 = (double)that.value / (double)that.weight; //stosunek wartości do wagi drugiego przedmiotu
        //System.out.println(r2);

        if(r1 > r2)
            return 1;
        else
            return r1 < r2 ? -1 : 0;
    }

    public Integer getWeight(){return this.weight;}

    public Integer getValue(){return this.value;}
}
