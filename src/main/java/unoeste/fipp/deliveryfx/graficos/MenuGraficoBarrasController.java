package unoeste.fipp.deliveryfx.graficos;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import unoeste.fipp.deliveryfx.DeliveryFX;

import java.io.IOException;
import java.time.LocalDate;

public class MenuGraficoBarrasController {

    private static LocalDate data;

    public static LocalDate getData() {
        return data;
    }

    public void setData(LocalDate dataEsc) {
        data = dataEsc;
    }

    public DatePicker dpGraficoBarras;

    public void onGerarGraficoBarras(ActionEvent actionEvent) throws IOException {
        setData(dpGraficoBarras.getValue());
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("grafico-barras-vendidos-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Grafico mais vendidos!");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
