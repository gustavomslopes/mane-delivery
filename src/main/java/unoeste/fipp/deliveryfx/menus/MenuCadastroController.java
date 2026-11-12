package unoeste.fipp.deliveryfx.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unoeste.fipp.deliveryfx.DeliveryFX;

import java.io.IOException;

public class MenuCadastroController {

    public void onMenuCategoria(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("cadastro-categoria-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Categoria!");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void onMenuPagamento(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("cadastro-tipo-pagamento-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Tipo de Pagamento!");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void onMenuMarca(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("cadastro-marca-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Marca!");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void onMenuProduto(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("cadastro-produto-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Produto!");
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void onMenuPedido(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("cadastro-pedido-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Produto!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
