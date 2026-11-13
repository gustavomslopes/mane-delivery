package unoeste.fipp.deliveryfx;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import unoeste.fipp.deliveryfx.db.dals.PedidoDal;
import unoeste.fipp.deliveryfx.db.entidade.Pedido;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TodosPedidosController implements Initializable {

    public TilePane painel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarPedidosPedentes();
    }

    private void carregarPedidosPedentes(){
        List<Pedido> pedidos = new ArrayList<>();
        PedidoDal pedidoDal = new PedidoDal();
        pedidos = pedidoDal.get("");
        try{
            for(int i=0;i< pedidos.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("bloco-pedido-view.fxml"));

                VBox bloco=(fxmlLoader.load());
                BlocoPedidoController controller=fxmlLoader.getController();
                controller.setIdPedido(pedidos.get(i).getId());
                controller.setLbNomeCliPedido(pedidos.get(i).getClienteNome());
                controller.setLbDataPedido(pedidos.get(i).getData());
                controller.setLbValorTotalPedido(pedidos.get(i).getTotal());
                controller.setLbStatusPedido(pedidos.get(i).getStatus().getDescricao());
                painel.getChildren().add(bloco);
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
