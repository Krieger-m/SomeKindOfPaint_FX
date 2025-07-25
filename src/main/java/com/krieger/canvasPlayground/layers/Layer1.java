package com.krieger.canvasPlayground.layers;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Layer1 {

    boolean isActive = false;


    ColorPicker l1picker1;
    ColorPicker l1picker2;

    Canvas can;
    GraphicsContext context;

    public Layer1() {

        this.isActive = false;

        this.can = new Canvas(1080,740);
        this.context = can.getGraphicsContext2D();

        initLayer();
    }

    public void initLayer(){
        createColorPicker();
        setDefaultColors();
    }

    public void createColorPicker(){
        this.l1picker1 = new ColorPicker();
        this.l1picker2 = new ColorPicker();
    }
    public void setDefaultColors(){
        this.l1picker1.setValue(Color.web("#55FA9B"));
        this.l1picker2.setValue(Color.web("#BABDBF"));
    }


    EventHandler<MouseEvent> layer1Handler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
            System.out.println(l1picker1.getValue());
            context.setFill(
                    new LinearGradient(0, 0, 1, 1, true,
                            CycleMethod.REFLECT,
                            new Stop(0.0, l1picker1.getValue()),
                            new Stop(1.0, l1picker2.getValue())) //Color.web("#55FA9B")))
            );
            context.setEffect(new DropShadow(BlurType.THREE_PASS_BOX,Color.web("#00000065"), 40, 0.2,5,30)); //4,
            // 4,4,
            // Color.web("#00000065")));
            context.fillOval(e.getX()-35,e.getY()-35,70,70);
        }
    };


    public boolean isL1Active() {
        return isActive;
    }
    public void setL1Active(boolean active) {
        isActive = active;
    }

    public ColorPicker getL1_picker1() {
        return l1picker1;
    }

    public ColorPicker getL1_picker2() {
        return l1picker2;
    }

    public Canvas getL1_Can() {
        return can;
    }

    public GraphicsContext getL1_Context() {
        return context;
    }
}
