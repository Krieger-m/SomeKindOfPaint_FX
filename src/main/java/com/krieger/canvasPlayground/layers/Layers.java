package com.krieger.canvasPlayground.layers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Layers extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        LayersContent lc = new LayersContent();

        lc.addLayers();
        lc.handleLayers();



        Scene scene = new Scene(lc.root);

        stage.setScene(scene);
        stage.setTitle("Layers");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
