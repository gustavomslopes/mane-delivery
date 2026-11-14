package unoeste.fipp.deliveryfx.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unoeste.fipp.deliveryfx.DeliveryFX;
import unoeste.fipp.deliveryfx.TodosPedidosController;

import java.io.IOException;

public class MenuMovimentacaoController {

    public void onPainelPedidos(ActionEvent actionEvent) throws IOException {
        abrirTelaPedidos("Painel de Pedidos", "TODOS OS PEDIDOS", false);
    }

    public void onPedidosNEntregues(ActionEvent actionEvent) throws IOException {
        abrirTelaPedidos("Pedidos não entregues", "PEDIDOS NÃO ENTREGUES", true);
    }

    private void abrirTelaPedidos(String tituloJanela, String tituloTela, boolean filtrarNaoEntregues) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("todos-pedidos-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        TodosPedidosController controller = fxmlLoader.getController();
        controller.configurarTela(tituloTela, filtrarNaoEntregues);

        Stage stage = new Stage();
        stage.setTitle(tituloJanela);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onNovoPedido(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("cadastro-pedido-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Produto!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }
}
