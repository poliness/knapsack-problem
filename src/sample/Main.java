package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;








import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;



public class Main extends Application{

    private long startTime, endTime;
    private float duration;

    private Input input = new Input("Test5.txt");

    private int capacityK = 165;

    //private int i = 20; // ile razy wykonany, do obliczenia sredniego czasu wykonania algorytmu
    private int i = 100;

    private float sum = 0;
    private float average = 0;

    private VBox vbox = new VBox();

    private HBox hbox = new HBox();
    private HBox hbox1 = new HBox();

    private Label algName = new Label();
    private ArrayList<Label> weights = new ArrayList<>();
    private ArrayList<Label> values = new ArrayList<>();
    private Label maxCap = new Label();
    private Label averageTime = new Label();



    private ScrollPane scrollPane = new ScrollPane(vbox);

    private Scene scene = new Scene(scrollPane,870,200);

    private PrintWriter writer;





    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, UnsupportedEncodingException {

        //STAGE


        MyScene scena = new MyScene();

        primaryStage.setTitle("Knapsack Problem Application");

        primaryStage.setScene(scena.getS1());

        primaryStage.show();



        //primaryStage.setTitle("Knapsack Problem");

        //primaryStage.setScene(scene);

        //primaryStage.show();



        //TESTY
        //ALGORITHMS

        //BACKTRACKING

        /*
        Backtracking bck = new Backtracking();

        writer = new PrintWriter("Backtracking.txt", "UTF-8");

        while(i > 0) {
            startTime = System.nanoTime();
            bck.Knapsack(0, capacityK, 0, input.getItemsList());
            endTime = System.nanoTime();
            duration = (float) (endTime - startTime) / 1000000f; //value in ms
            writer.println(duration);
            if(i > 80)
            {}
            else {
                sum += duration;
                //System.out.println("Backtracking solution: " + bck.getSolutionValue());
                System.out.println(i + ". Time of Backtracking: " + duration + " ms");
            }


            i--;
        }
        writer.close();
        average = sum/80;
        System.out.println("Average = " + average);

        testWindow("Backtracking Algorithm", bck.getSolutionValue(),sum);
        */

        /*algName.setText("Backtracking Algorithm");
        algName.setPadding(new Insets(3,0,0,0));
        Label n1 = new Label("Waga: \t");
        hbox.getChildren().add(n1);
        for(int j = 0; j < input.getItemsList().size(); j++) {
            Label temp = new Label(input.getItemsList().get(j).getWeight() + "\t");
            weights.add(temp);
            hbox.getChildren().add(weights.get(j));
        }
        Label n2 = new Label("Wartość: \t");
        hbox1.getChildren().add(n2);
        for(int j = 0; j < input.getItemsList().size(); j++) {
            Label temp = new Label(input.getItemsList().get(j).getValue() + "\t");
            values.add(temp);
            hbox1.getChildren().add(values.get(j));
        }

        Label zestawDanych = new Label("Zestaw danych:");
        zestawDanych.setPadding(new Insets(1,0,5,0));

        maxCap.setText("Pojemność plecaka wynosi: \t" + capacityK);
        maxCap.setPadding(new Insets(5,0,0,0));


        algName.setPadding(new Insets(10,0,5,0));

        Label knapsack = new Label("Maksymalna wartość plecaka wynosi: \t" + bck.getSolutionValue());
        knapsack.setPadding(new Insets(0,0,5,0));

        averageTime.setText("Średni czas działania algorytmu wynosi: " + average + " ms");



        vbox.getChildren().addAll(zestawDanych,hbox,hbox1,maxCap,algName,knapsack,averageTime);
        */


        //BRUTE FORCE

        /*
        BruteForce brt = new BruteForce();
        //input = new Input("A.txt");
        while(i > 0){
            startTime = System.nanoTime();
            brt.Knapsack(capacityK, input.getItemsList(), input.getItemsList().size());
            endTime = System.nanoTime();
            duration = (float) (endTime - startTime) / 1000000f; //value in ms
            if(i > 80){}
                else{
                sum += duration;
                System.out.println(i + ". Time of Brute Force: " + duration + " ms");
                //System.out.println("Brute force solution: " + brt.getBestValue() + " " + brt.getBestPosition());
            }

            i--;
        }

        average = sum/80;
        System.out.println("Average = " + average);

        testWindow("Brute Force Algorithm", brt.getBestValue(),sum);
        brt.getList();
        */


        //DYNAMIC PROGRAMMING

        /*
        DynamicProgramming dp = new DynamicProgramming();
        while (i > 0){
            startTime = System.nanoTime();
            dp.Knapsack(capacityK, input.getItemsList(), input.getItemsList().size());
            endTime = System.nanoTime();
            duration = (float) (endTime - startTime) / 1000000f; //value in ms
            if( i > 80 ){}
            else{
                sum += duration;
                System.out.println(i + ". Time of Dynamic Programming: " + duration + " ms");
            }

            i--;
        }

        average = sum/80;
        System.out.println("Average = " + average);

        testWindow("Dynamic Programming", dp.Knapsack(capacityK, input.getItemsList(), input.getItemsList().size()),sum);
        */

        //BRANCH AND BOUND

        /*
        BranchAndBound bab = new BranchAndBound();
        while (i > 0){
            startTime = System.nanoTime();
            bab.Knapsack(capacityK,input.getItemsList(),input.getItemsList().size());
            endTime = System.nanoTime();
            duration = (float) (endTime - startTime) / 1000000f; //value in ms
            if (i > 80) {
            }
            else{
                sum += duration;
                System.out.println(i + ". Time of Branch and Bound: " + duration + " ms");
            }

            i--;
        }

        average = sum/80;
        System.out.println("Average = " + average);

        testWindow("Branch and Bound Algorithm", bab.Knapsack(capacityK, input.getItemsList(), input.getItemsList().size()),sum);
        */


        //GREEDY

        /*
        GreedyAlgorithm grd = new GreedyAlgorithm();
        while (i > 0){
            startTime = System.nanoTime();
            grd.Knapsack(capacityK,input.getItemsList(),input.getItemsList().size());
            endTime = System.nanoTime();
            duration = (float) (endTime - startTime) / 1000000f; //value in ms
            if( i > 80){}
            else{
                sum += duration;
                System.out.println(i + ". Time of Greedy: " + duration + " ms");
            }

            i--;
        }

        average = sum/80;
        System.out.println("Average = " + average);

        //wywołuje się raz
        //grd.Knapsack(capacityK,input.getItemsList(),input.getItemsList().size());
        float res = (float)grd.getFinalValue();

        testWindow("Greedy Algorithm", res,sum);
        */

        /*
        //Platform.exit();
        */
    }


    private void testWindow(String algnm, float sol, float time){

        average = time/80;

        algName.setText(algnm);
        algName.setPadding(new Insets(3,0,0,0));
        Label n1 = new Label("Waga: \t");
        hbox.getChildren().add(n1);
        for(int j = 0; j < input.getItemsList().size(); j++) {
            Label temp = new Label(input.getItemsList().get(j).getWeight() + "\t");
            weights.add(temp);
            hbox.getChildren().add(weights.get(j));
        }
        Label n2 = new Label("Wartość: \t");
        hbox1.getChildren().add(n2);
        for(int j = 0; j < input.getItemsList().size(); j++) {
            Label temp = new Label(input.getItemsList().get(j).getValue() + "\t");
            values.add(temp);
            hbox1.getChildren().add(values.get(j));
        }

        Label zestawDanych = new Label("Zestaw danych:");
        zestawDanych.setPadding(new Insets(1,0,5,0));

        maxCap.setText("Pojemność plecaka wynosi: \t" + capacityK);
        maxCap.setPadding(new Insets(5,0,0,0));


        algName.setPadding(new Insets(10,0,5,0));

        Label knapsack = new Label("Maksymalna wartość plecaka wynosi: \t" + sol);
        knapsack.setPadding(new Insets(0,0,5,0));

        averageTime.setText("Średni czas działania algorytmu wynosi: " + average + " ms");



        vbox.getChildren().addAll(zestawDanych,hbox,hbox1,maxCap,algName,knapsack,averageTime);











    }







}
