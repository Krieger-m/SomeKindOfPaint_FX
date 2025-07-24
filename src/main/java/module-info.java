module com.krieger.canvasPlayground {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires javafx.base;

    exports com.krieger.canvasPlayground;
    opens com.krieger.canvasPlayground to javafx.fxml;
    exports com.krieger.canvasPlayground.layers;
    opens com.krieger.canvasPlayground.layers to javafx.fxml;
}
