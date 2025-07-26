package com.krieger.canvasPlayground.layersModified;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LayersContent {

    Group root;
    BorderPane borderPane;
    ChoiceBox<String> cb;
    // These pickers are now explicitly for the background
    ColorPicker backgroundPicker1;
    ColorPicker backgroundPicker2;
    ColorPicker backgroundPicker3;
    HBox menuContainer;

    double x = 1620;
    double y = 1080;

    Layer1 l1;
    Layer2 l2;
    Layer3 l3;

    Canvas layer1Canvas;
    Canvas layer2Canvas;
    Canvas layer3Canvas;
    GraphicsContext gc1;
    GraphicsContext gc2;
    GraphicsContext gc3;


    public LayersContent() {
        l1 = new Layer1();
        l2 = new Layer2();
        l3 = new Layer3();

        this.menuContainer = new HBox();
        this.root = new Group();
        this.borderPane = new BorderPane();
        this.layer1Canvas = l1.getL1_Can();
        this.layer2Canvas = l2.getL2_Can();
        this.layer3Canvas = l3.getL3_Can();
        this.gc1 = l1.getL1_Context();
        this.gc2 = l2.getL2_Context();
        this.gc3 = l3.getL3_Context();
//        ---------------------->> continue adding Layer3 <<------------------     \\

        // Initialize background pickers directly
        this.backgroundPicker1 = new ColorPicker(Color.web("#797979ff"));
        this.backgroundPicker2 = new ColorPicker(Color.web("#BABDBF"));
    }

    public void addLayers() {
        createChoiceBox();

        // Initially set Layer 1 as active and display its pickers
        updateMenuContainer(l1.getL1_picker1(), l1.getL1_picker2());
        borderPane.setTop(menuContainer);

        Pane pane = new Pane();
        pane.getChildren().add(layer1Canvas);
        pane.getChildren().add(layer2Canvas);
//        pane.getChildren().add(layer3); // Commented out
        layer1Canvas.toFront(); // Ensure Layer 1 is on top initially
        borderPane.setCenter(pane);
        root.getChildren().add(borderPane);

        handleLayers(); // Add event handlers once
    }

    // This method is now used to update the menuContainer with specific pickers
    private void updateMenuContainer(ColorPicker p1, ColorPicker p2) {
        menuContainer.getChildren().clear();
        menuContainer.getChildren().addAll(cb, p1, p2);
    }

    public void createChoiceBox() {
        this.cb = new ChoiceBox<>();
        this.cb.setItems(FXCollections.observableArrayList(
                "Background", "Layer 1", "Layer 2")); // Removed "Layer 3"
        this.cb.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<String>() {
                                @Override
                                public void changed(ObservableValue<? extends String> o, String o1, String newValue) {
                                    if (newValue != null) {
                                        switch (newValue) {
                                            case "Layer 1":
                                                layer1Canvas.toFront();
                                                l1.setL1Active(true);
                                                l2.setL2Active(false); // Deactivate other layers
                                                updateMenuContainer(l1.getL1_picker1(), l1.getL1_picker2()); // Display Layer 1's pickers
                                                break;
                                            case "Layer 2":
                                                layer2Canvas.toFront();
                                                l2.setL2Active(true);
                                                l1.setL1Active(false); // Deactivate other layers
                                                updateMenuContainer(l2.getL2_picker1(), l2.getL2_picker2()); // Display Layer 2's pickers
                                                break;
                                            // case "Layer 3": // Commented out
                                            //     layer3.toFront();
                                            //     l1.setL1Active(false);
                                            //     l2.setL2Active(false);
                                            //     // If Layer 3 had its own pickers, pass them here
                                            //     updateMenuContainer(defaultPicker1, defaultPicker2); // Placeholder
                                            //     break;
                                            case "Background":
                                                borderPane.toBack();
                                                l1.setL1Active(false); // Deactivate all drawing layers
                                                l2.setL2Active(false);
                                                updateMenuContainer(backgroundPicker1, backgroundPicker2); // Display background pickers
                                                borderPane.setBackground(new Background(new BackgroundFill(backgroundPicker1.getValue(), null, null)));
                                                break;
                                        }
                                    }
                                }
                            }
                );
        this.cb.setValue("Layer 1"); // Set initial value to Layer 1
    }

    public void handleLayers() {
        // Handler for Layer 1
        layer1Canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, l1.layer1Handler);
        layer1Canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, l1.layer1Handler);

        // Handler for Layer 2
        layer2Canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, l2.layer2Handler);
        layer2Canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, l2.layer2Handler);

        // Handler for Layer 3 (Commented out)
        // ...
    }
}