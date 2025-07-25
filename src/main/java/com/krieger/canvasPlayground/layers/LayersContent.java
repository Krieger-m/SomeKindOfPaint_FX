package com.krieger.canvasPlayground.layers;

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
    ColorPicker picker1;
    ColorPicker picker2;
    HBox menuContainer;

    double x = 1080;
    double y = 740;
    Color selectedColor;

    Layer1 l1;
    Layer2 l2;

    Canvas layer1Canvas;
    Canvas layer2Canvas;
    Canvas layer3;
    GraphicsContext gc1;
    GraphicsContext gc2;
    GraphicsContext gc3;


    public LayersContent() {
        l1 = new Layer1();
        l2 = new Layer2();

        this.menuContainer= new HBox();
        this.root = new Group();
        this.borderPane = new BorderPane();
        this.layer1Canvas = l1.getL1_Can();
        this.layer2Canvas = l2.getL2_Can();
//        this.layer3 = new Canvas(x,y);
        this.gc1 = l1.getL1_Context();
        this.gc2 = l2.getL2_Context();
//        this.gc3 = layer3.getGraphicsContext2D();

    }

    public void addLayers(){
        createChoiceBox();
        createColorPicker();

        updateMenuContainer();
        borderPane.setTop(menuContainer);
        Pane pane = new Pane();
        pane.getChildren().add(layer1Canvas);
        pane.getChildren().add(layer2Canvas);
//        pane.getChildren().add(layer3);
        layer1Canvas.toFront();
        borderPane.setCenter(pane);
        root.getChildren().add(borderPane);

    }
    public void createColorPicker(){
        this.picker1 = new ColorPicker();
        this.picker2 = new ColorPicker();
    }

    public void updateMenuContainer(){
        menuContainer.getChildren().clear();

        menuContainer.getChildren().addAll(cb,picker1,picker2);

    }

    public void setLayerDefault(){
        this.picker1.setValue(Color.web("#5ca7d9ff"));
        this.picker2.setValue(Color.web("#BABDBF"));
    };

    public void createChoiceBox(){
        this.cb = new ChoiceBox<>();
        this.cb.setItems(FXCollections.observableArrayList(
                "Background","Layer 1", "Layer 2")); //, "Layer 3"));
        this.cb.getSelectionModel().selectedItemProperty().
            addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> o, String o1, String o2) {
                    if (o2 != null && o2.contains("Layer 1")) {
                        layer1Canvas.toFront();
                        l1.setL1Active(true);

                        picker1 = l1.getL1_picker1();
                        picker2=l1.getL1_picker2();

                        updateMenuContainer();

                    } else if (o2 != null && o2.contains("Layer 2")) {
                        layer2Canvas.toFront();
                        l2.setL2Active(true);
                        l1.setL1Active(false);
                        picker1.setValue(l2.getL2_picker1().getValue());
                        picker2.setValue(l2.getL2_picker2().getValue());
                        updateMenuContainer();

                        picker1 = l2.getL2_picker1();
                        picker2 = l2.getL2_picker2();
                    }
//                    else if (o2 != null && o2.contains("Layer 3")) {
//                        layer3.toFront();
//                        layer1Obj.setActive(false);
//                        setLayerDefault();
//                    }
                    else if (o2 != null && o2.contains("Background")) {
                        borderPane.toBack();
                        setLayerDefault();

                        borderPane.setBackground(new Background(new BackgroundFill(picker1.getValue(), null, null)));

                        l1.setL1Active(false);

                    }

                }
            }
        );
        this.cb.setValue("Layer 1");
    }



    public void handleLayers(){

        // Handler for Layer 1

        layer1Canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, l1.layer1Handler);
        layer1Canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, l1.layer1Handler);



        // Handler for Layer 2

        layer2Canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, l2.layer2Handler);
        layer2Canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, l2.layer2Handler);
        

        // Handler for Layer 3
//        EventHandler<MouseEvent> layer3Handler = new EventHandler<MouseEvent>() {
//            public void setLayerdefaultColor3(){
//                    picker2.setValue(Color.web("#FA518B"));
//                }
//            @Override
//            public void handle(MouseEvent e) {
//                gc3.setFill(
//                        new LinearGradient(0, 0, 1, 1, true,
//                                CycleMethod.REFLECT,
//                                new Stop(0.0, picker1.getValue()),
//                                new Stop(1.0,  picker2.getValue()))
//                );
//                gc3.setEffect(new DropShadow(10,10,10,Color.web("#00000065")));
//                gc3.fillOval(e.getX()-35,e.getY()-35,70,70);
//            }
//        };
//        layer3.addEventHandler(MouseEvent.MOUSE_DRAGGED, layer3Handler);
//        layer3.addEventHandler(MouseEvent.MOUSE_CLICKED, layer3Handler);
    }
}
