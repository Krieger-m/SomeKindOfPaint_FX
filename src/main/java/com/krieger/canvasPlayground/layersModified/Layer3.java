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

public class Layer3 {

    boolean isActive = false;

    ColorPicker l3_picker1;
    ColorPicker l3_picker2;

    Canvas l3_can;
    GraphicsContext l3_context;

    public Layer3() {
        this.isActive = false;
        this.l3_can = new Canvas(1620,1080);
        this.l3_context = l3_can.getGraphicsContext2D();
        initLayer();
    }

    public void initLayer(){
        createColorPicker();
        setDefaultColors();
    }

    public void createColorPicker(){
        this.l3_picker1 = new ColorPicker();
        this.l3_picker2 = new ColorPicker();
    }
    public void setDefaultColors(){
        this.l3_picker1.setValue(Color.web("#5ca7d9"));
        this.l3_picker2.setValue(Color.web("#BABDBF"));
    }

    EventHandler<MouseEvent> layer2Handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            // System.out.println(l2_picker1.getValue());
            l3_context.setFill(
                    new LinearGradient(0, 0, 1, 1, true,
                            CycleMethod.REFLECT,
                            new Stop(0.0, l3_picker1.getValue()),
                            new Stop(1.0, l3_picker2.getValue()))
            );
            l3_context.setEffect(new DropShadow(BlurType.THREE_PASS_BOX,Color.web("#1414144a"), 30, 0,4,4));
            l3_context.fillRect(e.getX()-50,e.getY()-50,100,100);
        }
    };

    public boolean isL3Active() {
        return isActive;
    }
    public void setL3Active(boolean active) {
        isActive = active;
    }

    public ColorPicker getL3_picker1() {
        return l3_picker1;
    }

    public ColorPicker getL3_picker2() {
        return l3_picker2;
    }

    public Canvas getL3_Can() {
        return l3_can;
    }

    public GraphicsContext getL3_Context() {
        return l3_context;
    }
}