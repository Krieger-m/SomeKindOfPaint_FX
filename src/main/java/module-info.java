module com.krieger.canvasPlayground {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires javafx.base;

    exports com.krieger.canvasPlayground.layersModified;
    opens com.krieger.canvasPlayground.layersModified to javafx.fxml;
}
