package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    private ArrayList<Item> itemsList;

    public Input(String filename)
    {
        int i = 1;

        itemsList = new ArrayList<>();

        File file = new File(filename);

        try
        {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                Item item = new Item(scanner.nextInt(),scanner.nextInt());
                itemsList.add(item);
                i++;
            }

            scanner.close();

        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }




    }

    public ArrayList<Item> getItemsList(){return itemsList;}



}
