package unoeste.fipp.deliveryfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DeliveryFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("menu-principal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Mané Delivery!");
        stage.setScene(scene);
        stage.show();
    }
}
