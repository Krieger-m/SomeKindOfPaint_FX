package com.krieger.canvasPlayground.layersModified;

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

public class Layer2 {

    boolean isActive = false;

    ColorPicker l2_picker1;
    ColorPicker l2_picker2;

    Canvas l2_can;
    GraphicsContext l2_context;

    public Layer2() {
        this.isActive = false;
        this.l2_can = new Canvas();
        this.l2_context = l2_can.getGraphicsContext2D();
        initLayer();
    }

    public void initLayer(){
        createColorPicker();
        setDefaultColors();
    }

    public void createColorPicker(){
        this.l2_picker1 = new ColorPicker();
        this.l2_picker2 = new ColorPicker();
    }
    public void setDefaultColors(){
        this.l2_picker1.setValue(Color.web("#5ca7d9"));
        this.l2_picker2.setValue(Color.web("#BABDBF"));
    }

    EventHandler<MouseEvent> layer2Handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            // System.out.println(l2_picker1.getValue());
            l2_context.setFill(
                    new LinearGradient(0, 0, 1, 1, true,
                            CycleMethod.REFLECT,
                            new Stop(0.0, l2_picker1.getValue()),
                            new Stop(1.0, l2_picker2.getValue()))
            );
            l2_context.setEffect(new DropShadow(BlurType.THREE_PASS_BOX,Color.web("#1414144a"), 30, 0,4,4));
            l2_context.fillOval(e.getX()-75,e.getY()-75,150,150);
        }
    };

    public boolean isL2Active() {
        return isActive;
    }
    public void setL2Active(boolean active) {
        isActive = active;
    }

    public ColorPicker getL2_picker1() {
        return l2_picker1;
    }

    public ColorPicker getL2_picker2() {
        return l2_picker2;
    }

    public Canvas getL2_Can() {
        return l2_can;
    }

    public GraphicsContext getL2_Context() {
        return l2_context;
    }
}
