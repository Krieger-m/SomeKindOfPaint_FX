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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class LayersContent {

    Group root;
    BorderPane borderPane;
    ChoiceBox<String> cb;
    // These pickers are now explicitly for the background
    ColorPicker backgroundPicker1;
    ColorPicker backgroundPicker2;
    HBox menuContainer;

    double x = 1620;
    double y = 1080;

    Layer1 l1;
    Layer2 l2;
    Layer3 l3;
    Layer4 l4;

    Canvas layer1Canvas;
    Canvas layer2Canvas;
    Canvas layer3Canvas;
    Canvas layer4Canvas;
    GraphicsContext gc1;
    GraphicsContext gc2;
    GraphicsContext gc3;
    GraphicsContext gc4;


    public LayersContent() {
        l1 = new Layer1();
        l2 = new Layer2();
        l3 = new Layer3();
        l4 = new Layer4();
        
        this.menuContainer = new HBox();
        this.root = new Group();
        this.borderPane = new BorderPane();
        
        this.layer1Canvas = l1.getL1_Can();
        this.layer2Canvas = l2.getL2_Can();
        this.layer3Canvas = l3.getL3_Can();
        this.layer4Canvas = l4.getL4_Can();

        this.gc1 = l1.getL1_Context();
        this.gc2 = l2.getL2_Context();
        this.gc3 = l3.getL3_Context();
        this.gc4 = l4.getL4_Context();
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

        // copy of display background stuff for a unneccessary complicated linearGradient
        
        Stop[] stops = new Stop[] { new Stop(0, Color.web(backgroundPicker1.getValue().toString())), new Stop(1, Color.web(String.valueOf(backgroundPicker2.getValue())))};
        
        LinearGradient gradient = new LinearGradient(x*0.2, y*0.2, x*0.8, y*0.8, false, CycleMethod.REFLECT, stops);
        borderPane.setBackground(new Background(new BackgroundFill(gradient, null, null)));

        Pane pane = new Pane();
        pane.getChildren().addAll(layer1Canvas,layer2Canvas,layer3Canvas,layer4Canvas);  // added all Layers
        layer1Canvas.toFront(); // Ensure Layer 1 is on top initially
        borderPane.setCenter(pane);
        root.getChildren().add(borderPane);

        // Bind canvas dimensions to pane dimensions
        layer1Canvas.widthProperty().bind(pane.widthProperty());
        layer1Canvas.heightProperty().bind(pane.heightProperty());
        layer2Canvas.widthProperty().bind(pane.widthProperty());
        layer2Canvas.heightProperty().bind(pane.heightProperty());
        layer3Canvas.widthProperty().bind(pane.widthProperty());
        layer3Canvas.heightProperty().bind(pane.heightProperty());
        layer4Canvas.widthProperty().bind(pane.widthProperty());
        layer4Canvas.heightProperty().bind(pane.heightProperty());

        // Add listeners to clear and redraw when canvas size changes
        layer1Canvas.widthProperty().addListener((obs, oldVal, newVal) -> gc1.clearRect(0, 0, oldVal.doubleValue(), layer1Canvas.getHeight()));
        layer1Canvas.heightProperty().addListener((obs, oldVal, newVal) -> gc1.clearRect(0, 0, layer1Canvas.getWidth(), oldVal.doubleValue()));
        
        layer2Canvas.widthProperty().addListener((obs, oldVal, newVal) -> gc2.clearRect(0, 0, oldVal.doubleValue(), layer2Canvas.getHeight()));
        layer2Canvas.heightProperty().addListener((obs, oldVal, newVal) -> gc2.clearRect(0, 0, layer2Canvas.getWidth(), oldVal.doubleValue()));

        layer3Canvas.widthProperty().addListener((obs, oldVal, newVal) -> gc3.clearRect(0, 0, oldVal.doubleValue(), layer3Canvas.getHeight()));
        layer3Canvas.heightProperty().addListener((obs, oldVal, newVal) -> gc3.clearRect(0, 0, layer3Canvas.getWidth(), oldVal.doubleValue()));

        layer4Canvas.widthProperty().addListener((obs, oldVal, newVal) -> gc4.clearRect(0, 0, oldVal.doubleValue(), layer4Canvas.getHeight()));
        layer4Canvas.heightProperty().addListener((obs, oldVal, newVal) -> gc4.clearRect(0, 0, layer4Canvas.getWidth(), oldVal.doubleValue()));

        handleLayers(); // Add event handlers once
    }

    // This method is now used to update the menuContainer with specific pickers
    private void updateMenuContainer(ColorPicker p1, ColorPicker p2) {
        menuContainer.getChildren().clear();
        menuContainer.getChildren().addAll(cb, p1, p2);
    }

    public void createChoiceBox() {
        this.cb = new ChoiceBox<>();
        this.cb.setItems(FXCollections.observableArrayList("Background", "Layer 1", "Layer 2","Layer 3","Layer 4")); // added Layer3 and Layer4
        this.cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> o, String o1, String newValue) {
                if (newValue != null) {
                    switch (newValue) {
                        case "Layer 1":
                            layer1Canvas.toFront();
                            l1.setL1Active(true);
                            l2.setL2Active(false); 
                            l3.setL3Active(false);  // Deactivate other layers
                            l4.setL4Active(false); 
                            updateMenuContainer(l1.getL1_picker1(), l1.getL1_picker2());    // Display Layer 1's pickers
                            break;
                        case "Layer 2":
                            layer2Canvas.toFront();
                            l2.setL2Active(true);
                            l1.setL1Active(false); 
                            l3.setL3Active(false);  // Deactivate other layers
                            l4.setL4Active(false); 
                            updateMenuContainer(l2.getL2_picker1(), l2.getL2_picker2()); // Display Layer 2's pickers
                            break;
                        case "Layer 3": // added new Layer3
                            layer3Canvas.toFront();
                            l3.setL3Active(true);
                            l1.setL1Active(false);
                            l2.setL2Active(false);
                            l4.setL4Active(false);
                            updateMenuContainer(l3.getL3_picker1(), l3.getL3_picker2()); 
                            break;
                        case "Layer 4": // added new Layer4
                            layer4Canvas.toFront();
                            l3.setL3Active(false);
                            l1.setL1Active(false);
                            l2.setL2Active(false);
                            l4.setL4Active(true);
                            updateMenuContainer(l4.getL4_picker1(), l4.getL4_picker2()); 
                            break;
                        case "Background":
                            borderPane.toBack();
                            l1.setL1Active(false); 
                            l2.setL2Active(false);
                            l3.setL3Active(false);  // Deactivate all drawing layers
                            l4.setL4Active(false);
                            updateMenuContainer(backgroundPicker1, backgroundPicker2); 

                            // Display background stuff for a unneccessary complicated linearGradient
                            
                            Stop[] stops = new Stop[] { new Stop(0, Color.web(backgroundPicker1.getValue().toString())), new Stop(1, Color.web(String.valueOf(backgroundPicker2.getValue())))};
                            
                            LinearGradient gradient = new LinearGradient(x*0.2, y*0.2, x*0.8, y*0.8, false, CycleMethod.REFLECT, stops);
                            borderPane.setBackground(new Background(new BackgroundFill(gradient, null, null)));
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

        // Handler for Layer 3
        layer3Canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, l3.layer3Handler);
        layer3Canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, l3.layer3Handler);
        
        // Handler for Layer 4
        layer4Canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, l4.layer4Handler);
        layer4Canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, l4.layer4Handler);
        
    }
}
