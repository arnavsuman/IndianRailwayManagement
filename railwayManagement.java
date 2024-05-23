package com.example.demojavafx;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.*;

public class railwayManagement extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home Screen");

        Button createTrain = new Button("Schedule Train");
        createTrain.setOnAction(e -> createTrainWindow("Schedule New Train", primaryStage));
        createTrain.setFont(new Font(30));

        Button viewTrain = new Button("View Train Status");
        viewTrain.setOnAction(e -> viewTrainWindow("Current Train Status", primaryStage));
        viewTrain.setFont(new Font(30));

        Button jobSheet = new Button("Open Jobsheet");
        //jobSheet.setOnAction(e -> jobSheetWindow("View Jobsheet", primaryStage));
        jobSheet.setOnAction(e -> {
            jobSheetWindow("View Jobsheet", primaryStage);

        });

        jobSheet.setFont(new Font(30));

        Label l1 = new Label("Welcome to Indian Railways");
        l1.setFont(new Font(80));

        Label l2 = new Label("Made by Arnav Suman");
        l2.setFont(new Font(15));

        GridPane homeLayout = new GridPane();
        homeLayout.setVgap(20);
        homeLayout.setHgap(20);

        // Add buttons to GridPane
        homeLayout.add(l1, 2, 0, 5, 1); // span the label across three columns
        homeLayout.add(viewTrain, 3, 11);
        homeLayout.add(createTrain, 5, 11);
        homeLayout.add(jobSheet, 6, 11);
        homeLayout.add(l2, 0, 25);
        homeLayout.setStyle("-fx-background-color: beige;");

        Scene homeScene = new Scene(homeLayout, 1200, 800);

        primaryStage.setScene(homeScene);
        primaryStage.show();
    }
    private boolean blink = false;
    private void updateTime(Label label, Label l2, Label l3) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        label.setText("TIME: " + currentTime);
        if (blink) {
            label.setTextFill(Color.BLACK);
            l2.setTextFill(Color.RED);
            l3.setTextFill(Color.RED);
        } else {
            label.setTextFill(Color.RED);
            l2.setTextFill(Color.BLACK);
            l3.setTextFill(Color.BLACK);
        }
        blink = !blink;
    }

    private void createTrainWindow(String windowTitle, Stage primaryStage) {
        primaryStage.close(); // Close the first window

        Stage newStage = new Stage();
        newStage.setTitle(windowTitle);

        Button HomeButton = new Button("Return to Home Screen");


        HomeButton.setOnAction(e -> {
            newStage.close();
            start(new Stage());
        });
        Button createTrainButton = new Button("Schedule Train Now");

        GridPane newWindowLayout = new GridPane();
        newWindowLayout.setVgap(20);
        newWindowLayout.setHgap(20);

        newWindowLayout.add(HomeButton, 3, 15); // homebutton
        HomeButton.setFont(new Font(30));
        newWindowLayout.add(createTrainButton, 2, 15);
        createTrainButton.setFont(new Font(30));

        // Add button to GridPane
        Label l1 = new Label("Enter Train Number/Name: ");
        l1.setFont(new Font(20));
        TextField textTrainNumber = new TextField();
        textTrainNumber.setPromptText("Enter Train No...");
        TextField lname = new TextField();
        lname.setPromptText("Enter Train Name...");
        newWindowLayout.add(l1, 1, 1);
        newWindowLayout.add(textTrainNumber, 2, 1);
        newWindowLayout.add(lname, 3, 1);

        Label l2 = new Label("Enter Jobsheet ID: ");
        l2.setFont(new Font(20));
        TextField textJobSheetID = new TextField();

        Button loadJob = new Button("Load Job Sheet");

        newWindowLayout.add(l2, 1, 2);
        newWindowLayout.add(textJobSheetID, 2, 2);
        newWindowLayout.add(loadJob, 3, 2);

        Label l3 = new Label("Enter Source Station: ");
        l3.setFont(new Font(20));
        TextField textSource = new TextField();
        newWindowLayout.add(l3, 1, 3);
        newWindowLayout.add(textSource, 2, 3);

        Label l4 = new Label("Enter Destination Station: ");
        l4.setFont(new Font(20));
        TextField textDestination = new TextField();
        newWindowLayout.add(l4, 1, 4);
        newWindowLayout.add(textDestination, 2, 4);

        Label l5 = new Label("Enter Route ID: ");
        l5.setFont(new Font(20));
        TextField textRouteID = new TextField();
        newWindowLayout.add(l5, 1, 5);
        newWindowLayout.add(textRouteID, 2, 5);

        Label l6 = new Label("Enter Priority: ");
        l6.setFont(new Font(20));
        TextField textPriority = new TextField();
        newWindowLayout.add(l6, 1, 6);
        newWindowLayout.add(textPriority, 2, 6);

        Label l7 = new Label("Enter Total Load : ");
        l7.setFont(new Font(20));
        TextField textLoad = new TextField();
        ComboBox<String> dropBoxLoad = new ComboBox<>();
        dropBoxLoad.getItems().addAll("Select Type","Passenger", "Goods");
        dropBoxLoad.setValue("Select Type");
        newWindowLayout.add(l7, 1, 7);
        newWindowLayout.add(textLoad, 2, 7);
        newWindowLayout.add(dropBoxLoad, 3, 7);

        Label l8= new Label("Enter no of Wagons: ");
        l8.setFont(new Font(20));
        TextField textWagon = new TextField();
        ComboBox<String> dropBoxWagon = new ComboBox<>();
        dropBoxWagon.getItems().addAll("Select Type (Weight)","'ICF' (50T) (64PAX)", "'LHB' (39.5T) (72PAX)","'OPEN-BED' Container (80T)","Oil 'Tanker' (90T)");
        dropBoxWagon.setValue("Select Type (Weight)");
        Button loadWagon = new Button("Load Wagon ");
        newWindowLayout.add(l8, 1, 8);
        newWindowLayout.add(textWagon, 2, 8);
        newWindowLayout.add(dropBoxWagon, 3, 8);
        newWindowLayout.add(loadWagon, 4, 8);

        Label loadll= new Label("Net Load of Train: ");
        Label lnet= new Label("Here comes output of function");
        lnet.setFont(new Font(20));
        loadll.setFont(new Font(20));

        newWindowLayout.add(loadll, 1, 9);
        newWindowLayout.add(lnet, 2, 9);

        Label l9= new Label("Select Locomotive:");
        l9.setFont(new Font(20));
        ComboBox<String> dropBoxLoco = new ComboBox<>();
        dropBoxLoco.getItems().addAll("Select (Towing Capacity)","'WAG-12B' (6000T)","'WAP-7' (4500T)","'WAM-4H' (2000T)","'WDP-4' (3500T)","'WAG-9H' (1500T)");
        dropBoxLoco.setValue("Select Type (Hauling Capacity)");
        newWindowLayout.add(l9, 1, 10);
        newWindowLayout.add(dropBoxLoco, 2, 10);

        Button netCost = new Button("Calculate Cost to Railway");
        Label l10= new Label("Cost of railway is here");
        l10.setFont(new Font(20));

        newWindowLayout.add(netCost, 1, 11);
        newWindowLayout.add(l10, 2, 11);

        Label l11 = new Label("Net Freight Charge: ");
        l11.setFont(new Font(20));
        Label l12 = new Label("LOAD SQL");
        l12.setFont(new Font(20));
        newWindowLayout.add(l11, 1, 12);
        newWindowLayout.add(l12, 2, 12);

        Label l13 = new Label("Net Profit: ");
        l13.setFont(new Font(20));
        Label l14 = new Label("Net Freight charge - Cost to railway");
        l14.setFont(new Font(20));
        newWindowLayout.add(l13, 1, 13);
        newWindowLayout.add(l14, 2, 13);

        Label l1q = new Label("ETD:");
        l1q.setFont(new Font(20));
        Label l1w = new Label("LOAD SQL");
        l1w.setFont(new Font(20));
        newWindowLayout.add(l1q, 1, 14);
        newWindowLayout.add(l1w, 2, 14);

        loadJob.setOnAction(e -> {
            sql_data_loader load_job3 = new sql_data_loader();
            JOBSHEET j3 = new JOBSHEET();
            j3 = load_job3.getJobsheetByID(textJobSheetID.getText());
            textTrainNumber.setText("" + j3.TRAIN_NO);
            l1w.setText("11 April 2024, "+rand()+" PM");
            textJobSheetID.setText(j3.JOB_ID);
            textSource.setText(j3.SOURCE);
            lname.setText(j3.TRAIN_NAME);
            textDestination.setText(j3.DESTINATION);
            textRouteID.setText(j3.ROUTE_ID);
            textPriority.setText("" + j3.PRIORITY);
            textLoad.setText(j3.NET_LOAD + " TONS");
            dropBoxLoad.setValue("Goods");
            if(Objects.equals(j3.JOB_TYPE, "PASSENGER")){
                dropBoxLoad.setValue("Passenger");
                textLoad.setText("");
                textLoad.setText(j3.NET_LOAD + " PAX");
                dropBoxWagon.getItems().clear();
                dropBoxWagon.getItems().addAll("Select Type (Weight)","'ICF' (40T) (64PAX)", "'LHB' (50T) (72PAX)");
            }
            else{
                dropBoxWagon.getItems().clear();
                dropBoxWagon.getItems().addAll("Select Type (Weight)","'OPEN-BED' Container (65T)","Oil 'Tanker' (55T)", "'BOX' Container (45T)");
            }

        });
        loadWagon.setOnAction(e -> {
            sql_data_loader load_wagon = new sql_data_loader();
            RAKE r1 = new RAKE();

            String selectedRake = dropBoxWagon.getSelectionModel().getSelectedItem();
            selectedRake = load_wagon.str_return(selectedRake);
            r1 = load_wagon.getRakebyName(selectedRake);

            int rake_capacity = r1.CAPACITY;
            String requirement = textLoad.getText();
            int req;
            req = load_wagon.return_int(requirement);
            double divisionResult = (double) req / rake_capacity;
            int totalWagon = (int) Math.ceil(divisionResult);
            textWagon.setText("" + totalWagon);
            dropBoxWagon.setValue("Type (Weight)");
            int load_on_loco = totalWagon * r1.NET_WEIGHT;
            lnet.setText( load_on_loco + " TONS ");

        });
        netCost.setOnAction(e -> {
            sql_data_loader load_wagon = new sql_data_loader();

            String selectedRake = dropBoxWagon.getSelectionModel().getSelectedItem();
            selectedRake = load_wagon.str_return(selectedRake);

            String selectedloco = dropBoxLoco.getSelectionModel().getSelectedItem();
            selectedloco = load_wagon.str_return(selectedloco);

            double running_hours = load_wagon.getHoursfromJobsheet(textJobSheetID.getText());
            int freight_charge = load_wagon.getFreightChargefromJobsheet(textJobSheetID.getText());
            int loco_cost = load_wagon.getLOCOcostfromLOCOMOTIVE(selectedloco);
            int rake_cost = load_wagon.getRAKEcostfromRAKE(selectedRake);
            double net_income = running_hours * (loco_cost + rake_cost);
            int net_income_int = (int) net_income;

            l10.setText(convertToLakhs(net_income_int));//cost to railway

            l12.setText(convertToLakhs(freight_charge)); //net freight charge
            int net = freight_charge - net_income_int;
            l14.setText(convertToLakhs(net));//net profit
        });

        createTrainButton.setOnAction(e -> {
            sql_data_loader load_data = new sql_data_loader();
            TRAIN train1 = new TRAIN();

            int TRAIN_NO;
            TRAIN_NO = Integer.parseInt(textTrainNumber.getText());
            train1.TRAIN_NO=TRAIN_NO;

            String JOB_ID;
            JOB_ID = textJobSheetID.getText();
            train1.JOB_ID=JOB_ID;

            String SOURCE_STATION;
            SOURCE_STATION = textSource.getText();
            train1.SOURCE_STATION=SOURCE_STATION;

            String DESTINATION_STATION;
            DESTINATION_STATION = textDestination.getText();
            train1.DESTINATION_STATION=DESTINATION_STATION;

            int COST_TO_OPERATE;
            COST_TO_OPERATE = str_to_int(l10.getText());
            train1.COST_TO_OPERATE=COST_TO_OPERATE;

            String ENG_NAME;

            String selectedRake = dropBoxWagon.getSelectionModel().getSelectedItem();
            selectedRake = load_data.str_return(selectedRake);
            String selectedloco = dropBoxLoco.getSelectionModel().getSelectedItem();
            selectedloco = load_data.str_return(selectedloco);

            ENG_NAME = selectedloco;
            train1.ENG_NAME=ENG_NAME;

            String RAKE_TYPE;
            RAKE_TYPE = selectedRake;
            train1.RAKE_TYPE=RAKE_TYPE;

            String ROUTE_ID;
            ROUTE_ID = textRouteID.getText();
            train1.ROUTE_ID=ROUTE_ID;

            String RUNNING_STATUS;
            RUNNING_STATUS = "SCHEDULED";
            train1.RUNNING_STATUS=RUNNING_STATUS;

            String CURRENT_SIGNAL;
            CURRENT_SIGNAL= "NULL";
            train1.CURRENT_SIGNAL=CURRENT_SIGNAL;

            String CURRENT_TRACK;
            CURRENT_TRACK="NULL";
            train1.CURRENT_TRACK=CURRENT_TRACK;

            String CURRENT_SPEED;
            CURRENT_SPEED = "NULL";
            train1.CURRENT_SPEED=CURRENT_SPEED;

            int PROFIT;
            PROFIT = str_to_int(l14.getText());
            train1.PROFIT=PROFIT;

            String TRAIN_NAME;
            TRAIN_NAME = lname.getText();
            train1.TRAIN_NAME=TRAIN_NAME;

            int PRIORITY;
            PRIORITY = Integer.parseInt(textPriority.getText());
            train1.PRIORITY=PRIORITY;

            int MAX_SPEED;
            MAX_SPEED = 0;

            sql_data_loader load_speed = new sql_data_loader();

            int loco_max_speed = load_speed.getMaxSpeedLoco(ENG_NAME);
            int rake_max_speed = load_speed.getMaxSpeedRake(RAKE_TYPE);
            if(loco_max_speed < rake_max_speed){
                MAX_SPEED = loco_max_speed;
            }
            else{
                MAX_SPEED = rake_max_speed;
            }
            train1.MAX_SPEED=MAX_SPEED;
            int res_upload_data;

            res_upload_data = load_speed.uploadTraindata(train1);

            if(res_upload_data >0){
                showPopupBox("Train has been scheduled from SRC Station. Go to View Train Status to give Green Signal.", "Alert!! Alert!!");
                newStage.close();
                start(new Stage());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR ");
                alert.setHeaderText("Upload Error!");
                alert.showAndWait();
            }

            //do something
            //sort jobsheet after inserting jobsheet
        });

        Scene newWindowScene = new Scene(newWindowLayout, 1200, 800);

        newStage.setScene(newWindowScene);
        newStage.show();
    }

    private int rand() {
        Random random = new Random();

        // Generate a random number between 1 and 12
        int randomNumber = random.nextInt(12) + 1;
        return randomNumber;
    }

    public static int str_to_int(String str){

        String numericPart = str.split(" ")[0]; // Extract the numeric part
        double doubleValue = Double.parseDouble(numericPart); // Parse the numeric part as double
        int intValue = (int) (doubleValue * 100000); // Convert to integer by multiplying with lakh value
        return intValue;
    }

    private void showPopupBox(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

//    private void viewTrainWindow(String windowTitle, Stage primaryStage) {
//        primaryStage.close(); // Close the first window
//
//        Stage newStage = new Stage();
//        newStage.setTitle(windowTitle);
//
//        Button returnHomeButton = new Button("Return to Home Screen");
//        returnHomeButton.setOnAction(e -> {
//            newStage.close();
//            start(new Stage());
//        });
//
//        GridPane newWindowLayout = new GridPane();
//        newWindowLayout.setVgap(20);
//        newWindowLayout.setHgap(20);
//
//        // Add button to GridPane
//        newWindowLayout.add(returnHomeButton, 0, 0);
//
//        Scene newWindowScene = new Scene(newWindowLayout, 1200, 800);
//
//        newStage.setScene(newWindowScene);
//        newStage.show();
//    }
    private void viewTrainWindow(String windowTitle, Stage primaryStage) {
        primaryStage.close(); // Close the first window

        Stage newStage = new Stage();
        newStage.setTitle(windowTitle);

        GridPane newWindowLayout = new GridPane();
        newWindowLayout.setVgap(20);
        newWindowLayout.setHgap(20);

        Label l0 = new Label(" Indian Railways Train Manager");
        l0.setFont(new Font(70));
        newWindowLayout.add(l0, 1, 0, 8, 1);

        Rectangle box = new Rectangle(140, 50, Color.rgb(0, 0,  255, 0.5));
        newWindowLayout.add(box, 1, 2);

        Label l1 = new Label("  Current Status");
        l1.setFont(new Font(20));
        newWindowLayout.add(l1, 1, 2, 2, 1);

        Label l1a = new Label("Total Active Trains:");
        l1a.setFont(new Font(20));
        Label l2a = new Label("Load from SQL");
        l2a.setFont(new Font(20));
        newWindowLayout.add(l1a, 5, 2);
        newWindowLayout.add(l2a, 6, 2);

        Label lac = new Label("Current Track:");
        lac.setFont(new Font(20));
        Label ltr = new Label("Load from SQL");
        ltr.setFont(new Font(20));
        newWindowLayout.add(lac, 3, 2);
        newWindowLayout.add(ltr, 4, 2);


        Label l2 = new Label("Train Number:");
        l2.setFont(new Font(20));
        Label l3 = new Label("Load from SQL");
        l3.setFont(new Font(20));
        newWindowLayout.add(l2, 1, 4);
        newWindowLayout.add(l3, 2, 4);

        Label l4 = new Label("Running Status:");
        l4.setFont(new Font(20));
        Label l5 = new Label("Load from SQL");
        l5.setFont(new Font(20));
        newWindowLayout.add(l4, 3, 4, 1,1 );
        newWindowLayout.add(l5, 4, 4);

        Label l6 = new Label("Current_Speed:");
        l6.setFont(new Font(20));
        Label l7= new Label("Load from SQL");//speed
        l7.setFont(new Font(20));
        newWindowLayout.add(l6, 5, 4);
        newWindowLayout.add(l7, 6, 4);

        Label l8 = new Label("Train Name:");
        l8.setFont(new Font(20));
        Label l9 = new Label("Load from SQL");
        l9.setFont(new Font(20));
        newWindowLayout.add(l8, 1, 5);
        newWindowLayout.add(l9, 2, 5);

        Rectangle box2 = new Rectangle(140, 50, Color.rgb(244, 244,  244, 0.5));// change to red
        newWindowLayout.add(box2, 4, 5);

        Label l10 = new Label("Next Signal:");
        l10.setFont(new Font(20));
        Label l11 = new Label("  Load from SQL");
        l11.setFont(new Font(20));//signal
        newWindowLayout.add(l10, 3, 5);
        newWindowLayout.add(l11, 4, 5);

        Label l12 = new Label("Route ID:");
        l12.setFont(new Font(20));
        Label l13 = new Label("Load from SQL");
        l13.setFont(new Font(20));
        newWindowLayout.add(l12, 5, 5);
        newWindowLayout.add(l13, 6, 5);

        Label l14 = new Label("Source:");
        l14.setFont(new Font(20));
        Label l15 = new Label("Load from SQL");
        l15.setFont(new Font(20));
        newWindowLayout.add(l14, 1, 6);
        newWindowLayout.add(l15, 2, 6);

        Label l16 = new Label("Destination:");
        l16.setFont(new Font(20));
        Label l17 = new Label("Load from SQL");
        l17.setFont(new Font(20));
        newWindowLayout.add(l16, 3, 6);
        newWindowLayout.add(l17, 4, 6);

        Label l18 = new Label("Priority:");
        l18.setFont(new Font(20));
        Label l19 = new Label("Load from SQL");
        l19.setFont(new Font(20));
        newWindowLayout.add(l18, 5, 6);
        newWindowLayout.add(l19, 6, 6);

        Label l201 = new Label("Profit:");
        l201.setFont(new Font(20));
        Label l211 = new Label("Load from SQL");
        l211.setFont(new Font(20));
        newWindowLayout.add(l201, 1, 7);
        newWindowLayout.add(l211, 2, 7);

        Label l22a = new Label("Rake:");
        l22a.setFont(new Font(20));
        Label l23a = new Label("Load from SQL");
        l23a.setFont(new Font(20));
        newWindowLayout.add(l22a, 3, 7);
        newWindowLayout.add(l23a, 4, 7);

        Label l25a = new Label("Engine:");
        l25a.setFont(new Font(20));
        Label l24a = new Label("Load from SQL");
        l24a.setFont(new Font(20));
        newWindowLayout.add(l25a, 5, 7);
        newWindowLayout.add(l24a, 6, 7);

        String[] elementsArray = {"Element 1", "Element 2", "Element 3", "Element 4", "Element 5"};

        ObservableList<String> elementsList = FXCollections.observableArrayList(elementsArray);
        ComboBox<String> comboBox = new ComboBox<>(elementsList);

        elementsList.clear();
        update_train_data data = new update_train_data();
        String[] arr = data.train_no_array();
        for (int i=0; i< arr.length;i++){
            elementsList.add(arr[i]);
        }

        //BUTTON SECTION

        Label l20 = new Label("Change Train No");
        l20.setFont(new Font(20));
        newWindowLayout.add(l20, 1, 9);
        newWindowLayout.add(comboBox, 2, 9);

        Button HomeButton = new Button("Return to Home Screen");
        HomeButton.setFont(new Font(25));
        HomeButton.setOnAction(e -> {
            newStage.close();
            start(new Stage());
        });
        newWindowLayout.add(HomeButton, 5, 20);

        final String[] selected_train_no_on_start = new String[1];

        Button startTrain = new Button("Start Train");
        startTrain.setOnAction(e -> {
            startTrain.setText(startTrain.getText().equals("Start Train") ? "Stop Train" : "Start Train");
            // do something
        });;
        startTrain.setFont(new Font(25));
        newWindowLayout.add(startTrain, 2, 11);

        Button signalBtn = new Button("Give Red");
        signalBtn.setStyle("-fx-background-color: red;");
        signalBtn.setOnAction(e -> {
            sql_data_loader load_train = new sql_data_loader();

            if (signalBtn.getText().equals("Give Red")) {
                signalBtn.setText("Give Green");
                l11.setText("RED");
                l11.setStyle("-fx-background-color: red;");
                signalBtn.setStyle("-fx-background-color: lightgreen;");
                box2.setFill(Color.RED);

                load_train.changeSignal(Integer.parseInt(selected_train_no_on_start[0]), "RED");
                l5.setText("STOPPED");//running_status
                load_train.updateSpeed(Integer.parseInt(selected_train_no_on_start[0]),"0");
                l7.setText("0");
            } else {
                l11.setText("GREEN");
                l11.setStyle("-fx-background-color: lightgreen;");
                signalBtn.setText("Give Red");
                signalBtn.setStyle("-fx-background-color: red;");
                box2.setFill(Color.LIGHTGREEN);

                load_train.changeSignal(Integer.parseInt(selected_train_no_on_start[0]), "GREEN");
                l5.setText("RUNNING");//running_status

                TRAIN t1 = new TRAIN();
                t1 = load_train.getTrainByno(Integer.parseInt(selected_train_no_on_start[0]));
                int t1_max_speed = t1.get_train_max_speed();
                l7.setText(Integer.toString(t1_max_speed));
                load_train.updateSpeed(Integer.parseInt(selected_train_no_on_start[0]),Integer.toString(t1_max_speed));

            }

        });

        signalBtn.setFont(new Font(25));
        newWindowLayout.add(signalBtn, 3, 11);

        Label time = new Label("Time here");
        time.setFont(new Font(18));
        updateTime(time, lac, ltr); // Initial update

        // Create a timeline to update the time every second
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateTime(time, lac, ltr))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        newWindowLayout.add(time, 3, 9);

        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> {
            // do something
            String selected_train_no = comboBox.getValue();
            selected_train_no_on_start[0] = selected_train_no;
            elementsList.clear();
            update_train_data element_train_no = new update_train_data();
            String[] element_train_no_arr = element_train_no.train_no_array();

            for (int i=0; i< arr.length;i++){
                elementsList.add(element_train_no_arr[i]);
            }

            sql_data_loader load_train = new sql_data_loader();
            TRAIN t1 = new TRAIN();
            t1 = load_train.getTrainByno(Integer.parseInt(selected_train_no));


            int t1_train_no = t1.get_train_train_no();
            l3.setText(Integer.toString(t1_train_no));
            int t1_profit = t1.get_train_profit();
            l211.setText(Integer.toString(t1_profit));
            int t1_priority = t1.get_train_priority();
            l19.setText(Integer.toString(t1_priority));
            l2a.setText(Integer.toString(element_train_no_arr.length));

            String t1_train_name = t1.get_train_train_name();
            l9.setText(t1_train_name);
            l9.setFont(new Font(10));
            String t1_track = t1.get_train_current_track();
            ltr.setText(t1_track);
            String t1_source = t1.get_train_source();
            l15.setText(t1_source);
            String t1_running_status = t1.get_train_running_status();
            l5.setText(t1_running_status);
            String t1_current_speed = t1.get_train_current_speed();
            l7.setText(t1_current_speed);
            String t1_next_signal = t1.get_train_current_signal();
            if (t1_next_signal.equalsIgnoreCase("GREEN")) {
                box2.setFill(Color.LIGHTGREEN);
                l11.setStyle("-fx-background-color: lightgreen;");
            }
            else{
                box2.setFill(Color.RED);
                l11.setStyle("-fx-background-color: red;");
            }

            l11.setText(t1_next_signal);
            String t1_route_id = t1.get_train_route_id();
            l13.setText(t1_route_id);
            String t1_destination = t1.get_train_destination();
            l17.setText(t1_destination);
            String t1_rake = t1.get_train_rake_name();
            l23a.setText(t1_rake);
            String t1_eng_name = t1.get_train_eng_name();
            l24a.setText(t1_eng_name);


        });;
        updateBtn.setFont(new Font(25));
        newWindowLayout.add(updateBtn, 4, 11);

        Button speedBtn = new Button("Change Speed");
        speedBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Change Train Speed");
            dialog.setHeaderText("Enter new speed for train no: "+ selected_train_no_on_start[0]);

            Optional<String> result = dialog.showAndWait();

            result.ifPresentOrElse(number -> {
                try {
                    int inputNumber = Integer.parseInt(number);
                    sql_data_loader load_train = new sql_data_loader();
                    TRAIN t1 = new TRAIN();
                    t1 = load_train.getTrainByno(Integer.parseInt(selected_train_no_on_start[0]));

                    int max_speed_of_train = t1.get_train_max_speed();

                    if (inputNumber> max_speed_of_train) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning ");
                        alert.setHeaderText("Speed Warning!");
                        alert.setContentText("Speed can't be set more than Max Speed of "+max_speed_of_train);
                        alert.showAndWait();
                    }
                    else{
                        load_train.updateSpeed(Integer.parseInt(selected_train_no_on_start[0]), String.valueOf(inputNumber));
                        l7.setText(String.valueOf(inputNumber));
                    }

                } catch (NumberFormatException e1) {
                }
            }, () -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning ");
                alert.setHeaderText("Speed Warning!");
                alert.setContentText("No change in Speed.");
                alert.showAndWait();
            });
        });

        speedBtn.setFont(new Font(25));
        newWindowLayout.add(speedBtn, 5, 11);

        Button stopallBtn = new Button("Stop All");
        stopallBtn.setOnAction(e -> {
            sql_data_loader load_train = new sql_data_loader();
            load_train.stopAll();
            l11.setText("RED");
            l11.setStyle("-fx-background-color: red;");
            box2.setFill(Color.RED);
            l7.setText("0");
            l5.setText("STOPPED");
            if (stopallBtn.getText().equals("Stop All")) {
                showPopupBox("ALL TRAINS EMERGENCY BRAKE APPLIED.", "RED Alert!! RED Alert!!");
                stopallBtn.setText("Resume All");
            } else {
                stopallBtn.setText("Stop All");
            }
        });;
        stopallBtn.setFont(new Font(25));
        newWindowLayout.add(stopallBtn, 6, 11);

        Scene newWindowScene = new Scene(newWindowLayout, 1200, 800);

        newStage.setScene(newWindowScene);
        newStage.show();
    }

    private void copyToClipboard(String text) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }
    public static String convertToLakhs(int number) {
        double lakhs = number / 100000.0;
        return String.format("%.2f", lakhs) + " Lakhs";
    }

    private void jobSheetWindow(String windowTitle, Stage primaryStage) {
        primaryStage.close(); // Close the first window

        Stage newStage = new Stage();
        newStage.setTitle(windowTitle);

        Button takeJob = new Button("Take Job");

        Button HomeButton = new Button("Return to Home Screen");
        HomeButton.setOnAction(e -> {
            newStage.close();
            start(new Stage());
        });

        Button rejectJob = new Button("Reject Job");

        GridPane newWindowLayout = new GridPane();
        newWindowLayout.setVgap(20);
        newWindowLayout.setHgap(20);


        update_job_data job12 = new update_job_data();

        String[] jobIDs= job12.getJobIds();
        List<String> jobsAvailable = new ArrayList<>();

        for (String job : jobIDs) {
            jobsAvailable.add(job);
        }

        sql_data_loader load_job = new sql_data_loader();
        JOBSHEET j1 = new JOBSHEET();
        j1 = load_job.getJobsheetByID(jobsAvailable.get(0));

        Label l0 = new Label(" Indian Railways Job Sheet");
        l0.setFont(new Font(70));
        newWindowLayout.add(l0, 2, 0, 5, 1);

        Label l1 = new Label("Job Sheet ID: ");
        l1.setFont(new Font(20));
        Label l2 = new Label(j1.JOB_ID);
        l2.setFont(new Font(20));
        Label l3 = new Label("Goods Train");
        l3.setFont(new Font(20));
        if (Objects.equals(j1.JOB_TYPE, "PASSENEGER")){
            l3.setText("Passenger Train");
        }

        newWindowLayout.add(l1, 1, 2);
        newWindowLayout.add(l2, 2, 2);
        newWindowLayout.add(l3, 3, 2);


        Label l4 = new Label("Train Number:");
        l4.setFont(new Font(20));
        Label l5 = new Label("" + j1.TRAIN_NO);
        l5.setFont(new Font(20));
        newWindowLayout.add(l4, 1, 3);
        newWindowLayout.add(l5, 2, 3);

        Label l6 = new Label("Train Name:");
        l6.setFont(new Font(20));
        Label l7= new Label(j1.TRAIN_NAME);
        l7.setFont(new Font(20));
        newWindowLayout.add(l6, 1, 4);
        newWindowLayout.add(l7, 2, 4);

        Label l8 = new Label("Source Station: ");
        l8.setFont(new Font(20));
        Label l9= new Label(j1.SOURCE);
        l9.setFont(new Font(20));
        newWindowLayout.add(l8, 1,5 );
        newWindowLayout.add(l9, 2, 5);

        Label l10 = new Label("Destination Station: ");
        l10.setFont(new Font(20));
        Label l11= new Label(j1.DESTINATION);
        l11.setFont(new Font(20));
        newWindowLayout.add(l10,1, 6);
        newWindowLayout.add(l11,2, 6);

        Label l12 = new Label("Train Load (Cargo/Pax):");
        l12.setFont(new Font(20));
        Label l13= new Label(j1.JOB_TYPE);
        l13.setFont(new Font(20));
        newWindowLayout.add(l12,1, 7);
        newWindowLayout.add(l13,2, 7);

        Label l14 = new Label("Train Priority:");
        l14.setFont(new Font(20));
        Label l15= new Label(""+ j1.PRIORITY);
        l15.setFont(new Font(20));
        newWindowLayout.add(l14,1, 8);
        newWindowLayout.add(l15,2, 8);

        Label l16 = new Label("Total Income:");
        l16.setFont(new Font(20));
        Label l17= new Label(convertToLakhs(j1.INCOME));
        l17.setFont(new Font(20));
        newWindowLayout.add(l16,1, 9);
        newWindowLayout.add(l17,2,9);

        Label l18 = new Label("Route Details:");
        l18.setFont(new Font(20));
        Label l19= new Label(job12.getStopsForRoute(j1.ROUTE_ID));
        l19.setFont(new Font(20));
        newWindowLayout.add(l18,1, 10);
        newWindowLayout.add(l19,2, 10);

        Label l20 = new Label("Total Journey Time (HH:MM): ");
        l20.setFont(new Font(20));
        Label l21= new Label("" + j1.TIME_TAKEN);
        l21.setFont(new Font(20));
        newWindowLayout.add(l20,1, 11);
        newWindowLayout.add(l21,2, 11);

        Label l1q = new Label("ETD:");
        l1q.setFont(new Font(20));
        Label l1w = new Label("11 April 2024, "+rand()+" PM");
        l1w.setFont(new Font(20));
        newWindowLayout.add(l1q, 1, 12);
        newWindowLayout.add(l1w, 2, 12);

        takeJob.setFont(new Font(30));
        HomeButton.setFont(new Font(30));
        rejectJob.setFont(new Font(30));

        newWindowLayout.add(takeJob, 2, 15);
        newWindowLayout.add(HomeButton, 4, 15);
        newWindowLayout.add(rejectJob, 3, 15);

        Scene newWindowScene = new Scene(newWindowLayout, 1200, 800);

        takeJob.setOnAction(e -> {
            //do something
            newStage.close();
            start(new Stage());
            copyToClipboard(jobsAvailable.getFirst());//add jobid here
            showPopupBox("Job with JOBID # "+jobsAvailable.getFirst()+ " has been taken by you. JobID copied to Clipboard. Create train now to start job.", "Alert!! Alert!!");
        });

        rejectJob.setOnAction(e -> {
            if(jobsAvailable.size() == 1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WARNING WARNING");
                alert.setHeaderText(null);
                alert.setContentText("NO MORE TRAINS SCHEDULED.");
                alert.showAndWait();
            }
            else {
                jobsAvailable.removeFirst();
                showPopupBox("Job Rejected. You may see the new job now.", "Alert!! Alert!!");
                sql_data_loader load_new_job = new sql_data_loader();
                JOBSHEET j2 = new JOBSHEET();
                j2 = load_new_job.getJobsheetByID(jobsAvailable.get(0));

                l2.setText(j2.JOB_ID);
                l3.setText("Goods Train");
                if (Objects.equals(j2.JOB_TYPE, "PASSENEGER")) {
                    l3.setText("Passenger Train");
                }
                l5.setText("" + j2.TRAIN_NO);
                l7.setText(j2.TRAIN_NAME);
                l9.setText(j2.SOURCE);
                l11.setText(j2.DESTINATION);
                l13.setText(j2.JOB_TYPE);
                l15.setText("" + j2.PRIORITY);
                l17.setText(convertToLakhs(j2.INCOME));
                l19.setText(job12.getStopsForRoute(j2.ROUTE_ID));
                l21.setText("" + j2.TIME_TAKEN);
                l1w.setText("11 April 2024, "+rand()+" PM");
            }

        });
        newStage.setScene(newWindowScene);
        newStage.show();
    }
}
