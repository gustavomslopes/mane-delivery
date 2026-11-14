package unoeste.fipp.deliveryfx;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unoeste.fipp.deliveryfx.cadastros.CadastroPedidoController;
import unoeste.fipp.deliveryfx.db.dals.PedidoDal;
import unoeste.fipp.deliveryfx.db.entidade.Pedido;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TodosPedidosController implements Initializable {

    public TilePane painel;
    private Pedido pedido;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarPedidosPedentes();
    }

    private void carregarPedidosPedentes() {
        PedidoDal pedidoDal = new PedidoDal();
        List<Pedido> pedidos = pedidoDal.get("");

        try {
            for (Pedido pedidoAtual : pedidos) {
                FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("bloco-pedido-view.fxml"));
                VBox bloco = fxmlLoader.load();

                BlocoPedidoController controller = fxmlLoader.getController();
                controller.setIdPedido(pedidoAtual.getId());
                controller.setLbNomeCliPedido(pedidoAtual.getClienteNome());
                controller.setLbDataPedido(pedidoAtual.getData());
                controller.setLbValorTotalPedido(pedidoAtual.getTotal());
                controller.setLbStatusPedido(pedidoAtual.getStatus().getDescricao());

                bloco.setOnMouseClicked(mouseEvent -> {
                    try {
                        onMostrarPedido(pedidoAtual);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                painel.getChildren().add(bloco);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void onMostrarPedido(Pedido p) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("cadastro-pedido-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        CadastroPedidoController controller=fxmlLoader.getController();
        controller.carregarProdutos();
        controller.carregarTiposPagamento();
        controller.setLbid(p.getId());
        controller.setTfNome(p.getClienteNome());
        controller.setDpData(p.getData());
        controller.setTflocal(p.getLocal());
        controller.setTfnumero(p.getNumero());
        controller.setTftel(p.getClienteTelefone());
        controller.setValorTotal(p.getTotal());
        controller.setCbpgt(p.getTipoPagamento());
        controller.setTvTabela(p.getItens());


        Stage stage = new Stage();
        stage.setTitle("Cadastrar Produto!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
