package unoeste.fipp.deliveryfx.menus;

    import javafx.event.ActionEvent;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.stage.Stage;
    import unoeste.fipp.deliveryfx.DeliveryFX;
    import unoeste.fipp.deliveryfx.util.Relatorios;

    import java.awt.*;
    import java.io.File;
    import java.io.IOException;

    public class MenuRelatorioController {

        public void onListaPreçosPDF(ActionEvent actionEvent) throws IOException {
            if(Relatorios.Precos("precos.pdf"))
                Desktop.getDesktop().open(new File("precos.pdf"));
        }

        public void onPedidosDataPDF(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("menu-pedido-data-pdf-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Pedido PDF");
            stage.setScene(scene);
            stage.showAndWait();
        }

        public void onEstoqueBaixoPDF(ActionEvent actionEvent) throws IOException {
            if(Relatorios.EstoqueBaixo("estoquebaixo.pdf"))
                Desktop.getDesktop().open(new File("estoquebaixo.pdf"));
        }

        public void onGraficoVendas(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("grafico-linhas-vendas-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Grafico Ultimos 7 Dias!");
            stage.setScene(scene);
            stage.showAndWait();
        }

        public void onGraficoMVendidos(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("menu-grafico-barras-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Grafico Ultimos 7 Dias!");
            stage.setScene(scene);
            stage.showAndWait();
        }
    }



