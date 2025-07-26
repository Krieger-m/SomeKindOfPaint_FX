package com.krieger.canvasPlayground.layersModified;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Layer4 {

    boolean isActive = false;

    ColorPicker l4_picker1;
    ColorPicker l4_picker2;

    Canvas l4_can;
    GraphicsContext l4_context;

    public Layer4() {
        this.isActive = false;
        this.l4_can = new Canvas();
        this.l4_context = l4_can.getGraphicsContext2D();
        initLayer();
    }

    public void initLayer(){
        createColorPicker();
        setDefaultColors();
        l4_can.setEffect(new BoxBlur(3,3,2));
    }

    public void createColorPicker(){
        this.l4_picker1 = new ColorPicker();
        this.l4_picker2 = new ColorPicker();
    }
    public void setDefaultColors(){
        this.l4_picker1.setValue(Color.web("#89c6daff"));
        this.l4_picker2.setValue(Color.web("#4cfd5eff"));
    }

    EventHandler<MouseEvent> layer4Handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            // System.out.println(l4_picker1.getValue());
            l4_context.setFill(
                    new LinearGradient(0, 0, 1, 1, true,
                            CycleMethod.REFLECT,
                            new Stop(0.0, l4_picker1.getValue()),
                            new Stop(1.0, l4_picker2.getValue()))
            );
            l4_context.setEffect(new DropShadow(BlurType.THREE_PASS_BOX,Color.web("#1414144a"), 30, 0,4,4));
            l4_context.fillPolygon(
                new double[]{
                    e.getX()-100, e.getX() - 200, e.getX() + 140, e.getX() + 210},
                new double[]{
                    e.getY() -100, e.getY() - 40, e.getY() + 200, e.getY() + 60},
                4
            );

                       // commented out //fillPolygon(e.getX()-50,e.getX()-50,e.getY()-50,100,100,30,30);
        }
    };

    public boolean isL4Active() {
        return isActive;
    }
    public void setL4Active(boolean active) {
        isActive = active;
    }

    public ColorPicker getL4_picker1() {
        return l4_picker1;
    }

    public ColorPicker getL4_picker2() {
        return l4_picker2;
    }

    public Canvas getL4_Can() {
        return l4_can;
    }

    public GraphicsContext getL4_Context() {
        return l4_context;
    }
}
