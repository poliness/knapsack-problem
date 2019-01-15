package sample;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MyScene {

    private Scene s1;

    private ArrayList<TextField> wtxf = new ArrayList<>();
    private ArrayList<TextField> vtxf = new ArrayList<>();

    private ArrayList<Button> algorithmsButtons;

    private int n = 20;

    private int weight, value;

    private Integer[] weights = new Integer[n];
    private Integer[] values = new Integer[n];


    private ArrayList<Item> items = new ArrayList<>();
    private int knapsackCapacity;

    private long startTime, endTime;
    private float duration;

    private int i = 1;
    private float sum = 0;
    private float average = 0;




    private Label resultLabel = new Label();

    public MyScene(){
        this.scena1();
    }

    private void scena1(){

        int x, y;

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setGridLinesVisible(false); //help

        ScrollPane scrollPane = new ScrollPane(grid);


        //Weight in column 1, row 0
        Label lw = new Label("Weight:");
        lw.fontProperty().set(Font.font("Arial", FontWeight.BOLD, 12));
        lw.setPadding(new Insets(0,0,0,85));
        grid.add(lw,0,0);

        //Value in column 2, row 0
        Label lv = new Label("Value:");
        lv.fontProperty().set(Font.font("Arial", FontWeight.BOLD, 12));
        lv.setPadding(new Insets(0,0,0,92));
        grid.add(lv,0,1);



        //Weights TextFields in column 0, rows 1 - 10
        for(int i = 0; i < n; i++){
            TextField wt = new TextField();
            wt.setPrefWidth(40);
            wtxf.add(wt);
        }
        x = 0;
        y = 1;
        for (TextField it: wtxf) {
            grid.add(it, y++, x);
        }


        //Values TextFields in column 1, rows 1 - 10
        for(int i = 0; i < n; i++){
            TextField vt = new TextField();
            vt.setPrefWidth(40);
            vtxf.add(vt);
        }
        x = 1;
        y = 1;
        for (TextField it: vtxf) {
            grid.add(it, y++, x);
        }

        //Knapsack capacity Label and TextField
        Label capacityLabel = new Label("Capacity of Knapsack:");
        capacityLabel.setPrefWidth(130);
        capacityLabel.fontProperty().set(Font.font("Arial", FontWeight.BOLD, 12));
        grid.add(capacityLabel, 0,2);

        TextField capacityText = new TextField();
        capacityText.setPrefWidth(90);
        grid.add(capacityText, 1,2,2,1);


        //Clear Button
        Button clearButton = new Button("Clear");
        clearButton.setPrefWidth(90);
        grid.add(clearButton, 1,3,2,1);

        clearButton.setOnAction(event -> {

            disableAlgBut();

            for(TextField it: wtxf)
                it.clear();

            for(TextField it: vtxf)
                it.clear();

            capacityText.clear();





        });

        //Random Button
        Button randomButton = new Button("Random");
        randomButton.setPrefWidth(90);
        grid.add(randomButton, 3,3,2,1);

        Random random = new Random();

        randomButton.setOnAction(event -> {

            disableAlgBut();

            for(TextField it: wtxf)
                it.setText(Integer.toString(random.nextInt(100) + 1));

            for(TextField it: vtxf)
                it.setText(Integer.toString(random.nextInt(100) + 1));

            capacityText.setText(Integer.toString(random.nextInt(100) + 1));


        });



        //Apply Button
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(90);
        grid.add(applyButton, 5, 3,2,1);

        applyButton.setOnAction(event -> {


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wszystkie pola muszą zostać wypełnione!");

            for(TextField it: wtxf){
                if(it.getText().isEmpty()) {
                    alert.show();
                    return;
                }
            }

            for(TextField it: vtxf){
                if(it.getText().isEmpty()) {
                    alert.show();
                    return;
                }
            }

            if(capacityText.getText().isEmpty()){
                alert.show();
                return;
            }

            for(int i = 0; i < n; i++){
                weight = Integer.parseInt(wtxf.get(i).getText());
                weights[i] = weight;
                System.out.println("W"+i);
            }

            for(int i = 0; i < n; i++){
                value = Integer.parseInt(vtxf.get(i).getText());
                values[i] = value;
                System.out.println("V"+i);
            }

            knapsackCapacity = Integer.parseInt(capacityText.getText());

            enableAlgBut();

            createItems(weights,values);
        });

        //Button Input From File
        Button inputFromFileButton = new Button("From File");
        inputFromFileButton.setPrefWidth(90);
        grid.add(inputFromFileButton, 7,3,2,1);

        inputFromFileButton.setOnAction(event -> {

            showFileInputScreen();


        });






        //Algorithms Buttons
        Button la1 = new Button("Backtracking Algorithm");
        Button la2 = new Button("Branch and Bound Algorithm");
        Button la3 = new Button("Brute Force Algorithm");
        Button la4 = new Button("Dynamic Programming");
        Button la5 = new Button("Greedy Algorithm");

        algorithmsButtons = new ArrayList<>(Arrays.asList(la1,la2,la3,la4,la5));

        x = 4;
        y = 2;
        for (Button it: algorithmsButtons) {
            it.setPrefWidth(190);
            it.setDisable(true);
            grid.add(it, y, x++,5,1);


        }

        //Backtracking Button
        la1.setOnAction(event -> {
            disableAlgBut();

            Backtracking bck = new Backtracking();

            while(i > 0)
            {
                startTime = System.nanoTime();
                bck.Knapsack(0,this.knapsackCapacity,0, 0, this.items);
                endTime = System.nanoTime();
                duration = (float)(endTime - startTime)/1000000f; //value in ms
                if(i > 80){}
                else{
                    sum += duration;
                }
                i--;
            }

            average = sum/80;

            //bck.printElementy(items);

            //System.out.println("Backtracking solution: " + bck.getSolutionValue());
            System.out.println("The average execution time of the Backtracking: " + average + " ms\n");
            System.out.println("The waga " + bck.getSolutionWeight());
            resultLabel.setText("Backtracking Algorithm\n\n" +
                    "The maximum value of the knapsack is: \t\t\t\t" + bck.getSolutionValue() + "\nThe average execution time of the algorithm is: \t\t" + average + " ms\n");
            enableAlgBut();
        });

        //Branch and Bound Button
        la2.setOnAction(event -> {
            disableAlgBut();

            BranchAndBound bab = new BranchAndBound();

            int result = 0;

            while(i > 0)
            {
                startTime = System.nanoTime();
                result = bab.Knapsack(this.knapsackCapacity, this.items,this.items.size());
                endTime = System.nanoTime();
                duration = (float)(endTime - startTime)/1000000f; //value in ms
                if(i > 80){}
                else{
                    sum += duration;
                }
                i--;
            }

            average = sum/80;

            System.out.println("Branch and Bound solution: " + result);
            System.out.println("The average execution time of Branch and Bound: " + average + " ms\n");

            System.out.println("The waga " + bab.GetTotalWeight());

            resultLabel.setText("Branch and Bound Algorithm\n\n" +
                    "The maximum value of the knapsack is: \t\t\t\t" + result + "\nThe average execution time of the algorithm is: \t\t" + average + " ms\n");
            enableAlgBut();
        });

        //Brute Force Button
        la3.setOnAction(event -> {
            disableAlgBut();

            BruteForce brt = new BruteForce();

            while(i > 0)
            {
                startTime = System.nanoTime();
                brt.Knapsack(this.knapsackCapacity, this.items, this.items.size());
                endTime = System.nanoTime();
                duration = (float)(endTime - startTime)/1000000f; //value in ms
                if(i > 80){}
                else{
                    sum += duration;
                }
                i--;
            }

            average = sum/80;

            System.out.println("Brute Force solution: " + brt.getBestValue());
            System.out.println("The average execution time of Brute Force: " + average + " ms\n");

            System.out.println("The waga " + brt.getTotalWeight());

            resultLabel.setText("Brute Force Algorithm\n\n" +
                    "The maximum value of the knapsack is: \t\t\t\t" + brt.getBestValue() + "\nThe average execution time of the algorithm is: \t\t" + average + " ms\n");
            enableAlgBut();

        });

        //Dynamic Programming Button
        la4.setOnAction(event -> {
            disableAlgBut();

            DynamicProgramming dp = new DynamicProgramming();

            int result = 0;

            while(i > 0)
            {
                startTime = System.nanoTime();
                result = dp.Knapsack(this.knapsackCapacity, this.items, this.items.size());
                endTime = System.nanoTime();
                duration = (float)(endTime - startTime)/1000000f; //value in ms
                if(i > 80){}
                else{
                    sum += duration;
                }
                i--;
            }

            average = sum/80;

            System.out.println("The waga " + dp.GetFinalWeight());

            System.out.println("Dynamic Programming solution: " + result);
            System.out.println("The average execution time of Dynamic Programming: " + average + " ms\n");
            resultLabel.setText("Dynamic Programming Algorithm\n\n" +
                    "The maximum value of the knapsack is: \t\t\t\t" + result + "\nThe average execution time of the algorithm is: \t\t" + average + " ms\n" );
            enableAlgBut();
        });


        //Greedy Button
        la5.setOnAction(event -> {

            disableAlgBut();

            GreedyAlgorithm grd = new GreedyAlgorithm();

            while(i > 0)
            {
                startTime = System.nanoTime();
                grd.Knapsack(this.knapsackCapacity,this.items,this.items.size());
                endTime = System.nanoTime();
                duration = (float)(endTime - startTime)/1000000f; //value in ms
                if(i > 80){}
                else{
                    sum += duration;
                }
                i--;
            }

            average = sum/80;


            System.out.println("Greedy solution: " + grd.getFinalValue());
            System.out.println("Time of Greedy Algorithm: " + average + " ms\n");

            System.out.println("The waga " + grd.getCurrentWeight());

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(3);
            String sol = df.format((float)grd.getFinalValue());
            sol = sol.replace(',','.');
            resultLabel.setText("Greedy Algorithm\n\n" + "The maximum value of the knapsack is: \t\t\t\t" + grd.getFinalValue() + "\nThe average execution time of the algorithm is: \t\t" + average + " ms\n");
            enableAlgBut();

        });


        //Close Button
        Button finishButton = new Button("Close");
        finishButton.setPrefWidth(90);
        grid.add(finishButton,3,10,5,1);

        finishButton.setOnAction(event ->
                Platform.exit());


        //label result
        resultLabel.fontProperty().set(Font.font("Calibri", FontWeight.SEMI_BOLD, 16));
        grid.add(resultLabel,9,4,12,4);


        s1 = new Scene(scrollPane, 1155, 380);

        disableAlgBut();

    }

    private void disableAlgBut(){
        for(Button it: algorithmsButtons)
            it.setDisable(true);
    }

    private void enableAlgBut(){
        for(Button it: algorithmsButtons)
            it.setDisable(false);
    }




    private void createItems(Integer[] weights, Integer[] values){

        items.clear();

        for(int i = 0; i < weights.length; i++){
            Item item = new Item(weights[i], values[i]);
            items.add(item);
            System.out.println(i + " W: " + item.getWeight() + " V: " + item.getValue());
        }

    }


    private void showFileInputScreen(){

        Stage stage = new Stage();

        VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setSpacing(10);

        box.setAlignment(Pos.CENTER);

        Label label = new Label("Enter filename and knapsack capacity:");

        TextField textFiled = new TextField();
        textFiled.setPromptText("Enter filename");


        TextField knapsackCapacityField = new TextField();
        knapsackCapacityField.setPromptText("Enter knapsack capacity");

        Button btnOk = new Button();
        btnOk.setText("OK");

        btnOk.setOnAction(event -> {

            Input input = new Input(textFiled.getText() + ".txt");

            n = input.getItemsList().size();
            items = input.getItemsList();

            knapsackCapacity = Integer.parseInt(knapsackCapacityField.getText());

            enableAlgBut();

            stage.close();
        });

        box.getChildren().add(label);
        box.getChildren().add(textFiled);
        box.getChildren().add(knapsackCapacityField);
        box.getChildren().add(btnOk);
        Scene scene = new Scene(box,250,150);
        stage.setScene(scene);
        stage.show();
    }



    public Scene getS1() {
        return s1;
    }


}
